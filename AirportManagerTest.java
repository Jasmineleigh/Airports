package prob1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportManagerTest {
	static final String path = "src/prob1/";
	static final String airportsSmallFileName = path + "airportsSmall.txt";
	static final String airportsAllFileName = path + "airports.txt";
	static final String airportsSameCityFileName = path + "airportsSameCities.txt";
	static final File airportsSameCityFile = new File (airportsSameCityFileName);
	static final File airportsSmallFile = new File(airportsSmallFileName);
	static final File airportsAllFile = new File(airportsAllFileName);
	
	public static void main(String[]args) throws FileNotFoundException{
//		testGetAirport_Valid();
//		testGetAirport_Invalid();
//		testGetClosestTo_Code1();
//		testGetClosestTo_Code2();
//		testGetAirports();
//		testGetAirportsByCity_DifferentState();
//		testGetAirportsByCityState();
//		testGetNWSNamedAirports();
		testGetTopAirportsClosestTo_Top3();
		testGetTopAirportsClosestTo_Top5();
//		testGetAirportsSortedBy_City();
//		testGetAirportsSortedBy_State();
//		testGetAirportsWithin_25_lat_lon();
//		testGetAirportsWithin_50_lat_lon();
//		testGetAirportWithin_25_Code_Dist();
//		testGetAirportWithin_50_Code_Dist();
//		testGetAirportWithin_150_Code_Code();
//		testGetDistanceBetween_Code();
//		testGetDistanceBetween_Airports();
		
	}
	
	// Find an Airport in the Manager that has the code passed through the parameter
	public static void testGetAirport_Valid() throws FileNotFoundException {
		System.out.println("-->testGetAirport_Valid<--\n");
		AirportManager aM = createSmallAirportManager();
		
		System.out.print("Expected: ANB-Anniston, AL: 33.58, 85.85\nActual: ");
		System.out.println(aM.getAirport("ANB"));
		System.out.println();
	}
	
	// Try to find an Airport that doesn't exist in the Manager
	public static void testGetAirport_Invalid() throws FileNotFoundException {
		System.out.println("-->testGetAirport_Invalid<--\n");
		AirportManager aM = createSmallAirportManager();
		
		System.out.print("Expected: null\nActual: ");
		System.out.println(aM.getAirport("UTH"));
		System.out.println();
	}
	
	// Find Airport based on the code passed through parameter; code exists in the Manager
	public static void testGetClosestTo_Code1() {
		System.out.println("-->testGetClosestTo_Code<--\n");
		AirportManager aM = createMediumAirportManager();
		System.out.print("Expected: ANB-Anniston, AL: 33.58, 85.85\nActual: ");
		System.out.println(aM.getAirportClosestTo_Code("ANB"));
		System.out.println();
	}
	
	// Finds Airport with code similar to the parameter, but the code doesn't exist in the AirportManager
	public static void testGetClosestTo_Code2() {
		System.out.println("-->testGetClosestTo_Code2<--\n");
		AirportManager aM = createMediumAirportManager();
		System.out.print("Expected: LIT-LittleRock, AR: 35.22, 92.38\nActual: ");
		System.out.println(aM.getAirportClosestTo_Code("LAX"));
		System.out.println();
	}
	
	// Get all the airports in the Manager
	public static void testGetAirports() throws FileNotFoundException {
		System.out.println("-->testGetAirports<--\n");
		AirportManager aM = createSmallAirportManager();
		List<Airport> airports = aM.getAirports();
		
		System.out.println("Airports:");
		print(airports);
		System.out.println();
	}
	
	// Get all of the airports with this city name
	public static void testGetAirportsByCity_DifferentState() throws FileNotFoundException {
		System.out.println("-->testGetAirportsByCity_DifferentState<--\n");
		AirportManager aM = createSameCityAirportManager();
		String cityName = "Greenville";
		List<Airport> airportsFoundWithCity = aM.getAirportsByCity(cityName);
		
		System.out.println("All of the airports in " + cityName + ":\n");
		
		print(airportsFoundWithCity);
		System.out.println();
	}
	
	// Get all of the airports with this city name in this state
	public static void testGetAirportsByCityState() throws FileNotFoundException {
		System.out.println("-->testGetAirportsByCityState<--\n");
		AirportManager aM = createSameCityAirportManager();
		String cityName = "Greenville";
		String stateName = "TX";
		List<Airport> airportsFoundWithCityState = aM.getAirportsByCityState(cityName, stateName);
		String msg = "All of the airports in " + cityName + ", " + stateName + ":";
		
		System.out.println(msg + "\n");
		
		print(airportsFoundWithCityState);
		System.out.println();
	}
	
	// Get all of the National Weather Station airports in the manager
	public static void testGetNWSNamedAirports() throws FileNotFoundException {
		System.out.println("-->testGetNWSNamedAirports<--\n");
		AirportManager aM = createAirportManagerWithNWSAirports();
		List<Airport> nwsAirports = aM.getNWSNamedAirports();
		
		System.out.println("Expected:\n1.) JAX-JacksonVille, FL: 30.50, 81.70\n2.) PDX-Portland, OR: 45.60, 122.60"
							+ "\n3.) BKX-Brookings, SD: 44.30, 96.80\n4.) PRX-Paris/Cox, TX: 33.63, 95.45"
							+ "\n5.) SMX-SantaMaria, CA: 34.90, 120.45\n\nActual:");
		
		print(nwsAirports);
	}
	
	// Get top 3 airports closest to code
	public static void testGetTopAirportsClosestTo_Top3() {
		System.out.println("\n-->testGetTopAirportsClosestTo_Top3<--\n");
		AirportManager aM = createMediumAirportManager();
		int topNum = 3;
		String code = "ANN";
		List<Airport> topAirports = aM.getAirportsClosestTo_Code(code, topNum);
		
		System.out.println("Top " + topNum + " airports closest to \"" + code + "\" ."
				+ "\nExpected:\n1.) ANN-AnnetteIsl, AK: 55.03, 131.57\n2.) ANI-Aniak, AK: 61.58, 159.53"
				+ "\n3.) ANC-Anchorage, AK: 61.17, 150.02\n\nActual:");
		
		print(topAirports);
		System.out.println();
	}
	
	//Get top 5 airports closest to the code
	public static void testGetTopAirportsClosestTo_Top5() {
		System.out.println("-->testGetTopAirportsClosestTo_Top5<--\n");
		AirportManager aM = createMediumAirportManager();
		int topNum = 5;
		String code = "ANN";
		List<Airport> topAirports = aM.getAirportsClosestTo_Code(code, topNum);
		
		System.out.println("Top " + topNum + " airports closest to \"" + code + "\" ."
				+ "\nExpected:\n1.) ANN-AnnetteIsl, AK: 55.03, 131.57\n2.) ANI-Aniak, AK: 61.58, 159.53"
				+ "\n3.) ANC-Anchorage, AK: 61.17, 150.02\n4.) ANB-Anniston, AL: 33.58, 85.85\n"
				+ "5.) BLU-BlueCanyon, CA: 39.28, 120.70\n\nActual:");
		
		int i = 1;
		for(Airport a: topAirports) {
			System.out.println(i + ".) " + a);
			i++;
		}
		System.out.println();
	}

	// Get airports sorted by their cities
	public static void testGetAirportsSortedBy_City() {
		System.out.println("-->testAirportsSortedBy_City<--\n");
		AirportManager aM = createMediumAirportManager();
		List<Airport> sortedByCity = aM.getAirportsSortedBy("City");
		
		System.out.println("List before sorting:");
		print(aM.getAirports());
		System.out.println("\nList after sorting:");
		print(sortedByCity);
		System.out.println();
	}
	
	// Get airports sorted by their state
	public static void testGetAirportsSortedBy_State() {
		System.out.println("-->testAirportsSortedBy_State<--\n");
		AirportManager aM = createMediumAirportManager();
		List<Airport> sortedByState = aM.getAirportsSortedBy("State");
		
		System.out.println("List before sorting:");
		print(aM.getAirports());
		System.out.println("\nList after sorting:");
		print(sortedByState);
		System.out.println();
	}
	
	// Get airports within a 20 mile raidus of the given latitude and longitude
	public static void testGetAirportsWithin_25_lat_lon() throws FileNotFoundException {
		System.out.println("-->testGetAirportsWithin_25<--\n");
		AirportManager aM = createAllAirportManager();
		double withinDist = 25.0;
		double latitude = 30.93;
		double longitude = 83.00;
		List<Airport> withinDistance = aM.getAirportsWithin(withinDist, latitude, longitude);
		
		System.out.println("Airports within " + withinDist + " mile radius of Latitude: "
				+ latitude + ", Longitude: " + longitude + " :");
		
		System.out.println("Expected: \n1.) VAD-MoodyAFB, GA: 30.97, 83.20\n2.) VLD-Valdosta, GA: 30.78, 83.28\n\nActual:");
		
		print(withinDistance);
		System.out.println();
	}
	
	// Get airports within a 50 mile radius of the given latitude and longitude
	public static void testGetAirportsWithin_50_lat_lon() throws FileNotFoundException {
		System.out.println("-->testGetAirportsWithin_50<--\n");
		AirportManager aM = createAllAirportManager();
		double withinDist = 50.0;
		double latitude = 30.93;
		double longitude = 83.00;
		List<Airport> withinDistance = aM.getAirportsWithin(withinDist, latitude, longitude);
		
		System.out.println("Airports within " + withinDist + " mile radius of Latitude: "
				+ latitude + ", Longitude: " + longitude + " :");
		
		System.out.println("Expected: \n1.) VAD-MoodyAFB, GA: 30.97, 83.20\n2.) VLD-Valdosta, GA: 30.78, 83.28"
							+ "\n3.) AYS-Waycross, GA: 31.25, 82.40\n\nActual:");
		
		print(withinDistance);
		System.out.println();
	}
	
	// Get airports within a 25 mile radius of the airport with the given code
	public static void testGetAirportWithin_25_Code_Dist() throws FileNotFoundException {
		System.out.println("-->testGetAirportWithin_25_Code_Dist<--\n");
		AirportManager aM = createAllAirportManager();
		String code = "VLD";
		double dist = 25.0;
		List<Airport> withinDist = aM.getAirportsWithin(code, dist);
		
		System.out.printf("Airports within %.2f mile radius of \"VLD\":\nExpected: \n"
				+ "1.) VAD-MoodyAFB, GA: 30.97, 83.20\n\nActual:\n", dist);
		
		print(withinDist);
		System.out.println();
		
	}
	
	// Get airports within a 50 mile radius of the airport with the given code
	public static void testGetAirportWithin_50_Code_Dist() throws FileNotFoundException {
		System.out.println("-->testGetAirportWithin_50_Code_Dist<--\n");
		AirportManager aM = createAllAirportManager();
		String code = "VLD";
		double dist = 50.0;
		List<Airport> withinDist = aM.getAirportsWithin(code, dist);
		
		System.out.printf("Airports within %.2f mile distance of \"VLD\":\nExpected: \n"
						+ "1.) VAD-MoodyAFB, GA: 30.97, 83.20\n\nActual:\n", dist);
		
		print(withinDist);
		System.out.println();
	}

	// Get airports within a 150 mile radius of both of the airports with the given codes
	public static void testGetAirportWithin_150_Code_Code() throws FileNotFoundException {
		System.out.println("\n-->testGetAirportWithin_25_CodesOnly<--\n");
		AirportManager aM = createAllAirportManager();
		String code1 = "VLD";
		String code2 = "ATL";
		double dist = 150.0;
		List<Airport> withinDist = aM.getAirportsWithin(code1, code2, dist);
		
		System.out.printf("Airports within a %.1f mile radius of \"%s\" and \"%s\":\nExpected:\n1.) WRB-RobinsAFB, GA: 32.63, 83.60\n"
					+ "2.) MCN-Macon/Lewis, GA: 32.70, 83.65\n3.) ABY-Albany, GA: 31.53, 84.18\n"
					+ "4.) LSF-FortBenning, GA: 32.33, 85.00\n\nActual:\n", dist, code1, code2);
		
		print(withinDist);
	}
	
	// Get the distance between the airports with given codes
	public static void testGetDistanceBetween_Code() {
		System.out.println("\n-->testGetDistanceBetween_Code <--\n");
		
		String code1 = "VLD";
		String code2 = "ANB";
		double expDist = 244.95;
		AirportManager aM = createMediumAirportManager();
		
		System.out.printf("Expected: Distance from %s, %s to %s, %s = %.2f\nActual: ", aM.getAirport(code1).getCity(), 
						aM.getAirport(code1).getState(), aM.getAirport(code2).getCity(), aM.getAirport(code2).getState(), expDist);
		
		System.out.printf("Distance from %s, %s to %s, %s = %.2f\n\n", aM.getAirport(code1).getCity(), aM.getAirport(code1).getState(), 
			aM.getAirport(code2).getCity(), aM.getAirport(code2).getState(), aM.getDistanceBetween(aM.getAirport(code1), aM.getAirport(code2)));
	}
	
	// Get distance between the two airports
	public static void testGetDistanceBetween_Airports() {
		System.out.println("-->testGetDistanceBetween_Airports<--\n");
		
		Airport airport = new Airport("ANB", 33.58, 85.85, "Anniston", "AL");
		Airport airport2 = new Airport("VLD", 30.78, 83.28, "Valdosta", "GA");
		double expDist = 244.95;
		Map<String, Airport> airports = new HashMap<>();
		airports.put(airport.getCode(), airport);
		airports.put(airport2.getCode(), airport2);
		AirportManager aM = new AirportManager(airports);
		
		System.out.printf("Expected: Distance from %s, %s to %s, %s = %.2f\nActual:", airport.getCity(), 
							airport.getState(), airport2.getCity(), airport2.getState(), expDist);
		
		System.out.printf(" Distance from %s, %s to %s, %s = %.2f\n\n", airport.getCity(), airport.getState(), 
							airport2.getCity(), airport2.getState(), aM.getDistanceBetween(airport, airport2));
	}
	
	/*
	 * Helpers
	 */
	private static AirportManager createMediumAirportManager() {
		Map<String, Airport> airports = new HashMap<String, Airport>();
		airports.put("VLD", new Airport("VLD", 30.78, 83.28, "Valdosta", "GA"));
		airports.put("ANB", new Airport("ANB", 33.58, 85.85, "Anniston", "AL"));
		airports.put("GST", new Airport("GST", 58.42, 135.73, "Gustavus", "AK"));
		airports.put("CHD", new Airport("CHD", 33.3, 111.67, "WilliamsAFB", "AZ"));
		airports.put("BLU", new Airport("BLU", 39.28, 120.7, "BlueCanyon", "CA"));
		airports.put("HWD", new Airport("HWD", 37.65, 122.12, "Hayward", "CA"));
		airports.put("ANI", new Airport("ANI", 61.58, 159.53, "Aniak", "AK"));
		airports.put("ANN", new Airport("ANN", 55.03, 131.57, "AnnetteIsl", "AK"));
		airports.put("ANC", new Airport("ANC", 61.17, 150.02, "Anchorage", "AK"));
		airports.put("MRI", new Airport("MRI", 61.22, 149.85, "Anchorage", "AK"));
		airports.put("LIT", new Airport("LIT", 35.22, 92.38, "LittleRock", "AR"));
		airports.put("LRF", new Airport("LRF", 34.92, 92.15, "LittleRock", "AR"));
		airports.put("LZK", new Airport("LZK", 34.83, 92.25, "LittleRock", "AR"));
		
		AirportManager airportManager = new AirportManager(airports);
		
		return airportManager;
		
	}
	
	private static AirportManager createAirportManagerWithNWSAirports() {
		Map<String, Airport> airports = new HashMap<String, Airport>();
		airports.put("VLD", new Airport("VLD", 30.78, 83.28, "Valdosta", "GA"));
		airports.put("ANB", new Airport("ANB", 33.58, 85.85, "Anniston", "AL"));
		airports.put("BKX", new Airport("BKX", 44.30, 96.80, "Brookings", "SD"));
		airports.put("CHD", new Airport("CHD", 33.3, 111.67, "WilliamsAFB", "AZ"));
		airports.put("BLU", new Airport("BLU", 39.28, 120.7, "BlueCanyon", "CA"));
		airports.put("JAX", new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL"));
		airports.put("ANI", new Airport("ANI", 61.58, 159.53, "Aniak", "AK"));
		airports.put("PRX", new Airport("PRX", 33.63, 95.45, "Paris/Cox", "TX"));
		airports.put("ANC", new Airport("ANC", 61.17, 150.02, "Anchorage", "AK"));
		airports.put("MRI", new Airport("MRI", 61.22, 149.85, "Anchorage", "AK"));
		airports.put("PDX", new Airport("PDX", 45.60, 122.60, "Portland", "OR"));
		airports.put("LRF", new Airport("LRF", 34.92, 92.15, "LittleRock", "AR"));
		airports.put("SMX", new Airport("SMX", 34.90, 120.45, "SantaMaria", "CA"));
		
		AirportManager airportManager = new AirportManager(airports);
		
		return airportManager;
		
	}
	
	private static AirportManager createAllAirportManager() throws FileNotFoundException {
		Map<String,Airport> airports = AirportLoader.getAirportMap(airportsAllFile);
		AirportManager airportManager = new AirportManager(airports);
		
		return airportManager;
	}
	
	private static AirportManager createSameCityAirportManager() throws FileNotFoundException {
		Map<String,Airport> airports = AirportLoader.getAirportMap(airportsSameCityFile);
		AirportManager airportManager = new AirportManager(airports);
		
		return airportManager;
	}
	
	private static AirportManager createSmallAirportManager() throws FileNotFoundException {
		Map<String,Airport> airports = AirportLoader.getAirportMap(airportsSmallFile);
		AirportManager airportManager = new AirportManager(airports);
		
		return airportManager;
	}
	
	
	private static void print(List<Airport> list) {
		int i = 1; 
		for(Airport a: list) {
			System.out.println(i + ".) " + a);
			i++;
		}
	}
//	
//	private static void print(Map<String, Airport> airports) {
//		int i = 1; 
//		for(Airport a: airports.values()) {
//			System.out.println(i + ".) " + a);
//			i++;
//		}
//	}
}
