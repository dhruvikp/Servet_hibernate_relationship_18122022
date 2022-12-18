package com.simplilearn.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.simplilearn.entity.Student;
import com.simplilearn.util.HibernateUtil;

/**
 * Servlet implementation class ReadStudentServlet
 */
@WebServlet("/read-student")
public class ReadStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadStudentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		SessionFactory sf = HibernateUtil.buildSessionFactory();
		Session session = sf.openSession();

		List<Student> students = session.createQuery("from Student").list();

		if (students != null && students.size() > 0) {
			// Write code to pulate details in HTML
			out.println("<h1> Student List :- </h1>");
			out.println("<style> table,td,th {border:2px solid red;} </style>");
			out.println("<table>");
			out.println("<tr>");
			out.println("<th> Student ID </th>");
			out.println("<th> Student FirstName </th>");
			out.println("<th> Student LastName </th>");
			out.println("<th> Student PhoneNumbers </th>");
			out.println("<th> Student Courses </th>");
			out.println("<th> Student Address </th>");
			out.println("</tr>");

			for (Student s : students) {
				out.println("<tr>");
				out.println("<td>" + s.getStudent_id() + "</td>");
				out.println("<td>" + s.getFname() + "</td>");
				out.println("<td>" + s.getLname() + "</td>");
				out.println("<td>" + s.getPhones() + "</td>");
				out.println("<td>" + s.getCourses() + "</td>");
				out.println("<td>" + s.getAddress() + "</td>");
				out.println("</tr>");
			}

			out.println("</table>");

		}
		session.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
