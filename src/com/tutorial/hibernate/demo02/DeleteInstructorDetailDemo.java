package com.tutorial.hibernate.demo02;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity.Instructor;
import com.tutorial.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {
	
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
			// Start a transaction
			session.beginTransaction();;
			
			// Get the instructor detail object
			int theId = 3;
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, theId);
			
			// Print the instructor detail object
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			// Print the associated instructor
			System.out.println("The associated instructor: " + tempInstructorDetail.getInstructor());
			
			// Now let's delete the instructor detail
			System.out.println("Deleting tempInstructorDetail: " + tempInstructorDetail);
			
			session.delete(tempInstructorDetail);
			
			// Remove the associated object reference break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
			
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
			
			factory.close();
		}
	}
}
