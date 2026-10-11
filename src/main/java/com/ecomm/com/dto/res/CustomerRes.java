package com.ecomm.com.dto.res;

import com.ecomm.com.dto.req.AddressReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CustomerRes {
    private Long id;
    private String name;
    private int age;
    private String email;
    private Long phNo;
    private List<AddressRes> address;
}
