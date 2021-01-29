package com.itgod.restController.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhangsq
 * @date 2021/1/9 17:56
 */
@Getter
@Setter
@ToString
public class testentity {
    private String birthday;
    private String cardNum;
    private String cardTypeCode;
    private String description;
    private String gender;
    private String jobNum;
    private String name;
    private String nickname;
    private String orgCode;
    private String phone;
    private String portrait;
    private String FirstCode;
    private String positionalSecondCode;
    private String skillful;
    private String timestamp;
    private String updateTime;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardTypeCode() {
        return cardTypeCode;
    }

    public void setCardTypeCode(String cardTypeCode) {
        this.cardTypeCode = cardTypeCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getFirstCode() {
        return FirstCode;
    }

    public void setFirstCode(String firstCode) {
        this.FirstCode = firstCode;
    }

    public String getPositionalSecondCode() {
        return positionalSecondCode;
    }

    public void setPositionalSecondCode(String positionalSecondCode) {
        this.positionalSecondCode = positionalSecondCode;
    }

    public String getSkillful() {
        return skillful;
    }

    public void setSkillful(String skillful) {
        this.skillful = skillful;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
