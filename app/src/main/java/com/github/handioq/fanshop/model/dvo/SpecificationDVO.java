package com.github.handioq.fanshop.model.dvo;

import java.util.ArrayList;
import java.util.List;

public class SpecificationDVO {

    private String color;
    private String country;
    private String code;
    private String brand;
    private List<SizeDVO> sizes = new ArrayList<>();

    public SpecificationDVO(String color, String country, String code, String brand, List<SizeDVO> sizes) {
        this.color = color;
        this.country = country;
        this.code = code;
        this.brand = brand;
        this.sizes = sizes;
    }

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

    public List<SizeDVO> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeDVO> sizes) {
        this.sizes = sizes;
    }

    @Override
    public String toString() {
        return "SpecificationDVO{" +
                "color='" + color + '\'' +
                ", country='" + country + '\'' +
                ", code='" + code + '\'' +
                ", brand='" + brand + '\'' +
                ", sizes=" + sizes +
                '}';
    }
}
