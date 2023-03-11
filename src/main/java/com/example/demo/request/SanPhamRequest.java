package com.example.demo.request;

public class SanPhamRequest {
    private String ten;
    private String gia;

    public SanPhamRequest(String ten, String gia) {
        this.ten = ten;
        this.gia = gia;
    }

    public SanPhamRequest() {
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }
}
