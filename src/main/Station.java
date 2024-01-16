/**
* <This class represents a Station on the red line. 
* It tracks which Trains and Riders are waiting to go north or south. >
* Known Bugs: <“None”>
*
* @author Sulaiman Lateef
* <sulaimanlateef@brandeis.edu>
* <October 17, 2023>
* COSI 21A PA1
*/

package main;

import java.util.Objects;

public class Station {

	private static final int CAPACITY = 20;

	public Queue<Rider> northBoundRiders = new Queue<>(CAPACITY);
	public Queue<Rider> southBoundRiders = new Queue<>(CAPACITY);
	public Queue<Train> northBoundTrains = new Queue<>(CAPACITY);
	public Queue<Train> southBoundTrains = new Queue<>(CAPACITY);
	private final String name;

	/**
     * Constructs an empty Station with a given name.
     *
     * @param name The name of the station.
     */
	
	public Station(String name) {
		this.name = name;
	}

	/**
     * adds a Rider to the appropriate Queue, depending on the Rider’s direction, 
     * as long as they should be in this Station.
     *
     * @param r The rider
     * @return Return true if this is possible and false otherwise
     * runtime: O(1)
     */
	public boolean addRider(Rider r) {
		if (!r.getStarting().equals(name))
			return false;
		Queue<Rider> q = r.goingNorth() ? northBoundRiders : southBoundRiders;
		q.enqueue(r);
		return true;
	}

	/**
     * moves a Train into this Station, removes all of the passengers who are meant to disembark 
     * at this Station, and places the Train in the appropriate Queue depending on its direction. 
     *
     * @param t The train
     * @return String that includes that some passengers were removed at this Station.
     * 
     * runtime: O(1)
     */
	public String addTrain(Train t) {
	    t.updateStation(name);
	    String passengers = t.disembarkPassengers();
	    Queue<Train> q = northBoundTrains;

	    if (!t.goingNorth()) {
	        q = southBoundTrains;
	    }

	    q.enqueue(t);

	    if (passengers.trim().isEmpty()) {
	        return "";
	    } else {
	        return name + " Disembarking Passenger\n" + passengers;
	    }
	}

	
	 /**
     * prepares a southbound Train
     *
     * @return Return the train that has been filled or null if there are no waiting southbound Trains.
     * 
     * runtime: O(n)
     */
	public Train southBoardTrain() {
		if (southBoundTrains.size() <= 0)
			return null;
		Train t = southBoundTrains.front();
		southBoundTrains.dequeue();
		while (southBoundRiders.size() > 0) {
			Rider passenger = southBoundRiders.front();
			if (!t.addPassenger(passenger))
				break;
			southBoundRiders.dequeue();
		}
		return t;
	}

	 /**
     * prepares a northbound Train
     *
     * @return Return the train that has been filled or null if there are no waiting northbound Trains.
     * 
     * runtime: O(n)
     */
	
	public Train northBoardTrain() {
		if (northBoundTrains.size() <= 0)
			return null;
		Train t = northBoundTrains.front();
		northBoundTrains.dequeue();
		while (northBoundRiders.size() > 0) {
			Rider passenger = northBoundRiders.front();
			if (!t.addPassenger(passenger))
				break;
			northBoundRiders.dequeue();
		}
		return t;
	}

	
	/**
     * changes the direction of the first waiting northbound Train and moves it to the southbound queue.
     * 
     * runtime: O(1)
     */
	
	public void moveTrainNorthToSouth() {
		if (northBoundTrains.size() > 0) {
			Train t = northBoundTrains.front();
			northBoundTrains.dequeue();
			southBoundTrains.enqueue(t);
			t.swapDirection();
		}
	}

	/**
     * changes the direction of the first waiting southbound Train and moves it to the northbound queue.
     * 
     * runtime: O(1)
     */
	
	public void moveTrainSouthToNorth() {
		if (southBoundTrains.size() > 0) {
			Train t = southBoundTrains.front();
			southBoundTrains.dequeue();
			northBoundTrains.enqueue(t);
			t.swapDirection();
		}
	}

	 /**
     * returns the string representation of the name and status of the station.
     *
     * @return A string representation of the name and status of the station.
     */
	
	@Override
	public String toString() {
		return "Station: " + name + "\n" + northBoundTrains.size() + " north-bound trains waiting\n"
				+ southBoundTrains.size() + " south-bound trains waiting\n" + northBoundRiders.size()
				+ " north-bound passengers waiting\n" + southBoundRiders.size() + " south-bound passengers waiting";
	}

	/**
	 * Gets the name of the station.
	 * @return The name of the station.
	 */
	
	public String stationName() {
		return name;
	}

	/**
	 * used to return a hash code value for the Station object.
	 * @return hash code value for the Station object.
	 */
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	/**
     * Checks if a Station is equal to some object based on name.
     *
     * @param obj object to be compared
     * @return true if the stations have the same name, else false
     */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Station))
			return false;
		Station other = (Station) obj;
		return Objects.equals(name, other.name);
	}
}
