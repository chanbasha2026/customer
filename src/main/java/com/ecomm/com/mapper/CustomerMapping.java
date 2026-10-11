package com.ecomm.com.mapper;

import com.ecomm.com.dto.req.AddressReq;
import com.ecomm.com.dto.req.CustomerReq;
import com.ecomm.com.dto.res.AddressRes;
import com.ecomm.com.dto.res.CustomerRes;
import com.ecomm.com.entity.Address;
import com.ecomm.com.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapping {

    public List<Address> toAddressList(List<AddressReq> addressReqs){
        return addressReqs.stream().map(this::toAddress).collect(Collectors.toCollection(ArrayList::new));

    }

    public Address toAddress(AddressReq req){
        return Address.builder()
                .dno(req.getDno())
                .street(req.getStreet())
                .city(req.getCity())
                .state(req.getState())
                .country(req.getCountry())
                .pinCode(req.getPinCode())
                .build();
    }

    public AddressRes toAddresRes(Address address){
        return AddressRes.builder()
                .id(address.getId())
                .dno(address.getDno())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .pinCode(address.getPinCode())
                .build();
    }

    public List<AddressRes> toAddressResList(List<Address> address){
        return address.stream().map(this::toAddresRes).collect(Collectors.toCollection(ArrayList::new));
    }

    public Customer toCustomerEntity(CustomerReq req){
        Customer customer = Customer.builder()
                .name(req.getName())
                .age(req.getAge())
                .email(req.getEmail())
                .phNo(req.getPhNo())
                .build();
        List<Address> addresses = toAddressList(req.getAddress());
        addresses.forEach(address -> address.setCustomer(customer));
        customer.setAddress(addresses);
        return customer;

    }
    public CustomerRes toCustomerRes(Customer req){
        return CustomerRes.builder()
                .id(req.getId())
                .name(req.getName())
                .age(req.getAge())
                .email(req.getEmail())
                .phNo(req.getPhNo())
                .address(toAddressResList(req.getAddress()))
                .build();
    }
}
