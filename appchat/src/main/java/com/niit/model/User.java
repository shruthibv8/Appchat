package com.niit.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User
{
@Id
String username;
String password;
String customerName;
String emailId;
String role;
String status;
String isOnline;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getIsOnline() {
	return isOnline;
}
public void setIsOnline(String isOnline) {
	this.isOnline = isOnline;
}



}
