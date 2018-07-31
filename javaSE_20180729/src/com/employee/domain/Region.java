package com.employee.domain;

// 지역 정보 자료형 클래스
public class Region {

	// 지역번호, 지역명
	private String regionID, regionName;
	
	public Region() {
		
	}

	public Region(String regionID, String regionName) {
		this.regionID = regionID;
		this.regionName = regionName;
	}

	public String getRegionID() {
		return this.regionID;
	}

	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", this.getRegionID(), this.getRegionName());
	}
	
	
}
