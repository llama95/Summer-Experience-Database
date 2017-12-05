import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneLayout;

/**
 * New and updated GUI with GridBagLayOut and up to date (09/17/2017) Experience class usage.
 * @author Nick
 *
 */
public class SummerExperienceGUI
{
	final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
	
    //Components as global variables for easy identification.
    private JPanel mainPane;
	private JScrollPane scrollPane;  
	private JFrame masterFrame; 
	private JTextArea outputText;
	
	
	//Selection Tools: public so that command objects can get and update status. 
	public JComboBox stateList ;
	public JComboBox studentStandingList;
	public JComboBox compensationList;
	public JComboBox industryList;
	public JComboBox hoursList;
	public JCheckBox internationalBox; 
	public JCheckBox internshipBox ;
	public JButton searchButton;
	public JButton restorePreviousSearchButton;
	public JButton resetButton;
	private JTable fullTable;
	private JTable currentDisplayedTable;
	
	//Stack for commands
	private Stack<guiCommand> commands = new Stack<guiCommand>();
	
	
	//public for use in command objects.
	public static String INTERNATIONAL_STRING = "Yes";
	public static String INTERNSHIP_STRING = "Yes";
	
	//ColorScheme below
	private Color colorOne = convertFXColorToSwingColor(javafx.scene.paint.Color.BLACK); //Dark
	private Color colorTwo = convertFXColorToSwingColor(javafx.scene.paint.Color.GOLDENROD); //Light
	private Color colorThree = convertFXColorToSwingColor(javafx.scene.paint.Color.DARKGOLDENROD);
	private Color colorFour = convertFXColorToSwingColor(javafx.scene.paint.Color.GOLD);
	private Color colorFive = convertFXColorToSwingColor(javafx.scene.paint.Color.GHOSTWHITE);
	private Color fontColor = Color.BLACK;
	
	
	//Instance of utility class
	
	private GuiFileIO guiIO = new GuiFileIO();
	private TableBuilder tableBuilder = new TableBuilder();
	
	//List of experiences that the GUI uses
	ArrayList<Experience> experiences = new ArrayList<Experience>();
	
	
	
	
	public SummerExperienceGUI(ArrayList<Experience> experiences)
	{
		this.experiences = experiences;
	}
	
	/**
	 * This method gets the selected options of the GUI and returns them as a string.
	 * @param state
	 * @param standing
	 * @param compensationList
	 * @param industryList
	 * @param hoursList
	 * @param internationalBox
	 * @param internshipBox
	 * @return
	 */
	
	
	public void refreshGui()
	{
		mainPane.revalidate();
		mainPane.setVisible(true);
		masterFrame.validate();
	}
	public String experienceObjectListToString(ArrayList<Experience> experiences)
	{
		String experiencesString = "";
		for(Experience temp: experiences)
		{
			experiencesString = experiencesString + temp.toString() +"\n";
		}
		
		
		return experiencesString;
	}
	
	/**
	 * This method gets the selected options of the GUI and returns them as an ArrayList of Strings.
	 * @param state
	 * @param standing
	 * @param compensationList
	 * @param industryList
	 * @param hoursList
	 * @param internationalBox
	 * @param internshipBox
	 * @return
	 */

	
	public void displayTable() 
	{
		
	}
	
