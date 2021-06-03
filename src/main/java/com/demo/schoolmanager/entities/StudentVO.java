package com.demo.schoolmanager.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "student_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "student_name")
	private String studentName;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "student_course", joinColumns = { @JoinColumn(name = "student_id") }, inverseJoinColumns = {
			@JoinColumn(name = "course_id") })
	private Set<CourseVO> courses;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Set<CourseVO> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseVO> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "StudentVO [id=" + id + ", studentName=" + studentName + ", courses=" + courses + "]";
	}
}