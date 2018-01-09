package com.hospi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="user")
public class User {
	 @Id
	private long id;
	private String name;
	private String phone;
	private String addr;
	private Date createtime;
	private String tezheng;
	private int money;
	private String yiliao;
	
	
	public  User() {
		
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getTezheng() {
		return tezheng;
	}
	public void setTezheng(String tezheng) {
		this.tezheng = tezheng;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getYiliao() {
		return yiliao;
	}
	public void setYiliao(String yiliao) {
		this.yiliao = yiliao;
	}
	
	

}
