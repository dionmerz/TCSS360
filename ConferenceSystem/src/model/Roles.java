package model;

import java.io.Serializable;

/**
 * Parent class for individual roles.
 * @author Andrew Merz, Adam Marr, Bernabe Guzman, Bincheng Li
 */
public class Roles implements Serializable {

	private static final long serialVersionUID = -2725015553416196613L;
	private Conference myConference;
	
	/**
	 * @param theConference determines which conference the role gives permissions for.
	 */
	public Roles (Conference theConference) {
		myConference = theConference;
	}
	
	/**
	 * @return returns the conference the role has permissions in.
	 */
	public Conference getConference() {
		return myConference;
	}
}
