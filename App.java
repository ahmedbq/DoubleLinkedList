//CS3810 Data Structures and Algorithms
//Assignment #3 4/15/2016
//Ahmed B. Qureshi 700636758
//The app class starts off by importing the InputMismatchException, which occurs
//if the user inputs a string/letter instead of an int, and suddenly error comes. 
//A boolean sentinel was created for the future while loop, so that once it 
//becomes false by the user quitting, the loop stops and so does the program.
//An object of RecordList was created before the while loop. WIthin the while loop
//there is first a try and catch block created for all future user inputs.
//Thre was also an int for the menu created at this instance, simply for the 
//switch statements. Case 0 represents displaying the Menu for easiness, instead
//of scrolling back up again. Case 1 and 2 insertFirst and insertLast. In both 
//the app asks the user for some input for the methods. And in both and especially
//the whole class, loopinput represents the user input, and isEmpty was checked
//only in the app class. The methods for inserts and later codes itself only contains
//what isEmpty and !isEmpty both do after doing their individual codes. But
//after they are implemented, the insertAfter/Before functions are called to do 
//whatever is left. Also in these methods and later ones, the Link objects are
//created beforehand before they are specifically linked to other nodes and
//given data. Case 3 and 4 are basically insertAfter and insertBefore. Both
//of these cases have similar code in which they also ask the user an ID of which
//you would insert a link after/before. Then the individual functions would be
//called using that data. The functions themselves are explained in the RecordList
//class. Case 5/6 are basically deleteFirst and deleteLast, which still require
//to check if the list is empty or not, because you can't delete something out
//of nothing. Case 7 is similar to 4/5 in which the app asks you for a specific
//ID, but this time it's to delete. It checks isEmpty and calls the function.
//Case 8 calls the function to sort. It does not ask for user input since you
//would call the method using the object name you used. Then it would only sort
//whatever is in the object. Nothing is returned either. Case 9 basically displays
//the nodes by calling the function. Also checks if there is any nodes or it will
//say there's nothing to display. Case 10 is the quit function in which the sentinel
//is set to false and the whole loop ends and forces the program to end since there
//is no code after the loop.

