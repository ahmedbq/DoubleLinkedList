//CS3810 Data Structures and Algorithms
//Assignment #3 4/15/2016
//Ahmed B. Qureshi 700636758
//The Student class basically represents a student which contains 2 private Strings
//id and name (instance variables). The constructor sets them to a default "".
//And the overloaded constructor basically sets them to the user inputs. A shortcut
//was used for setting something equal to ID, in which you also do + "", so that
//it turns into a String. Then the getters for the ID and Name were made. No setters
//were made since Students do not normally change ID or Name. It's better to just
//delete the student and then create a new one since it contains only 2 instance
//variables. The toString basically just returns the id and name.

public class Student {

	private String id;
	private String name;

	public Student ()
	{
		id = "";
		name = "";
	}

	public Student (String newID, String newName)
	{
		id = newID + ""; //Easy shortcut to convert int to String
		name = newName;
	}

	public String getID()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String toString(){
		return "ID : " + id + "\nName : " + name + "\n\n";
	}

} //class end
