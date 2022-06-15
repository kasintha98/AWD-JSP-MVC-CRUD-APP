package com.student.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.student.model.Student;

public class StudentDAO {
	
    public int registerStudent(Student student) throws ClassNotFoundException {
        String INSERT_STUDENT_SQL = "INSERT INTO student" +"  (firstName, lastName, indexNumber, faculty) VALUES" +
            " (?, ?, ?, ?);";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/awdstudent?useSSL=false", "root", "root");
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
        	
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getIndexNumber());
            preparedStatement.setString(4, student.getFaculty());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }
    
    public List<Student> SelectAllStudents() throws ClassNotFoundException {
    	
        String SELECT_ALL_STUDENTS = "select * from student";
		List<Student> student = new ArrayList<Student>();



		Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/awdstudent?useSSL=false", "root", "root");
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String indexNumber = rs.getString("indexNumber");
				String faculty = rs.getString("faculty");
				
		        Student newStudent = new Student();
		        newStudent.setId(id);
		        newStudent.setFirstName(firstName);
		        newStudent.setLastName(lastName);
		        newStudent.setIndexNumber(indexNumber);
		        newStudent.setFaculty(faculty);
		        
				student.add(newStudent);
			}

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return student;
    }
    
	public boolean deleteStudent(int id) throws SQLException {
		
		String DELETE_STUDENT_SQL = "delete from student where id = ?;";
		boolean rowDeleted;
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/awdstudent?useSSL=false", "root", "root");
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT_SQL)) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean updateStudent(Student student) throws SQLException, ClassNotFoundException {
		
		String UPDATE_STUDENT_SQL = "update student set firstName = ?,lastName = ?, indexNumber = ?, faculty = ? where id = ?;";
		boolean rowUpdated;
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/awdstudent?useSSL=false", "root", "root");
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT_SQL)) {
			
            System.out.println(preparedStatement);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setString(3, student.getIndexNumber());
            preparedStatement.setString(4, student.getFaculty());
			preparedStatement.setInt(5, student.getId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	
	public Student selectStudent(int id) {
		
		String SELECT_STUDENT_BY_ID = "select * from student where id =?";
		Student student = null;
		// Step 1: Establishing a Connection
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/awdstudent?useSSL=false", "root", "root");
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String indexNumber = rs.getString("indexNumber");
				String faculty = rs.getString("faculty");
				
				student = new Student();
				
				student.setId(id);
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setIndexNumber(indexNumber);
				student.setFaculty(faculty);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return student;
	}

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
