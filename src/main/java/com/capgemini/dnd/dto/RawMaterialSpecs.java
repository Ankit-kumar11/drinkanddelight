package com.capgemini.dnd.dto;

import java.util.Date;

public class RawMaterialSpecs {
	private String rmsId;
	private String name;
	private double pricePerUnit;
	private int duration; // best before ______(in months)
	private String description; // to be elaborated later
	// private String minStock; // minimum quantity to be kept in warehouse for
	// avoiding delay to meet demands

	public RawMaterialSpecs(String rmsId, String name, double pricePerUnit, Date manufacturingDate, int duration,
			String description) {
		this.rmsId = rmsId;
		this.name = name;
		this.pricePerUnit = pricePerUnit;
		// this.manufacturingDate = manufacturingDate;
		this.duration = duration;
		this.description = description;
		// this.minStock = minStock;
	}

	public RawMaterialSpecs() {

	}

	public String getRmsId() {
		return rmsId;
	}

	public String getName() {
		return name;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public int getDuration() {
		return duration;
	}

	public String getDescription() {
		return description;
	}

//	public String getMinStock() {
//		return minStock;
//	}

	public RawMaterialSpecs getRawMaterialLot() {
		return this;
	}

	@Override
	public String toString() {
		return "RawMaterialSpecs [rmsId=" + rmsId + ", name=" + name + ", pricePerUnit=" + pricePerUnit + ", duration="
				+ duration + ", description=" + description + "]";
	}
}
