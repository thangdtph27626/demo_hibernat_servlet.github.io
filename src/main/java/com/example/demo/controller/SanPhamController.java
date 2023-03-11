package com.example.demo.controller;

import com.example.demo.request.SanPhamRequest;
import com.example.demo.response.SanPhamRespone;
import com.example.demo.service.SanPhamService;
import com.example.demo.service.impl.SanPhamSerViceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "Servlet", value = {"/san-pham/hien-thi", "/san-pham/add-update", "/san-pham/delete", "/san-pham/detail"})
public class SanPhamController extends HttpServlet {

    private SanPhamService sanPhamService = new SanPhamSerViceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("hien-thi")) {
            this.hienThiSanPham(request, response);
        } else if (uri.contains("detail")) {
            this.detailSanPham(request, response);
        } else {
            this.deleteSanPham(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("add-update")) {
            this.addOrUpdateLopHoc(request, response);
        }
    }

    private void hienThiSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
        request.setAttribute("sanPhamRespones", sanPhamRespones);
        request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
    }

    private void detailSanPham(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        SanPhamRespone sanPhamRespone = sanPhamService.findById(Integer.parseInt(id));
        List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
        request.setAttribute("sanPhamRespone", sanPhamRespone);
        request.setAttribute("sanPhamRespones", sanPhamRespones);
        request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
    }

    private void deleteSanPham(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        sanPhamService.delete(Integer.parseInt(id));
        List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
        request.setAttribute("sanPhamRespones", sanPhamRespones);
        request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
    }

    private void addOrUpdateLopHoc(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String ten = request.getParameter("ten");
        String gia = request.getParameter("gia");

        SanPhamRequest sanPhamRequest = new SanPhamRequest();
        sanPhamRequest.setTen(ten);
        sanPhamRequest.setGia(gia);

        if(id != null){
            HashMap<String, String> errors = sanPhamService.add(sanPhamRequest);
            request.setAttribute("errors", errors);
            List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
            request.setAttribute("sanPhamRespones", sanPhamRespones);
            request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
        }else{
            HashMap<String, String> errors = sanPhamService.update(sanPhamRequest, Integer.parseInt(id));
            request.setAttribute("errors", errors);
            List<SanPhamRespone> sanPhamRespones = sanPhamService.getAll();
            request.setAttribute("sanPhamRespones", sanPhamRespones);
            request.getRequestDispatcher("/view/san-pham.jsp").forward(request, response);
        }

    }
}
