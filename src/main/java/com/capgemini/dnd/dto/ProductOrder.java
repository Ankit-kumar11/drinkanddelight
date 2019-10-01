package com.capgemini.dnd.dto;

 

import java.util.Date;

 

public class ProductOrder {
    private String orderId;
    private static int orderIdCount = 1;
    private String name;
    private String pid;
    private String distributorId;
    private double quantityValue; // enum later....it is string coz we dont know the quantity unit.
    private String quantityUnit;
    private Date dateOfOrder;
    private Date dateofDelivery;
    private double pricePerUnit;
    private double totalPrice;
    private String deliveryStatus; // enum later //delivery status
    private String warehouseId;

 

    public ProductOrder(String name, String pid, String distributorId, double quantityValue, String quantityUnit, Date dateOfOrder,
            Date dateofDelivery, double pricePerUnit, String warehouseId) {
        super();
        this.orderId = "PID" + orderIdCount++;
        this.name = name;
        this.pid = pid;
        this.distributorId = distributorId;
        this.quantityValue = quantityValue;
        this.quantityUnit = quantityUnit;
        this.dateOfOrder = dateOfOrder;
        this.dateofDelivery = dateofDelivery;
        this.deliveryStatus = "pending";
        this.pricePerUnit = pricePerUnit;
        this.warehouseId = warehouseId;
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
        ProductOrder.orderIdCount = orderIdCount;
    }

 

    public String getPid() {
        return pid;
    }

 

    public void setPid(String pid) {
        this.pid = pid;
    }

 

    public String getDistributorId() {
        return distributorId;
    }

 

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
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

 

    public Date getDateofDelivery() {
        return dateofDelivery;
    }

 

    public void setDateofDelivery(Date dateofDelivery) {
        this.dateofDelivery = dateofDelivery;
    }



    public double getPricePerUnit() {
        return pricePerUnit;
    }

 

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

 

    public String getWarehouseId() {
        return warehouseId;
    }

 

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public double getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}



	public String getDeliveryStatus() {
		return deliveryStatus;
	}



	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

 

}