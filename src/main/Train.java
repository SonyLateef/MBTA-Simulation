/**
* <This class represents a Train on the red line >
* Known Bugs: <“None”>
*
* @author Sulaiman Lateef
* <sulaimanlateef@brandeis.edu>
* <October 17, 2023>
* COSI 21A PA1
*/

package main;

import java.util.Arrays;

public class Train {

	public static final int TOTAL_PASSENGERS = 10;
	public Rider[] passengers = new Rider[TOTAL_PASSENGERS];
	public int passengerIndex;

	private String currentStation;
	private boolean north;

	/**
     * constructs an empty Train at a given Station going either south (1) or north (0).
     *
     * @param currentStation current station
     * @param direction      The direction of the train
     */
	
	public Train(String currentStation, int direction) {
		this.currentStation = currentStation;
		north = direction == MBTA.NORTHBOUND;
	}

	/**
     * Checks if the train is traveling in the northbound direction.
     *
     * @return returns true if this Train is northbound. Else, return false.
     */
	
	public boolean goingNorth() {
		return north;
	}

	/**
     * swaps the Train’s direction.
     */
	public void swapDirection() {
		north = !north;
	}

	/**
     * returns a String of the current passengers on the Train.
     *
     * @return  a String of the current passengers on the Train.
     * 
     * runtime: O(n)
     */
	
	public String currentPassengers() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < passengerIndex; ++i) {
			if (i > 0)
				sb.append('\n');
			sb.append(passengers[i]);
		}
		return sb.toString();
	}

	/**
     * adds a passenger to the Train as long as the Rider is at the correct Station to enter the Train, 
     * the Train is going in the appropriate direction, 
     * and there is space on the Train.
     *
     * @param r The rider
     * @return Return true if the addition was completed. Else, return false.
     * 
     * runtime: O(1)
     */
	
	public boolean addPassenger(Rider r) {
		if (r.getStarting().equals(currentStation) && r.goingNorth() == goingNorth()
				&& hasSpaceForPassengers()) {
			passengers[passengerIndex++] = r;
			return true;
		}
		return false;
	}

	/**
     * Checks if there is space for additional passengers on the train.
     *
     * @return returns true if the Train has space for additional passengers.
     * 
     * runtime: O(1)
     */
	
	public boolean hasSpaceForPassengers() {
		return passengerIndex < passengers.length;
	}

	/**
     * Removes all of the passengers who should be exiting the Train at the Train’s current station.
     *
     * @return a String of the removed passengers
     * 
     * runtime: O(n)
     */
	
	public String disembarkPassengers() {
		StringBuilder sb = new StringBuilder();
		int j = 0;
		for (int i = 0; i < passengerIndex; ++i) {
			Rider passenger = passengers[i];
			if (passenger.getDestination().equals(currentStation)) {
				if (i > j)
					sb.append('\n');
				sb.append(passenger);
			} else {
				passengers[j++] = passenger;
			}
		}
		Arrays.fill(passengers, j, passengerIndex, null);
		passengerIndex = j;
		return sb.toString();
	}

	/**
     * Updates the name of this Train’s current station to be the name of another station.
     *
     * @param s The name of the new station
     */
	
	public void updateStation(String s) {
		currentStation = s;
	}

	/**
     * returns the name of the Train’s current Station.
     *
     * @return The name of the station
     */
	
	public String getStation() {
		return currentStation;
	}

	/**
     * returns a String representation of this Train.
     *
     * @return A string representation of the train.
     */
	@Override
	public String toString() {
		return currentStation + ", " + (north ? "North" : "South") + "\n" + currentPassengers();
	}
}
