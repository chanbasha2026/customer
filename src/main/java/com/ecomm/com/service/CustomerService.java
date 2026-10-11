package com.ecomm.com.service;

import com.ecomm.com.dto.req.CustomerReq;
import com.ecomm.com.dto.res.CustomerRes;
import com.ecomm.com.entity.Customer;

import java.util.List;

public interface CustomerService {

    public CustomerRes addCustomer(CustomerReq req);
    public CustomerRes updateCustomer(CustomerReq req, Long id);
    public CustomerRes getCustomerById(Long id);
    public List<CustomerRes> getAllCustomers();
    public void deleteCustomerById(Long id);
    public void deleteAllCustomers();
}
