package com.ecomm.com.service.impl;

import com.ecomm.com.constants.ErrorConstants;
import com.ecomm.com.dto.req.AddressReq;
import com.ecomm.com.dto.req.CustomerReq;
import com.ecomm.com.dto.res.CustomerRes;
import com.ecomm.com.entity.Address;
import com.ecomm.com.entity.Customer;
import com.ecomm.com.exception.CustomerNotFoundException;
import com.ecomm.com.mapper.CustomerMapping;
import com.ecomm.com.repo.CustomerRepo;
import com.ecomm.com.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo crepo;
    @Autowired
    private CustomerMapping cmap;

    @Override
    public CustomerRes addCustomer(CustomerReq req) {
        Customer customerEntity = cmap.toCustomerEntity(req);
        Customer saved = crepo.save(customerEntity);
        return cmap.toCustomerRes(saved);
    }


    @Transactional
    @Override
    public CustomerRes updateCustomer(CustomerReq req, Long id) {

        Customer customer = crepo.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        ErrorConstants.CUSTMOR_NOT_FOUND + " " + id
                ));

        customer.setName(req.getName());
        customer.setAge(req.getAge());
        customer.setEmail(req.getEmail());
        customer.setPhNo(req.getPhNo());

        if (req.getAddress() != null) {

            // Index existing addresses by ID
            Map<Long, Address> existingAddresses =
                    customer.getAddress().stream()
                            .filter(a -> a.getId() != null)
                            .collect(Collectors.toMap(
                                    Address::getId,
                                    Function.identity()
                            ));

            Set<Long> retainedIds = new HashSet<>();

            for (AddressReq addressReq : req.getAddress()) {

                if (addressReq.getId() != null) {
                    // Update existing address without changing its ID
                    Address address =
                            existingAddresses.get(addressReq.getId());

                    if (address == null) {
                        throw new IllegalArgumentException(
                                "Address ID " + addressReq.getId()
                                        + " does not belong to customer " + id
                        );
                    }

                    address.setDno(addressReq.getDno());
                    address.setStreet(addressReq.getStreet());
                    address.setCity(addressReq.getCity());
                    address.setState(addressReq.getState());
                    address.setCountry(addressReq.getCountry());
                    address.setPinCode(addressReq.getPinCode());

                    retainedIds.add(address.getId());

                } else {
                    // Create a new address
                    Address address = cmap.toAddress(addressReq);
                    address.setCustomer(customer);
                    customer.getAddress().add(address);
                }
            }

            // Remove existing addresses omitted from the request
            customer.getAddress().removeIf(address ->
                    address.getId() != null
                            && !retainedIds.contains(address.getId())
            );
        }

        return cmap.toCustomerRes(customer);
    }

    @Override
    public CustomerRes getCustomerById(Long id) {
        Customer customer = crepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(ErrorConstants.CUSTMOR_NOT_FOUND + " " + id));
        return cmap.toCustomerRes(customer);
    }

    @Override
    public List<CustomerRes> getAllCustomers() {
        List<Customer> all = crepo.findAll();
       return all.stream().map(c -> cmap.toCustomerRes(c)).toList();
    }

    @Override
    public void deleteCustomerById(Long id) {
        Customer customer = crepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(ErrorConstants.CUSTMOR_NOT_FOUND + " " + id));
        crepo.delete(customer);

    }

    @Override
    public void deleteAllCustomers() {
    crepo.deleteAll();
    }
}
