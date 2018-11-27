package edu.ec.session7.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ec.session7.model.Student;

public class StudentsJdbcDao extends AbstractJdbcCollegeDao{
	
	/**
	 * Adds a new student to the database
	 * @param firstName
	 * @param middleInitial
	 * @param lastName
	 */
	public void addStudent(String firstName, String middleInitial, String lastName) {
		Connection conn = null;
		PreparedStatement pStatement = null;
		
		try {
			conn = getConnection();
			pStatement = conn.prepareStatement("INSERT INTO student (firstName, middleInitial, lastName) VALUES(?,?,?)");
			pStatement.setString(1, firstName);
			pStatement.setString(2, middleInitial);
			pStatement.setString(3, lastName);
			pStatement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Returns a list of all students in Database
	 * @return
	 * @throws Exception
	 */
	public List<Student> getAll() throws Exception{
		List<Student> studentList = new ArrayList<Student>();
		Connection conn = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			conn = getConnection();
			statement = conn.createStatement();
			resultSet = statement.executeQuery("Select * from student");
			while(resultSet.next()) {
				int id = resultSet.getInt("ID");
				String fName = resultSet.getString("firstName");
				String mInit = resultSet.getString("middleInitial");
				String lName = resultSet.getString("lastName");
				Student student = new Student();
				student.setFirstName(fName);
				student.setMiddleInitial(mInit);
				student.setLastName(lName);
				student.setId(id);
				studentList.add(student);
			}
		}finally {
			releaseResources(conn, statement, resultSet);
		}
		
		return studentList;
		
	}

}
