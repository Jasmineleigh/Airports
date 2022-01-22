package prob1;

public class AirportTest {
	public static void main(String[]args) {
		testEquals_true();
		testEquals_false();
		testToString();
	}
	
	public static void testEquals_true() {
		System.out.println("-->testEquals_true<--");
		
		Airport lAX = new Airport("LAX", 33.93, 118.4, "Los Angeles", "CA");
		Airport losAngeles = new Airport("LAX", 33.93, 118.4, "Los Angeles", "CA");
		
		System.out.print("Expected: true\nActual: ");
		System.out.println(lAX.equals(losAngeles));
		System.out.println();
	}
	
	public static void testEquals_false() {
		System.out.println("-->testEquals_false<--");
		
		Airport lAX = new Airport("LAX", 33.93, 118.4, "Los Angeles", "CA");
		Airport mMH = new Airport("MMH", 37.63, 118.92, "MammothLakes", "CA");
		
		System.out.print("Expected: false\nActual: ");
		System.out.println(lAX.equals(mMH));
		System.out.println();
	}
	
	public static void testToString() {
		System.out.println("-->testToString<--");
		
		Airport lAX = new Airport("LAX", 33.93, 118.4, "Los Angeles", "CA");
		
		System.out.println(lAX);
	}

}
