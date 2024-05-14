<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
request.setCharacterEncoding("euc-kr");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<script>
//수량 증가
function increaseQuantity(productId) {
    var quantityElement = document.getElementById("quantity_" + productId);
    var quantity = parseInt(quantityElement.value);
    quantityElement.value = quantity + 1;
    // hidden input의 값을 업데이트
    document.getElementById("quantity_" + productId).value = quantityElement.value;
    // 이벤트의 기본 동작 중지
    event.preventDefault();
}

// 수량 감소
function decreaseQuantity(productId) {
    var quantityElement = document.getElementById("quantity_" + productId);
    var quantity = parseInt(quantityElement.value);
    if (quantity > 1) {
        quantityElement.value = quantity - 1;
        // hidden input의 값을 업데이트
        document.getElementById("quantity_" + productId).value = quantityElement.value;
    }
    // 이벤트의 기본 동작 중지
    event.preventDefault();
}
    
// 로그아웃 함수
function logout() {
    if (confirm("로그아웃 하시겠습니까?")) {
        location.href='Logout.jsp';
    }
}
</script>

<title>주문</title>
<style>
    .product-row {
        display: flex;
        justify-content: center;
    }
    .product {
        margin: 0 10px;
    }
</style>
</head>
<center>
<body>
<h2>쿠키닷 주문</h2>
<hr>
<div><jsp:include page="/login/loginState.jsp"></jsp:include></div>

<form id="orderForm" name="orderForm" method="post" action="BasketAddAction.ba" >

<div class="product-row">
    <div class="product">
        <h3>일반 쿠키</h3>
        <img src="image/AmericanCookie.png" alt="일반 쿠키 이미지" height="200px" width="200px">
        <div>가격: 5,000원</div>
        <div>수량: 
            <input type="number" id="quantity_product1" name="quantity_product1" value="0" min="0">
            <button onclick="increaseQuantity('product1')">+</button>
            <button onclick="decreaseQuantity('product1')">-</button>
        </div>
    </div>
    
    <div class="product">
        <h3>초코 쿠키</h3>
        <img src="image/DoubleChocoCookie.png" alt="일반 쿠키 이미지" height="200px" width="200px" name="image/DoubleChocoCookie.png">
        <div>가격: 5,500원</div>
        <div>수량: 
            <input type="number" id="quantity_product2" name="quantity_product2" value="0" min="0">
            <button onclick="increaseQuantity('product2')">+</button>
            <button onclick="decreaseQuantity('product2')">-</button>
        </div>
    </div>
    
    <div class="product">
        <h3>오트밀 쿠키</h3>
        <img src="image/OtmealCookie.png" alt="일반 쿠키 이미지" height="200px" width="200px">
        <div>가격: 5,500원</div>
        <div>수량: 
            <input type="number" id="quantity_product3" name="quantity_product3" value="0" min="0">
            <button onclick="increaseQuantity('product3')">+</button>
            <button onclick="decreaseQuantity('product3')">-</button>
        </div>
    </div>
</div><br>

<input type="submit" value="장바구니 담기"> </form>

</body>
</center>

</html>

