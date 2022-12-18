package com.simplilearn.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.simplilearn.entity.Address;
import com.simplilearn.entity.Course;
import com.simplilearn.entity.PhoneNumber;
import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("add-student.html").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// STEP 1: Populate Properties
		Student student = populateStudent(request);
		
		// STEP 2: Gets session object
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();
		
		// STEP 3: Persist object through hibernate
		Transaction txn = session.beginTransaction();
		session.save(student);
		txn.commit();
		
		request.getRequestDispatcher("student-add-success.html").forward(request, response);
		
	}

	private Student populateStudent(HttpServletRequest request) {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		String phone1 = request.getParameter("phone_1");
		String phone_type1= request.getParameter("type1");
		
		String phone2 = request.getParameter("phone_2");
		String phone_type2= request.getParameter("type2");
		
		String phone3 = request.getParameter("phone_3");
		String phone_type3= request.getParameter("type3");
		
		String course1 = request.getParameter("course_1");
		String courseType1=request.getParameter("courseType_1");
		
		String course2 = request.getParameter("course_2");
		String courseType2=request.getParameter("courseType_2");
		
		String course3 = request.getParameter("course_3");
		String courseType3=request.getParameter("courseType_3");
		

		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		
		Student student = new Student();
		List<Student> students = new ArrayList<>();
		students.add(student);
		List<Course> courses = new ArrayList<>();
		List<PhoneNumber> phones = new ArrayList<PhoneNumber>();
		
		// Populate PhoneNumbers
		PhoneNumber phoneNumber1 = new PhoneNumber();
		phoneNumber1.setPhoneNumber(phone1);
		phoneNumber1.setPhoneType(phone_type1);
		phoneNumber1.setStudent(student);
		phones.add(phoneNumber1);
		
		PhoneNumber phoneNumber2 = new PhoneNumber();
		phoneNumber2.setPhoneNumber(phone2);
		phoneNumber2.setPhoneType(phone_type2);
		phoneNumber2.setStudent(student);
		phones.add(phoneNumber2);
		
		
		PhoneNumber phoneNumber3 = new PhoneNumber();
		phoneNumber3.setPhoneNumber(phone3);
		phoneNumber3.setPhoneType(phone_type3);
		phoneNumber3.setStudent(student);
		phones.add(phoneNumber3);
		
		// Populate Courses
		Course c1 = new Course();
		c1.setCourseName(course1);
		c1.setCourseType(courseType1);
		c1.setStudents(students);
		courses.add(c1);
		
		Course c2 = new Course();
		c2.setCourseName(course2);
		c2.setCourseType(courseType2);
		c2.setStudents(students);
		courses.add(c2);
		
		Course c3 = new Course();
		c3.setCourseName(course3);
		c3.setCourseType(courseType3);
		c3.setStudents(students);
		courses.add(c3);
		
		// Populate Address Object
		Address address = new Address();
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		
		student.setFname(fname);
		student.setLname(lname);
		student.setPhones(phones);
		student.setCourses(courses);
		student.setAddress(address);
		
		return student;
	}

}
