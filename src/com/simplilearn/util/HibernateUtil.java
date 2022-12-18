package com.simplilearn.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.simplilearn.entity.Course;
import com.simplilearn.entity.PhoneNumber;
import com.simplilearn.entity.Student;

public class HibernateUtil {

	public static SessionFactory sessionFactory = null;

	public static SessionFactory buildSessionFactory() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(Student.class).addAnnotatedClass(PhoneNumber.class).addAnnotatedClass(Course.class);
			sessionFactory = configuration.buildSessionFactory();
		}
		return sessionFactory;
	}
}
