package com.ecomm.com.controller;

import com.ecomm.com.constants.ApiConstants;
import com.ecomm.com.dto.req.CustomerReq;
import com.ecomm.com.dto.res.CustomerRes;
import com.ecomm.com.response.ApiResponse;
import com.ecomm.com.service.CustomerService;
import com.ecomm.com.util.ResponseUtil;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/customer")
public class CustomerController {
    @Autowired
    private CustomerService cs;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CustomerRes>> addCustomer(@Valid @RequestBody CustomerReq dto){
        CustomerRes customerRes = cs.addCustomer(dto);
        return  ResponseUtil.success(customerRes, ApiConstants.CUSTOMER_CREATED, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<CustomerRes>> updateCustomer(@Valid @RequestBody CustomerReq dto,@PathVariable Long id){
        CustomerRes customerRes = cs.updateCustomer(dto, id);
        return  ResponseUtil.success(customerRes, ApiConstants.CUSTOMER_UPDATED, HttpStatus.OK);
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<CustomerRes>> getCustomerById(@PathVariable Long id){
        CustomerRes customerRes = cs.getCustomerById(id);
        return  ResponseUtil.success(customerRes, ApiConstants.CUSTOMER_EXIST, HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<CustomerRes>>> getAllCustomers(){
        List<CustomerRes> allCustomers = cs.getAllCustomers();
        return  ResponseUtil.success(allCustomers, ApiConstants.CUSTOMER_EXIST, HttpStatus.OK);
    }
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCustomerById(@PathVariable Long id){
       cs.deleteCustomerById(id);
        return  ResponseUtil.success(ApiConstants.CUSTOMER_DELETED, ApiConstants.CUSTOMER_NOT_EXIST, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<String>> deleteAllCustomer(){
        cs.deleteAllCustomers();
        return  ResponseUtil.success(ApiConstants.CUSTOMER_DELETED, ApiConstants.CUSTOMER_NOT_EXIST, HttpStatus.OK);
    }

}
