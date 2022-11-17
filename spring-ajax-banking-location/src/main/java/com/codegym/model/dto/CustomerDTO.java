package com.codegym.model.dto;

import com.codegym.model.Customer;
import com.codegym.model.LocationRegion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class CustomerDTO {
    private Long id;
    @Size(min = 5, max = 100, message = "Họ tên có độ dài nằm trong khoảng 5 - 100 ký tự")
    @NotEmpty(message = "Tên Khách Hàng không được để trống , xin vui lòng nhập lại !")
    private String fullName;

    @NotEmpty(message = "Email không được để trống , xin vui lòng nhập lại !")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không đúng định dạng")
    private String email;
    @Pattern(regexp = "^\\d+$", message = "Số điện thoại phải là số")
//    @Pattern(regexp = "/(84|0[3|5|7|8|9])+([0-9]{8})\\b/" , message = "Phone nhập đúng định dạng hoặc để trống , vui lòng nhập lại !")
    @NotEmpty(message = "Phone Không được để trống ,vui lòng nhập số điện thoại")
    private String phone;

    @Pattern(regexp = "^\\d+$", message = "Số tiền gửi phải là số")
    private String balance;

    private LocationRegionDTO locationRegion;

    public CustomerDTO(Long id, String fullName, String email, String phone, BigDecimal balance, LocationRegion locationRegion) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.balance = balance.toString();
        this.locationRegion = locationRegion.toLocationRegionDTO();
    }

    public Customer toCustomer(){
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setEmail(email)
                .setPhone(phone)
                .setBalance(new BigDecimal(Long.parseLong(balance)))
                .setLocationRegion(locationRegion.toLocationRegion());
    }
}