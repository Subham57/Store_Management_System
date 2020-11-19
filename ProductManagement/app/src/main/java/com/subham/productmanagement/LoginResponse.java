package com.subham.productmanagement;

public class LoginResponse {

    public String message;

    public LoginResponse() {
        super();
    }

    public LoginResponse(String messege){
        this.message = messege;
    }

    public String getMessege() {
        return message;
    }

    public void setMessege(String messege) {
        this.message = messege;
    }
}