	public ArrayList<Experience> filterDB(ArrayList<Experience> allExperiences, ArrayList<String> criteria) {
		
		ArrayList<Experience> matchingExperiences = new ArrayList<Experience>();
		matchingExperiences.addAll(allExperiences);
		ArrayList<Experience> valuesToRemove = new ArrayList<Experience>();
		String state = criteria.get(2);
		String industry = criteria.get(5);
		String year= criteria.get(3);
		String hours = criteria.get(6);
		String paid = criteria.get(4);
		String visa = criteria.get(0);
		
		for(Experience e : matchingExperiences) {
			if( state != null && !state.equals(e.getState())) {
				valuesToRemove.add(e);
			} 
			if( industry != null && !industry.equals(e.getNatureOfWork())) {
				valuesToRemove.add(e);
			} 
			if( year != null && !year.equals(e.getStanding())) {
				valuesToRemove.add(e);
			} 
			if( hours != null && !hours.equals(e.getHoursPerWeek())) {
				valuesToRemove.add(e);
			} 
			if( paid != null && !paid.equals(e.getCompensation())) {
				valuesToRemove.add(e);
			} 
			if( visa != null && !visa.equals(e.getInternational())) {
				valuesToRemove.add(e);
			} 
		}
		matchingExperiences.removeAll(valuesToRemove);
		System.out.println(matchingExperiences.isEmpty());
		return matchingExperiences;
	}
	
	
	/**
	 * This method creates and formats the GUI.
	 */
	public void createGUI() 
	{
		
		masterFrame = new JFrame("Database Search");
		masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		masterFrame.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		masterFrame.setBounds(0, 0, 1200, 900);
	
		mainPane = new JPanel();
		mainPane.setBorder(BorderFactory.createLineBorder(colorThree));
		mainPane.setLayout(new GridBagLayout());
		masterFrame.setContentPane(mainPane);
		
		//From the GridBagLayoutDemo
		GridBagConstraints c = new GridBagConstraints();
		if (shouldFill) 
		{
		c.fill = GridBagConstraints.HORIZONTAL;
		}
		c.insets = new Insets(2,2,2,2);
		//Main background color
		mainPane.setBackground(colorTwo);
		
		
		//Below are all of the filters, checkBoxes, and Buttons.
		//__________________________________________________________________________________________________________
		String[] StateFilter =  guiIO.getFileLinesAsStringArray("States.txt");
		stateList = new JComboBox(StateFilter);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		mainPane.add(stateList, c);
		
		String[] studentStandingFilter = { "Select Year" ,"Freshman", "Sophomore", "Junior"};
		studentStandingList = new JComboBox(studentStandingFilter);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 0;
		mainPane.add(studentStandingList, c);
		
		String[] compensationFilter = { "Select Financial Compensation", "Unpaid", "Paid Hourly Wage", "Stipend"};
		compensationList = new JComboBox(compensationFilter);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 0;
		mainPane.add(compensationList, c);
		
		String[] industryFilter = { "Select Industry", "Arts, Media and Communications","Community Organizations/Non-Profits"
				,"Consulting, Management and Human Resources","Education", "Engineering and Technology","Environment and Sustainability"
				, "Finance, Real Estate and Insurance", "Healthcare","Public Policy, Government and Law","Scientific Research"
				,"Sports and Outdoor Recreation", "Other"};
		industryList = new JComboBox(industryFilter);
		c.weightx = 0.5; 
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 0;
		mainPane.add(industryList, c);
		
		String[] hoursFilter = {"Select Hours/Week",  "Under 15", "15-29", "30+"};
		hoursList = new JComboBox(hoursFilter);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		mainPane.add(hoursList, c);
		
		internationalBox = new JCheckBox("International Students");
		internationalBox.setMnemonic(KeyEvent.VK_C); 
		internationalBox.setSelected(false);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		mainPane.add(internationalBox, c);
		
		internshipBox = new JCheckBox("Internships only");
		internshipBox.setMnemonic(KeyEvent.VK_C); 
		internshipBox.setSelected(false);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		mainPane.add(internshipBox, c);
	
		searchButton = new JButton("Search");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 3;
		c.gridy = 1;
		mainPane.add(searchButton, c);
		
		restorePreviousSearchButton = new JButton("Restore Previous Search");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 1;
		mainPane.add(restorePreviousSearchButton, c);
		
		resetButton = new JButton("Reset Criteria");
		c.weightx = 0.5;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 4;
		c.gridy = 0;
		mainPane.add(resetButton, c);
		
		//__________________________________________________________________________________________________________

		//Sets up the Text are where output will go.
		outputText = new JTextArea("");
		outputText.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		//color of text box
		outputText.setBackground(Color.WHITE);
		//color of writing
		outputText.setForeground(fontColor);
		outputText.setBounds(0,0,800,1000);
		
		//Setting up the scroll pane 
		scrollPane = new JScrollPane(outputText);
		scrollPane.setBorder(BorderFactory.createBevelBorder(3,colorThree, colorFour));
		//ScrollPane color
		scrollPane.setBackground(colorTwo);
		scrollPane.setVisible(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 100;    
		c.ipadx = 100;
		c.weightx = 0.0;
		c.gridwidth = 18;
		c.gridheight = 18;
		c.gridx = 0;
		c.gridy = 2;
		mainPane.add(scrollPane, c);
		mainPane.revalidate();
		mainPane.setVisible(true);
		masterFrame.validate();
		
		//Initilize table as full table
		fullTable = tableBuilder.getFullTable();
		fullTable.setBackground(colorFive);
		fullTable.setForeground(fontColor);
		scrollPane.setViewportView(fullTable);
		
		restorePreviousSearchButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(!commands.isEmpty())
				{
					SearchCommand previousSearch = (SearchCommand) commands.pop();
					String search = previousSearch.getSearchCriteria();
					outputText.setText(search);
					previousSearch.undoCommand();
					refreshGui();
					
				}
				else
				{
					//Not printing temporary error string into text area for whatever reason. Can't figure it out. 
					
					System.out.println("HERE");
			
					String current = outputText.getText();
					outputText.setText("No last search to restore.");
					scrollPane.setViewportView(outputText);
					refreshGui();
					
					try
					{
						refreshGui();
						Thread.sleep(1500);
					}
					catch(Exception ex)
					{
						System.out.print("Error in pause: " + ex +"\n" + "Moving on.");
					}
					scrollPane.setViewportView(fullTable);
					refreshGui();
				}
				
			}
		} );
	
		resetButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				stateList.setSelectedIndex(0);
				studentStandingList.setSelectedIndex(0);
				compensationList.setSelectedIndex(0);
				industryList.setSelectedIndex(0);
				hoursList.setSelectedIndex(0);
				internationalBox.setSelected(false);
				internshipBox.setSelected(false);
				refreshGui();
				
			}
		} );
		
		searchButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				scrollPane.setViewportView(outputText);
				SearchCommand searchCommand = new SearchCommand(stateList, studentStandingList, compensationList, industryList, hoursList, internationalBox, internshipBox);
				commands.push(searchCommand);
				String search = searchCommand.getSearchCriteria();
				outputText.setText(search);
				refreshGui();
				
			}
		} );
		
		refreshGui();
		masterFrame.setVisible(true);
			
	}

	/**
	 * converts javafx colors to colors usable by swing.
	 * @param fxColor
	 * @return
	 */
	public Color convertFXColorToSwingColor(javafx.scene.paint.Color fxColor)
	{
		java.awt.Color awtColor = new java.awt.Color((float) fxColor.getRed(),
		                                             (float) fxColor.getGreen(),
		                                             (float) fxColor.getBlue(),
		                                             (float) fxColor.getOpacity());
		return awtColor;
	}

}
	