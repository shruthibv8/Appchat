package com.niit.dbconfig;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.niit.model.Blog;
import com.niit.model.BlogComment;
import com.niit.model.Forum;
import com.niit.model.ForumComment;
import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.User;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.niit")
public class DBConfiguration 
{

	//Data Source Object
	public DataSource getDataSource()
	{
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/project2");
		dataSource.setUsername("SHRUTHI");
		dataSource.setPassword("SHRUTHI");
		System.out.println("DataSource Created");
		return dataSource;
	}
	//SessionFactory Bean
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory()
	{
		Properties hibernateProp=new Properties();
		hibernateProp.put("hibernate.hbm2ddl.auto","update");
		hibernateProp.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
		LocalSessionFactoryBuilder sessionFactoryBuilder=new LocalSessionFactoryBuilder(getDataSource());
		sessionFactoryBuilder.addProperties(hibernateProp);
		
	   sessionFactoryBuilder.addAnnotatedClass(Blog.class);
	   sessionFactoryBuilder.addAnnotatedClass(Forum.class);
	   sessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
	   sessionFactoryBuilder.addAnnotatedClass(ForumComment.class);
	   sessionFactoryBuilder.addAnnotatedClass(User.class);
	   sessionFactoryBuilder.addAnnotatedClass(Job.class);
	   sessionFactoryBuilder.addAnnotatedClass(Friend.class);
		
		SessionFactory sessionFactory=sessionFactoryBuilder.buildSessionFactory();
		System.out.println("SessionFactory Object");
		return sessionFactory;
	}
	@Bean
	
	public HibernateTransactionManager getHibernateTransactionManager(SessionFactory sessionFactory)
	{
		System.out.println("Hibernate Transaction manager object created");
		return new HibernateTransactionManager(sessionFactory);
	}
}
