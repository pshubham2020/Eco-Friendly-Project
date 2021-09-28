<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<c:if test="${empty email}">
	<c:redirect url="${pageContext.request.contextPath}/user/sign-in"/>  
</c:if>
	<title>Eco-Friendly Stationery Items | Orders History</title>
	<jsp:include page="layouts/header.jsp"></jsp:include>
	<link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="layouts/user-navigation.jsp"></jsp:include>
	<jsp:include page="layouts/user-menu.jsp"></jsp:include>
	<!-- banner-2 -->
	<div class="page-head_agile_info_w3l"></div>
	<!-- //banner-2 -->
	<!-- page -->
	<div class="services-breadcrumb">
		<div class="agile_inner_breadcrumb">
			<div class="container">
				<ul class="w3_short">
					<li>
						<a href="${pageContext.request.contextPath}/home">Home</a>
						<i>|</i>
					</li>
					<li>Orders History</li>
				</ul>
			</div>
		</div>
	</div>
	<!-- //page -->
	<div class="privacy py-sm-5 py-4">
		<div class="container py-xl-4 py-lg-2">
			<h3 class="tittle-w3l text-center mb-lg-5 mb-sm-4 mb-3">
				<span>O</span>rders History
			</h3>
			<div class="container">
			<div class="table-responsive">
<table id="example" class="table table-hover table-bordered text-center">
      <thead>
        <tr>
          <th>SR. No.</th>
          <th>Order No.</th>
          <th>Item</th>
          <th>Quantity</th>
          <th>Price(&#x20b9;)</th>
          <th>Total Price(&#x20b9;)</th>
          <th>Date</th>
          <th>Status</th>
        <th>Action</th>
                </tr>
      </thead>
      <tbody>
      <c:forEach var="orders" items="${orders}">
       <c:set var="count" value="${count + 1}" scope="page"/>
		<tr>
		  <td>${count}</td>
		  <td>${orders.paymentId}</td>
		  <td>
          <img src="${pageContext.request.contextPath}${orders.product.image}" width="70" height="90" class="img img-responsiive" /></a>
          <br>${orders.product.name}</td>
          <td>${orders.quantity}</td>
          <td>${orders.price}</td>
          <td>${orders.amount}</td>
          <td><fmt:formatDate pattern="dd-MMM-yyyy HH:mm" value="${orders.order.orderDate}" /></td>
          <td>
        
          	<c:if test="${orders.orderStatus eq 'Delivered'}">
          	<span class="badge badge-success">${orders.orderStatus}</span>
          	</c:if>
       
          </td>	
          <td> 
           	
         	<c:if test="${orders.orderStatus eq 'Delivered'}">
         	<a href="${pageContext.request.contextPath}/product/productdetails?code=${orders.product.code}" class="btn btn-success">Re-Order</a> 
        	</c:if> 
         	
           </td> 
        </tr>   
        </c:forEach>     
      </tbody>
    </table>
    </div>
			</div>
		</div>
	</div>
	<!-- //checkout page -->
	<div>
	</div>
<jsp:include page="layouts/cart-footer.jsp"></jsp:include>
	<!-- Bootstrap core JavaScript-->
<!-- Page level plugin JavaScript-->
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"></script>
	<!--quantity-->
	<script>
	$(document).ready(function() {
	    $('#example').DataTable();
	} );
	</script>
</body>
</html>