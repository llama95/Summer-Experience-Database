import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

/**
 * Displays the online database table in our GUI
 * fetches the information from a myPHPAdmin database
 * @author Jacob and Pietro
 *
 */


public class DbQuery extends JFrame {

	private JPanel contentPane;
	private JTextField databaseTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;    
	private JTable table;
	private JComboBox filterList;
	private JLabel experienceLabel;
	private JTextArea output;
	
	//Public for implementation of reset criteria and restore previous search buttons.
	public static SummerExperienceGUI gui;

	
	ArrayList<Experience> experiences = null;
	DatabaseConverter dbFilter = new DatabaseConverter();
	

	public static void main(String[] args) {
		
	       
	        DbQuery runQuery = new DbQuery();  
	    }





	
	public DbQuery() {
		
		experiences = null;
		try
		{
	
			experiences = (ArrayList<Experience>) dbFilter.getAllExperiences();
			
			
			 
			//Just add it or print it here wherever you want in your guy
			//ExperienceTableModel model = new ExperienceTableModel(experiences);
			//set it to whatever you want
			//table.setModel(model);
			
			
			
		}
		catch (Exception exc) {
			System.out.println(exc); 
		}
		gui = new SummerExperienceGUI(experiences);
		gui.createGUI();
	}
}
		
		
		
//		setTitle("Database Search");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(400, 400, 900, 600);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(50, 50, 50, 50));
//		contentPane.setLayout(new BorderLayout(0, 0));
//		setContentPane(contentPane); 
//		
//		
//		
//		JPanel panel = new JPanel();
//		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
//		flowLayout.setAlignment(FlowLayout.LEFT);
//		contentPane.add(panel, BorderLayout.NORTH);
//		
//		
//		//JLabel enterQuery = new JLabel("Enter Query");
//		//panel.add(enterQuery);
//		
//		databaseTextField = new JTextField();
//		panel.add(databaseTextField);
//		databaseTextField.setColumns(10);
//		
//		//btnSearch = new JButton("Search");
//		//panel.add(btnSearch);
//		
//		scrollPane = new JScrollPane();
//		contentPane.add(scrollPane, BorderLayout.CENTER);
//		
//		table = new JTable();
//		scrollPane.setViewportView(table);
//		
//		output = new JTextArea(20,20);
//		panel.add(output);
//		
//		
//	
//	
//		
//		String[] filterNames = { "Select State ", "Alabama", "Alaska", "Arkansas", "California" };
//		JComboBox filterList = new JComboBox(filterNames);
//		panel.add(filterList );
//		
//
//		experienceLabel = new JLabel();
//		contentPane.add(experienceLabel);
//		
//		btnSearch = new JButton("Search");  
//		panel.add(btnSearch) ;
//		btnSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				// Get data from the text field
//
//				
//				
//				try {
//					String searchQuery = databaseTextField.getText();
//					
//					experiences = null;
//
//					if (searchQuery != null && searchQuery.trim().length() > 0) {
//						experiences = FilterDb.searchExperiences();
//					} else {
//						experiences = FilterDb.getAllExperiences();
//					}
//					
//					 
//					//Just add it or print it here wherever you want in your guy
//					//ExperienceTableModel model = new ExperienceTableModel(experiences);
//					//set it to whatever you want
//					//table.setModel(model);
//					
//					experiences = FilterDb.getAllExperiences();
//					for (Experience temp : experiences) {
//						output.append(temp.toString());
//						
//	
//					}
//					
//				} catch (Exception exc) {
//					JOptionPane.showMessageDialog(DbQuery.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
//				}
//				}
//			});
//		//panel.add(btnSearch);
//		setVisible(true);
