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
	
	/**
	 * Checks to see if a Role is the same as another. 
	 * @param theOther Manuscript object
	 * @returns a boolean
	 */
	@Override
	public boolean equals(Object theOther) {
		
		boolean equal = false;
		@SuppressWarnings("unused")
		Roles other;
		
		if ((theOther instanceof Roles)){
			other = (Roles) theOther;
			if (this.getClass().getSimpleName().equals(theOther.getClass().getSimpleName()) 
					&& this.myConference.getName().equals(((Roles) theOther).getConference().getName())) {
				equal = true;
			}
		}
		return equal;
	}
}
