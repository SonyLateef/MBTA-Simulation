/**
* <This class represents a Rider on the red line. 
* A rider has an ID, starting station, destination station, and a direction.>
* Known Bugs: <“None”>
*
* @author Sulaiman Lateef
* <sulaimanlateef@brandeis.edu>
* <October 17, 2023>
* COSI 21A PA1
*/

package main;

import java.util.Objects;

public class Rider {

	private final String riderID;
	private final String startingStation;
	private final String destinationStation;
	private boolean north;

	/**
     * Constructs a Rider with an ID as well as starting and ending stations.
     *
     * @param riderID           The rider ID.
     * @param startingStation   The station where the rider starts.
     * @param destinationStation The destination station.
     */
	
	public Rider(String riderID, String startingStation, String destinationStation) {
		this.riderID = riderID;
		this.startingStation = startingStation;
		this.destinationStation = destinationStation;
	}
	
	/**
     * returns the name of this Rider’s starting station.
     *
     * @return The name of this Rider’s starting station..
     */
	
	public String getStarting() {
		return startingStation;
	}
	
	/**
     * returns the name of this Rider’s ending station.
     *
     * @return The name of this Rider’s ending station.
     */
	
	public String getDestination() {
		return destinationStation;
	}
	
	/**
     * returns this Rider’s ID.
     *
     * @return The rider's ID.
     */
	
	public String getRiderID() {
		return riderID;
	}
	
	/**
     * returns true if this Rider is northbound. Else, false.
     *
     * @return returns true if this Rider is northbound. Else, false.
     */
	
	public boolean goingNorth() {	
		return north;
	}
	
	/**
     * Swaps the Rider’s current direction.
     */
	
	public void swapDirection() {
		north = !north;
	}
	
	
	/**
     * returns a String representation of this Rider.
     *
     * @return A string representation of the rider.
     */
	
	@Override
	public String toString() {
		return riderID + ", " + destinationStation;
	}

	/**
     * checks if this Rider is equal to another Object based on ID.
     *
     * @param obj The object to compare with this rider.
     * @return True if the riders have the same rider ID, false otherwise.
     */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Rider))
			return false;
		Rider other = (Rider) obj;
		return Objects.equals(riderID, other.riderID);
	}

	/**
	 * used to return a hash code value for the Rider object.
	 * @return A hash code value for the Rider object.
	 */
	
	@Override
	public int hashCode() {
		return Objects.hash(riderID);
	}
}
