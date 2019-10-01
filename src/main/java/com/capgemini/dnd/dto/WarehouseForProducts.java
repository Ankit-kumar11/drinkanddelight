package com.capgemini.dnd.dto;

public class WarehouseForProducts {
	private String warehouseId;
	private String managerId;
	private String address;

	public WarehouseForProducts(String warehouseId, String managerId, String address) {
		this.warehouseId = warehouseId;
		this.managerId = managerId;
		this.address = address;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "WarehouseForRM [warehouseId=" + warehouseId + ", managerId=" + managerId + ", address=" + address + "]";
	}
}
