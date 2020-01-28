package com.tutorial.hibernate.demo03;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity01.Course01;
import com.tutorial.hibernate.demo.entity01.Instructor01;
import com.tutorial.hibernate.demo.entity01.InstructorDetail01;

public class CreateCoursesDemo {
	
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
			
			// Get the instructor to the data base
			int theId = 1;
			Instructor01 tempInstructor = session.get(Instructor01.class, theId);
			
			// Create some courses
			Course01 tempCourse1 = new Course01("Guitar Ultimate Guide");
			Course01 tempCourse2 = new Course01("Pintball Ultimate Guide");
			
			// Add course to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			
			// Save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
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
