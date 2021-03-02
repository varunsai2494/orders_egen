package com.egen.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import com.egen.orders.domain.Address;
import com.egen.orders.domain.Order;
import com.egen.orders.domain.OrderPayment;
import com.egen.orders.repository.OrderRepository;
import com.egen.orders.service.OrderService;

import junit.framework.Assert;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

//@RunWith(SpringRunner.class)
@SpringBootTest
//@ExtendWith(MockitoExtension.class)
public class OrdersControllerTest {
	
	@Autowired
	private OrderService orderService;
	
	@MockBean
	private OrderRepository orderRepo;
	
	private Order order;
	
	@BeforeAll
	public void setup() throws Exception {
		order = new Order();
        Address a = new Address();
        
        a.setType("billing");
        a.setAddressLine1("600 Garson Dr NE");
        a.setAddressLine2("APT 3005");
        a.setCity("Atlanta");
        a.setState("GA");
        a.setZip("30324");

        OrderPayment op = new OrderPayment();
        op.setDate(new Date());
        op.setMethod("VISA");

        order.setSub_total(55.0);
        order.setTotal(44.0);
        order.setTax(11.0);
        order.setOrderStatus(0);
        order.getAddressesList().add(a);
        order.getOrderPaymentList().add(op);
	    }
	
	@Test
	public void findOneOrderTest(){
		when(orderService.findOne(anyLong())).thenReturn(order);
		assertEquals(order, orderService.findOne(5678L));
		
		
	}

}



