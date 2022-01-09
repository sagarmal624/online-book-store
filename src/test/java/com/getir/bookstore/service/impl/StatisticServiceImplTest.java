package com.getir.bookstore.service.impl;

import com.getir.bookstore.domain.Customer;
import com.getir.bookstore.domain.Statistic;
import com.getir.bookstore.dto.response.ResponseDto;
import com.getir.bookstore.dto.response.StatisticDto;
import com.getir.bookstore.repository.OrderRepository;
import com.getir.bookstore.service.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {StatisticServiceImpl.class})
@ExtendWith(SpringExtension.class)
class StatisticServiceImplTest {
    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private StatisticServiceImpl statisticServiceImpl;

    @Test
    void testGetMonthlyStatistic() {
        ArrayList<Statistic> statisticList = new ArrayList<>();
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("iloveyou");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic();
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        assertEquals(statisticList, actualMonthlyStatistic.getData());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(this.authenticationService).getLoginCustomer();
    }

    @Test
    void testGetMonthlyStatistic2() {
        Statistic statistic = mock(Statistic.class);
        when(statistic.getTotalOrder()).thenReturn(1);
        when(statistic.getTotalPurchaseBookCount()).thenReturn(3);
        when(statistic.getTotalAmount()).thenReturn(BigDecimal.valueOf(42L));
        when(statistic.getMonth()).thenReturn(1);

        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(statistic);
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("iloveyou");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic();
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        List<StatisticDto> data = actualMonthlyStatistic.getData();
        assertEquals(1, data.size());
        StatisticDto getResult = data.get(0);
        assertEquals("JANUARY", getResult.getMonth());
        assertEquals("StatisticDto(month=JANUARY, totalOrder=1, totalAmount=42, totalPurchaseBookCount=3)",
                getResult.toString());
        assertEquals(3, getResult.getTotalPurchaseBookCount());
        assertEquals(1, getResult.getTotalOrder());
        assertEquals("42", getResult.getTotalAmount().toString());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(statistic).getMonth();
        verify(statistic).getTotalAmount();
        verify(statistic).getTotalOrder();
        verify(statistic).getTotalPurchaseBookCount();
        verify(this.authenticationService).getLoginCustomer();
    }

    @Test
    void testGetMonthlyStatistic3() {
        ArrayList<Statistic> statisticList = new ArrayList<>();
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic(123L);
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        assertEquals(statisticList, actualMonthlyStatistic.getData());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
    }

    @Test
    void testGetMonthlyStatistic4() {
        Statistic statistic = mock(Statistic.class);
        when(statistic.getTotalOrder()).thenReturn(1);
        when(statistic.getTotalPurchaseBookCount()).thenReturn(3);
        when(statistic.getTotalAmount()).thenReturn(BigDecimal.valueOf(42L));
        when(statistic.getMonth()).thenReturn(1);

        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(statistic);
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic(123L);
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        List<StatisticDto> data = actualMonthlyStatistic.getData();
        assertEquals(1, data.size());
        StatisticDto getResult = data.get(0);
        assertEquals("JANUARY", getResult.getMonth());
        assertEquals("StatisticDto(month=JANUARY, totalOrder=1, totalAmount=42, totalPurchaseBookCount=3)",
                getResult.toString());
        assertEquals(3, getResult.getTotalPurchaseBookCount());
        assertEquals(1, getResult.getTotalOrder());
        assertEquals("42", getResult.getTotalAmount().toString());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(statistic).getMonth();
        verify(statistic).getTotalAmount();
        verify(statistic).getTotalOrder();
        verify(statistic).getTotalPurchaseBookCount();
    }

    @Test
    void testGetMonthlyStatistic5() {
        ArrayList<Statistic> statisticList = new ArrayList<>();
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("iloveyou");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic();
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        assertEquals(statisticList, actualMonthlyStatistic.getData());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(this.authenticationService).getLoginCustomer();
    }

    @Test
    void testGetMonthlyStatistic6() {
        Statistic statistic = mock(Statistic.class);
        when(statistic.getTotalOrder()).thenReturn(1);
        when(statistic.getTotalPurchaseBookCount()).thenReturn(3);
        when(statistic.getTotalAmount()).thenReturn(BigDecimal.valueOf(42L));
        when(statistic.getMonth()).thenReturn(1);

        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(statistic);
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("iloveyou");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic();
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        List<StatisticDto> data = actualMonthlyStatistic.getData();
        assertEquals(1, data.size());
        StatisticDto getResult = data.get(0);
        assertEquals("JANUARY", getResult.getMonth());
        assertEquals("StatisticDto(month=JANUARY, totalOrder=1, totalAmount=42, totalPurchaseBookCount=3)",
                getResult.toString());
        assertEquals(3, getResult.getTotalPurchaseBookCount());
        assertEquals(1, getResult.getTotalOrder());
        assertEquals("42", getResult.getTotalAmount().toString());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(statistic).getMonth();
        verify(statistic).getTotalAmount();
        verify(statistic).getTotalOrder();
        verify(statistic).getTotalPurchaseBookCount();
        verify(this.authenticationService).getLoginCustomer();
    }

