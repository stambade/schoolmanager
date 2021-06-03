package com.demo.schoolmanager;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.schoolmanager.dao.StudentDao;
import com.demo.schoolmanager.entities.CourseVO;
import com.demo.schoolmanager.entities.StudentVO;

/**
 * we can make interface-Class hierarchy if needed for dao class. Also, I was
 * told to design dao skeleton only, so not writing separate service layer
 * calling dao from main class directly
 */
@SpringBootApplication
public class SchoolmanagerMain implements CommandLineRunner {

	@Autowired
	StudentDao dao;

	public static void main(String[] args) {
		SpringApplication.run(SchoolmanagerMain.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("------------- Starting SchoolManager---------------");

		/** 1. add a new student along with their course registrations----------- */
		StudentVO student = new StudentVO();
		student.setStudentName("Sagar");

		CourseVO course = new CourseVO();
		course.setCourseName("Computer");

		Set<CourseVO> courses = new HashSet<CourseVO>();
		courses.add(course);
		student.setCourses(courses);

		long id = dao.save(student);

		/** 2. Delete a student------------------------- */
		dao.deleteById(id);

		/**
		 * 3. Get all students, sorted by their name, for a given course with course
		 * name as input.--------------------------------------------
		 */

		// ##### this particular logic for sorting should be in service class
		List<StudentVO> registeredStudents = dao.getAllByCourseName("Computer", true);
		Collections.sort(registeredStudents, new Comparator<StudentVO>() {

			@Override
			public int compare(StudentVO o1, StudentVO o2) {
				return o1.getStudentName().compareTo(o2.getStudentName());
			}
		});
		System.out.println(registeredStudents);
		// ##### this students should be returned from service method

		/**
		 * 4. What if we want to record course scores? What possible changes need to be
		 * made?
		 * 
		 * Approach 1 > We can create new table of StudentMarksheet and here one column
		 * will be student_id and other columns will be named as per course_<Score> for
		 * ex. computer_score
		 * 
		 * Approach 2 > This is Good approach
		 * 
		 * We can add one more column to the 'student_course' table, which will be named
		 * as course_score, as below [Refer data.sql file for table queries]
		 * course_score varchar(50) DEFAULT NULL > we can call this third table as
		 * student_marksheet as well
		 */

		/** 5. How to find all students who don’t register for a given course? */
		List<StudentVO> unregisteredStudents = dao.getAllByCourseName("Computer", false);
		System.out.println(unregisteredStudents);

	}

}
