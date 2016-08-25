package com.github.handioq.fanshop.model.dvo;

public class SizeDVO {

    private String size;

    public SizeDVO(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "SizeDVO{" +
                "size='" + size + '\'' +
                '}';
    }
}
