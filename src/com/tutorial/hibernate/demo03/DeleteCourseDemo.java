package com.tutorial.hibernate.demo03;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity01.Course01;
import com.tutorial.hibernate.demo.entity01.Instructor01;
import com.tutorial.hibernate.demo.entity01.InstructorDetail01;

public class DeleteCourseDemo {
	
	public static void main(String[] args) {
		
		// Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate01.cfg.xml")
				.addAnnotatedClass(Instructor01.class)
				.addAnnotatedClass(InstructorDetail01.class)
				.addAnnotatedClass(Course01.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {
			// Start a transaction
			session.beginTransaction();
		
			// Get a course
			int theId = 10;
			Course01 tempCourse = session.get(Course01.class, theId);
			
			// Delete a course
			System.out.println("Deleting course: " + tempCourse);
			session.delete(tempCourse);
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
			
		} finally {
			// add clean up code
			session.close();
			
			factory.close();
		}
	}

}
