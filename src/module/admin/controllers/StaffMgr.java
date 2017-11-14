package module.admin.controllers;

import app.Main;
import obj.*;
import obj.interfaces.IUser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * StaffMgr is a logic class to process any logic methods within admin module
 * @author ANG SHU LIANG
 * @author Fu, Yunhao
 * @author LEONG MEI HAN
 */
public class StaffMgr  {
	private static Scanner systemScanner = Main.getSystemScanner();
    private static StaffMgr staffMgr;
	private static IUser staff;

	private static Cineplex selectedCineplex;
	private static Cinema selectedCinema;
	private static Movie selectedMovie;
	private static MovieInCinema selectedMovieInCinema;

	private static ArrayList<Cinema> cinemasList = Main.getCinemasList();
	private static ArrayList<Cineplex> cineplexesList = Main.getCineplexesList();
	private static ArrayList<Review> reviewsList = Main.getReviewsList();
	private static ArrayList<Movie> moviesList = Main.getMoviesList();

	/**
	 * Mono constructor for only generate one object
	 */
	private StaffMgr(){}
	static {
		staffMgr = new StaffMgr();
	}

	/**
	 * Return mono StaffMgr
	 * @return StaffMgr
	 */
	public static StaffMgr getStaffMgr() {
		if(staff == null) {
			System.err.println("Manager has no staff value");
			return null;
		}
		return staffMgr;
	}

	/**
	 * Activate StaffMgr when there comes a user who is Staff
	 * @param Staff User who is Staff
	 */
	public static void activateStaffMgr(IUser Staff) {staff = Staff;}

	//Methods
	/**
	 * To select a movie object and recorded inside mgr
	 * @return The selected movie object
	 */
	public static Movie selectMovie(){
		int selection;
		int counter = 1;
		while(true) {
			for (Movie m : moviesList) {
				System.out.println(counter + " " + m.getMovieName() + "\t" + m.getMovieType() + "\t" + m.getMovieStatus());
				counter++;
			}
			System.out.println("Please select the movie you want to edit: (0 for return)");
			selection = Main.scannerIntegerInput();
			if (selection==0) {
				return null;
			}else if(selection < moviesList.size()) {
				selectedMovie = moviesList.get(selection - 1);
				return moviesList.get(selection - 1);
			} else {
				System.out.println("Input out of boundary!");
			}
		}
	}

	/**
	 * To select a cineplex by the index in cineplex list
	 * @param cineplexIndex Index in cineplex list
	 */
	public void selectedCineplex(int cineplexIndex){
		StaffMgr.selectedCineplex = cineplexesList.get(cineplexIndex);
	}

	/**
	 * To select a cinema by the index in cinema list
	 * @param cinemaIndex Index in cinema list
	 */
	public void selectCinema(int cinemaIndex) {
		StaffMgr.selectedCinema = cinemasList.get(cinemaIndex);
	}

	/**
	 * Add a movie in to a cinema
	 * @param movie Movie object, the one which will show in the cinema
	 * @param cinema Cinema object, where will show the movie
	 * @return MovieInCinema object
	 */
	public MovieInCinema addMovieInCinemaToCinema(Movie movie, Cinema cinema){
		MovieInCinema temp = new MovieInCinema(movie,cinema);
		if(cinema == this.getSelectedCinema()){
			StaffMgr.selectedCinema.addMovieInCinema(temp);
		}else{
			cinema.addMovieInCinema(temp);
		}
		return temp;
	}

