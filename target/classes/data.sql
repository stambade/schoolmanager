-- Lets assume we are using database names as school

CREATE DATABASE IF NOT EXISTS school;
 
USE school;
 
-- DROP TABLE IF EXISTS // drop student and course tables if exists


CREATE TABLE student (
  student_id int(10) NOT NULL AUTO_INCREMENT,
  student_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (student_id)
);

CREATE TABLE IF NOT EXISTS course (
  course_id int(10) NOT NULL AUTO_INCREMENT,
  course_name varchar(50) DEFAULT NULL,
  PRIMARY KEY (course_id)
);

CREATE TABLE IF NOT EXISTS student_course (
  id int(10) NOT NULL,
  student_id int(10) NOT NULL,
  course_id int(10) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_student 
  FOREIGN KEY (student_id) REFERENCES student (student_id),
  CONSTRAINT fk_course 
  FOREIGN KEY (course_id) REFERENCES course (course_id)
);


-- What if we want to record course scores? What possible changes need to be made?

-- We can add one more column to the 'student_course' table, which will be named
-- as course_score, as below [Refer data.sql file for table queries]
-- course_score varchar(50) DEFAULT NULL > we can call this third table as student_marksheet

CREATE TABLE IF NOT EXISTS student_marksheet (
  id int(10) NOT NULL,
  student_id int(10) NOT NULL,
  course_id int(10) NOT NULL,
  course_score varchar(50) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_student 
  FOREIGN KEY (student_id) REFERENCES student (student_id),
  CONSTRAINT fk_course 
  FOREIGN KEY (course_id) REFERENCES course (course_id)
);

