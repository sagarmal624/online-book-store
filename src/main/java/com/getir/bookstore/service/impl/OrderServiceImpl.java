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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
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
    public ResponseDto<OrderDto> createOrder(List<ItemOrderRequestDto> request) {
        if (CollectionUtils.isEmpty(request)) {
            return ResponseDto.buildError();
        } else {
            Order order = orderRepository.save(buildOrder(request));
            if (Objects.nonNull(order.getId())) {
                List<ItemOrder> itemOrders = order.getItemOrders();
                itemOrders.stream().map(this::updateStockInDb).forEach(stockRepository::save);
            }
            return Objects.nonNull(order.getId()) ? ResponseDto.buildSuccess() : ResponseDto.buildError();
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

    private Order buildOrder(List<ItemOrderRequestDto> request) {
        List<ItemOrder> itemOrders = convertDtoToEntity(request);
        Order order = new Order();
        order.setItemOrders(itemOrders);
        order.setStatus(OrderStatus.IN_PROGRESS);
        order.setAmount(getTotalAmount(itemOrders));
        order.setCustomer(getLoginCustomer());
        return order;
    }

    private Stock updateStockInDb(ItemOrder itemOrder) {
        Stock stock = getStock(itemOrder.getBook());
        Integer dbQuantity = stock.getQuantity();
        stock.setQuantity(dbQuantity - itemOrder.getQuantity());
        return stock;
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

    private List<ItemOrder> convertDtoToEntity(List<ItemOrderRequestDto> itemOrderDtos) {
        return itemOrderDtos.stream().map(this::convertDtoToEntity).collect(Collectors.toList());
    }

    private ItemOrder convertDtoToEntity(ItemOrderRequestDto itemOrderDto) {
        Book book = bookRepository.findById(itemOrderDto.getBookId()).orElseThrow(() -> new RecordNotFoundException(BookStoreErrorCode.BOOK_NOT_FOUND.getMessage(), "bookId"));
        ItemOrder itemOrder = new ItemOrder();
        Integer stock = getStock(book).getQuantity();
        if (isStockExist(stock, itemOrderDto.getQuantity())) {
            itemOrder.setQuantity(itemOrderDto.getQuantity());
            itemOrder.setBook(book);
        } else
            throw new RecordNotFoundException("Only " + stock + " books are in stock so Change Quantity for " + book.getTitle() + " Book", "quantity");
        return itemOrder;
    }

    private boolean isStockExist(Integer stock, Integer qnty) {
        return stock - qnty >= 0;
    }

    private Stock getStock(Book book) {
        Optional<Stock> stockOptional = stockRepository.findByBook_Id(book.getId());
        Stock stock = stockOptional.orElseThrow(() -> new RecordNotFoundException(BookStoreErrorCode.STOCK_NOT_FOUND.getMessage(), "bookId"));
        return stock;
    }
}
