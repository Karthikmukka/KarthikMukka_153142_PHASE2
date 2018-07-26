package com.cg.mypaymentapp.beans;

import java.math.BigDecimal;
import java.util.Date;

public class Transactions 
{
	private String mobileNo;
	private String transactionType;
	private Date date;
	private BigDecimal amount;
	public Transactions() {
		super();
	}
	public Transactions(String mobileNo, String transactionType, Date date, BigDecimal amount) {
		super();
		this.mobileNo = mobileNo;
		this.transactionType = transactionType;
		this.date = date;
		this.amount = amount;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Transactions [mobileNo=" + mobileNo + ", transactionType=" + transactionType + ", date=" + date
				+ ", amount=" + amount + "]";
	}
	
	
	
}
