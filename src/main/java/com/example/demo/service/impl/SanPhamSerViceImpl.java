/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.service.impl;

import com.example.demo.entity.SanPham;
import com.example.demo.repository.SanPhamRepository;
import com.example.demo.request.SanPhamRequest;
import com.example.demo.response.SanPhamRespone;
import com.example.demo.service.SanPhamService;
import com.microsoft.sqlserver.jdbc.StringUtils;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author thang
 */
public class SanPhamSerViceImpl implements SanPhamService {

    private SanPhamRepository sanPhamRepository = new SanPhamRepository();
    
    @Override
    public List<SanPhamRespone> getAll() {
        return sanPhamRepository.getAll();
    }

    @Override
    public HashMap<String, String> add(SanPhamRequest request) {
        HashMap<String, String> lists = new HashMap<>();
        // Check validate
        if (StringUtils.isEmpty(request.getTen())) {
            lists.put("GIA_EMPTY", "Vui lòng nhập giá");
        }
        if (StringUtils.isEmpty(request.getGia())) {
            lists.put("TEN_EMPTY", "Tên sản Phẩm không được để trống");
        }
        // Khi thoả mãn validate
        if (lists.isEmpty()) {
            SanPham sanPham = new SanPham();
            sanPham.setTen(request.getTen());
            sanPham.setGia(Float.parseFloat(request.getGia()));
            sanPhamRepository.add(sanPham);
        }

        return  lists;
    }

    @Override
    public SanPhamRespone findById(int id) {
        return sanPhamRepository.findById(id);
    }

    @Override
    public HashMap<String, String> update(SanPhamRequest request, int id) {
        HashMap<String, String> lists = new HashMap<>();
        SanPham updateSanPham = sanPhamRepository.getOne(id);
        // Check validate
        if (StringUtils.isEmpty(request.getTen())) {
            lists.put("GIA_EMPTY", "Vui lòng nhập giá");
        }
        if (StringUtils.isEmpty(request.getGia())) {
            lists.put("TEN_EMPTY", "Tên sản Phẩm không được để trống");
        }
        if(updateSanPham == null){
            lists.put("San_PHAM_EXIST", "Id không tồn tại");
        }
        // Khi thoả mãn validate
        if (lists.isEmpty()) {
            updateSanPham.setTen(request.getTen());
            updateSanPham.setGia(Float.parseFloat(request.getGia()));
            sanPhamRepository.update(updateSanPham);
        }

        return  lists;
    }

    @Override
    public boolean delete(int id) {
       SanPham sanPham = sanPhamRepository.getOne(id);
       return sanPhamRepository.delete(sanPham);
    }
    
}
