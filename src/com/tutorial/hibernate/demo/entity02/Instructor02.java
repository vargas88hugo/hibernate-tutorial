package com.tutorial.hibernate.demo.entity02;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor")
public class Instructor02 {

	// Annotate the class as an entity and map to data base table
	
	// Define the fields
	
	// Annotate the fields with data base column names
	
	// Set up mapping to InstructorDetail entity
	
	// Create constructors
	
	// Generate getters and setters
	
	// Generate toString method
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail02 instructorDetail;

	// Added new field
	@OneToMany(fetch = FetchType.EAGER,
				mappedBy = "instructor", 
				cascade = {
						CascadeType.PERSIST,
						CascadeType.MERGE,
						CascadeType.DETACH,
						CascadeType.REFRESH
	})
	private List<Course02> courses;
	
	public Instructor02() {
	}

	public Instructor02(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public InstructorDetail02 getInstructorDetail() {
		return instructorDetail;
	}

	public void setInstructorDetail(InstructorDetail02 instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	public List<Course02> getCourses() {
		return courses;
	}

	public void setCourses(List<Course02> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", instructorDetail=" + instructorDetail + "]";
	}
	
	// Add convenience methods for bi-directional relationship
	public void add(Course02 tempCourse) {
		
		if (courses == null) {
			courses = new ArrayList<Course02>();
		}
		
		courses.add(tempCourse);
		
		tempCourse.setInstructor(this);
	}
}
