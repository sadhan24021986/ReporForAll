package com.csvparse.util;

public class HashObject {

	private String auaCode;
	private String auaName;
	private String asaCode;
	private String asaName;
	private String requestTime;
	
	
	
	public HashObject() {
		super();
	}
	public HashObject(String auaCode, String auaName, String asaCode,
			String asaName, String requestTime) {
		super();
		this.auaCode = auaCode;
		this.auaName = auaName;
		this.asaCode = asaCode;
		this.asaName = asaName;
		this.requestTime = requestTime;
	}
	public String getAuaCode() {
		return auaCode;
	}
	public void setAuaCode(String auaCode) {
		this.auaCode = auaCode;
	}
	public String getAuaName() {
		return auaName;
	}
	public void setAuaName(String auaName) {
		this.auaName = auaName;
	}
	public String getAsaCode() {
		return asaCode;
	}
	public void setAsaCode(String asaCode) {
		this.asaCode = asaCode;
	}
	public String getAsaName() {
		return asaName;
	}
	public void setAsaName(String asaName) {
		this.asaName = asaName;
	}
	public String getRequestTime() {
		return requestTime;
	}
	
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	
	 public int hashCode()
	 {
	String finalString=this.asaCode+this.auaCode+this.asaName+this.auaName+this.requestTime;
	return finalString.hashCode();
		
	}
	@Override
	public String toString() {
		return "HashObject [auaCode=" + auaCode + ", auaName=" + auaName
				+ ", asaCode=" + asaCode + ", asaName=" + asaName
				+ ", requestTime=" + requestTime + ", getAuaCode()="
				+ getAuaCode() + ", getAuaName()=" + getAuaName()
				+ ", getAsaCode()=" + getAsaCode() + ", getAsaName()="
				+ getAsaName() + ", getRequestTime()=" + getRequestTime()
				+ ", hashCode()=" + hashCode() + "]";
	}
}
