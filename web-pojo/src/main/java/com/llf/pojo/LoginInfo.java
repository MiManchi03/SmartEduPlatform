package com.llf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*分装登录结果*/
public class LoginInfo {
    private Integer id;
    private String username;
    private String name;
    private String token;

/*    public LoginInfo() {
    }

    public LoginInfo(Integer id, String username, String name, String token) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }*/
}