    @Test
    void testGetMonthlyStatistic7() {
        ArrayList<Statistic> statisticList = new ArrayList<>();
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic(123L);
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        assertEquals(statisticList, actualMonthlyStatistic.getData());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
    }

    @Test
    void testGetMonthlyStatistic8() {
        Statistic statistic = mock(Statistic.class);
        when(statistic.getTotalOrder()).thenReturn(1);
        when(statistic.getTotalPurchaseBookCount()).thenReturn(3);
        when(statistic.getTotalAmount()).thenReturn(BigDecimal.valueOf(42L));
        when(statistic.getMonth()).thenReturn(1);

        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(statistic);
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic(123L);
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        List<StatisticDto> data = actualMonthlyStatistic.getData();
        assertEquals(1, data.size());
        StatisticDto getResult = data.get(0);
        assertEquals("JANUARY", getResult.getMonth());
        assertEquals("StatisticDto(month=JANUARY, totalOrder=1, totalAmount=42, totalPurchaseBookCount=3)",
                getResult.toString());
        assertEquals(3, getResult.getTotalPurchaseBookCount());
        assertEquals(1, getResult.getTotalOrder());
        assertEquals("42", getResult.getTotalAmount().toString());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(statistic).getMonth();
        verify(statistic).getTotalAmount();
        verify(statistic).getTotalOrder();
        verify(statistic).getTotalPurchaseBookCount();
    }

    @Test
    void testGetMonthlyStatistic9() {
        ArrayList<Statistic> statisticList = new ArrayList<>();
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("iloveyou");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic();
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        assertEquals(statisticList, actualMonthlyStatistic.getData());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(this.authenticationService).getLoginCustomer();
    }

    @Test
    void testGetMonthlyStatistic10() {
        Statistic statistic = mock(Statistic.class);
        when(statistic.getTotalOrder()).thenReturn(1);
        when(statistic.getTotalPurchaseBookCount()).thenReturn(3);
        when(statistic.getTotalAmount()).thenReturn(BigDecimal.valueOf(42L));
        when(statistic.getMonth()).thenReturn(1);

        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(statistic);
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);

