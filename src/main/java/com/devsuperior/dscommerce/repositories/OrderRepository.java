package com.devsuperior.dscommerce.repositories;

import org.apache.commons.logging.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
