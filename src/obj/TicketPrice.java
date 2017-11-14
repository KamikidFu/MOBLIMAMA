package obj;

/**
 * 	Ticket price is calculator entity class
 * @author ANG SHU LIANG
 * @author LEONG MEI HAN
 */
public class TicketPrice {

	static double basePrice = 8;
	static double dayMultiplier = 1; // for weekdays vs weekends&holiday
	static double ageBenefit = 0; // for different age group
	static double movieTypeExtra = 0; // for digital vs 3D
	static double finalPrice;
	public static boolean isWeekday;
	static boolean isStudent;
	public static boolean isSenior;
	public static boolean isDigital;
	
	public TicketPrice() {
		isWeekday = true;
		isStudent = false;
		isSenior = false;
		isDigital = true;
		converter(isStudent, isSenior, isWeekday, isDigital);
		oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
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
	
	public static double getDayMultiplier() {
		return dayMultiplier;
	}

	public static void setDayMultiplier(double dayMultiplier) {
		TicketPrice.dayMultiplier = dayMultiplier;
	}

	public static double getAgeBenefit() {
		return ageBenefit;
	}

	public static void setAgeBenefit(double ageBenefit) {
		TicketPrice.ageBenefit = ageBenefit;
	}

	public static double getMovieTypeExtra() {
		return movieTypeExtra;
	}

	public static void setMovieTypeExtra(double movieTypeExtra) {
		TicketPrice.movieTypeExtra = movieTypeExtra;
	}

	public static double weekdaySeniorDigital() {
		isWeekday = true;
		isStudent = false;
		isSenior = true;
		isDigital = true;
		converter(isStudent, isSenior, isWeekday, isDigital);
		return oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekdayStudentDigital() {
		isWeekday = true;
		isStudent = true;
		isSenior = false;
		isDigital = true;
		converter(isStudent, isSenior, isWeekday, isDigital);
		return oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekdayStudent3D() {
		isWeekday = true;
		isStudent = true;
		isSenior = false;
		isDigital = false;
		converter(isStudent, isSenior, isWeekday, isDigital);
		return oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekdayOthersDigital() {
		isWeekday = true;
		isStudent = false;
		isSenior = false;
		isDigital = true;
		converter(isStudent, isSenior, isWeekday, isDigital);
		return oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekdayOthers3D() {
		isWeekday = true;
		isStudent = false;
		isSenior = false;
		isDigital = false;
		converter(isStudent, isSenior, isWeekday, isDigital);
		return oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekendEveryoneDigital() {
		isWeekday = false;
		isStudent = false;
		isSenior = false;
		isDigital = true;
		converter(isStudent, isSenior, isWeekday, isDigital);
		return oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	public static double weekendEveryone3D() {
		isWeekday = false;
		isStudent = false;
		isSenior = false;
		isDigital = false;
		converter(isStudent, isSenior, isWeekday, isDigital);
		return oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
	}
	
	// this is called upon booking
	public static double theTotalPrice(MovieOnScene movieOnScene, int[] ticketInfo) {
		double totalPrice = 0.0d;
		CinemaDate showTime = movieOnScene.getOnSceneShowTime();
		String day = showTime.convertDateToDay();
		String type = movieOnScene.getOnSceneMovie().getMovieType();
		
		// check whether showTime is weekend or weekday
		if (day == "Sat" || day == "Sun" || showTime.isHoliday(showTime))
			isWeekday = false;
		
		// check whether showType is digital or 3D
		if (type == "3D")
			isDigital = false;
		
		// check whether student
		for (int i = 0; i < ticketInfo[0]; i++) {
			isStudent = (ticketInfo[0] > 0)? true : false;
			converter(isStudent, isSenior, isWeekday, isDigital);
			totalPrice += oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
		}

		// check whether senior
		for (int i = 0; i < ticketInfo[1]; i++) {
			isSenior = (ticketInfo[1] > 0)? true : false;
			converter(isStudent, isSenior, isWeekday, isDigital);
			totalPrice += oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
		}
		
		// check whether standard
		for(int i = 0; i < ticketInfo[2]; i++) {
			converter(isStudent, isSenior, isWeekday, isDigital);
			totalPrice += oneTicketPrice(dayMultiplier, ageBenefit, movieTypeExtra);
		}

		//Final price
		return totalPrice;
	}

	// isStudent, isSenior, isWeekday, isDigital
	public static void converter(boolean isStudent, boolean isSenior, boolean isWeekday, boolean isDigital) {
		// this is to make sure weekend pricing supersedes everything else
		if (isWeekday == false) {
			//System.out.println("The day is weekend");
			isStudent = false;
			isSenior = false;
		}
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
		else
			dayMultiplier = 1.5;
		// checks type of movie
		if (isDigital == true)
			movieTypeExtra = 0;
		else
			movieTypeExtra = 2;
	}

	// this is called upon printing price table
	public static double oneTicketPrice(double d, double a, double m) {
		dayMultiplier = d;
		ageBenefit = a;
		movieTypeExtra = m;
		finalPrice = basePrice * dayMultiplier + ageBenefit + movieTypeExtra;
		return finalPrice;
	}
	
}