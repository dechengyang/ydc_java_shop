package com.ydcjavashop.shop.base;

/**
 * @Description 返回接口的基类
 * @Author ydc
 * @CreateDate 2016/11/15
 * @Version 1.0
 */
public class BaseFeed {
    private String token;
    private String status;
    private String message;
    private String solution;
    private int totalSize;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

}
