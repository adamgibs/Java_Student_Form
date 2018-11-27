package edu.ec.session7.model;

/**
 * 
 * @author adamg
 *Description: Class that validates user inputs for ID, First Name, Middle Initial, and Last Name
 */
public class StudentRecordValidation {
	
	
	/**
	 * 
	 * @param firstName
	 * @return boolean
	 * Ensures the first name is Non-null, non-empty, Greater than 3 characters
	 * and may include "-" and " ' "
	 */
	public static boolean validateFirstName(String firstName) {
		if(firstName == null) {
			return false;
		}else {
			return firstName.matches("[A-Z][-\'a-zA-Z]{3,}");
		}
	}
	
	/**
	 * 
	 * @param middleInitial
	 * @return boolean
	 * Ensures the middle initial is nullable, cannot be empty string, 1 character.
	 */
	public static boolean validateMiddleInitial(String middleInitial ) {
		if(middleInitial == null) {
			return true;
		}else {
			return middleInitial.matches("[a-zA-Z]");
		}
	}
	
	/**
	 * 
	 * @param lastName
	 * @return boolean
	 * Ensures the last name is Non-null, non-empty, Greater than 5 characters
	 */
	public static boolean validateLastName(String lastName ) {
		if(lastName == null) {
			return false;
		}else {
			return lastName.matches("[A-Z][a-zA-Z]{5,}");
		}
	}
	
	

}
