package com.getir.bookstore.service.impl;

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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {OrderServiceImpl.class})
@ExtendWith(SpringExtension.class)
class OrderServiceImplTest {
    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @MockBean
    private StockRepository stockRepository;

    @Test
    void testCreateOrder() {
        ResponseDto<OrderDto> actualCreateOrderResult = this.orderServiceImpl.createOrder(new ArrayList<>());
        assertEquals(1000, actualCreateOrderResult.getCode().intValue());
        assertFalse(actualCreateOrderResult.getSuccess());
        assertEquals("Something Went Wrong.Internal Server Error.Please try again", actualCreateOrderResult.getMessage());
        assertNull(actualCreateOrderResult.getErrors());
        assertNull(actualCreateOrderResult.getData());
    }

    @Test
    void testCreateOrderWithOrderIdNullCase() {
        Book book = new Book();
        book.setAuthor("Sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");

        Stock stock = new Stock();
        stock.setBook(book);
        stock.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stock.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setId(123L);
        stock.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        Optional<Stock> ofResult = Optional.of(stock);
        when(this.stockRepository.findByBook_Id((Long) any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");


        Order order = new Order();
        order.setAmount(BigDecimal.valueOf(42L));
        order.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customer);
        order.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setStatus(OrderStatus.IN_PROGRESS);

        when(this.orderRepository.save((Order) any())).thenReturn(order);
        when(this.stockRepository.save(any())).thenReturn(new Stock());

        Book book1 = new Book();
        book1.setAuthor("Sagar");
        book1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setDescription("The characteristics of someone or something");
        book1.setId(123L);
        book1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setPrice(BigDecimal.valueOf(42L));
        book1.setTitle("Dr");
        Optional<Book> ofResult1 = Optional.of(book1);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult1);

        Customer customer1 = new Customer();
        customer1.setFirstName("Sagar");
        customer1.setId(123L);
        customer1.setLastName("Doe");
        customer1.setMobileNumber("42");
        customer1.setPassword("Admin");
        customer1.setRoles(new HashSet<>());
        customer1.setEmail("Sagar");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer1);

        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(0);

        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList = new ArrayList<>();
        itemOrderRequestDtoList.add(itemOrderRequestDto);
        ResponseDto<OrderDto> actualCreateOrderResult = this.orderServiceImpl.createOrder(itemOrderRequestDtoList);
        assertEquals(2000, actualCreateOrderResult.getCode().intValue());
        assertTrue(actualCreateOrderResult.getSuccess());
        assertEquals("Record is Saved successfully",actualCreateOrderResult.getMessage());
    }
    @Test
    void testCreateOrder2() {
        Book book = new Book();
        book.setAuthor("Sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");

        Stock stock = new Stock();
        stock.setBook(book);
        stock.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stock.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setId(123L);
        stock.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        Optional<Stock> ofResult = Optional.of(stock);
        when(this.stockRepository.findByBook_Id((Long) any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");

        ItemOrder itemOrder=new ItemOrder();
        itemOrder.setBook(book);
        itemOrder.setQuantity(10);
        itemOrder.setId(1L);

        Order order = new Order();
        order.setAmount(BigDecimal.valueOf(42L));
        order.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customer);
        order.setId(123L);
        order.setItemOrders(Arrays.asList(itemOrder));
        order.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setStatus(OrderStatus.IN_PROGRESS);

        when(this.orderRepository.save((Order) any())).thenReturn(order);
        when(this.stockRepository.save(any())).thenReturn(new Stock());

        Book book1 = new Book();
        book1.setAuthor("Sagar");
        book1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setDescription("The characteristics of someone or something");
        book1.setId(123L);
        book1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setPrice(BigDecimal.valueOf(42L));
        book1.setTitle("Dr");
        Optional<Book> ofResult1 = Optional.of(book1);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult1);

        Customer customer1 = new Customer();
        customer1.setFirstName("Sagar");
        customer1.setId(123L);
        customer1.setLastName("Doe");
        customer1.setMobileNumber("42");
        customer1.setPassword("Admin");
        customer1.setRoles(new HashSet<>());
        customer1.setEmail("Sagar");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer1);

        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(0);

        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList = new ArrayList<>();
        itemOrderRequestDtoList.add(itemOrderRequestDto);
        ResponseDto<OrderDto> actualCreateOrderResult = this.orderServiceImpl.createOrder(itemOrderRequestDtoList);
        assertEquals(2000, actualCreateOrderResult.getCode().intValue());
        assertTrue(actualCreateOrderResult.getSuccess());
        assertEquals("Record is Saved successfully", actualCreateOrderResult.getMessage());
        assertNull(actualCreateOrderResult.getErrors());
        assertNull(actualCreateOrderResult.getData());
    }

    @Test
    void testGetOrdersByCustomerFromDB() {
        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");
        when(authenticationService.getLoginCustomer()).thenReturn(customer);
        Order order = new Order();
        order.setAmount(BigDecimal.valueOf(42L));
        order.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customer);
        order.setId(123L);
        order.setItemOrders(new ArrayList<>());
        order.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setStatus(OrderStatus.IN_PROGRESS);


        Page<Order> page = Mockito.mock(Page.class);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(page.getPageable()).thenReturn(PageRequest.of(0, 10));
        when(page.getContent()).thenReturn(orderList);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(orderRepository.findAllByCustomer(any(), Mockito.isA(Pageable.class))).thenReturn(page);
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(0);
        pageRequestDto.setSize(10);
        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = orderServiceImpl.getOrdersByCustomer(pageRequestDto);
        assertNotNull(responseDtoResponseDto);
        assertNotNull(responseDtoResponseDto.getData());
        assertNotNull(responseDtoResponseDto.getData().getRecords());
        assertEquals(1, responseDtoResponseDto.getData().getRecords().size());
        assertEquals(order.getItemOrders().size(), responseDtoResponseDto.getData().getRecords().get(0).getItems().size());
        assertEquals(order.getStatus(), responseDtoResponseDto.getData().getRecords().get(0).getStatus());
    }

    @Test
    void testGetOrdersByCustomerIdFromDB() {
        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");
        when(authenticationService.getLoginCustomer()).thenReturn(customer);
        Order order = new Order();
        order.setAmount(BigDecimal.valueOf(42L));
        order.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customer);
        order.setId(123L);
        order.setItemOrders(new ArrayList<>());
        order.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setStatus(OrderStatus.IN_PROGRESS);


        Page<Order> page = Mockito.mock(Page.class);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(page.getPageable()).thenReturn(PageRequest.of(0, 10));
        when(page.getContent()).thenReturn(orderList);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(orderRepository.findAllByCustomer(any(), Mockito.isA(Pageable.class))).thenReturn(page);
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(0);
        pageRequestDto.setSize(10);
        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = orderServiceImpl.getOrdersByCustomer(1L, pageRequestDto);
        assertNotNull(responseDtoResponseDto);
        assertNotNull(responseDtoResponseDto.getData());
        assertNotNull(responseDtoResponseDto.getData().getRecords());
        assertEquals(1, responseDtoResponseDto.getData().getRecords().size());
        assertEquals(order.getItemOrders().size(), responseDtoResponseDto.getData().getRecords().get(0).getItems().size());
        assertEquals(order.getStatus(), responseDtoResponseDto.getData().getRecords().get(0).getStatus());
    }

    @Test
    void testFilterOrdersByDateRangeFromDb() {
        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");
        when(authenticationService.getLoginCustomer()).thenReturn(customer);
        Order order = new Order();
        order.setAmount(BigDecimal.valueOf(42L));
        order.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customer);
        order.setId(123L);
        order.setItemOrders(new ArrayList<>());
        order.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setStatus(OrderStatus.IN_PROGRESS);


        Page<Order> page = Mockito.mock(Page.class);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        when(page.getPageable()).thenReturn(PageRequest.of(0, 10));
        when(page.getContent()).thenReturn(orderList);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(orderRepository.findAllByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(any(),any(), Mockito.isA(Pageable.class))).thenReturn(page);
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        OrderFilterRequestDto pageRequestDto = new OrderFilterRequestDto();
        pageRequestDto.setPage(0);
        pageRequestDto.setSize(10);
        pageRequestDto.setFromDate(LocalDateTime.now());
        pageRequestDto.setToDate(LocalDateTime.now());
        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = orderServiceImpl.filterOrdersByDateRange( pageRequestDto);
        assertNotNull(responseDtoResponseDto);
        assertNotNull(responseDtoResponseDto.getData());
        assertNotNull(responseDtoResponseDto.getData().getRecords());
        assertEquals(1, responseDtoResponseDto.getData().getRecords().size());
        assertEquals(order.getItemOrders().size(), responseDtoResponseDto.getData().getRecords().get(0).getItems().size());
        assertEquals(order.getStatus(), responseDtoResponseDto.getData().getRecords().get(0).getStatus());
    }
    @Test
    void testFilterOrdersByDateRangeFromDbWithEmptyRecords() {
        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");
        when(authenticationService.getLoginCustomer()).thenReturn(customer);


        Page<Order> page = Mockito.mock(Page.class);
        List<Order> orderList = new ArrayList<>();

        when(page.getPageable()).thenReturn(PageRequest.of(0, 10));
        when(page.getContent()).thenReturn(orderList);
        when(page.getTotalPages()).thenReturn(1);
        when(page.getTotalElements()).thenReturn(1L);
        when(orderRepository.findAllByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(any(),any(), Mockito.isA(Pageable.class))).thenReturn(page);
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer));
        OrderFilterRequestDto pageRequestDto = new OrderFilterRequestDto();
        pageRequestDto.setPage(0);
        pageRequestDto.setSize(10);
        pageRequestDto.setFromDate(LocalDateTime.now());
        pageRequestDto.setToDate(LocalDateTime.now());
        ResponseDto<PageResponseDto<OrderDto>> responseDtoResponseDto = orderServiceImpl.filterOrdersByDateRange( pageRequestDto);
        assertNotNull(responseDtoResponseDto);
        assertNotNull(responseDtoResponseDto.getData());
        assertNotNull(responseDtoResponseDto.getData().getRecords());
        assertEquals(0, responseDtoResponseDto.getData().getRecords().size());
    }

    @Test
    void testCreateOrder3() {
        Book book = new Book();
        book.setAuthor("Sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");

        Stock stock = new Stock();
        stock.setBook(book);
        stock.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stock.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setId(123L);
        stock.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(1);
        Optional<Stock> ofResult = Optional.of(stock);
        when(this.stockRepository.findByBook_Id((Long) any())).thenReturn(ofResult);
        when(this.orderRepository.save((Order) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));

        Book book1 = new Book();
        book1.setAuthor("Sagar");
        book1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setDescription("The characteristics of someone or something");
        book1.setId(123L);
        book1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setPrice(BigDecimal.valueOf(42L));
        book1.setTitle("Dr");
        Optional<Book> ofResult1 = Optional.of(book1);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult1);

        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer);

        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(0);

        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList = new ArrayList<>();
        itemOrderRequestDtoList.add(itemOrderRequestDto);
        //assertThrows(RecordNotFoundException.class, () -> this.orderServiceImpl.createOrder(itemOrderRequestDtoList));
