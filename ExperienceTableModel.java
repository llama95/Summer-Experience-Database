
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * The following class was adapted from luv2code.com to fit into our summer
 * experience project. 
 * @author jacobnehama
 *
 */

class ExperienceTableModel extends AbstractTableModel {
	private static final int CLASS_STADNING = 0;
	private static final int INTERNATIONAL = 1;
	private static final int INTERNSHIP = 2;
	private static final int STATE = 3;
	private static final int ORG = 4;
	private static final int CITY = 5;
	private static final int NATURE = 6;
	private static final int HRS = 7;
	private static final int COMPENSATION = 8;

	private String[] columnNames = { "what is your class standing?",  "international",  "internship",  "org",  "state",
			 "city",   "nature",  "hrs",
			 "compensation",  };
	private List<Experience> experiences;

	public ExperienceTableModel(List<Experience> theExperiences) {
		experiences = theExperiences;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return experiences.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Experience tempExperiences = experiences.get(row);

		switch (col) {
		case CLASS_STADNING:
			return tempExperiences.getStanding();
		case INTERNATIONAL:
			return tempExperiences.getInternational();
		case INTERNSHIP:
			return tempExperiences.getInternship();
		case ORG:
			return tempExperiences.getOrganization();
		case STATE:
			return tempExperiences.getState();
		case CITY:
			return tempExperiences.getCity();
		case NATURE:
			return tempExperiences.getNatureOfWork();
		case HRS:
			return tempExperiences.getHoursPerWeek();
		case COMPENSATION:
			return tempExperiences.getCompensation();
		default:
			return null;
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
