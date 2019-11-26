package com.ydcjavashop.shop.account.bean;

import java.io.Serializable;

public class TokenBean implements Serializable {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiredate() {
        return expiredate;
    }

    public void setExpiredate(String expiredate) {
        this.expiredate = expiredate;
    }

    private String token;
    private String expiredate;
}
