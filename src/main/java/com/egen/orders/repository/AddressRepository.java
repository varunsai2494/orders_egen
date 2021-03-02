package com.egen.orders.repository;

import com.egen.orders.domain.Address;
import com.egen.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByOrder(Order order);
}
