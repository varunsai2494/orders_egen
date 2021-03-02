package com.egen.orders.repository;

import com.egen.orders.domain.OrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment,Integer> {
}
