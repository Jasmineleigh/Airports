package prob2;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import prob1.Airport;
import prob1.AirportLoader;
import prob1.AirportManager;

public class FlightManagerTest {
	static final String path = "src/prob1/";
	static final String airportsAllFileName = path + "airports.txt";
	static final File airportsAllFile = new File(airportsAllFileName);
	
	public static void main(String[]args) throws Exception {
		System.out.println("**DISCLAIMER**\nI used the random generator to create the cost of the flights,"
				+ "\nso the prices change each time the program is ran.\n");
		testAddFlight_Flight_Success();
		testAddFlight_Flight_Fail();
		testAddFlightUsingFlightConstructor_Success();
		testAddFlightUsingFlightConstructor_Fail();
		testGetFlight_Success();
		testGetFlight_Fail();
		testGetFlightsByOrigin();
		testGetFlightsByOrigin_Date();
		testGetFlightsByOriginAndDestination();
		
		// For testing createFlightManager helper method
		testCreateFlightManager();
		
	}
	
	public static void testAddFlight_Flight_Success() throws Exception {
		System.out.println("--testAddFlight_Flight_Success--");
		AirportManager airportManager = createAllAirportManager();
		FlightManager flightManager = new FlightManager(airportManager);
		Airport origin = new Airport("VLD", 30.78, 83.28, "Valdosta", "GA");
		Flight flight1 = new Flight("5426", LocalDate.of(2021, 12, 2), origin, new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL"), 450.88);
		Flight flight2 = new Flight("5896", LocalDate.of(2021, 12, 2), origin, new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL"), 450.88);
		Flight flight3 = new Flight("1226", LocalDate.of(2021, 12, 2), origin, new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL"), 450.88);
		
		flightManager.addFlight(flight2);
		flightManager.addFlight(flight3);
		
		System.out.println("Expected: true\nActual: " + flightManager.addFlight(flight1));
	}
	

	public static void testAddFlight_Flight_Fail() throws Exception {
		System.out.println("\n--testAddFlight_Flight_Fail--");
		AirportManager airportManager = createAllAirportManager();
		FlightManager flightManager = new FlightManager(airportManager);
		Airport origin = new Airport("VLD", 30.78, 83.28, "Valdosta", "GA");
		Flight flight1 = new Flight("5426", LocalDate.of(2021, 12, 2), origin, new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL"), 450.88);
		Flight flight2 = new Flight("5426", LocalDate.of(2021, 12, 2), origin, new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL"), 450.88);
		Flight flight3 = new Flight("1226", LocalDate.of(2021, 12, 2), origin, new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL"), 450.88);
		
		flightManager.addFlight(flight2);
		flightManager.addFlight(flight3);
		
		System.out.println("Expected: false\nActual: " + flightManager.addFlight(flight1) + "\n");
	}
	
	public static void testAddFlightUsingFlightConstructor_Success() throws Exception {
		System.out.println("--testAddFlightUsingFightConstructor_Success--");
		FlightManager manager = createFlightManager();
		boolean catchStatus = manager.addFlight("4589", "2021-08-19", "ATL", "VLD", 789.12);
		
		System.out.println("Expected: true\nActual: " + catchStatus + "\n");
	}
	
	public static void testAddFlightUsingFlightConstructor_Fail() throws Exception {
		System.out.println("--testAddFlightUsingFightConstructor_Fail--");
		FlightManager manager = createFlightManager();
		boolean catchStatus = manager.addFlight("4444", "2021-08-19", "ATL", "VLD", 789.12);
		
		System.out.println("Expected: false\nActual: " + catchStatus + "\n");
	}
	
	public static void testGetFlight_Success() throws Exception {
		System.out.println("--testGetFlight_Success--");
		FlightManager manager = createFlightManager();
		String str = "";
		String expected = String.format("%-2sFlight: 4444, Date: 2021-09-13\n%-4sFrom: PDX-Portland, OR\n "
				+ "%-4sTo: LVS-LasVegas, NM\nDistance: 1140 miles, Cost: a random amount", str, str, str);
		System.out.println("EXPECTED\n" + expected +"\n\nACTUAL\n" + manager.getFlight("4444") + "\n");
		
	}
	
	public static void testGetFlight_Fail() throws Exception {
		System.out.println("--testGetFlight_Fail--");
		FlightManager manager = createFlightManager();
		System.out.println("EXPECTED\nnull\n\nACTUAL\n" + manager.getFlight("4336") + "\n");
		
	}
	
	public static void testGetFlightsByOrigin() throws Exception{
		System.out.println("--testGetFlightsByOrigin--");
		FlightManager manager = createFlightManager();
		String str = "";
		String expected = String.format("%-2sFlight: 6123, Date: 2021-06-04\n%-4sFrom: DUT-DutchHarbor, AK\n "
				+ "%-4sTo: LVM-Livingston, MT\nDistance: 2496 miles, Cost: a random amount\n\n2.)\n%-2sFlight: 6123, Date: "
				+ "2021-11-15\n%-4sFrom: DUT-DutchHarbor, AK\n%-4sTo: NZW-SWeymouth, MA\nDistance: 4148 miles, Cost: a random amount\n", str, str, str, str, str, str);
		List<Flight> flightsWithOrigin = manager.getFlightsByOrigin("DUT");
		System.out.println("EXPECTED\n1.)\n" + expected +"\n\nACTUAL");
		print(flightsWithOrigin);
		System.out.println();
	}
	
	public static void testGetFlightsByOrigin_Date() throws Exception{
		System.out.println("--testGetFlightsByOrigin_Date--");
		FlightManager manager = createFlightManager();
		String str = "";
		String expected = String.format("%-2sFlight: 6123, Date: 2021-11-15\n%-4sFrom: DUT-DutchHarbor, AK"
										+ "\n%-4sTo: NZW-SWeymouth, MA\nDistance: 4148 miles, Cost:a random amount\n", str, str, str);
		List<Flight> flightsWithOrigin = manager.getFlightsByOrigin("DUT", "2021-11-15");
		System.out.println("EXPECTED\n1.)\n" + expected +"\n\nACTUAL");
		print(flightsWithOrigin);
		System.out.println();
	}
	
	public static void testGetFlightsByOriginAndDestination() throws Exception{
		System.out.println("--testGetFlightsByOriginAndDestination--");
		FlightManager manager = createFlightManager();
		String str = "";
		String expected = String.format("%-2sFlight: 6123, Date: 2021-06-04\n%-4sFrom: DUT-DutchHarbor, AK"
										+ "\n%-4sTo: LVM-Livingston, MT\nDistance: 2496 miles, Cost: a random amount\n", str, str, str);
		List<Flight> flightsWithOrigin = manager.getFlightsByOriginAndDestination("DUT", "LVM", "2021-06-04");
		System.out.println("EXPECTED\n1.)\n" + expected +"\n\nACTUAL");
		print(flightsWithOrigin);
		System.out.println();
	}
	
	public static void testCreateFlightManager() throws Exception {
		System.out.println("--testCreateFlightManager--");
		FlightManager manager = createFlightManager();
		print(manager.getFlights());
	}
	
	/*
	 * Helpers
	 */
	
	private static void print(List<Flight> flights) {
		int i = 1;
		for(Flight f: flights) {
			System.out.println(i + ".)\n" + f);
			i++;
		}
	}
	
	private static FlightManager createFlightManager() throws Exception {
		ArrayList<LocalDate> dates = new ArrayList<>();
		LocalDate date1 = LocalDate.parse("2021-05-26");
		LocalDate date2 = LocalDate.parse("2021-05-30");
		LocalDate date3 = LocalDate.parse("2021-06-04");
		LocalDate date4 = LocalDate.parse("2021-08-26");
		LocalDate date5 = LocalDate.parse("2021-07-26");
		LocalDate date6 = LocalDate.parse("2021-09-13");
		LocalDate date7 = LocalDate.parse("2021-11-15");
		LocalDate date8 = LocalDate.parse("2021-12-20");
		
		dates.add(date1);
		dates.add(date2);
		dates.add(date3);
		dates.add(date4);
		dates.add(date5);
		dates.add(date6);
		dates.add(date7);
		dates.add(date8);
		
		String[] numbers = {"4569", "7895", "6123", "6128", "4178", "4444", "6302", "1024"};
		
		Airport origin1 = new Airport("VLD", 30.78, 83.28, "Valdosta", "GA");
		Airport origin2 = new Airport("PDX", 45.60, 122.60, "Portland", "OR");
		Airport origin3 = new Airport("DUT", 53.88, 166.53, "DutchHarbor", "AK");
		Airport origin4 = new Airport("BEH", 42.13, 86.43, "BentonHarbor", "MI");
		
		Airport[] origins = {origin1, origin2, origin3, origin4, origin1, origin2, origin3, origin4};
		
		AirportManager airportManager = createAllAirportManager();
		FlightManager flightManager = new FlightManager(airportManager);
		
		Random rand = new Random();
		
		for(int i = 0; i < 8; i++) {
			Flight flight = new Flight(numbers[i], dates.get(i), origins[i], airportManager.getAirports().get(i), ((rand.nextDouble() * 500) + 200));
			flightManager.addFlight(flight);
		}
		
		return flightManager;
	}
	
	private static AirportManager createAllAirportManager() throws FileNotFoundException {
		Map<String,Airport> airports = AirportLoader.getAirportMap(airportsAllFile);
		AirportManager airportManager = new AirportManager(airports);
		
		return airportManager;
	}

}
