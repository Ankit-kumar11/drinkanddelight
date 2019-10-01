package com.capgemini.dnd.dto;

public class ProductSpecs {
	private String psId;
	private String name;
	private double pricePerUnit;
	private String duration; // best before______(enum later) it is string coz we dont know the quantity
								// unit.
	private String description;

	// private String minStock; // minimum quantity to be kept in warehouse for
	// avoiding delay to meet demands

	@Override
	public String toString() {
		return "ProductSpecs [psId=" + psId + ", name=" + name + ", pricePerUnit=" + pricePerUnit + ", duration="
				+ duration + ", description=" + description + "]";
	}

	public String getPsId() {
		return psId;
	}

	public void setPsId(String psId) {
		this.psId = psId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProductSpecs(String psId, String name, double pricePerUnit, String duration, String description) {
		this.psId = psId;
		this.name = name;
		this.pricePerUnit = pricePerUnit;
		this.duration = duration;
		this.description = description;
	}

}
