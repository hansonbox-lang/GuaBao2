package model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Porder {
    private int orderId;
    private String memberId;
    private int totalAmount;
    private int earnedPoints;
    private int giftCount;
    private Timestamp orderTime;
    private List<PorderDetail> details = new ArrayList<>();
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getEarnedPoints() {
		return earnedPoints;
	}
	public void setEarnedPoints(int earnedPoints) {
		this.earnedPoints = earnedPoints;
	}
	public int getGiftCount() {
		return giftCount;
	}
	public void setGiftCount(int giftCount) {
		this.giftCount = giftCount;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public List<PorderDetail> getDetails() {
		return details;
	}
	public void setDetails(List<PorderDetail> details) {
		this.details = details;
	}

}