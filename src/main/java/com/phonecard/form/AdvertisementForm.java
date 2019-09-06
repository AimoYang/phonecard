package com.phonecard.form;

import lombok.Data;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/5 0005 13:44
 * @Description:
 */
@Data
public class AdvertisementForm {
    private Integer id;

    private String title;

    private String image;

    private Integer sort;

    private Short type;

    private Integer isShow;

    private Short isDelete;
}
