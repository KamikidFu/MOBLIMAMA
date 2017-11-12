package obj;

public class TicketPrice {

	static double basePrice = 8;
	static double dayMultiplier = 1; // for weekdays vs weekends&holiday
	static double ageBenefit = 0; // for different age group
	static double movieTypeExtra = 0; // for digital vs 3D
	static double finalPrice;
	static boolean isWeekday = false, isWeekend = false, isStudent = false, isSenior = false, isDigital = false, is3D = false;
	static double weekdaySeniorDigital;
	
	public TicketPrice() {
		isWeekday = true;
		isWeekend = false;
		isStudent = false;
		isSenior = false;
		isDigital = true;
		is3D = false;
		converter(isStudent, isSenior, isWeekday, isWeekend, isDigital, is3D);
		theTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}

	
	public static double getBasePrice() {
		return basePrice;
	}

	public static void setBasePrice(double basePrice) {
		TicketPrice.basePrice = basePrice;
	}

	public static double getFinalPrice() {
		return finalPrice;
	}

	public static void setFinalPrice(double finalPrice) {
		TicketPrice.finalPrice = finalPrice;
	}

	public static boolean isWeekday() {
		return isWeekday;
	}

	public static void setWeekday(boolean isWeekday) {
		TicketPrice.isWeekday = isWeekday;
	}

	public static boolean isWeekend() {
		return isWeekend;
	}

	public static void setWeekend(boolean isWeekend) {
		TicketPrice.isWeekend = isWeekend;
	}

	public static boolean isStudent() {
		return isStudent;
	}

	public static void setStudent(boolean isStudent) {
		TicketPrice.isStudent = isStudent;
	}

	public static boolean isSenior() {
		return isSenior;
	}

	public static void setSenior(boolean isSenior) {
		TicketPrice.isSenior = isSenior;
	}

	public static boolean isDigital() {
		return isDigital;
	}

	public static void setDigital(boolean isDigital) {
		TicketPrice.isDigital = isDigital;
	}

	public static boolean isIs3D() {
		return is3D;
	}

	public static void setIs3D(boolean is3d) {
		is3D = is3d;
	}

	public static double getPrice(){
		return 1.0d;
	}

	public static double weekdaySeniorDigital() {

		isWeekday = true;
		isWeekend = false;
		isStudent = false;
		isSenior = true;
		isDigital = true;
		is3D = false;
		converter(isStudent, isSenior, isWeekday, isWeekend, isDigital, is3D);
		return theTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekdayStudentDigital() {
		isWeekday = true;
		isWeekend = false;
		isStudent = true;
		isSenior = false;
		isDigital = true;
		is3D = false;
		converter(isStudent, isSenior, isWeekday, isWeekend, isDigital, is3D);
		return theTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekdayStudent3D() {
		isWeekday = true;
		isWeekend = false;
		isStudent = true;
		isSenior = false;
		isDigital = false;
		is3D = true;
		converter(isStudent, isSenior, isWeekday, isWeekend, isDigital, is3D);
		return theTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekdayOthersDigital() {
		isWeekday = true;
		isWeekend = false;
		isStudent = false;
		isSenior = false;
		isDigital = true;
		is3D = false;
		converter(isStudent, isSenior, isWeekday, isWeekend, isDigital, is3D);
		return theTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekdayOthers3D() {
		isWeekday = true;
		isWeekend = false;
		isStudent = false;
		isSenior = false;
		isDigital = false;
		is3D = true;
		converter(isStudent, isSenior, isWeekday, isWeekend, isDigital, is3D);
		return theTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekendEveryoneDigital() {
		isWeekday = false;
		isWeekend = true;
		isStudent = false;
		isSenior = false;
		isDigital = true;
		is3D = false;
		converter(isStudent, isSenior, isWeekday, isWeekend, isDigital, is3D);
		return theTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekendEveryone3D() {
		isWeekday = false;
		isWeekend = true;
		isStudent = false;
		isSenior = false;
		isDigital = false;
		is3D = true;
		converter(isStudent, isSenior, isWeekday, isWeekend, isDigital, is3D);
		return theTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	// isStudent, isSenior, isWeekday, isWeekend, isDigital, is3D
	private static void converter(boolean isStudent, boolean isSenior, boolean isWeekday, boolean isWeekend, boolean isDigital, boolean is3D) {
		// checks who
		if (isStudent == true)
			ageBenefit = -2;
		else if (isSenior == true)
			ageBenefit = -4;
		else
			ageBenefit = 0;
		// checks day
		if (isWeekday == true)
			dayMultiplier = 1;
		else if (isWeekend == true)
			dayMultiplier = 1.5;
		// checks type of movie
		if (isDigital == true)
			movieTypeExtra = 0;
		else if (is3D == true)
			movieTypeExtra = 2;
	}

	
	private static double theTicketPrice(double d, double a, double m) {
		dayMultiplier = d;
		ageBenefit = a;
		movieTypeExtra = m;
		finalPrice = basePrice * dayMultiplier + ageBenefit + movieTypeExtra;
		return finalPrice;
	}

}