//        verify(this.stockRepository).findByBook_Id((Long) any());
//        verify(this.orderRepository).save((Order) any());
//        verify(this.bookRepository).findById((Long) any());
//        verify(this.authenticationService).getLoginCustomer();
    }

    @Test
    void testCreateOrder4() {
        Book book = new Book();
        book.setAuthor("Sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");

        Stock stock = new Stock();
        stock.setBook(book);
        stock.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        stock.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setId(123L);
        stock.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        stock.setQuantity(-1);
        Optional<Stock> ofResult = Optional.of(stock);
        when(this.stockRepository.findByBook_Id((Long) any())).thenReturn(ofResult);

        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");

        Order order = new Order();
        order.setAmount(BigDecimal.valueOf(42L));
        order.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customer);
        order.setId(123L);
        order.setItemOrders(new ArrayList<>());
        order.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setStatus(OrderStatus.IN_PROGRESS);
        when(this.orderRepository.save((Order) any())).thenReturn(order);

        Book book1 = new Book();
        book1.setAuthor("Sagar");
        book1.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book1.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setDescription("The characteristics of someone or something");
        book1.setId(123L);
        book1.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book1.setPrice(BigDecimal.valueOf(42L));
        book1.setTitle("Dr");
        Optional<Book> ofResult1 = Optional.of(book1);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult1);

        Customer customer1 = new Customer();
        customer1.setFirstName("Sagar");
        customer1.setId(123L);
        customer1.setLastName("Doe");
        customer1.setMobileNumber("42");
        customer1.setPassword("Admin");
        customer1.setRoles(new HashSet<>());
        customer1.setEmail("Sagar");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer1);

        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(0);

        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList = new ArrayList<>();
        itemOrderRequestDtoList.add(itemOrderRequestDto);
        assertThrows(RecordNotFoundException.class, () -> this.orderServiceImpl.createOrder(itemOrderRequestDtoList));
        verify(this.stockRepository).findByBook_Id((Long) any());
        verify(this.bookRepository).findById((Long) any());
    }

    @Test
    void testCreateOrder5() {
        when(this.stockRepository.findByBook_Id((Long) any())).thenReturn(Optional.empty());

        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");

        Order order = new Order();
        order.setAmount(BigDecimal.valueOf(42L));
        order.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customer);
        order.setId(123L);
        order.setItemOrders(new ArrayList<>());
        order.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setStatus(OrderStatus.IN_PROGRESS);
        when(this.orderRepository.save((Order) any())).thenReturn(order);

        Book book = new Book();
        book.setAuthor("Sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setPrice(BigDecimal.valueOf(42L));
        book.setTitle("Dr");
        Optional<Book> ofResult = Optional.of(book);
        when(this.bookRepository.findById((Long) any())).thenReturn(ofResult);

        Customer customer1 = new Customer();
        customer1.setFirstName("Sagar");
        customer1.setId(123L);
        customer1.setLastName("Doe");
        customer1.setMobileNumber("42");
        customer1.setPassword("Admin");
        customer1.setRoles(new HashSet<>());
        customer1.setEmail("Sagar");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer1);

        ItemOrderRequestDto itemOrderRequestDto = new ItemOrderRequestDto();
        itemOrderRequestDto.setBookId(123L);
        itemOrderRequestDto.setQuantity(0);

        ArrayList<ItemOrderRequestDto> itemOrderRequestDtoList = new ArrayList<>();
        itemOrderRequestDtoList.add(itemOrderRequestDto);
        assertThrows(RecordNotFoundException.class, () -> this.orderServiceImpl.createOrder(itemOrderRequestDtoList));
        verify(this.stockRepository).findByBook_Id((Long) any());
        verify(this.bookRepository).findById((Long) any());
    }

    @Test
    void testGetOrdersByCustomer() {
        when(this.orderRepository.findAllByCustomer((Customer) any(), (org.springframework.data.domain.Pageable) any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        when(this.customerRepository.findById((Long) any())).thenReturn(Optional.empty());

        PageRequestDto pageRequestDto = new PageRequestDto();
        pageRequestDto.setPage(1);
        pageRequestDto.setSize(3);
        assertThrows(RecordNotFoundException.class, () -> this.orderServiceImpl.getOrdersByCustomer(123L, pageRequestDto));
        verify(this.customerRepository).findById((Long) any());
    }

    @Test
    void testGetOrdersById() {
        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");

        Order order = new Order();
        order.setAmount(BigDecimal.valueOf(42L));
        order.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customer);
        order.setId(123L);
        ArrayList<ItemOrder> itemOrderList = new ArrayList<>();
        order.setItemOrders(itemOrderList);
        order.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setStatus(OrderStatus.IN_PROGRESS);
        Optional<Order> ofResult = Optional.of(order);
        when(this.orderRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseDto<OrderDto> actualOrdersById = this.orderServiceImpl.getOrdersById(123L);
        assertEquals(2000, actualOrdersById.getCode().intValue());
        assertEquals("ResponseDto(code=2000, message=null, success=true, data=OrderDto(status=IN_PROGRESS, amount=42,"
                + " createdDate=0001-01-01T01:01, items=[]), errors=null)", actualOrdersById.toString());
        assertTrue(actualOrdersById.getSuccess());
        assertNull(actualOrdersById.getMessage());
        assertNull(actualOrdersById.getErrors());
        OrderDto data = actualOrdersById.getData();
        assertEquals("OrderDto(status=IN_PROGRESS, amount=42, createdDate=0001-01-01T01:01, items=[])", data.toString());
        assertEquals(itemOrderList, data.getItems());
        assertEquals(OrderStatus.IN_PROGRESS, data.getStatus());
        assertEquals("0001-01-01", data.getCreatedDate().toLocalDate().toString());
        assertEquals(123L, data.getId().longValue());
        assertEquals("42", data.getAmount().toString());
        verify(this.orderRepository).findById((Long) any());
    }

    @Test
    void testGetOrdersById2() {
        when(this.orderRepository.findById((Long) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));
        assertThrows(RecordNotFoundException.class, () -> this.orderServiceImpl.getOrdersById(123L));
        verify(this.orderRepository).findById((Long) any());
    }

    @Test
    void testGetOrdersById3() {
        when(this.orderRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(RecordNotFoundException.class, () -> this.orderServiceImpl.getOrdersById(123L));
        verify(this.orderRepository).findById((Long) any());
    }

    @Test
    void testGetOrdersById4() {
        Customer customer = new Customer();
        customer.setFirstName("Sagar");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("Admin");
        customer.setRoles(new HashSet<>());
        customer.setEmail("Sagar");

        Book book = new Book();
        book.setAuthor("Sagar");
        book.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        book.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        book.setDescription("The characteristics of someone or something");
        book.setId(123L);
        book.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        BigDecimal valueOfResult = BigDecimal.valueOf(42L);
        book.setPrice(valueOfResult);
        book.setTitle("Dr");

        ItemOrder itemOrder = new ItemOrder();
        itemOrder.setBook(book);
        itemOrder.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        itemOrder.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        itemOrder.setId(123L);
        itemOrder.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        itemOrder.setQuantity(1);

        ArrayList<ItemOrder> itemOrderList = new ArrayList<>();
        itemOrderList.add(itemOrder);
        itemOrderList.addAll(new ArrayList<>());

        Order order = new Order();
        order.setAmount(BigDecimal.valueOf(42L));
        order.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        order.setCreatedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setCustomer(customer);
        order.setId(123L);
        order.setItemOrders(itemOrderList);
        order.setLastModifiedDate(LocalDateTime.of(1, 1, 1, 1, 1));
        order.setStatus(OrderStatus.IN_PROGRESS);
        Optional<Order> ofResult = Optional.of(order);
        when(this.orderRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseDto<OrderDto> actualOrdersById = this.orderServiceImpl.getOrdersById(123L);
        assertEquals(2000, actualOrdersById.getCode().intValue());
        assertEquals(
                "ResponseDto(code=2000, message=null, success=true, data=OrderDto(status=IN_PROGRESS, amount=42,"
                        + " createdDate=0001-01-01T01:01, items=[ItemOrderDto(quantity=1, book=BookDto(title=Dr, author=Sagar,"
                        + " description=The characteristics of someone or something, price=42))]), errors=null)",
                actualOrdersById.toString());
        assertTrue(actualOrdersById.getSuccess());
        assertNull(actualOrdersById.getMessage());
        assertNull(actualOrdersById.getErrors());
        OrderDto data = actualOrdersById.getData();
        BigDecimal amount = data.getAmount();
        assertEquals(valueOfResult, amount);
        List<ItemOrderDto> items = data.getItems();
        assertEquals(1, items.size());
        assertEquals(OrderStatus.IN_PROGRESS, data.getStatus());
        assertEquals("01:01", data.getCreatedDate().toLocalTime().toString());
        assertEquals(123L, data.getId().longValue());
        assertEquals("42", amount.toString());
        ItemOrderDto getResult = items.get(0);
        assertEquals("ItemOrderDto(quantity=1, book=BookDto(title=Dr, author=Sagar, description=The characteristics of"
                + " someone or something, price=42))", getResult.toString());
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(1, getResult.getQuantity().intValue());
        BookDto book1 = getResult.getBook();
        BigDecimal price = book1.getPrice();
        assertEquals(amount, price);
        assertEquals(123L, book1.getId().longValue());
        assertEquals("Sagar", book1.getAuthor());
        assertEquals("The characteristics of someone or something", book1.getDescription());
        assertEquals("Dr", book1.getTitle());
        assertEquals("42", price.toString());
        verify(this.orderRepository).findById((Long) any());
    }

    @Test
    void testFilterOrdersByDateRange() {
        when(this.orderRepository.findAllByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual((LocalDateTime) any(),
                (LocalDateTime) any(), (org.springframework.data.domain.Pageable) any()))
                .thenThrow(new RecordNotFoundException("An error occurred", "Field Name"));

        OrderFilterRequestDto orderFilterRequestDto = new OrderFilterRequestDto();
        orderFilterRequestDto.setFromDate(LocalDateTime.of(1, 1, 1, 1, 1));
        orderFilterRequestDto.setPage(1);
        orderFilterRequestDto.setSize(3);
        orderFilterRequestDto.setToDate(LocalDateTime.of(1, 1, 1, 1, 1));
        assertThrows(RecordNotFoundException.class,
                () -> this.orderServiceImpl.filterOrdersByDateRange(orderFilterRequestDto));
        verify(this.orderRepository).findAllByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual((LocalDateTime) any(),
                (LocalDateTime) any(), (org.springframework.data.domain.Pageable) any());
    }
}

