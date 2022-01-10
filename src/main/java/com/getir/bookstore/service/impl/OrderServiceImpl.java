package com.getir.bookstore.service.impl;


import com.getir.bookstore.constant.enums.BookStoreErrorCode;
import com.getir.bookstore.constant.enums.OrderStatus;
import com.getir.bookstore.domain.*;
import com.getir.bookstore.dto.request.ItemOrderRequestDto;
import com.getir.bookstore.dto.request.OrderFilterRequestDto;
import com.getir.bookstore.dto.request.PageRequestDto;
import com.getir.bookstore.dto.response.*;
import com.getir.bookstore.exception.RecordNotFoundException;
import com.getir.bookstore.repository.BookRepository;
import com.getir.bookstore.repository.CustomerRepository;
import com.getir.bookstore.repository.OrderRepository;
import com.getir.bookstore.repository.StockRepository;
import com.getir.bookstore.service.AuthenticationService;
import com.getir.bookstore.service.OrderService;
import com.getir.bookstore.util.BookStoreUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.getir.bookstore.constant.BookStoreConstant.BOOK_ID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    AuthenticationService authenticationService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class, propagation = Propagation.REQUIRED)
    public ResponseDto<OrderDto> createOrder(List<ItemOrderRequestDto> request) {
        if (CollectionUtils.isEmpty(request)) {
            return ResponseDto.buildError();
        } else {
            if (isStockExistForAllBooks(request)) {
                boolean isStockUpdated = false;
                List<ItemOrder> itemOrders = convertDtoToEntity(request);

                List<Stock> stocks = createStockEntities(itemOrders);
                try {
                    stockRepository.saveAllAndFlush(stocks);
                    isStockUpdated = true;
                    Order order = buildOrder(itemOrders);
                    orderRepository.save(order);
                } catch (Exception exception) {
                    if (isStockUpdated) {
                        revertStock(itemOrders, stocks);
                    }
                    return ResponseDto.buildError();
                }
                return ResponseDto.buildSuccess();
            }

            throw new RecordNotFoundException(MDC.get("msg"), "quantity");
        }
    }

    @Override
    public ResponseDto<PageResponseDto<OrderDto>> getOrdersByCustomer(PageRequestDto pageRequestDto) {
        Customer customer = getLoginCustomer();
        return getOrders(customer, pageRequestDto);
    }

    @Override
    public ResponseDto<PageResponseDto<OrderDto>> getOrdersByCustomer(Long customerId, PageRequestDto pageRequestDto) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RecordNotFoundException(BookStoreErrorCode.CUSTOMER_NOT_FOUND.getMessage(), "customerId"));
        return getOrders(customer, pageRequestDto);
    }

    public ResponseDto<PageResponseDto<OrderDto>> getOrders(Customer customer, PageRequestDto pageRequestDto) {
        Pageable pageable = PageRequest.of(pageRequestDto.getPage(), pageRequestDto.getSize());
        Page<Order> page = orderRepository.findAllByCustomer(customer, pageable);
        PageResponseDto<OrderDto> pageResponseDto = PageResponseDto.<OrderDto>builder().records(convertOrderEntityToOrderDto(page.getContent())).page(BookStoreUtils.getPageDto(page)).build();
        return ResponseDto.buildSuccess(pageResponseDto);
    }

    @Override
    public ResponseDto<OrderDto> getOrdersById(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        Order order = orderOptional.orElseThrow(() -> new RecordNotFoundException(BookStoreErrorCode.ORDER_NOT_FOUND.getMessage(), "orderId"));
        return ResponseDto.buildSuccess(convertOrderEntityToOrderDto(order));
    }

    @Override
    public ResponseDto<PageResponseDto<OrderDto>> filterOrdersByDateRange(OrderFilterRequestDto orderFilterRequestDto) {
        Pageable pageable = PageRequest.of(orderFilterRequestDto.getPage(), orderFilterRequestDto.getSize());
        Page<Order> page = orderRepository.findAllByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(orderFilterRequestDto.getFromDate(), orderFilterRequestDto.getToDate(), pageable);
        PageResponseDto<OrderDto> pageResponseDto = PageResponseDto.<OrderDto>builder().records(convertOrderEntityToOrderDto(page.getContent())).page(BookStoreUtils.getPageDto(page)).build();
        return ResponseDto.buildSuccess(pageResponseDto);

    }

    private List<OrderDto> convertOrderEntityToOrderDto(List<Order> orderList) {
        if (CollectionUtils.isEmpty(orderList)) {
            return new ArrayList<>();
        } else {
            return orderList.stream().map(this::convertOrderEntityToOrderDto).collect(Collectors.toList());
        }
    }

    private OrderDto convertOrderEntityToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAmount(order.getAmount());
        orderDto.setStatus(order.getStatus());
        orderDto.setCreatedDate(order.getCreatedDate());
        orderDto.setItems(convertItemOrderEntityToDto(order.getItemOrders()));
        return orderDto;
    }

    private List<ItemOrderDto> convertItemOrderEntityToDto(List<ItemOrder> itemOrders) {
        if (CollectionUtils.isEmpty(itemOrders)) {
            return new ArrayList<>();
        } else {
            return itemOrders.stream().map(this::convertItemOrderEntityToDto).collect(Collectors.toList());
        }
    }

    private ItemOrderDto convertItemOrderEntityToDto(ItemOrder itemOrder) {
        ItemOrderDto itemOrderDto = new ItemOrderDto();
        itemOrderDto.setQuantity(itemOrder.getQuantity());
        itemOrderDto.setId(itemOrder.getId());
        itemOrderDto.setBook(convertEntityToDto(itemOrder.getBook()));
        return itemOrderDto;
    }

    private BookDto convertEntityToDto(Book book) {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        return bookDto;
    }

    private Order buildOrder(List<ItemOrder> itemOrders) {
        Order order = new Order();
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setCustomer(getLoginCustomer());
        order.setAmount(getTotalAmount(itemOrders));
        order.setTotalQuantity(getTotalQuantity(itemOrders));
        order.setItemOrders(itemOrders);
        return order;
    }

    private List<Stock> createStockEntities(List<ItemOrder> itemOrders) {
        return itemOrders.stream().map(itemOrder -> {
            Stock stock = getStock(itemOrder.getBook());
            Integer dbQuantity = stock.getQuantity();
            stock.setQuantity(dbQuantity - itemOrder.getQuantity());
            return stock;
        }).collect(Collectors.toList());
    }

    private void revertStock(List<ItemOrder> itemOrders, List<Stock> stocks) {
        stocks.stream().forEach(stock -> {
            stock = stockRepository.findById(stock.getId()).get();
            Integer qnty = stock.getQuantity();
            stock.setQuantity(qnty + getQuantity(itemOrders, stock.getBook()));
            stockRepository.save(stock);
        });
    }

    private Integer getQuantity(List<ItemOrder> itemOrders, Book book) {
        return itemOrders.stream().filter(itemOrder -> itemOrder.getBook().getId().equals(book.getId())).mapToInt(ItemOrder::getQuantity).sum();
    }

    private Customer getLoginCustomer() {
        return authenticationService.getLoginCustomer();
    }

    private BigDecimal getTotalAmount(List<ItemOrder> itemOrders) {
        return itemOrders.stream().map(it -> {
            BigDecimal price = it.getBook().getPrice();
            return price.multiply(new BigDecimal(it.getQuantity()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    private Integer getTotalQuantity(List<ItemOrder> itemOrders){
        return itemOrders.stream().mapToInt(ItemOrder::getQuantity).sum();
    }
    private List<ItemOrder> convertDtoToEntity(List<ItemOrderRequestDto> itemOrderDtos) {
        return itemOrderDtos.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }

    private ItemOrder convertDtoToEntity(ItemOrderRequestDto itemOrderDto) {
        Book book = bookRepository.findById(itemOrderDto.getBookId()).orElseThrow(() -> new RecordNotFoundException(BookStoreErrorCode.BOOK_NOT_FOUND.getMessage()+" for Id "+itemOrderDto.getBookId(), BOOK_ID));
        ItemOrder itemOrder = new ItemOrder();
        Integer stock = getStock(book).getQuantity();
        if (stock - itemOrderDto.getQuantity() >= 0) {
            itemOrder.setQuantity(itemOrderDto.getQuantity());
            itemOrder.setBook(book);
        } else
            throw new RecordNotFoundException("Only " + stock + " books are in stock so Change Quantity for " + book.getTitle() + " Book", "quantity");
        return itemOrder;
    }

    private boolean isStockExistForAllBooks(List<ItemOrderRequestDto> itemOrderDtos) {
        for (ItemOrderRequestDto itemOrderRequestDto : itemOrderDtos) {
            Book book = bookRepository.findById(itemOrderRequestDto.getBookId()).orElseThrow(() -> new RecordNotFoundException(BookStoreErrorCode.BOOK_NOT_FOUND.getMessage()+" for Id "+itemOrderRequestDto.getBookId(), BOOK_ID));
            Integer stock = getStock(book).getQuantity();
            if (stock <= 0) {
                setMsgInMDCContext(book.getTitle() + " is not in our stock currently so Please try different book");
                return false;
            } else if (stock - itemOrderRequestDto.getQuantity() < 0) {
                setMsgInMDCContext("Only " + stock + " books are in stock so Change Quantity for " + book.getTitle() + " Book");
                return false;
            }
        }
        return true;
    }

    private void setMsgInMDCContext(String msg) {
        MDC.clear();
        MDC.put("msg", msg);
    }


    private Stock getStock(Book book) {
        Optional<Stock> stockOptional = stockRepository.findByBook_Id(book.getId());
        return stockOptional.orElseThrow(() -> new RecordNotFoundException(BookStoreErrorCode.STOCK_NOT_FOUND.getMessage()+" for Book "+book.getId(), BOOK_ID));
    }
}
