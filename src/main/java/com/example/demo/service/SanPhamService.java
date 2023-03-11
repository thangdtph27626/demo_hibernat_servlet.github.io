/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service;

import com.example.demo.entity.SanPham;
import com.example.demo.request.SanPhamRequest;
import com.example.demo.response.SanPhamRespone;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author thang
 */
public interface SanPhamService {
    
    List<SanPhamRespone> getAll();

    HashMap<String, String> add(SanPhamRequest request);

    SanPhamRespone findById(int id);

    HashMap<String, String> update(SanPhamRequest request, int id);
    
    boolean delete(int id);
    
    
}
