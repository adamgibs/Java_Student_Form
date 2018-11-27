package edu.ec.session7.model;

/**
 * Model Class that contains fields and getters and setters for the Student's First
 * and last names, student id and a middle initial.
 * @author adamg
 *
 */
public class Student {
	private int id;
	private String firstName;
	private String lastName;
	private String middleInitial;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleInitial() {
		return middleInitial;
	}
	public void setMiddleInitial(String middleInitial) {
		this.middleInitial = middleInitial;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		
	}
	
	@Override
	public String toString() {
		return "\n" + getId() + "\t" + getFirstName() + "\t" + getMiddleInitial() + "\t" + getLastName();
	}
	
}
