package com.ecomm.com.repo;

import com.ecomm.com.dto.req.CustomerReq;
import com.ecomm.com.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer,Long> {
}
