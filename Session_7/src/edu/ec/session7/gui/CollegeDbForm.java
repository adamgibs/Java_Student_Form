package edu.ec.session7.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import edu.ec.session7.dao.StudentsJdbcDao;
import edu.ec.session7.model.*;



/**
 * This is the view of Application the allows the user to view DB data and to add
 * new data via fields.
 * @author adamg
 *
 */
public class CollegeDbForm extends JFrame {
	private JPanel topPanel;
	private JPanel mainPanel;
	private JButton btnLoad;
	private JButton btnAdd;
	private JButton btnSave;
	private JLabel lblFirstName;
	private JTextField txtFirstName;
	private JLabel lblMiddleInitial;
	private JTextField txtMiddleInitial;
	
	private JLabel lblLastName;
	private JTextField txtLastName;
	private JPanel resultPanel;
	private JTextArea txtResultArea;
	private JScrollPane resultScrollPane;
	private List<Student> originalStudentList = new ArrayList<Student>();
	private List<Student> newStudentList = new ArrayList<Student>();
	private StudentsJdbcDao dao =new StudentsJdbcDao();
	
	private StudentsJdbcDao StudentsDao = new StudentsJdbcDao();

	public CollegeDbForm() {
		super("Student Record Form");
		init();
		setup();
		addListeners();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 150, 600, 300);
		validate();
		setVisible(true);
	}
	private void init() {
		topPanel = new JPanel(new FlowLayout()); //panel where buttons will reside
		mainPanel = new JPanel(new GridLayout(0, 2)); //panel with labels and text fields for info 2 columns and rows as needed

		resultPanel = new JPanel(new GridLayout(1,1));
		txtResultArea = new JTextArea(5, 0);	
		//putting the txtResultArea into a scroll pane. This scroll pane will be put into resultPanel
		resultScrollPane = new JScrollPane(txtResultArea, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		btnLoad = new JButton("Load DB");
		btnAdd = new JButton("Add");
		btnSave = new JButton("Save");
		lblFirstName = new JLabel("First Name");
		txtFirstName = new JTextField(10);
		lblMiddleInitial = new JLabel("Middle Initial");
		txtMiddleInitial = new JTextField(10);
		lblLastName = new JLabel("Last Name");
		txtLastName = new JTextField(10);		
	}
	private void setup() {
		add(topPanel,BorderLayout.NORTH);
		getContentPane().add(mainPanel,BorderLayout.CENTER);
		add(resultPanel, BorderLayout.SOUTH);
		resultPanel.add(resultScrollPane);
		txtResultArea.setText("");
		topPanel.add(btnAdd);
		topPanel.add(btnLoad);
		topPanel.add(btnSave);
		mainPanel.add(lblFirstName);
		mainPanel.add(txtFirstName);
		mainPanel.add(lblMiddleInitial);
		mainPanel.add(txtMiddleInitial);
		mainPanel.add(lblLastName);
		mainPanel.add(txtLastName);

	}
	private void addListeners() {
		
		CollegeDbFormButtonHandler myHandler= new CollegeDbFormButtonHandler();
		btnAdd.addActionListener(myHandler);
		
		//Saves the data to DB Student
		btnSave.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					for(Student a : newStudentList) {
						dao.addStudent(a.getFirstName(), a.getMiddleInitial(), a.getLastName());;
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}	
				
				//Clears the newStudentList after items have been saved to the DB.
				newStudentList.clear();
			}
		});
			
		//Loads the data from the DB Students
		btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StringBuilder result = new StringBuilder();
				try {
					originalStudentList = dao.getAll();
					for(Student a : originalStudentList) {
						result.append(a);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				txtResultArea.setText(result.toString());
			}
		});
	}
		
	


	private class CollegeDbFormButtonHandler implements ActionListener{
		/**
		 * Helper method that clears the text fields
		 */
		private void clearFields() {
			txtFirstName.setText("");
			txtMiddleInitial.setText("");
			txtLastName.setText("");
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Student myStudent = new Student();
			StringBuilder errorMessage = new StringBuilder();
			
			
			
			String firstName = txtFirstName.getText();
			String middleInitial = txtMiddleInitial.getText();
			String lastName = txtLastName.getText();
		
			
			
			/**
			 * Sets firstName if valid. Otherwise, adds to error message.
			 */
			if (StudentRecordValidation.validateFirstName(firstName) == false) {
				errorMessage.append("\nStudent's first name is invalid");
			} else {
				myStudent.setFirstName(firstName);
			}
			
			/**
			 * Sets middleInitial if valid. Otherwise, adds to error message.
			 */
			if (StudentRecordValidation.validateMiddleInitial(middleInitial) == false) {
				errorMessage.append("\nStudent's middle initial is invalid");
			} else {
				myStudent.setMiddleInitial(middleInitial);
			}
			
			/**
			 * Sets lastName if valid. Otherwise, adds to error message.
			 */
			if (StudentRecordValidation.validateLastName(lastName) == false) {
				errorMessage.append("\nStudent's last name is invalid");
			} else {
				myStudent.setLastName(lastName);
			}
			
	
			/**
			 * Display an error message if there were any errors.
			 * Otherwise, add data to list and append to textArea.
			 */
			if (errorMessage.length() > 0) {
				JOptionPane.showMessageDialog(null, errorMessage, "Invalid Input", JOptionPane.ERROR_MESSAGE);
			} else {
				newStudentList.add(myStudent);
				txtResultArea.setText(txtResultArea.getText() + myStudent.toString());
				clearFields(); //clears the fields
				
			} 
				
		}
	}
	
	
	
	
	
	
	
	
	

}
