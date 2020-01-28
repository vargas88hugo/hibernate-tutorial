package com.tutorial.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity.Instructor;
import com.tutorial.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {
	
	public static void main(String[] args) {
		
		// Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {
			// Create the objects
			/*
			Instructor tempInstructor = new Instructor("Hugo", "Vargas", "hugo@email.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail(
						"http://www.youtube.com",
						"See videos"
					);
			*/
			Instructor tempInstructor = new Instructor("Mairelys", "Rondon", "mairelys@email.com");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail(
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
			factory.close();
		}
	}

}
