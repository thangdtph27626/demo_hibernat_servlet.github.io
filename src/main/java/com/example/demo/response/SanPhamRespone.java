package com.example.demo.response;

public class SanPhamRespone {

    private int id;
    private String ten;
    private float gia;

    public SanPhamRespone(int id, String ten, float gia) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
    }

    public SanPhamRespone() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }
}
