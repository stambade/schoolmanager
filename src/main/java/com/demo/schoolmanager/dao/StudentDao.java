package com.demo.schoolmanager.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.schoolmanager.entities.StudentVO;


/**
 * we can make interface-Class hierarchy if needed for dao class. Also, I was
 * told to design dao skeleton only, so not writing separate service layer
 * calling dao from main class directly
 */
@Component
public class StudentDao {

	// session factory object
	@Autowired(required = true)
	SessionFactory sessionFactory;

	public final static Logger logger = Logger.getLogger(StudentDao.class);

	public Long save(StudentVO s) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		Long id = 0l;
		try {
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			id = (Long) session.save(s);
			session.getTransaction().commit();
		} catch (HibernateException he) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			throw new Exception(he.getMessage());
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return id;
	}

	public StudentVO getById(Serializable id) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		StudentVO student = null;
		try {
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			student = (StudentVO) session.get(StudentVO.class, id);
			student.getCourses(); // if needed only
			session.getTransaction().commit();
		} catch (HibernateException he) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			throw new Exception(he.getMessage());
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return student;

	}

	public void deleteById(Serializable id) throws Exception {
		Session session = null;
		StudentVO obj = null;
		try {
			session = sessionFactory.openSession();
			session.getTransaction().begin();
			obj = (StudentVO) session.get(StudentVO.class, id);

			if (obj != null) {
				session.delete(obj);
			}
			session.getTransaction().commit();
		} catch (HibernateException he) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			throw new Exception(he.getMessage());
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<StudentVO> getAllByCourseName(String courseName, boolean registered) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		List<Object> objects = null;
		List<StudentVO> students = null;
		try {
			session = sessionFactory.openSession();
			session.getTransaction().begin();

			String hql = registered
					? "From StudentVO as student left join student.courses as course where course.courseName=:courseName"
					: "From StudentVO as student left join student.courses as course where course.courseName!=:courseName";
			objects = session.createQuery(hql).setParameter("courseName", courseName).list();

			// if list not null
			students = new ArrayList<StudentVO>();
			for (Object obj : objects) {
				StudentVO s = (StudentVO) obj;
				s.getCourses(); // if needed only
				students.add(s);
			}

			session.getTransaction().commit();
		} catch (HibernateException he) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
			}
			throw new Exception(he.getMessage());
		}

		finally {
			if (session != null) {
				session.close();
			}
		}
		return students;
	}
}