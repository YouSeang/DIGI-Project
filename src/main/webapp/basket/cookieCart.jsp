<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "net.basket.db.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<style>
    table {
        border-collapse: collapse;
        width: 60%;
        margin: auto;
    }
    th, td {
        border: 1px solid #dddddd;
        padding: 8px;
        text-align: center;
    }
    th {
        background-color: #f2f2f2;
    }
    .quantity-input {
        width: 50px;
        text-align: center;
    }
    .delete-button {
        background-color: #ff4444;
        color: white;
        border: none;
        padding: 5px 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        cursor: pointer;
    }
</style>
</head>
<center>
<body>
<h2>장바구니</h2>
<hr>
<% BasketBean basketbean = (BasketBean) request.getAttribute("basketData"); %>
<form action="BasketAddAction.ba" method="post">
<table>
    <tr>
        <th>선택</th>
        <th>쿠키 종류</th>
        <th>수량</th>
        <th>변경</th>
    </tr>
    <tr>
        <td><input type="checkbox" name="product" value="product1"></td>
        <td>일반 쿠키</td>
        <td><input type="number" class="quantity-input" name="quantity_product1" value="<%= basketbean.getQuantity_product1() %>"></td>
        <td><button type="submit" class="delete-button" name="quantity_product1" value="product1">변경</button></td>
    </tr>
    <tr>
        <td><input type="checkbox" name="product" value="product2"></td>
        <td>초코 쿠키</td>
        <td><input type="number" class="quantity-input" name="quantity_product2" value="<%= basketbean.getQuantity_product2() %>"></td>
        <td><button type="submit" class="delete-button" name="quantity_product2" value="product2">변경</button></td>
    </tr>
    <tr>
        <td><input type="checkbox" name="product" value="product3"></td>
        <td>오트밀 쿠키</td>
        <td><input type="number" class="quantity-input" name="quantity_product3" value="<%= basketbean.getQuantity_product3() %>"></td>
        <td><button type="submit" class="delete-button" name="quantity_product3" value="product3">변경</button></td>
    </tr>
    
</table>
</form>

</body>
</center>
</html>