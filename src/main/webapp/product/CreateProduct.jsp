<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Regalite
  Date: 8/4/2022
  Time: 9:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateProduct</title>
</head>
<body>
<c:if test="${product.getId() != null}">
<form action="/product?action=edit&id=${product.getId()}" method="post">
    <label for="id">Mã sản phẩm</label>
    <input type="text" name="id"  readonly value="${product.getId()}">
    <br>
    <label for="name">Tên sản phẩm</label>
    <input type="text" name="name"  value="${product.getName()}">
    <br>
    <label for="price">Giá sản phẩm</label>
    <input type="text" name="price" value="${product.getPrice()}">
    <br>
    <label for="quantity">Số lượng</label>
    <input type="text" name="quantity"  value="${product.getQuantity()}">
    <br>
    <label for="color">Màu sắc</label>
    <input type="text" name="color"value="${product.getColor()}">
    <br>
    <label for="color">Mô tả</label>
    <input type="text" name="description"  value="${product.getDescription()}">
    <br>
    <label for="category">Danh mục</label>
    <select name="idCategory" >
        <c:forEach items="${categories}" var="c">
            <option value="${c.getId()}">${c.getName()}</option>
        </c:forEach>
    </select>
    <br>
    <button type="submit">Create</button>
</form>
</c:if>
    <br>
    <c:if test="${product.getId() == null}">
        <label for="id">ID</label>
        <input type="text" name="id" id="id" value="${product.getId()}" readonly>
    <form action="/product?action=create" method="post">
        <label for="id">Mã sản phẩm</label>
        <input type="text" name="id" id="idProduct" readonly value="${product.getId()}">
        <br>
        <label for="name">Tên sản phẩm</label>
        <input type="text" name="name" id="name" value="${product.getName()}">
        <br>
        <label for="price">Giá sản phẩm</label>
        <input type="text" name="price" id="price" value="${product.getPrice()}">
        <br>
        <label for="quantity">Số lượng</label>
        <input type="text" name="quantity" id="quantity" value="${product.getQuantity()}">
        <br>
        <label for="color">Màu sắc</label>
        <input type="text" name="color" id="color" value="${product.getColor()}">
        <br>
        <label for="color">Mô tả</label>
        <input type="text" name="description" id="description" value="${product.getDescription()}">
        <br>
        <label for="category">Danh mục</label>
        <select name="idCategory" id="category">
            <c:forEach items="${categories}" var="c">
            <option value="${c.getId()}">${c.getName()}</option>
            </c:forEach>
        </select>
        <br>
        <button type="submit">Create</button>
    </form>
    </c:if>
</body>
</html>
