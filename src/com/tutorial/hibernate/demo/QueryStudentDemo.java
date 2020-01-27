package com.tutorial.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.tutorial.hibernate.demo.entity.Student;

public class QueryStudentDemo {
	
	public static void main(String[] args) {
		
		// Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// Start a transaction
			session.beginTransaction();
			
			// Query the students
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			// Display the students
			displayStudents(theStudents);
			
			// Query students with last name 'Celis'
			theStudents = session.createQuery("from Student s where s.lastName='Celis'").getResultList(); 
			
			// Display the students
			System.out.println("\nStudents who have last name Celis");
			displayStudents(theStudents);
			
			// Query students with last name 'Rondon' or first name 'Hugo'
			theStudents = session.createQuery("from Student s where" +
												" s.lastName='Rondon' OR s.firstName='Hugo'")
												.getResultList();

			// Display the students
			System.out.println("\nStudents who have last name Rondon or first name Hugo");
			displayStudents(theStudents);
		
			// Query students where email LIKE '%correo.com'
			theStudents = session.createQuery("from Student s where" +
												" s.email LIKE '%correo.com'").getResultList();

			// Display the students
			System.out.println("\nStudents who email ends with correo.com");
			displayStudents(theStudents);
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!!!");
			
		} finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student tempStudent: theStudents) {
			System.out.println(tempStudent);
		}
	}

}
