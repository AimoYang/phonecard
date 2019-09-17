package com.phonecard.form;

import com.phonecard.bean.AddressSelf;
import lombok.Data;

import java.util.List;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/17 0017 13:02
 * @Description:
 */
@Data
public class AddressForm {

    private Integer cityId;

    private String cityName;

    private List<AddressSelfForm> addressSelves;
}
