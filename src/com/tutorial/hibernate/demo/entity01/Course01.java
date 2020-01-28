package com.tutorial.hibernate.demo.entity01;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course01 {
	
	// Define Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="title")
	private String title;
	
	@ManyToOne(cascade = {
			CascadeType.PERSIST, 
			CascadeType.MERGE,
			CascadeType.DETACH,
			CascadeType.REFRESH
	})
	@JoinColumn(name="instructor_id")
	private Instructor01 instructor;
	
	// Define constructors
	public Course01() {
		
	}

	public Course01(String title) {
		this.title = title;
	}

	// Define getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Instructor01 getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor01 instructor) {
		this.instructor = instructor;
	}

	// Define toString
	@Override
	public String toString() {
		return "Course01 [id=" + id + ", title=" + title + "]";
	}		
	
}