        Customer customer = new Customer();
        customer.setFirstName("Jane");
        customer.setId(123L);
        customer.setLastName("Doe");
        customer.setMobileNumber("42");
        customer.setPassword("iloveyou");
        customer.setRoles(new HashSet<>());
        customer.setEmail("janedoe");
        when(this.authenticationService.getLoginCustomer()).thenReturn(customer);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic();
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        List<StatisticDto> data = actualMonthlyStatistic.getData();
        assertEquals(1, data.size());
        StatisticDto getResult = data.get(0);
        assertEquals("JANUARY", getResult.getMonth());
        assertEquals("StatisticDto(month=JANUARY, totalOrder=1, totalAmount=42, totalPurchaseBookCount=3)",
                getResult.toString());
        assertEquals(3, getResult.getTotalPurchaseBookCount());
        assertEquals(1, getResult.getTotalOrder());
        assertEquals("42", getResult.getTotalAmount().toString());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(statistic).getMonth();
        verify(statistic).getTotalAmount();
        verify(statistic).getTotalOrder();
        verify(statistic).getTotalPurchaseBookCount();
        verify(this.authenticationService).getLoginCustomer();
    }

    @Test
    void testGetMonthlyStatistic11() {
        ArrayList<Statistic> statisticList = new ArrayList<>();
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic(123L);
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        assertEquals(statisticList, actualMonthlyStatistic.getData());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
    }

    @Test
    void testGetMonthlyStatistic12() {
        Statistic statistic = mock(Statistic.class);
        when(statistic.getTotalOrder()).thenReturn(1);
        when(statistic.getTotalPurchaseBookCount()).thenReturn(3);
        when(statistic.getTotalAmount()).thenReturn(BigDecimal.valueOf(42L));
        when(statistic.getMonth()).thenReturn(1);

        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(statistic);
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);
        ResponseDto<List<StatisticDto>> actualMonthlyStatistic = this.statisticServiceImpl.getMonthlyStatistic(123L);
        assertEquals(2000, actualMonthlyStatistic.getCode().intValue());
        assertTrue(actualMonthlyStatistic.getSuccess());
        assertNull(actualMonthlyStatistic.getMessage());
        assertNull(actualMonthlyStatistic.getErrors());
        List<StatisticDto> data = actualMonthlyStatistic.getData();
        assertEquals(1, data.size());
        StatisticDto getResult = data.get(0);
        assertEquals("JANUARY", getResult.getMonth());
        assertEquals("StatisticDto(month=JANUARY, totalOrder=1, totalAmount=42, totalPurchaseBookCount=3)",
                getResult.toString());
        assertEquals(3, getResult.getTotalPurchaseBookCount());
        assertEquals(1, getResult.getTotalOrder());
        assertEquals("42", getResult.getTotalAmount().toString());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(statistic).getMonth();
        verify(statistic).getTotalAmount();
        verify(statistic).getTotalOrder();
        verify(statistic).getTotalPurchaseBookCount();
    }

    @Test
    void testGetStatisticDto() {
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(new ArrayList<>());
        assertTrue(this.statisticServiceImpl.getStatisticDto(123L).isEmpty());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
    }

    @Test
    void testGetStatisticDto2() {
        Statistic statistic = mock(Statistic.class);
        when(statistic.getTotalOrder()).thenReturn(1);
        when(statistic.getTotalPurchaseBookCount()).thenReturn(3);
        when(statistic.getTotalAmount()).thenReturn(BigDecimal.valueOf(42L));
        when(statistic.getMonth()).thenReturn(1);

        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(statistic);
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);
        List<StatisticDto> actualStatisticDto = this.statisticServiceImpl.getStatisticDto(123L);
        assertEquals(1, actualStatisticDto.size());
        StatisticDto getResult = actualStatisticDto.get(0);
        assertEquals("JANUARY", getResult.getMonth());
        assertEquals("StatisticDto(month=JANUARY, totalOrder=1, totalAmount=42, totalPurchaseBookCount=3)",
                getResult.toString());
        assertEquals(3, getResult.getTotalPurchaseBookCount());
        assertEquals(1, getResult.getTotalOrder());
        assertEquals("42", getResult.getTotalAmount().toString());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(statistic).getMonth();
        verify(statistic).getTotalAmount();
        verify(statistic).getTotalOrder();
        verify(statistic).getTotalPurchaseBookCount();
    }

    @Test
    void testGetStatisticDto3() {
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(new ArrayList<>());
        assertTrue(this.statisticServiceImpl.getStatisticDto(123L).isEmpty());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
    }

    @Test
    void testGetStatisticDto4() {
        Statistic statistic = mock(Statistic.class);
        when(statistic.getTotalOrder()).thenReturn(1);
        when(statistic.getTotalPurchaseBookCount()).thenReturn(3);
        when(statistic.getTotalAmount()).thenReturn(BigDecimal.valueOf(42L));
        when(statistic.getMonth()).thenReturn(1);

        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(statistic);
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);
        List<StatisticDto> actualStatisticDto = this.statisticServiceImpl.getStatisticDto(123L);
        assertEquals(1, actualStatisticDto.size());
        StatisticDto getResult = actualStatisticDto.get(0);
        assertEquals("JANUARY", getResult.getMonth());
        assertEquals("StatisticDto(month=JANUARY, totalOrder=1, totalAmount=42, totalPurchaseBookCount=3)",
                getResult.toString());
        assertEquals(3, getResult.getTotalPurchaseBookCount());
        assertEquals(1, getResult.getTotalOrder());
        assertEquals("42", getResult.getTotalAmount().toString());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(statistic).getMonth();
        verify(statistic).getTotalAmount();
        verify(statistic).getTotalOrder();
        verify(statistic).getTotalPurchaseBookCount();
    }

    @Test
    void testGetStatisticDto5() {
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(new ArrayList<>());
        assertTrue(this.statisticServiceImpl.getStatisticDto(123L).isEmpty());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
    }

    @Test
    void testGetStatisticDto6() {
        Statistic statistic = mock(Statistic.class);
        when(statistic.getTotalOrder()).thenReturn(1);
        when(statistic.getTotalPurchaseBookCount()).thenReturn(3);
        when(statistic.getTotalAmount()).thenReturn(BigDecimal.valueOf(42L));
        when(statistic.getMonth()).thenReturn(1);

        ArrayList<Statistic> statisticList = new ArrayList<>();
        statisticList.add(statistic);
        when(this.orderRepository.getGroupByMonthlyReport((Long) any())).thenReturn(statisticList);
        List<StatisticDto> actualStatisticDto = this.statisticServiceImpl.getStatisticDto(123L);
        assertEquals(1, actualStatisticDto.size());
        StatisticDto getResult = actualStatisticDto.get(0);
        assertEquals("JANUARY", getResult.getMonth());
        assertEquals("StatisticDto(month=JANUARY, totalOrder=1, totalAmount=42, totalPurchaseBookCount=3)",
                getResult.toString());
        assertEquals(3, getResult.getTotalPurchaseBookCount());
        assertEquals(1, getResult.getTotalOrder());
        assertEquals("42", getResult.getTotalAmount().toString());
        verify(this.orderRepository).getGroupByMonthlyReport((Long) any());
        verify(statistic).getMonth();
        verify(statistic).getTotalAmount();
        verify(statistic).getTotalOrder();
        verify(statistic).getTotalPurchaseBookCount();
    }
}

