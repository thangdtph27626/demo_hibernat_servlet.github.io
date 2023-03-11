<%--
  Created by IntelliJ IDEA.
  User: thang
  Date: 3/11/2023
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3 style="text-align: center;">Quản Lý Lớp Học</h3>
<section>
    <form action="/san-pham/add-update?id=${sanPhamRespone.id}" method="post">
        <div class="row mt-4">
            <div class="col-6">
                <label>Tên</label>
                <input type="text" class="form-control" name="ten" value="${sanPhamRespone.ten}"/>
                <span style="color: red;">${errors.get("TEN_EMPTY")}</span>
            </div>
            <div class="col-6">
                <label>Giá</label>
                <input type="number" class="form-control" name="gia" value="${sanPhamRespone.gia}"/>
                <span style="color: red;">${errors.get("GIA_EMPTY")}</span>
            </div>
        </div>

        <div class="row mt-4" style="justify-content: center">
            <button class="btn btn-success col-1 m-3">
                Add
            </button>
            <button class="btn btn-primary col-1 m-3">
                Update
            </button>
        </div>
    </form>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Tên</th>
        <th scope="col">Giá</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${sanPhamRespones}" var="sanPham">
        <tr>
            <td>${sanPham.id}</td>
            <td>${sanPham.ten}</td>
            <td>${sanPham.gia}</td>
            <td>
                <a href="/san-pham/detail?id=${sanPham.id}" class="btn btn-success " tabindex="-1" role="button"
                   aria-disabled="true">Detail</a>
                <a href="/san-pham/delete?id=${sanPham.id}" class="btn btn-danger " tabindex="-1" role="button"
                   aria-disabled="true">Remove</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
