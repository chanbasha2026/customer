package com.ecomm.com.dto.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AddressRes {
    private Long id;
    private String dno;
    private String street;
    private String city;
    private String state;
    private String country;
    private Long pinCode;
}
