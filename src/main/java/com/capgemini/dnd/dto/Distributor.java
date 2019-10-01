package com.capgemini.dnd.dto;

import java.util.List;

public class Distributor {
	private String distributorId;
	private String name;
	private List<DistributorAddress> addresses;
	private String emailId;
	private String phoneNo;

	public Distributor(String distributorId, String name, List<DistributorAddress> addresses, String emailId,
			String phoneNo) {
		this.distributorId = distributorId;
		this.name = name;
		this.addresses = addresses;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
	}

	public String getDistributorId() {
		return distributorId;
	}
	
	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DistributorAddress> getAddress() {
		return addresses;
	}

	public void setAddress(List<DistributorAddress> addresses) {
		this.addresses = addresses;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	@Override
	public String toString() {
		return "Distributor [distributorId=" + distributorId + ", name=" + name + ", addresses=" + addresses
				+ ", emailId=" + emailId + ", phoneNo=" + phoneNo + "]";
	}

}
