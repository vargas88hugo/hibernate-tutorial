package com.tutorial.hibernate.demo01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity.Student;

public class CreateStudentDemo {
	
	public static void main(String[] args) {
		
		// Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {
			// Create a student object
			System.out.println("Creating new student object...");
			Student tempStudent = new Student("Danie", "Querales", "daniel@correo.com");
			
			// Start a transaction
			session.beginTransaction();
			
			// Save the student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
			
		} finally {
			factory.close();
		}
	}

}
