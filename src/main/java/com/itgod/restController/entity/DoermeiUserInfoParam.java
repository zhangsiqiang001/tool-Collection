package com.itgod.restController.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangsq
 * @date 2020/12/30 17:43
 */
@Data
@Accessors(chain = true)
public class DoermeiUserInfoParam {

    private Long timestamp;

    private String token;

    private String userId;

    private String cardTypeCode;

    private String cardNum;

    private String name;

    private String phone;

    private Integer gender;

    private String birthday;

    private Integer treatmentCardType;

    private String treatmentCardNum;

    private String hospitalorgCode;

    private String hospitalorgName;

}
