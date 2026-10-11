package com.ecomm.com.dto.req;

import com.ecomm.com.constants.ErrorConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class CustomerReq {
    @NotBlank(message = ErrorConstants.CNAME_ERROR)
    private String name;
    @NotNull(message = ErrorConstants.CAGE_ERROR)
    private int age;
    @Email(message =ErrorConstants.EMAIL_ERROR )
    private String email;
    @NotNull(message =ErrorConstants.PHNO_ERROR )
    private Long phNo;
    @NotNull(message = ErrorConstants.ADDERS_ERROR)
    private List<AddressReq> address;
}
