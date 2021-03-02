package com.egen.orders;

import static org.mockito.Mockito.*;
import com.egen.orders.domain.Address;
import com.egen.orders.domain.Order;
import com.egen.orders.domain.OrderDetail;
import com.egen.orders.domain.OrderPayment;
import com.egen.orders.repository.AddressRepository;
import com.egen.orders.repository.OrderDetailRepository;
import com.egen.orders.repository.OrderPaymentRepository;
import com.egen.orders.repository.OrderRepository;
import com.egen.orders.service.impl.OrderServiceImpl;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.egen.orders.service.impl.OrderServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotSame;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private OrderDetailRepository orderDetailRepository;

    @Mock
    private OrderPaymentRepository orderPaymentRepository;


    @InjectMocks
    private OrderServiceImpl orderService;
    
    private Order order;
    private List<Address> addressList;
    private List<OrderDetail> orderDetailList;
    @BeforeEach
	public void setup() throws Exception {
		order = new Order();
        Address a = new Address();
        
        a.setType("billing");
        a.setAddressLine1("600 Garson Dr NE");
        a.setAddressLine2("APT 3005");
        a.setCity("Atlanta");
        a.setState("GA");
        a.setZip("30324");
        a.setOrder(order);

        OrderPayment op = new OrderPayment();
        op.setDate(new Date());
        op.setMethod("VISA");

        order.setSub_total(55.0);
        order.setTotal(44.0);
        order.setTax(11.0);
        order.setOrderStatus(0);
        order.getAddressesList().add(a);
        order.getOrderPaymentList().add(op);
        
        OrderDetail ol= new OrderDetail();
        ol.setId(23425L);
        ol.setItemName("item1");
        ol.setItemQty(23);
        ol.setOrder(order);
        addressList=order.getAddressesList();
        List<OrderDetail> orderDetailList =new ArrayList<>();
        orderDetailList.add(ol);
        
	    }
    
    @Test
    void shouldSaveOrderSuccesfully() {
        given(orderRepository.save(order)).willReturn(order);
        Order savedOrder = orderService.create(order);
        verify(orderRepository).save(order);
        assertThat(order).isNotNull();


    }
    
    
    @Test
    void getOrderByIdSuccessfully() {

    	given(orderRepository.findById(anyLong())).willReturn(order);
    	given(orderDetailRepository.findByOrder(any(Order.class))).willReturn(orderDetailList);
    	given(addressRepository.findByOrder(any(Order.class))).willReturn(addressList);
    	Order ordermain=orderService.findOne(123L);
    	verify(orderRepository).findById(anyLong());
    	verify(orderDetailRepository).findByOrder(any(Order.class));
    	verify(addressRepository).findByOrder(any(Order.class));
    	assertThat(order).isNotNull().isEqualTo(ordermain);

    }
    
    @Test 
    void finishOrderSuccessfully(){
    	given(orderRepository.findById(anyLong())).willReturn(order);
    	given(orderDetailRepository.findByOrder(any(Order.class))).willReturn(orderDetailList);
    	given(addressRepository.findByOrder(any(Order.class))).willReturn(addressList);
    	given(orderRepository.save(any(Order.class))).willReturn(order);
    	given(orderRepository.findById(anyLong())).willReturn(order);
    	Order finishedOrder=orderService.finish(234L);
    	assertThat(finishedOrder).isEqualTo(order);
    }
    
    @Test
    void CheckIfOrderExistsSuccessfully() {

    	given(orderRepository.existsById(anyLong())).willReturn(true);
    	boolean b=orderService.checkIfOrderExists(234L);
    	verify(orderRepository).existsById(anyLong());
    	assertThat(b).isEqualTo(true);
        

    }
    
    @Test
    void cancelOrderSuccessfully() {
    	given(orderRepository.findById(anyLong())).willReturn(order);
    	given(orderDetailRepository.findByOrder(any(Order.class))).willReturn(orderDetailList);
    	given(addressRepository.findByOrder(any(Order.class))).willReturn(addressList);
    	given(orderRepository.save(any(Order.class))).willReturn(order);
    	given(orderRepository.findById(anyLong())).willReturn(order);
    	Order cancelledorder=orderService.cancel(234L);
    	assertThat(cancelledorder).isEqualTo(order);
    }
    
    
    
    
    
}
