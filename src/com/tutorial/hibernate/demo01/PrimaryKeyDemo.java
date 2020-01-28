package com.tutorial.hibernate.demo01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {
			// Create 3 student objects
			System.out.println("Creating new student objects...");
			Student tempStudent1 = new Student("Paul", "Wall", "paulwall@email.com");
			Student tempStudent2 = new Student("Hugo", "Vargas", "hugo@email.com");
			Student tempStudent3 = new Student("Mairelys", "Rondon", "mairelys@email.com");
			
			// Start a transaction
			session.beginTransaction();
			
			// Save 3 student objects
			System.out.println("Saving the students...");
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
			
		} finally {
			factory.close();
		}
	}

}
