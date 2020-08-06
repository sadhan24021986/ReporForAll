package com.csvparse.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;




@Entity
@Table(name = "daily_aua_transaction")
public class DailyAUATransaction {

	private Integer id;
	private String auaCode;
	private String asaCode;
	private Date requestDate;
	private String aggType;
	private Double aggValue;
	private Date createDate;
	private Date lastUpdateDate;
	private String createdBy;
	private String lastUpdatedBy;
	
	public DailyAUATransaction() {
		super();
	}

	public DailyAUATransaction(Integer id, String auaCode, String asaCode,
			Date requestDate, String aggType, Double aggValue, Date createDate,
			Date lastUpdateDate, String createdBy, String lastUpdatedBy) {
		super();
		this.id = id;
		this.auaCode = auaCode;
		this.asaCode = asaCode;
		this.requestDate = requestDate;
		this.aggType = aggType;
		this.aggValue = aggValue;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
		this.createdBy = createdBy;
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Column(name = "id")
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "aua_code")
	public String getAuaCode() {
		return auaCode;
	}

	public void setAuaCode(String auaCode) {
		this.auaCode = auaCode;
	}

	@Column(name = "asa_code")
	public String getAsaCode() {
		return asaCode;
	}

	public void setAsaCode(String asaCode) {
		this.asaCode = asaCode;
	}

	@Column(name = "request_date")
	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Column(name = "agg_type")
	public String getAggType() {
		return aggType;
	}

	public void setAggType(String aggType) {
		this.aggType = aggType;
	}

	@Column(name = "agg_value")
	public Double getAggValue() {
		return aggValue;
	}

	public void setAggValue(Double aggValue) {
		this.aggValue = aggValue;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "last_update_date")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "last_updated_by")
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
}