//imports
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

	public static void main (String [] args){

		System.out.println("*List Application*\n\n");
		PrintMenu();
		System.out.println("[Enter 0 to display menu.]\n\nEnter: ");

		//Sentinel
		boolean sentinel = true;

		//Creates RecordList
		RecordList list = new RecordList ();

		//starts with a try catch statement inside sentinel loop to catch all inputs
		while (sentinel){
			//Initialization set to -1 so it doesn't go to a menu right away
			int selection = -1;
			//Scanner is in here so that exception won't be in an infinite loop
			Scanner loopInput = new Scanner (System.in);

			try{
				selection = loopInput.nextInt();}
			catch(InputMismatchException e){System.out.println("Enter only a number. \n"
					+ "-----------------\nEnter: "); }

			switch (selection){

			//For insert classes there is no limit since nodes can go on forever
			//But you still have to check if it's empty or not for correct insertion
			case 0: PrintMenu(); System.out.println("\n\nEnter: "); 

			//System.out.println("last\n" + list.last);
			//System.out.println("\nfirst\n" + list.first);
			//These print outs were to check the first and last locations

			break;

			case 1: //insertFirst
				System.out.println("\nCreate a student to insert at front."
						+ "\n\nEnter ID#: ");				
				//Checks duplicates
				String IDNum = "" + loopInput.next();
				try{
					if (duplicate(IDNum, list))
					{
						break;
					}
				}
				catch(NumberFormatException e){
					System.out.println("ID can not contain any letter."
							+ "\n\nEnter: ");
					break;
				}

				System.out.println("Enter Name: ");
				//Parameters are created
				String Name = "" + loopInput.next();	
				//RecordList method is called and at the same time creates
				//an Item object without declaring a name to avoid
				//changing the name every time.

				//Checks if name is valid
				if(!ValidName(Name))
				{
					break;
				}

				//You have to create a new object, you can't just say
				//Link newLink;

				if(list.isEmpty())
				{
					Link newLink = new Link(new Student (IDNum, Name));
					list.last = newLink;
					list.insertFirst(newLink);
				}
				else{
					Link newLink = new Link(new Student (IDNum, Name));
					newLink.prev = list.first.prev; //DEBUG: Otherwise last wouldn't be pointing to anything
					//since it's not initialized to null in class Link.
					list.first.prev = newLink;
					list.insertFirst(newLink);
				}

				System.out.println("Student successfully inserted at first. \n"
						+ "\n-----------------\nEnter: ");

				break;


			case 2: //insertLast
				System.out.println("\nCreate a student to insert at last."
						+ "\n\nEnter ID#: ");
				//Checks duplicates
				String IDNum2 = "" + loopInput.next();
				try{
					if (duplicate(IDNum2, list))
					{
						break;
					}
				}
				catch(NumberFormatException e){
					System.out.println("ID can not contain any letter."
							+ "\n\nEnter: ");
					break;
				}
				System.out.println("Enter Name: ");
				//Parameters are created
				String Name2 = "" + loopInput.next();	
				//RecordList method is called and at the same time creates
				//an Item object without declaring a name to avoid
				//changing the name every time.

				//Checks if name is valid
				if(!ValidName(Name2))
				{
					break;
				}

				if (list.isEmpty())
				{
					Link newLink2 = new Link(new Student (IDNum2, Name2));
					list.insertLast(newLink2);
					//list.first = newLink2; //DEBUG: Removed since it's a repeat
					//inside the method
					newLink2.prev = null; //DEBUG: Have to initialize .prev
					newLink2.next = null; //DEBUG
					list.first = newLink2; //DEBUG
				}
				else
				{
					Link newLink2 = new Link(new Student (IDNum2, Name2));
					list.last.next = newLink2;
					newLink2.prev = list.last;
					list.insertLast(newLink2);
					newLink2.next = null; //DEBUG: Initialized .next to null
				}
				System.out.println("Student successfully inserted at last. \n"
						+ "\n-----------------\nEnter: ");

				break;

			case 3: //insertAfter
				if (list.isEmpty())
				{
					System.out.println("No students to place after."
							+ "\n-----------------\nEnter: ");
				}
				else{
					System.out.println("\nCreate a student to insert after."
							+ "\n\nEnter ID#: ");
					//Checks duplicates
					String IDNum3 = "" + loopInput.next();
					try{
						if (duplicate(IDNum3, list))
						{
							break;
						}
					}
					catch(NumberFormatException e){
						System.out.println("ID can not contain any letter."
								+ "\n\nEnter: ");
						break;
					}

					//Scanner
					System.out.println("Enter Name: ");
					//Parameters are created
					String Name3 = "" + loopInput.next();	
					//RecordList method is called and at the same time creates
					//an Item object without declaring a name to avoid
					//changing the name every time.

					//Checks if name is valid
					if(!ValidName(Name3))
					{
						break;
					}

					System.out.println("You want to insert after this ID: ");
					String FindID1 = "" + loopInput.next();

					//It activates the method inside the if statement
					if(!list.insertAfter(FindID1, new Student (IDNum3, Name3)))
					{
						System.out.println("Failed to insert after. Key does not exist."
								+ "\n-----------------\nEnter: ");
					}
					else
					{
						System.out.println("Successfully inserted after."
								+ "\n-----------------\nEnter: ");
					}
					

				}

				break;

			case 4: //insertBefore
				if (list.isEmpty())
				{
					System.out.println("No students to insert this way."
							+ "\n-----------------\nEnter: ");
				}
				else
				{
					System.out.println("\nCreate a student to insert before."
							+ "\n\nEnter ID#: ");
					//Checks duplicates
					String IDNum4 = "" + loopInput.next();
					try{
						if (duplicate(IDNum4, list))
						{
							break;
						}
					}
					catch(NumberFormatException e){
						System.out.println("ID can not contain any letter."
								+ "\n\nEnter: ");
						break;
					}

					System.out.println("Enter Name: ");
					//Parameters are created
					String Name4 = "" + loopInput.next();	
					//RecordList method is called and at the same time creates
					//an Item object without declaring a name to avoid
					//changing the name every time.

					//Checks if name is valid
					if(!ValidName(Name4))
					{
						break;
					}

					System.out.println("You want to insert before this ID: ");
					String FindID2 = "" + loopInput.next();

					if(!list.insertBefore(FindID2, new Student (IDNum4, Name4)))
					{
						System.out.println("Failed to insert before. Key does not exist."
								+ "\n-----------------\nEnter: ");
					}
					else
					{
						System.out.println("Successfully inserted before."
								+ "\n-----------------\nEnter: ");
					}
				}
				break;



				//For delete methods, can only delete if it's not empty
			case 5: //DeleteFirst
				if(!list.isEmpty()){
					System.out.println("\nDeleting first student.");
					list.deleteFirst();
					System.out.println("First student successfully deleted. "
							+ "\n-----------------\nEnter: ");
					break;}
				else{System.out.println("No students. Cannot delete."
						+ "\n-----------------"); break;}

			case 6: //DeleteLast
				if(!list.isEmpty()){
					System.out.println("\nDeleting last student.");
					list.deleteLast();
					System.out.println("Last student successfully deleted. \n");
					System.out.println("\n-----------------\nEnter: ");
					break;}
				else{System.out.println("No students. Cannot delete."
						+ "\n-----------------"); break;}

			case 7: //Sort
				if (list.first.next == null) //DEBUG
				{
					System.out.println("List sorted.");
					break;
				}
				if (!list.isEmpty())
				{
					System.out.println("Sorting list.");
					list.insertionSort();
					System.out.println("List sorted. "
							+ "\n-----------------\nEnter: ");
				}
				else{System.out.println("There is no list. There is nothing to sort."
						+ "\n-----------------\nEnter: ");}
				break;

			case 8: //Display
				if (!list.isEmpty())
				{
					System.out.println(list);
					System.out.println("\n-----------------\nEnter: ");
				}
				else
				{
					System.out.println("No students. Nothing to display."
							+ "\n-----------------\nEnter: ");
				}
				break;

			case 9: //Quit
				System.out.println("System abort. Thank you.");
				sentinel = false;
				break;

			}//Switch End	
		}//Loop End




	}//Main Method End

	//This is to keep things simple
	static void PrintMenu(){
		System.out.println(	"Menu\n1) Insert at first\n"
				+ "2) Insert at last \n3) Insert After\n"
				+ "4) Insert Before \n5) Delete First\n"
				+ "6) Delete Last \n7) Sort "
				+ "\n8) Display \n9) Quit");
	}

	//duplicate ID checker and checks for letters
	public static boolean duplicate(String StringIDChecker, RecordList list)
	{

		int CheckerID = Integer.valueOf(StringIDChecker);
		Link current = list.first;
		while(current != null)
		{
			int currentID = Integer.valueOf(current.Data.getID());

			if (currentID == CheckerID)
			{
				System.out.println("This is a duplicate ID."
						+ "\n\nEnter: ");
				return true;
			}
			current = current.next;
		}
		return false;
	}

	//Checks for numbers in Name inputs
	public static boolean ValidName(String name)
	{
		if (name.matches(".*[0-9].*")){ 
			System.out.println("Name must contain only letters"
					+ "\n\nEnter: "); 
			return false;
		} 
		return true;
	}



}//Class End
