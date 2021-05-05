//Part: 2
// Written by: Patrick Keenan 40175307
// -----------------------------------
package Assignment_4;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
/**
 * <p>Author: Patrick Keenan, Student ID: 40175307 </p>
 * <p>COMP 249 </p>
 * <p>Assignment 4 </p>
 * <p>Due Date: April 24 2021 </p>
 * <p>ProcessWishList class that contains the main driver for part 2 of assignment 4/p>
 */
public class ProcessWishList{
	/**
	 * Main method which creates a ShowList using the TVGuide.txt file. It then asks for a input file which
	 * contains the shows the user is currently watching and which shows they wish to watch. It says which
	 * shows the user can and can't watch on their wishlist based off the information derived from the TVGuide.txt
	 * file, and then asks the user to check if the Show List contains certain shows by their ID. After that, some of 
	 * the other classes methods are tested.
	 */
	public static void main(String[] args) throws CloneNotSupportedException {
		ShowList first = new ShowList();
		ArrayList<String> watching = new ArrayList<String>();
		ArrayList<String> wishlist = new ArrayList<String>();
		Scanner user = new Scanner(System.in);
		Scanner sc = null;
		Scanner sc2 = null;
		try {
			sc = new Scanner(new FileInputStream("TVGuide.txt"));
		}
		catch (FileNotFoundException e) {
			System.out.println("The file TVGuide.txt could not be found.");
			System.exit(0);
		}
		while(sc.hasNext()) {
			String[] string_info = new String[2];
			double[] double_info = new double[2];
			int info_number=0;
			String line = "";
			if (info_number==0) {
				String[] split = new String[2];
				line = sc.nextLine();
				split=line.split(" ");
				string_info[0]=split[0];
				string_info[1]=split[1];
				info_number++;
			}
			if (info_number==1) {
				String[] split = new String[2];
				line=sc.nextLine();
				split=line.split(" ");
				double_info[0]= Double.parseDouble(split[1]);
				info_number++;
			}
			if (info_number==2) {
				String[] split = new String[2];
				line=sc.nextLine();
				split=line.split(" ");
				double_info[1]= Double.parseDouble(split[1]);
				info_number++;
			}
			if (info_number==3) { //create the TV show with 4 pieces of information and reset the loop
				info_number=0;
				line=sc.nextLine();
				TVShow CurrentShow = new TVShow(string_info[0],string_info[1],double_info[0],double_info[1]);
				if (!first.contains(CurrentShow.showName)) { //no show contains the same ID so we can't use showID to find copies... must use showName
					first.addToStart(CurrentShow); //add show if it isn't a copy
				}
				info_number=0;
			}			
			
		}
		sc.close();
		System.out.println("Enter the name of the input file that has your watching and wishlist shows: ");
		String input = user.next(); //use Interest.txt for practice
		try {
			sc2= new Scanner(new FileInputStream(input));
		}
		catch (FileNotFoundException e) {
			System.out.println("The file "+ input +" could not be found.");
			System.exit(0);
		}
		int list_nb = 0;
		int debug1=0;
		int debug2=0;
		while (sc2.hasNext()) { //create watching and wishlist arraylists
			String line = "";
			line = sc2.nextLine();
			if (line.equals("Watching")) {
				list_nb=1;		
			}
			if (list_nb==1) {
				debug1++;
				if(debug1==1) {
					line=sc2.nextLine();
				}
				if (!line.equals("Wishlist")) {
					watching.add(line);
				}
				else {
					list_nb=2;
				}
			}
			if (list_nb==2) {
				debug2++;
				if(debug2==1) {
					line=sc2.nextLine();
				}
				wishlist.add(line);
			}
			
		}
		sc2.close();
		ArrayList<String> process = new ArrayList<String>();
		for (int i=0; i<watching.size(); i++) { //create the process arraylist to show the user which shows they can and can't watch on the wish list
			String Currentid = watching.get(i);			
			if (first.findShow(Currentid) != null) {
				TVShow Currentshow = first.findShow(Currentid);
				for (int j=0; j<wishlist.size(); j++) {
					String Comparedid = wishlist.get(j);
					if (first.findShow(Comparedid) != null) {
						TVShow Comparedshow = first.findShow(Comparedid);
						String s=Currentshow.isOnSameTime(Comparedshow); //testing isOnSameTime method
						if(s.substring(5,10).equals("can't")){
							wishlist.remove(j);
							if (!process.contains(s)) {
								process.add(s);
							}
						}
						else {
							if (i==watching.size()-1) {
								process.add(s);
							}
						}
					}
				}
			}
		}
		for (int i=0; i<process.size(); i++) {
			System.out.println(process.get(i));
		}
		System.out.println("Enter three Show Ids to check: ");
		String id1 = user.next();
		String id2 = user.next();
		String id3 = user.next();
		user.close();
		if (first.contains(id1)) {
			System.out.println("The show " +id1+ " was found!");
		}
		else {
			System.out.println("The show " +id1+ " wasn't found...");
		}
		if (first.contains(id2)) {
			System.out.println("The show " +id2+ " was found!");
		}
		else {
			System.out.println("The show " +id2+ " wasn't found...");
		}
		if (first.contains(id3)) {
			System.out.println("The show " +id3+ " was found!");
		}
		else {
			System.out.println("The show " +id3+ " wasn't found...");
		}
		System.out.println("Now testing constructors/methods of the classes");
		TVShow newshow = new TVShow("ADD123", "Addition show", 22.0 ,23.0);
		TVShow newshow2 = new TVShow (newshow, "ADD124");
		//TVShow newshow3 = (TVShow)newshow.clone();
		TVShow newshow4 = new TVShow ("ABC125", "Subtraction show", 12.0, 12.3);
		if (newshow.equals(newshow2) && !newshow.equals(newshow4)) {
			System.out.println("TV Show equals methods works! (Does not check for ID)");
		}
		ShowList second = new ShowList(first);
		ShowList third = new ShowList();
		if (first.equals(second) && !first.equals(third)) {
			System.out.println("ShowList equals methods works!");
		}
		first.find("NBC20"); //prints
		System.out.println("Show NBC20 found at spot 7 in index will now be deleted");
		first.deleteFromIndex(7); //NBC20 is spot 8, show list starts at 0 so its spot in the index is 7
		first.find("NBC20"); //doesn't print (good)
		System.out.println("If no iterations have been made it was succesfully deleted");
		TVShow NBC20 = new TVShow("NBC20", "World_Of_Dance", 20.0, 22.0);
		TVShow EXA31 = new TVShow("EXA31", "Replace Index Example", 18.0, 19.0);
		first.insertAtIndex(NBC20, 7);
		System.out.println("Show NBC20 has been reinserted at spot 7 in index");
		first.find("NBC20"); //prints
		first.replaceAtIndex(EXA31, 7);
		System.out.println("Show NBC20 has been replaced by show EXA31 at spot 7 in index, now searching for EXA31");
		first.find("EXA31"); //prints
		System.out.println("Now finding show ABC22 in the index");
		first.find("ABC22"); //prints
		first.deleteFromStart();
		System.out.println("ABC22 is first spot (spot 0) in index, deleteFromStart method is now executed and if no iterations are made trying to find ABC22 the method is functional.");
		first.find("ABC22"); //doesn't print (good)
		
	}

}