	/**
	 * Add a new movie
	 * @return Movie in cinema object
	 * @throws IOException
	 */
	public MovieInCinema addNewMovie() throws IOException {
		String mName=null,mType=null,mStatus=null,mSynopsis=null,mDirector=null,temp=null;
		int i=1;
		boolean allFormsCorrect = false;
		boolean allFormsFilled = false;
		Movie movie = new Movie();
		MovieInCinema newMovieInCinema = new MovieInCinema();
		System.out.println("New Movie------------------");
		do {
			if (!allFormsFilled) {
				System.out.println("Step 1 of 3----------------");
				System.out.println("Enter Movie Title: ");
				mName = systemScanner.nextLine();
				System.out.println("Enter Movie Type (Digital / 3D) : ");
				mType = systemScanner.nextLine();
				System.out.println("Enter Movie Status (Coming Soon/ Preview / Now Showing / End of Showing) :");
				mStatus = systemScanner.nextLine();
				System.out.println("Enter Movie Synopsis: ");
				mSynopsis = systemScanner.nextLine();
				System.out.println("Enter Movie Director: ");
				mDirector = systemScanner.nextLine();				
				allFormsFilled = true;
			}

			// initial getting of user input
			// check the user's input before sending it to Movie class
			if (!allFormsCorrect) {
				// if any of the forms are wrong, this portion will check all
				// the fields
				System.out.println("\nChecking Step 1 of 3");
				if (!mType.matches("Digital") && !mType.matches("3D")) {
					System.out.println("Please enter a valid movie type (Digital / 3D) : \n" );
					mType= systemScanner.nextLine();
				} else if (!mStatus.matches("Coming Soon") && !mStatus.matches("Preview") && !mStatus.matches("Now Showing")
						&& !mStatus.matches("End of Showing")) {
					System.out.print("Please enter a valid movie status (Coming Soon/ Preview / Now Showing / End of Showing): \n");
					mStatus = systemScanner.nextLine();
				} else {
					// if all forms entered are correct
					allFormsCorrect = true;
					System.out.println("All data in step 1 is correct.");
					System.out.println("Step 1 of 3 complete\n");
				}
			}
		} while (!allFormsCorrect);

		if (allFormsCorrect) {
			// enter all the data
			movie = new Movie(mName,mType,mStatus,mSynopsis,mDirector);
			System.out.println("Step 2 of 3: Cast List-----");
			do {
				System.out.println("Enter Movie Cast" + i + " Name (Press Enter if no more cast): ");
				temp = systemScanner.nextLine();
				if (!temp.isEmpty()) {
					movie.addMovieCast(temp);
					i++;}
			} while (!temp.isEmpty());
			System.out.println("Step 2 of 3 completed. Casts added.");
			staffMgr.getMoviesList().add(movie);
			System.out.println("Step 3 of 3: Movie Timing--");
			newMovieInCinema = addShowTime(movie);
			System.out.println("Step 3 of 3 completed. Movie Show Time added.");
			System.out.println("New Movie is added.");
			if(newMovieInCinema==null){
				System.err.println("Failed to add new movie to cinema.");
				addNewMovie();
			}
		}
		/*		//Result print
		System.out.println("Your Movie has been added successfully.");
		System.out.println("Following are the Movie Details:");
		System.out.println("========================================");
		System.out.println("Movie Title: " + mName);
		System.out.println("Type: " + mType);
		System.out.println("Status: " + mStatus);
		System.out.println("Synopsis: " + mSynopsis);
		System.out.println("Director: " + mDirector);
		System.out.println("Casts : ");
		for (int n=0; n<i-1;n++)
			System.out.println(mCast[n]);
		System.out.println("ShowTime : ");
		for (int p = 0; p < staffMgr.getCineplexesList().size(); p++) {
			staffMgr.selectedCineplex(p);
			for (int q = 0; q < staffMgr.getCinemasList().size(); q++) {
				staffMgr.selectCinema(q);
				for (int n=0; n<i-1;n++) {
					DateFormat outDF = new SimpleDateFormat( "EEE dd MMM yyyy hh:mm aa");
					String printDate = outDF.format(newMovieInCinema.getInCinemaShowTimes().get(n));
					System.out.println(printDate);
				}
			}
		}*/
		return newMovieInCinema;
	} //end of newMovie method

