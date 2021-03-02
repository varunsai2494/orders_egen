package com.egen.orders.controller;

import com.egen.orders.domain.Address;
import com.egen.orders.domain.Order;
import com.egen.orders.dto.OrderDTO;
import com.egen.orders.dto.ResponseMessage;
import com.egen.orders.enums.OrderStatusEnum;
import com.egen.orders.repository.OrderRepository;
import com.egen.orders.service.OrderService;
import com.egen.orders.service.Producer;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    Producer producer;

    private final Logger log = LogManager.getLogger(this.getClass());

    @PostMapping(value = "/order")
    @ApiOperation(value = "Create an order", notes = "create an order with single/multiple payments",response = Order.class)
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO){
        log.info("Inside post order ");

        Order order = new Order();
        ResponseMessage responseMessage = new ResponseMessage();
        List<Address> addressList = new ArrayList<>();

        if(orderDTO != null) {
            Address billingAddress = orderDTO.getBillingAddress();
            Address shipAddress = orderDTO.getShippingAddress();
            billingAddress.setType("Billing");
            shipAddress.setType("Shipping");
            order.setOrderStatus(OrderStatusEnum.NEW.getCode());
            order.setSub_total(orderDTO.getSub_total());
            order.setTax(orderDTO.getTax());
            order.setTotal(orderDTO.getTotal());
            order.setUserId(21L);
            order.setOrderDetails(orderDTO.getOrderDetailList());
            addressList.add(billingAddress);
            addressList.add(shipAddress);
            order.setAddressesList(addressList);
            order.setOrderPaymentList(orderDTO.getPayment_details());
            return ResponseEntity.ok(orderService.create(order));
        } else {
            responseMessage.setMessage("Post body incorrect");
            return new ResponseEntity<>(responseMessage,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/order/{id}")
    @ApiOperation(value = "Find order by id", notes = "provide an id to lookup the order in the order registry",response = Order.class)
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.findOne(id));
    }


    @PatchMapping(value = "/order/cancel/{id}")
    @ApiOperation(value = "cancel order by id", notes = "provide an id to cancel the order",response = Order.class)
    public ResponseEntity<Order> cancelOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(orderService.cancel(id));
    }
    @PostMapping(value = "/bulk/order")
    @ApiOperation(value = "Create bulk orders", notes = "create multiple orders async call",response = Order.class)
    public ResponseEntity<Order> createBulkOrder(@RequestBody List<OrderDTO> orderList){

        for(OrderDTO orderDTO: orderList) {
            Order order = new Order();
            List<Address> addressList = new ArrayList<>();
            Address billingAddress = orderDTO.getBillingAddress();
            Address shipAddress = orderDTO.getShippingAddress();

            billingAddress.setType("Billing");
            shipAddress.setType("Shipping");
            order.setOrderStatus(OrderStatusEnum.NEW.getCode());
            order.setSub_total(orderDTO.getSub_total());
            order.setTax(orderDTO.getTax());
            order.setTotal(orderDTO.getTotal());
            order.setUserId(21L);
            order.setOrderDetails(orderDTO.getOrderDetailList());
            addressList.add(billingAddress);
            addressList.add(shipAddress);
            order.setAddressesList(addressList);
            order.setOrderPaymentList(orderDTO.getPayment_details());
            producer.sendMessage(order);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/bulk/order/update")
    @ApiOperation(value = "Create bulk orders", notes = "update orders in bulk",response = Order.class)
    public ResponseEntity<Order> updateOrders(@RequestBody List<Order> orderList){
        for(Order order:orderList) producer.sendMessage(order);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
