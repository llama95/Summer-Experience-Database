import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;


/**
 * Search functionality Encapsulated into a command object
 * @author Nick
 *
 */
public class SearchCommand implements guiCommand
{
	
	private String state;
	private String standing;
	private String compensation;
	private String industry;
	private String hours;
	private String international;
	private String internship;
	private boolean isInternational;
	private boolean isInternship;
	//search command could have methods that return arrayLists, strings. 
	//Assign stuff in constructor? yes
	
	public SearchCommand(JComboBox state, JComboBox standing, JComboBox compensation, JComboBox industry, JComboBox hours, JCheckBox internationalBox, JCheckBox internshipBox)
	{ 
		this.state = (String) state.getSelectedItem();
		this.standing = (String) standing.getSelectedItem();
		this.compensation = (String) compensation.getSelectedItem();
		this.industry = (String) industry.getSelectedItem();
		this.hours = (String) hours.getSelectedItem();
		this.international = getInternationalStatus(internationalBox);
		this.internship = getInternshipStatus(internshipBox);
		this.isInternational = internationalBox.isSelected();
		this.isInternship = internshipBox.isSelected();
		
		
	}
	
	//Search might activate the filters. 
	public void doCommand() 
	{
		
	}
	
	//undo will restore the previous search and activate the filters. 
	public void undoCommand()
	{
		DbQuery.gui.stateList.setSelectedItem(state);
		DbQuery.gui.studentStandingList.setSelectedItem(standing);
		DbQuery.gui.compensationList.setSelectedItem(compensation);
		DbQuery.gui.industryList.setSelectedItem(industry);
		DbQuery.gui.hoursList.setSelectedItem(hours);
		DbQuery.gui.internationalBox.setSelected(isInternational);
		DbQuery.gui.internshipBox.setSelected(isInternship);
		
		DbQuery.gui.refreshGui();
		
	}
	
	public String getInternationalStatus(JCheckBox internationalBox)
	{
		String status;
		if(internationalBox.isSelected())
		{
			status = "Yes";
		}
		else
		{
			status = "No";
		}
		return status;
		
	}
	
	public String getInternshipStatus(JCheckBox internshipBox)
	{
		String status;
		if(internshipBox.isSelected())
		{
			status = "Yes";
		}
		else
		{
			status = "No";
		}
		return status;
	}
	
	public ArrayList<String> getCrieteriaList()
	{
		ArrayList<String> criteria = new ArrayList<String>();
		
		criteria.add(international);
		criteria.add(internship);
		criteria.add(state);
		criteria.add(standing);
		criteria.add(compensation);
		criteria.add(industry);
		criteria.add(hours);
		
		return criteria;
	}
	
	/**
	 * @returna String with the search criteria.
	 */
	public String getSearchCriteria()
	{
		String criteriaToReturn = international + ", " + internship + ", " + state + ", " + standing + ", " + compensation + 
				", " + industry + ", " + hours;
				
		return criteriaToReturn;
	}
	

}