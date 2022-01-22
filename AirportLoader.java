package prob1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AirportLoader {
	public AirportLoader() {}
	
	public static Map<String,Airport> getAirportMap(File airportFile) throws FileNotFoundException{
		Scanner scan = new Scanner(airportFile);
		Map<String, Airport> airportMap = new HashMap<>();
		
		while(scan.hasNext()) {
			String line = scan.nextLine();
			String[] tokens = line.split("\\s");
			if(isValid(tokens)) {
				String code = tokens[0];
				double latitude = Double.parseDouble(tokens[1]);
				double longitude = Double.parseDouble(tokens[2]);
				String city = tokens[3];
				String state = tokens[4];
				Airport airport = new Airport(code, latitude, longitude, city, state);
				airportMap.put(code, airport);
			}
		}
		
		scan.close();
		return airportMap;
	}
	
	public static boolean isValid(String[] s) {
		if(s.length == 5 && isValidLatLon(s[1], s[2]) && isValidCode(s[0]) && isValidState(s[4])) {
				return true;
		}
		else {
			return false;
		}
	}
	
	private static boolean isValidState(String state) {
		if(state.length() == 2) {
			return true;
		}
		return false;
	}

	private static boolean isValidCode(String code) {
		if(code.length() == 3) {
			return true;
		}
		return false;
	}

	private static boolean isValidLatLon(String lat, String lon) {
		
		if(isDouble(lat) && isDouble(lon)) {
			return true;
		}
		return false;
	}

	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		}
		catch(NumberFormatException nfe) {
			return false;
		}
	}
	
}
