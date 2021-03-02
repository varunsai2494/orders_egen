package com.egen.orders.dto;

import com.egen.orders.domain.Address;
import com.egen.orders.domain.OrderDetail;
import com.egen.orders.domain.OrderPayment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Address billingAddress;
    private Address shippingAddress;
    private Double total;
    private Double tax;
    private Double sub_total;
    private String deliverType;
    private List<OrderPayment> payment_details;
    private List<OrderDetail> orderDetailList;
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getSub_total() {
		return sub_total;
	}
	public void setSub_total(Double sub_total) {
		this.sub_total = sub_total;
	}
	public String getDeliverType() {
		return deliverType;
	}
	public void setDeliverType(String deliverType) {
		this.deliverType = deliverType;
	}
	public List<OrderPayment> getPayment_details() {
		return payment_details;
	}
	public void setPayment_details(List<OrderPayment> payment_details) {
		this.payment_details = payment_details;
	}
	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
}