	/**
	 * Add show time for movie in cinema
	 * @param movie Movie object, which is waiting for adding show time
	 * @return MovieInCinema object
	 * @throws IOException
	 */
	public static MovieInCinema addShowTime(Movie movie) throws IOException {
		String cineplex, cinema, inTime;
		MovieInCinema tempMovieInCinema=null;

		do {
			System.out.println("New Movie Showtime---------");
			System.out.println("\nPress Enter to return Terminate current process");
			System.out.println("\nCineplex-------------------");
			for (int p = 0; p < staffMgr.getCineplexesList().size(); p++) {
				System.out.println((p + 1) + ". " + staffMgr.getCineplexesList().get(p).getCineplexName());
			}
			System.out.println("Please select one of cineplexes above!");
			cineplex = systemScanner.nextLine();
			if(cineplex.isEmpty())
				break;
			while(!staffMgr.tryParseInteger(cineplex)){
				System.out.println("Wrong input please check!");
				cineplex = systemScanner.nextLine();
			}
			if (staffMgr.tryParseInteger(cineplex)) {
				staffMgr.selectedCineplex(Integer.parseInt(cineplex)-1);
			}
			if(staffMgr.getSelectedCineplex()!=null && !cineplex.isEmpty()) {
				System.out.println("\nCinema---------------------");
				for (int q = 0; q < staffMgr.getSelectedCineplex().getCinemaList().size(); q++) {
					System.out.println((q + 1) + ". " + staffMgr.getSelectedCineplex().getCinemaList().get(q).getCinemaName());
				}
				System.out.println("Please select one of cinema above!");
				cinema = systemScanner.nextLine();
				while(!staffMgr.tryParseInteger(cinema)){
					System.out.println("Wrong input please check!");
					cinema = systemScanner.nextLine();
				}
				if (staffMgr.tryParseInteger(cinema)) {
					staffMgr.selectCinema(Integer.parseInt(cinema) - 1);
				}
				tempMovieInCinema = staffMgr.addMovieInCinemaToCinema(movie, staffMgr.getSelectedCinema());

				do {
					System.out.println("Enter Screening Date (hh/min/dd/mm/yyyy): ");
					inTime = systemScanner.nextLine();
					if (!inTime.isEmpty()) {
						String[] temp1 = inTime.split("/");
						if(temp1.length==5 && staffMgr.tryParseInteger(temp1[0]) && staffMgr.tryParseInteger(temp1[1]) && staffMgr.tryParseInteger(temp1[2]) &&
								staffMgr.tryParseInteger(temp1[3]) && staffMgr.tryParseInteger(temp1[4])) {
							if((-1<Integer.parseInt(temp1[0]) && Integer.parseInt(temp1[0])<25) && (-1<Integer.parseInt(temp1[1]) && Integer.parseInt(temp1[1])<61) &&
									(-1<Integer.parseInt(temp1[2]) && Integer.parseInt(temp1[2])<30) && (-1<Integer.parseInt(temp1[3]) && Integer.parseInt(temp1[3])<13)
									&& (-1<Integer.parseInt(temp1[4]) && Integer.parseInt(temp1[4])<9999)) {
								CinemaDate inDF = new CinemaDate(Integer.parseInt(temp1[4]),
										Integer.parseInt(temp1[3]), Integer.parseInt(temp1[2]),
										Integer.parseInt(temp1[1]), Integer.parseInt(temp1[0]));
								tempMovieInCinema.addShowTime(inDF);
							}else {
								System.out.println("Wrong input please check!");
								continue;
							}
						}else{
							System.out.println("Wrong input please check!");
							continue;
						}
					}

				} while (!inTime.isEmpty());
			}
		} while (!cineplex.isEmpty());
		return tempMovieInCinema;
	}//end of add new show time

