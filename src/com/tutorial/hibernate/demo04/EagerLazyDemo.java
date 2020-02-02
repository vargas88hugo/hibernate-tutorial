package com.tutorial.hibernate.demo04;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity02.Course02;
import com.tutorial.hibernate.demo.entity02.Instructor02;
import com.tutorial.hibernate.demo.entity02.InstructorDetail02;

public class EagerLazyDemo {
	
	public static void main(String[] args) {
		
		// Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate01.cfg.xml")
				.addAnnotatedClass(Instructor02.class)
				.addAnnotatedClass(InstructorDetail02.class)
				.addAnnotatedClass(Course02.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {
			// Start a transaction
			session.beginTransaction();
			
			// Get the instructor to the data base
			int theId = 1;
			Instructor02 tempInstructor = session.get(Instructor02.class, theId);
			
			System.out.println("EagerLazy Instructor: " + tempInstructor);
			
			// Get courses for the instructor
			System.out.println("EagerLazy Courses: " + tempInstructor.getCourses());
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("EagerLazy Done!!!");
			
		} finally {
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}
