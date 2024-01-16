/**
* <Contains the program’s main method and runs a simulation of a Railway..>
* Known Bugs: <“None”>
*
* @author Sulaiman Lateef
* <sulaimanlateef@brandeis.edu>
* <October 17, 2023>
* COSI 21A PA1
*/

package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MBTA {

	public static final int SOUTHBOUND = 1; // value of the southbound direction (1).
	public static final int NORTHBOUND = 0; //value of the northbound direction (0).

	static final int TIMES = 3; 
	static Railway r = new Railway();

	
	/**
	 * The main method that constructs the Railway with the Stations, Riders, and Trains 
	 * loaded from the provided text files and then run the simulation.
	 *
	 * @param args arguments
	 * @throws IOException if an error occurs while reading input files.
	 */
	
	public static void main(String[] args) throws IOException {
		initStations("redLine.txt");
		initRiders("riders.txt");
		initTrains("trains.txt");
		runSimulation();
	}

	/**
	 * This method runs the simulation using TIMES and Railway’s simulate().
	 * 
	 * runtime: O(n) 
	 */
	
	public static void runSimulation() {
		for (int i = 0; i < TIMES; ++i) {
			System.out.println("Simulating one run of MBTA.");
			String logs = r.simulate();
			System.out.println(logs);			
			System.out.println("------------\n\n");
		}
	}

	/**
	 * Constructs Trains from an input file and adds them to the Railway.
	 *
	 * runtime: O(n)
	 * @param trainsFile Input file containing train data.
	 * @throws IOException if an error occurs while reading the file.
	 */
	
	public static void initTrains(String trainsFile) throws IOException {
		try (BufferedReader br =
				new BufferedReader(new InputStreamReader(new FileInputStream(trainsFile)))) {
			String line;
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String stationName = line.trim();
				int direction = Integer.parseInt(br.readLine().trim());
				r.addTrain(new Train(stationName, direction));
			}
		}
	}

	/**
	 * Constructs Riders from an input file and adds them to the Railway.
	 *
	 * runtime: O(n)
	 * @param ridersFile input file containing rider data.
	 * @throws IOException if an error occurs while reading the file.
	 */
	
	public static void initRiders(String ridersFile) throws IOException {
		try (BufferedReader br =
				new BufferedReader(new InputStreamReader(new FileInputStream(ridersFile)))) {
			String line;
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				String riderID = line.trim();
				String startingStation = br.readLine().trim();
				String destinationStation = br.readLine().trim();
				r.addRider(new Rider(riderID, startingStation, destinationStation));
			}
		}
	}

	/**
	 * Constructs Stations from an input file and adds them to the Railway.
	 *
	 * runtime: O(n)
	 * @param stationsFile Input file containing station data.
	 * @throws IOException if an error occurs while reading the file.
	 */
	
	public static void initStations(String stationsFile) throws IOException {
		try (BufferedReader br =
				new BufferedReader(new InputStreamReader(new FileInputStream(stationsFile)))) {
			String line;
			while ((line = br.readLine()) != null && !line.isEmpty()) {
				r.addStation(new Station(line.trim()));
			}
		}
	}
}