	/**
	 * Edit movie method
	 * @throws IOException
	 */
	public void editMovie() throws IOException {

		System.out.println("Edit Movie-----------------");
		Movie movieToEdit = selectMovie();
		if(movieToEdit==null){
			return;
		}
		String movieName,movieType,movieStatus,movieSynopsis,movieDirector,temp;

		System.out.println("-------------------------------------");
		System.out.println("Edit " + movieToEdit.getMovieName() +" Movie");
		System.out.println("-------------------------------------");
		//prints out Movie Title, Type, Status, Synopsis, Director, Casts, and Showtime
		System.out.println("Please select detail to be edited: ");
		System.out.println("(1) Modify Movie Title");
		System.out.println("(2) Modify Movie Type");
		System.out.println("(3) Modify Movie Status");
		System.out.println("(4) Modify Movie Synopsis");
		System.out.println("(5) Modify Movie Director");
		System.out.println("(6) Modify Movie Cast");
		System.out.println("(7) Modify Movie Showtime");
		System.out.println("(0) Back to Home");
		
        int selection = Main.scannerIntegerInput();
		if (selection>-1&&selection<8) {
			switch (selection) {
				case 0:
					return;
				case 1:
					System.out.println("Please enter new Movie Title: ");
					movieName = systemScanner.nextLine();
					movieToEdit.setMovieName(movieName);
					break;
				case 2:
					System.out.println("Enter new Movie Type (Digital / 3D) : ");
					movieType = systemScanner.nextLine();
					movieToEdit.setMovieType(movieType);
					break;
				case 3:
					System.out.println("Enter new Movie Status (Coming Soon/ Preview / Now Showing / End of Showing) :");
					movieStatus = systemScanner.nextLine();
					movieToEdit.setMovieStatus(movieStatus);
					break;
				case 4:
					System.out.println("Enter new Movie Synopsis: ");
					movieSynopsis = systemScanner.nextLine();
					movieToEdit.setMovieSynopsis(movieSynopsis);
					break;
				case 5:
					System.out.println("Enter new Movie Director: ");
					movieDirector = systemScanner.nextLine();
					movieToEdit.setMovieDirector(movieDirector);
					break;
				case 6:
					systemScanner.nextLine();
					int i=1;
					do {
						System.out.println("Enter new Movie Cast" + i + " Name (Press Enter if no more cast): ");
						temp = systemScanner.nextLine();
						if (!temp.isEmpty()) {
							movieToEdit.addMovieCast(temp);
							i++;
						}
					} while (!temp.isEmpty());
					break;
				case 7:
					addShowTime(movieToEdit);
					break;
			}
		} else {
			System.out.println("ERROR: Wrong input!\n");
			editMovie();
		}
	}

	/**
	 * Set a holiday time
	 */
	public void setHoliday() {
		String temp;
		while(true){
			System.out.println("Please type the holiday in format of YYYYMMDD. Input 0 for return");
			temp = systemScanner.nextLine();
			if(temp.toCharArray().length==8 && staffMgr.tryParseInteger(temp)){
				CinemaDate.addHoliday(temp);
				System.out.println("Added the date as holiday!");
			}else if (temp.matches("0")) {
				return;
			}else{
				System.out.println("Wrong input, please check!");
				continue;
			}
		}
	}
	
	public boolean tryParseInteger(String string){
		try{
			Integer.parseInt(string);
			return true;
		}catch (Exception e){
			return false;
		}
	}

	//Getters

	/**
	 * Return the list of cineplex
	 * @return List of cineplexs
	 */
	public  ArrayList<Cineplex> getCineplexesList() {
		return cineplexesList;
	}

	/**
	 * Return list of movies
	 * @return List of movie
	 */
	public  ArrayList<Movie> getMoviesList() {
		return moviesList;
	}

	/**
	 * Return the selected cineplex
	 * @return Currently selected cineplex
	 */
	public Cineplex getSelectedCineplex() {
		return selectedCineplex;
	}

	/**
	 * Return the selected cinema
	 * @return Currently selected cinema
	 */
	public Cinema getSelectedCinema() {
		return selectedCinema;
	}
}
