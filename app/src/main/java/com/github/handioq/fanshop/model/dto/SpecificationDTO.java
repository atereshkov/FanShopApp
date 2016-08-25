package com.github.handioq.fanshop.model.dto;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SpecificationDTO {

    @SerializedName("color")
    private String color;

    @SerializedName("country")
    private String country;

    @SerializedName("code")
    private String code;

    @SerializedName("brand")
    private String brand;

    @SerializedName("sizes")
    private List<SizeDTO> sizes = new ArrayList<>();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<SizeDTO> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeDTO> sizes) {
        this.sizes = sizes;
    }

    @Override
    public String toString() {
        return "SpecificationDTO{" +
                "color='" + color + '\'' +
                ", country='" + country + '\'' +
                ", code='" + code + '\'' +
                ", brand='" + brand + '\'' +
                ", sizes=" + sizes +
                '}';
    }
}
