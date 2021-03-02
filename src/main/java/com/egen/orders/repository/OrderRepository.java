package com.egen.orders.repository;

import com.egen.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {

    Order findById(Long orderId);
    boolean existsById(Long orderId);
}
