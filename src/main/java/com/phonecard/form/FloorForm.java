package com.phonecard.form;

import lombok.Data;

/**
 * @Auther: Mr.Yang
 * @Date: 2019/9/5 0005 17:32
 * @Description:
 */
@Data
public class FloorForm {

    private Integer id;

    private String floorImg;

    private String floorTitle;

    private Integer sort;

    private Short isDelete;

    private Short isIndex;

}
