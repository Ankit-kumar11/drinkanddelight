package com.capgemini.dnd.dto;

public class DistributorAddress {
	private String addrLine1;
	private String addrLine2;
	private String city;
	private String state;
	private String pincode;

	public DistributorAddress(String addrLine1, String addrLine2, String city, String state, String pincode) {
		this.addrLine1 = addrLine1;
		this.addrLine2 = addrLine2;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public String getAddrLine1() {
		return addrLine1;
	}

	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	public String getAddrLine2() {
		return addrLine2;
	}

	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "DistributorAddress [addrLine1=" + addrLine1 + ", addrLine2=" + addrLine2 + ", city=" + city + ", state="
				+ state + ", pincode=" + pincode + "]";
	}
}