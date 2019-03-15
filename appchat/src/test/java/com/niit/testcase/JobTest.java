package com.niit.testcase;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.dao.JobDAO;
import com.niit.model.Job;

public class JobTest
{
	static JobDAO jobDAO;

	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		jobDAO = (JobDAO) context.getBean("jobDAO");
	}
	
	@Ignore
	@Test
	public void addJobTest() {
		Job job = new Job();
		
		job.setJobId(21);
		job.setDesignation("Analyst");
		job.setJobDesc("Heads the analyst team");
		job.setQualification("masters in data analytics");
		job.setStatus("Open");
		
		assertTrue("Problem adding job", jobDAO.addJob(job));
	}

}
