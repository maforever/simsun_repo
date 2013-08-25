package com.zhaori.simsun.model;

public class Payee {

	private Integer payee_id;
	private String payee_name;
	private String payee_account;
	private String info;
	private String app_username;
	
	public Payee(){};
	
	
	public Payee(String payee_name, String payee_account, String info,
			String app_username) {
		super();
		this.payee_name = payee_name;
		this.payee_account = payee_account;
		this.info = info;
		this.app_username = app_username;
	}


	public Integer getPayee_id() {
		return payee_id;
	}
	public void setPayee_id(Integer payee_id) {
		this.payee_id = payee_id;
	}
	public String getPayee_name() {
		return payee_name;
	}
	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}
	public String getPayee_account() {
		return payee_account;
	}
	public void setPayee_account(String payee_account) {
		this.payee_account = payee_account;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getApp_username() {
		return app_username;
	}
	public void setApp_username(String app_username) {
		this.app_username = app_username;
	}
	@Override
	public String toString() {
		return "Payee [payee_id=" + payee_id + ", payee_name=" + payee_name
				+ ", payee_account=" + payee_account + ", info=" + info
				+ ", app_username=" + app_username + "]";
	}
	
	
	
}
