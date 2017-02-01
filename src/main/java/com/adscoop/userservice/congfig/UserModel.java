package com.adscoop.userservice.congfig;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by thokle on 27/12/2016.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserModel {

    private String email;
    private String password;


    public String getEmail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
