/**
* <Represents the red line railway and is made up of a list of Stations.>
* Known Bugs: <“None”>
*
* @author Sulaiman Lateef
* <sulaimanlateef@brandeis.edu>
* <October 17, 2023>
* COSI 21A PA1
*/

package main;

public class Railway {

	public DoubleLinkedList<Station> railway = new DoubleLinkedList<>();
	public String[] stationNames = new String[MAX_STATIONS];

	private static int MAX_STATIONS = 18;
	private int numStations;

	/**
	 * Constructs an empty Railway.
	 */
	public Railway() {

	}

	/**
     * Adds a Station to the Railway.
     *
     * runtime: O(1)
     * @param s The station to be added.
     */
	
	public void addStation(Station s) {
		if (numStations >= stationNames.length)
			throw new IndexOutOfBoundsException();
		stationNames[numStations++] = s.stationName();
		railway.insert(s);
	}

	/**
     * sets a Rider’s direction based on the order of the Stations in the Railway and
     * adds the Rider to the appropriate Station in the Railway.
     *
     * runtime: O(1)
     * @param r The rider to be added.
     */
	
	public void addRider(Rider r) {
		setRiderDirection(r);
		getStation(r.getStarting()).addRider(r);
	}

	/**
     * adds a Train to the appropriate Station in this Railway.
     *
     * runtime: O(1)
     * @param t The train to be added.
     */
	
	public void addTrain(Train t) {
		getStation(t.getStation()).addTrain(t);
	}

	/**
     * sets a Rider’s direction based on the Rider’s starting and ending Stations.
     * 
     * runtime: O(1)
     * @param r The rider
     */
	
	public void setRiderDirection(Rider r) {
		int startingIndex = getStationIndex(r.getStarting());
		int destinationIndex = getStationIndex(r.getDestination());
		boolean goingNorth;
		if (startingIndex < destinationIndex) {
			goingNorth = false;
		} else if (startingIndex > destinationIndex) {
			goingNorth = true;
		} else {
			throw new IllegalArgumentException("Starting and destination can't be the same: " + r);
		}
		if (goingNorth != r.goingNorth())
			r.swapDirection();
	}

	/**
     * Executes one simulation of the Railway.
     *
     * runtime: O(n)
     * @return A string containing simulation logs.
     */
	
	public String simulate() {
		StringBuilder sb = new StringBuilder();
		for (Node<Station> node = railway.getFirst(); node != null; node = node.getNext()) {
			Station s = node.getData();
			boolean firstStation = node == railway.getFirst();
			boolean lastStation = node.getNext() == null;
			sb.append(s).append("\n\n");
			if (!lastStation) {
				Train t = s.southBoardTrain();
				if (t != null) {
					String log = node.getNext().getData().addTrain(t);
					if (!log.trim().isEmpty())
						sb.append(log).append('\n');
				}
			}
			if (!firstStation) {
				Train t = s.northBoardTrain();
				if (t != null) {
					String log = node.getPrev().getData().addTrain(t);
					if (!log.trim().isEmpty())
						sb.append(log).append('\n');
				}
			}
			if (lastStation) {
				s.moveTrainSouthToNorth();
			}
			if (firstStation) {
				s.moveTrainNorthToSouth();
			}
		}
		return sb.toString();
	}

	/**
     * returns the Stations list’s String representation.
     *
     * @return A string representation of the station's list.
     */
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node<Station> node = railway.getFirst(); node != null; node = node.getNext()) {
			if (node != railway.getFirst())
				sb.append('\n');
			sb.append(node.getData());
		}
		return sb.toString();
	}
	
	/**
	 * Gets the index of a station by its name.
	 * @param name The name of the station
	 * @return The index of the station
	 * @throws IllegalArgumentException if the station with the name is not found.
	 */
	private int getStationIndex(String name) {
		for (int i = 0; i < numStations; ++i) {
			if (name.equals(stationNames[i]))
				return i;
		}
		throw new IllegalArgumentException("Station '" + name + "' not found");
	}

	/**
	 * Gets a Station object by its name.
	 *
	 * @param name The name of the station
	 * @return The Station object with the name.
	 * @throws IllegalArgumentException if the station with the name is not found.
	 */
	
	private Station getStation(String name) {
		Station s = railway.get(new Station(name));
		if (s == null)
			throw new IllegalArgumentException("Station '" + name + "' not found");
		return s;
	}
}
