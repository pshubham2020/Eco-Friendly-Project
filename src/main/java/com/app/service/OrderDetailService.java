package com.app.service;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;

import com.app.entity.Order;
import com.app.entity.OrderDetail;
import com.app.entity.Product;

public interface OrderDetailService {
	boolean saveOrderDetail(OrderDetail od);
	List<OrderDetail> getAllOrdersOrderId(Set<Long> id, String customerEmail);
	void deleteOrderDetailByNum(int paymentId);
	boolean saveCartOrderDetail(Order orders, Product products, int quantity, double mrpPrice, double amount, double totalPrice, int paymentId, String orderStatus, String paymentMode);
	List<OrderDetail> getOrderByPayId(int paymentId);
	void sendPdfEmail(String name, String email, int OrderNum, byte[] bytes);
	void saveOrderDetails(List<OrderDetail> orderDetails);
	List<OrderDetail> getLastOrderByIdDesc(Pageable pageable);
	List<OrderDetail> getAllDeliveredOrdersOrderId(Set<Long> Id, String customerEmail, String orderStatus);
}	
