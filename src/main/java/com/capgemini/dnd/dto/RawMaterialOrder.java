package com.capgemini.dnd.dto;

import java.util.Date;

public class RawMaterialOrder {
	private String orderId;
	private static int orderIdCount = 1;
	private String name;
	private String rmId;
	private String supplierId;
	private double quantityValue;
	private String quantityUnit;
	private Date dateOfOrder;
	private Date dateOfDelivery;
	private double pricePerUnit;
	private double totalPrice;
	private String deliveryStatus; // enum later
	private String warehouseId;


	public RawMaterialOrder(String name, String rmId, String supplierId, double quantityValue,
			String quantityUnit, Date dateOfOrder, Date dateOfDelivery, double pricePerUnit, String warehouseId) {
//		super();
		this.orderId = "OID" + orderIdCount++;
		this.name = name;
		this.rmId = rmId;
		this.supplierId = supplierId;
		this.quantityValue = quantityValue;
		this.quantityUnit = quantityUnit;
		this.dateOfOrder = dateOfOrder;
		this.dateOfDelivery = dateOfDelivery;
		this.pricePerUnit = pricePerUnit;
		this.totalPrice = this.quantityValue*this.pricePerUnit;
		this.deliveryStatus = "PENDING";
		this.warehouseId = warehouseId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public static int getOrderIdCount() {
		return orderIdCount;
	}

	public static void setOrderIdCount(int orderIdCount) {
		RawMaterialOrder.orderIdCount = orderIdCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRmId() {
		return rmId;
	}

	public void setRmId(String rmId) {
		this.rmId = rmId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public double getQuantityValue() {
		return quantityValue;
	}

	public void setQuantityValue(double quantityValue) {
		this.quantityValue = quantityValue;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
}
