package com.tutorial.hibernate.demo03;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity01.Course01;
import com.tutorial.hibernate.demo.entity01.Instructor01;
import com.tutorial.hibernate.demo.entity01.InstructorDetail01;

public class CreateInstructorDemo {
	
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
			Instructor01 tempInstructor = new Instructor01("Mairelys", "Rondon", "mairelys@email.com");
			
			InstructorDetail01 tempInstructorDetail = new InstructorDetail01(
						"http://www.instagram.com",
						"See gossip"
					);
			
			// Associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			// Start a transaction
			session.beginTransaction();
			
			// Save the instructor
			/**
			 * Note: this will also save the details object
			 * because CascadeType.ALL
			 */
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);
			
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
