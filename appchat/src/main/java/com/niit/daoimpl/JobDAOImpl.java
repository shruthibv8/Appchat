package com.niit.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.dao.JobDAO;
import com.niit.model.Job;

@Repository("jobDAO")
@Transactional

public class JobDAOImpl implements JobDAO 
{
	@Autowired
	SessionFactory sessionFactory;

	public boolean addJob(Job job)
	{
		try 
		{
	     sessionFactory.getCurrentSession().save(job);
		 return true;
		} 
		catch (Exception e) {
			System.out.println("Exception Arised:Adding Job"+e);
			return false;
		}
	}

	public boolean deleteJob(Job job)
	{
		try 
		{
	 sessionFactory.getCurrentSession().save(job);
	 return true;
		} 
		catch (Exception e) {
	 System.out.println("Exception Arised:Deleting Job"+e);
	return false;
		}
	}
	
	

	public boolean updateJob(Job job)
	{
		try 
		{
	    sessionFactory.getCurrentSession().save(job);
	    return true;
		} 
		catch (Exception e) 
		{
	    System.out.println("Exception Arised:Updating Job"+e);
	    return false;
		}
	}
	

	public Job getJob(int jobId)
	{
		Session session = sessionFactory.openSession();
		Job job = session.get(Job.class, jobId);
		session.close();
		return job;
	}

	public List<Job> getJobList() 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Job");
		@SuppressWarnings("unchecked")
		List<Job> listJob=(List<Job>)query.list();
		session.close();
		return listJob;
		
	}

}
