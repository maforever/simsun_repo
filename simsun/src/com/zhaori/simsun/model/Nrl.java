package com.zhaori.simsun.model;

import java.io.Serializable;

public class Nrl implements Serializable {
	private Integer idx;
	private String time;
	private String to_name;
	private String to_account;
	private String from_account;
	private float quantity;
	private String request_date;
	private String examine_email;
	private String examine_phone;
	private Integer examine_userid;
	private Integer request_userid;
	private Integer status = 0;
	private Integer business_type = 0;
	private String auth_key;
	private Integer notify_sms = 1;
	private Integer notify_email = 1;
	private String business_serial_num;
	private String remark;
	
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTo_name() {
		return to_name;
	}
	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}
	public String getTo_account() {
		return to_account;
	}
	public void setTo_account(String to_account) {
		this.to_account = to_account;
	}
	public String getFrom_account() {
		return from_account;
	}
	public void setFrom_account(String from_account) {
		this.from_account = from_account;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public String getRequest_date() {
		return request_date;
	}
	public void setRequest_date(String request_date) {
		this.request_date = request_date;
	}
	public String getExamine_email() {
		return examine_email;
	}
	public void setExamine_email(String examine_email) {
		this.examine_email = examine_email;
	}
	public String getExamine_phone() {
		return examine_phone;
	}
	public void setExamine_phone(String examine_phone) {
		this.examine_phone = examine_phone;
	}
	public Integer getExamine_userid() {
		return examine_userid;
	}
	public void setExamine_userid(Integer examine_userid) {
		this.examine_userid = examine_userid;
	}
	public Integer getRequest_userid() {
		return request_userid;
	}
	public void setRequest_userid(Integer request_userid) {
		this.request_userid = request_userid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getBusiness_type() {
		return business_type;
	}
	public void setBusiness_type(Integer business_type) {
		this.business_type = business_type;
	}
	public String getAuth_key() {
		return auth_key;
	}
	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}
	public Integer getNotify_sms() {
		return notify_sms;
	}
	public void setNotify_sms(Integer notify_sms) {
		this.notify_sms = notify_sms;
	}
	public Integer getNotify_email() {
		return notify_email;
	}
	public void setNotify_email(Integer notify_email) {
		this.notify_email = notify_email;
	}
	
	
	public String getBusiness_serial_num() {
		return business_serial_num;
	}
	public void setBusiness_serial_num(String business_serial_num) {
		this.business_serial_num = business_serial_num;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Nrl [idx=" + idx + ", time=" + time + ", to_name=" + to_name
				+ ", to_account=" + to_account + ", from_account="
				+ from_account + ", quantity=" + quantity + ", request_date="
				+ request_date + ", examine_email=" + examine_email
				+ ", examine_phone=" + examine_phone + ", examine_userid="
				+ examine_userid + ", request_userid=" + request_userid
				+ ", status=" + status + ", business_type=" + business_type
				+ ", auth_key=" + auth_key + ", notify_sms=" + notify_sms
				+ ", notify_email=" + notify_email + ", business_serial_num="
				+ business_serial_num + ", remark=" + remark + "]";
	}
	
	
	
	

}
