//CS3810 Data Structures and Algorithms
//Assignment #3 4/15/2016
//Ahmed B. Qureshi 700636758
//The link class represents what a Link is. It has Data which is actually a
//Student object. It's done this way so that there is less to write, and you don't
//have to put extra lines of codes for extra instance variables. The next and 
//prev are both Link objects, otherwise you can not set them equal to another link
//or link them at all. The constructor sets Data to equal an inputted Student object,
//otherwise it will set it equal to a blank student object. The toString just
//returns the Data.

public class Link {

	//instance variables
	//Need to be public to be accessible (for this assignment).
	public Student Data;
	public Link next;
	public Link prev;

	//constructors
	public Link()
	{
		Data = new Student ("", "");
	}

	public Link(Student newData)
	{
		Data = newData;
	}
	
	//display
	public String displayDoublyLinkedList()
	{
		return Data + "";
	}

} // class end
