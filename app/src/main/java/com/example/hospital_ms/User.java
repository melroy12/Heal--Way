package com.example.hospital_ms;

public class User {
    public  String name,email,phone,address,password;

    public User(){

    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public User(String name, String email, String phone, String address, String passwd)
    {
        this.name=name;
        this.email=email;
        this.phone=phone;
        this.address=address;
        this.password=passwd;
    }
}
