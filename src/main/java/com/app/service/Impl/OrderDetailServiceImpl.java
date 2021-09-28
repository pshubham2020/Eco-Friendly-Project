package com.app.service.Impl;

import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailParseException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Order;
import com.app.entity.OrderDetail;
import com.app.entity.Product;
import com.app.repository.OrderDetailRepository;
import com.app.service.OrderDetailService;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private Environment env;

	@Autowired
	private OrderDetailRepository orderDetailRepo;

	@Override
	public boolean saveOrderDetail(OrderDetail od) {
		boolean status = false;
		if (od != null) {
			status = true;
			orderDetailRepo.save(od);
		}
		return status;
	}

	@Override
	public List<OrderDetail> getAllOrdersOrderId(Set<Long> id, String customerEmail) {
		return orderDetailRepo.findOrdersByOrderId(id, customerEmail);
	}

	@Override
	public boolean saveCartOrderDetail(Order orders, Product products, int quantity, double mrpPrice, double amount, double totalPrice,
			int paymentId, String orderStatus, String paymentMode) {
		boolean status = false;
		if (orders != null) {
			status = true;
			orderDetailRepo.saveOrderDetails(orders, products, quantity, mrpPrice, amount, totalPrice, paymentId, orderStatus, paymentMode);
		}
		return status;
	}

	@Override
	public void deleteOrderDetailByNum(int paymentId) {
		orderDetailRepo.deleteOrderDetailByNum(paymentId);
	}

	@Override
	public List<OrderDetail> getOrderByPayId(int paymentId) {
		return orderDetailRepo.findOrderByPayId(paymentId);
	}

	@Override
	public void saveOrderDetails(List<OrderDetail> orderDetails) {
		orderDetailRepo.saveAll(orderDetails);
	}

	@Override
	public void sendPdfEmail(String name, String email, int OrderNum, byte[] bytes) {
		MimeMessage message = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			String content = "Hi, <b>" + name + "</b> Thank you for your order. PDF of Order Details Attached.<br>";
			helper.setSubject("Eco-Friendly Stationery Items: Order#" + OrderNum);
			helper.setText(content, true);
			helper.setTo(email);
			helper.addCc(env.getProperty("mailToCc"));
			helper.setFrom(env.getProperty("spring.mail.username"));
			String fileName = "order_"+OrderNum+".pdf";
			helper.addAttachment(fileName, new ByteArrayResource(bytes));
			emailSender.send(message);
			log.info("Email Sent With File : "+fileName);
		} catch (MessagingException e) {
			log.info("Exception Occurred While Sendimg Email.");
			throw new MailParseException(e);
		}	
	}

	@Override
	public List<OrderDetail> getLastOrderByIdDesc(Pageable pageable) {
		return orderDetailRepo.getLastOrder(pageable);
	}

	@Override
	public List<OrderDetail> getAllDeliveredOrdersOrderId(Set<Long> Id, String customerEmail,
			String orderStatus) {
		return orderDetailRepo.findDeliveredItems(Id, customerEmail, orderStatus);
	}
}
