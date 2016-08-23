package com.github.handioq.fanshop.model.dvo;

public class UserDVO {

    private int id;
    private String name;
    private double amountSpent;
    private String email;
    private String phone;
    private AddressDVO address;

    public UserDVO(int id, String name, double amountSpent, String email, String phone, AddressDVO address) {
        this.id = id;
        this.name = name;
        this.amountSpent = amountSpent;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDVO getAddress() {
        return address;
    }

    public void setAddress(AddressDVO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amountSpent=" + amountSpent +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address=" + address +
                '}';
    }
}
