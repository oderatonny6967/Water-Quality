package com.example.waterqua;


public class HelperClasss {
    String username ,email,password,confirmpassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this. username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public HelperClasss(String username, String email, String password, String confirmpassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public HelperClasss() {
    }
}
