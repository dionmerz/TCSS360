package TCSS360;

import java.io.Serializable;

public class Roles implements Serializable {

	/**
	 * Serial Version ID
	 */
	private static final long serialVersionUID = -2725015553416196613L;
	private Conference myConference;
	
	public Roles (Conference theConference) {
		myConference = theConference;
	}
	
	public void setConference(Conference theConference) {
		myConference = theConference;
	}
	
	public Conference getConference() {
		return myConference;
	}
}
