package edu.ec.session7.gui;

import javax.swing.SwingUtilities;

/**
 * 
 * @author adamg
 * 10/13/2018
 * Description: Driver class for the application
 *
 */
public class StudentApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new CollegeDbForm();

			}
		});

	}

}
