package com.niit.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@SequenceGenerator(name="jobidseq",sequenceName="myjobseq")
public class Job {
@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="jobidseq")
int jobId;
String designation;
String jobDesc;
String qualification;
String status;

Date lastDate;

public int getJobId() {
	return jobId;
}

public void setJobId(int jobId) {
	this.jobId = jobId;
}

public String getDesignation() {
	return designation;
}

public void setDesignation(String designation) {
	this.designation = designation;
}

public String getJobDesc() {
	return jobDesc;
}

public void setJobDesc(String jobDesc) {
	this.jobDesc = jobDesc;
}

public String getQualification() {
	return qualification;
}

public void setQualification(String qualification) {
	this.qualification = qualification;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Date getLastDate() {
	return lastDate;
}

public void setLastDate(Date lastDate) {
	this.lastDate = lastDate;
}


}
