package com.student.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.dao.StudentDAO;
import com.student.model.Student;


@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDAO studentDao = new StudentDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/studentregister.jsp");
		dispatcher.forward(request, response);
		
		this.doPost(request, response);

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/register":
				registerStudent(request, response);
				break;
			case "/registerForm":
				showNewForm(request, response);
				break;
			case "/delete":
				deleteStudent(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateStudent(request, response);
				break;
			default:
				try{
					listStudent(request, response);
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}

	}
	
	private void registerStudent(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String indexNumber = request.getParameter("indexNumber");
        String faculty = request.getParameter("faculty");
        
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setIndexNumber(indexNumber);
		student.setFaculty(faculty);
        
        try {
        	studentDao.registerStudent(student);
//    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/studentdetails.jsp");
//    		dispatcher.forward(request, response);
            listStudent(request, response);
    		
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	        
	        try {
	    		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/studentregister.jsp");
	    		dispatcher.forward(request, response);
	    		
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }


		}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		Student existingStudent = studentDao.selectStudent(id);
		request.setAttribute("student", existingStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/studentregister.jsp");
		dispatcher.forward(request, response);

	}
	
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ClassNotFoundException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String indexNumber = request.getParameter("indexNumber");
        String faculty = request.getParameter("faculty");
		
        Student student = new Student();

        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setIndexNumber(indexNumber);
		student.setFaculty(faculty);

		studentDao.updateStudent(student);
		response.sendRedirect("list");
	}
	
	private void listStudent(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException {
		
		try {
			List<Student> listStudent = studentDao.SelectAllStudents();
			request.setAttribute("listStudent", listStudent);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/studentdetails.jsp");
			dispatcher.forward(request, response);
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		studentDao.deleteStudent(id);
		response.sendRedirect("list");

	}
	


	
	

}
