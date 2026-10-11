package com.ecomm.com.dto.req;

import com.ecomm.com.constants.ErrorConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class AddressReq {
    @NotNull(message = ErrorConstants.ID_ERROR)
    private Long id;
    @NotBlank(message = ErrorConstants.DNO_ERROR)
    private String dno;
    @NotBlank(message = ErrorConstants.STREET_ERROR)
    private String street;
    @NotBlank(message = ErrorConstants.CITY_ERROR)
    private String city;
    @NotBlank(message = ErrorConstants.STATE_ERROR)
    private String state;
    @NotBlank(message =ErrorConstants.COUNTRY_ERROR )
    private String country;
    @NotNull(message = ErrorConstants.PINCODE_ERROR)
    private Long pinCode;
}
