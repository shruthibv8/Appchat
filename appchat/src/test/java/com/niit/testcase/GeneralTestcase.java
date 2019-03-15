package com.niit.testcase;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GeneralTestcase {
public static void main(String arg[])
{
	AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
	context.scan("com.niit");
	context.refresh();
}

}
