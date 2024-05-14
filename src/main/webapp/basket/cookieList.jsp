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
//���� ����
function increaseQuantity(productId) {
    var quantityElement = document.getElementById("quantity_" + productId);
    var quantity = parseInt(quantityElement.value);
    quantityElement.value = quantity + 1;
    // hidden input�� ���� ������Ʈ
    document.getElementById("quantity_" + productId).value = quantityElement.value;
    // �̺�Ʈ�� �⺻ ���� ����
    event.preventDefault();
}

// ���� ����
function decreaseQuantity(productId) {
    var quantityElement = document.getElementById("quantity_" + productId);
    var quantity = parseInt(quantityElement.value);
    if (quantity > 1) {
        quantityElement.value = quantity - 1;
        // hidden input�� ���� ������Ʈ
        document.getElementById("quantity_" + productId).value = quantityElement.value;
    }
    // �̺�Ʈ�� �⺻ ���� ����
    event.preventDefault();
}
    
// �α׾ƿ� �Լ�
function logout() {
    if (confirm("�α׾ƿ� �Ͻðڽ��ϱ�?")) {
        location.href='Logout.jsp';
    }
}
</script>

<title>�ֹ�</title>
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
<h2>��Ű�� �ֹ�</h2>
<hr>
<div><jsp:include page="/login/loginState.jsp"></jsp:include></div>

<form id="orderForm" name="orderForm" method="post" action="BasketAddAction.ba" >

<div class="product-row">
    <div class="product">
        <h3>�Ϲ� ��Ű</h3>
        <img src="image/AmericanCookie.png" alt="�Ϲ� ��Ű �̹���" height="200px" width="200px">
        <div>����: 5,000��</div>
        <div>����: 
            <input type="number" id="quantity_product1" name="quantity_product1" value="0" min="0">
            <button onclick="increaseQuantity('product1')">+</button>
            <button onclick="decreaseQuantity('product1')">-</button>
        </div>
    </div>
    
    <div class="product">
        <h3>���� ��Ű</h3>
        <img src="image/DoubleChocoCookie.png" alt="�Ϲ� ��Ű �̹���" height="200px" width="200px" name="image/DoubleChocoCookie.png">
        <div>����: 5,500��</div>
        <div>����: 
            <input type="number" id="quantity_product2" name="quantity_product2" value="0" min="0">
            <button onclick="increaseQuantity('product2')">+</button>
            <button onclick="decreaseQuantity('product2')">-</button>
        </div>
    </div>
    
    <div class="product">
        <h3>��Ʈ�� ��Ű</h3>
        <img src="image/OtmealCookie.png" alt="�Ϲ� ��Ű �̹���" height="200px" width="200px">
        <div>����: 5,500��</div>
        <div>����: 
            <input type="number" id="quantity_product3" name="quantity_product3" value="0" min="0">
            <button onclick="increaseQuantity('product3')">+</button>
            <button onclick="decreaseQuantity('product3')">-</button>
        </div>
    </div>
</div><br>

<input type="submit" value="��ٱ��� ���"> </form>

</body>
</center>

</html>

