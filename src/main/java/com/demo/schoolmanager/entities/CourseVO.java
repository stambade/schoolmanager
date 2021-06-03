package com.demo.schoolmanager.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class CourseVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "course_name")
	private String courseName;

	@ManyToMany(mappedBy = "courses")
	private Set<StudentVO> students;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Set<StudentVO> getStudents() {
		return students;
	}

	public void setStudents(Set<StudentVO> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "CourseVO [id=" + id + ", courseName=" + courseName + ", students=" + students + "]";
	}

}