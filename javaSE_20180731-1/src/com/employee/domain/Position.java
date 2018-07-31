package com.employee.domain;

// 직위정보 자료형 클래스
public class Position {

	// 직위번호, 직위명, 최소기본급
	private String positionID, positionName;
	private int minimumPay;
	
	public Position() {
		
	}

	public Position(String positionID, String positionName, int minimumPay) {
		this.positionID = positionID;
		this.positionName = positionName;
		this.minimumPay = minimumPay;
	}

	public String getPositionID() {
		return this.positionID;
	}

	public void setPositionID(String positionID) {
		this.positionID = positionID;
	}

	public String getPositionName() {
		return this.positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public int getMinimumPay() {
		return this.minimumPay;
	}

	public void setMinimumPay(int minimumPay) {
		this.minimumPay = minimumPay;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s", this.getPositionID(), this.getPositionName(), this.getMinimumPay());
	}
	
	
	
	
}
