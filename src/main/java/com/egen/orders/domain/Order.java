package com.egen.orders.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Data
@Table(name="orders")
public class Order implements Serializable {

    private static final long serialVersionUID = -3819883511505235030L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_status")
    @ColumnDefault("0")
    private Integer orderStatus;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "total")
    private Double total;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "sub_total")
    private Double sub_total;

    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @OneToMany(cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @OneToMany(cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private List<Address> addressesList;

    @OneToMany(cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER,
            mappedBy = "order")
    private List<OrderPayment> orderPaymentList;

//    public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Integer getOrderStatus() {
//		return orderStatus;
//	}
//
//	public void setOrderStatus(Integer orderStatus) {
//		this.orderStatus = orderStatus;
//	}
//
//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}
//
//	public Double getTotal() {
//		return total;
//	}
//
//	public void setTotal(Double total) {
//		this.total = total;
//	}
//
//	public Double getTax() {
//		return tax;
//	}
//
//	public void setTax(Double tax) {
//		this.tax = tax;
//	}
//
//	public Double getSub_total() {
//		return sub_total;
//	}
//
//	public void setSub_total(Double sub_total) {
//		this.sub_total = sub_total;
//	}
//
//	public Date getCreatedAt() {
//		return createdAt;
//	}
//
//	public void setCreatedAt(Date createdAt) {
//		this.createdAt = createdAt;
//	}
//
//	public Date getUpdatedAt() {
//		return updatedAt;
//	}
//
//	public void setUpdatedAt(Date updatedAt) {
//		this.updatedAt = updatedAt;
//	}
//
//	public List<OrderDetail> getOrderDetails() {
//		return orderDetails;
//	}
//
//	public void setOrderDetails(List<OrderDetail> orderDetails) {
//		this.orderDetails = orderDetails;
//	}
//
//	public List<Address> getAddressesList() {
//		return addressesList;
//	}
//
//	public void setAddressesList(List<Address> addressesList) {
//		this.addressesList = addressesList;
//	}
//
//	public List<OrderPayment> getOrderPaymentList() {
//		return orderPaymentList;
//	}
//
//	public void setOrderPaymentList(List<OrderPayment> orderPaymentList) {
//		this.orderPaymentList = orderPaymentList;
//	}
//
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	public Order() {
        this.orderDetails = new ArrayList<>();
        this.addressesList = new ArrayList<>();
        this.orderPaymentList = new ArrayList<>();
    }
}
