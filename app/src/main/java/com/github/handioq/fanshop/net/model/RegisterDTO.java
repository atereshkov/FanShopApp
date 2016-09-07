package com.github.handioq.fanshop.net.model;

import com.github.handioq.fanshop.model.dto.AddressDTO;
import com.google.gson.annotations.SerializedName;

public class RegisterDTO {

    @SerializedName("mail")
    private String mail;

    @SerializedName("password")
    private String password;

    @SerializedName("name")
    private String name;

    @SerializedName("phone")
    private String phone;

    @SerializedName("address")
    private AddressDTO address;

    public RegisterDTO(String mail, String password, String name, String phone, AddressDTO address) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
