<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<table>
    <a href="productServlet">Trang chủ</a>

<tr>
    <th>Mã sản phẩm</th>
    <th>Tên sản phẩm</th>
    <th>Giá sản phẩm</th>
    <th>Số lương</th>
    <th>Màu sắc</th>
    <th>Danh mục</th>
    <th colspan="2">Action</th>
</tr>
<c:forEach items="${products}" var="p">
    <tr>
        <td>${p.getId()}</td>
        <td>${p.getName()}</td>
        <td>${p.getPrice()}</td>
        <td>${p.getQuantity()}</td>
        <td>${p.getColor()}</td>
        <td>${p.getCategory().getName()}</td>
        <td><button><a href="/product?action=edit&id=${p.getId()}">Edit</a></button></td>
        <td><button><a href="/product?action=delete&id=${p.getId()}">Delete</a></button></td>
    </tr>
</c:forEach>
</table>
</body>
</html>