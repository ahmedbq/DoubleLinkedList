//CS3810 Data Structures and Algorithms
//Assignment #3 4/15/2016
//Ahmed B. Qureshi 700636758
//The RecordList class has all of the methods and 2 Links representing first and
//last. First and Last are not in the Link class since when working with nodes
//there is only 1 RecordList class and only 1 of each first and last... but an
//infinite possibility of Links. Creating a RecordList class just sets the first
//and last to null since there are no nodes. The insertFirst function takes in
//a link and sets the.next = to first since it's setting the old first node to 
//become second. Then first will equal to the new first node. This is basically
//what will happen no matter if the list is empty or not. In the app class you
//can see that the Link objects are created in almost all the cases before the
//methods are called. And for isEmpty last is set to the new first also since
//the only node would be first and last. Otherwise the .prev of previous first
//node will be set to the new first node. For insertLast, it is almost the same
//as insertFirst except in the app class first and last are switched in the isEmpty
//and !isEmpty parts. And the only thing they have in common is that they both
//will set last equal to the new Node. The function deleteFirst is an easy
//function which just sets the .next of the first node to be null and then
//sets last to be null if it's empty. Otherwise the .prev of the next node will
//be set to null and the first will be set to the .next of the old first node.
//DeleteLast works in the similar manner except it focuses on last instead of 
//first and the .prev and .next kind of switch compared to DeleteFirst. insertAfter
//basically takes in another parameter which is a key for an ID. It searches for
//the node with the same key using a temporary Link object, otherwise the key
//does not exist. Then afterwards, there are 2 options. One if it's the last node
//and one if it's not. If it's the last node then it has to set the .next of the
//new node to null and not try to set the .prev of the next node (which doesn't
//(exist) to itself (causes Null Pointer Error). Otherwise it will just rewire
//the .next and .prev in a certain order so that neither nodes outside of the
//new Node will be lost and that certain .next and .prev will be pointing to
//the new Node instead (as well as setting the new Node .next and .prev).
//insertBefore is almost exactly the same as insertAfter, except the rewiring of
//the .next and .prev links are made so that the node is placed behind. So a 
//.next of the last function can now turn into a .prev in this function. The
//deleteID function works similar to some of the older functions in which it uses
//a user inputted key and tries to find it. Now if it finds it, there are 3 options
//that may happen, one is if it's the first element, another if it's the last
//and also if it's neither first or last. If it's first or last, it rewires the
//.next and .prev such that they are pointing to null depending upon if it's first
//or last. If it's inbetween, then all it does it takes the .prev of the next node
//to be equal to the .prev of the deleted node, and sets current.prev.next to be
//equal to current.next so that the current (or whatever name to represent the
//node wished to be deleted) is completely ignored and not pointed at. It should
//be noted that == does not work when comparing the ID, since it's a String not
//int. So blah or .equals were used in a lot of cases. the toString creates 
//a link that's equal to the first node so that it can go through all of them. Then
//a String to collect the data from them is created. Now a while loop is made in 
//which the link goes to the next link in the end, but before that the String 
//collects the data from the display function of the link. After the loop the 
//String is simply returned and shows all of the data it collected. In this class 
//the last and first methods were not public so their setters and getters were 
//created. Also the isEmpty function just checks if the getFirst is equal to null, 
//since it's only null if there's no link at all. The insertionSort method was 
//basically created by also looking at the insertionSort method created for arrays,
//but just tried to convert it for the doubly linked list. First it checks if 
//getFirst is null because that signifies if it's empty or not. Also a link was 
//made to represent a marker and another to represent current. Since current is the
//second node, it can compare it to the first one instead of some null error. Then 
//a nested loop was created which both run if marker and current.prev hit null 
//respectively. Then the valueOf function is used to turn the Strings into int in 
//order to see which ID is bigger. Then inside the if statement the swap function 
//occurs. Outside each while loop the current and marker go to the next node 
//respectively. The swap class accepts both of the Links. Then it creates Links 
//representing the next and prev of both nodes. What the swap method is supposed 
//to do is to just swap the nodes. The .next and .prev are just switched around. 
//And the first and last are supposed to be fixed depending on the situation but 
//that was not implemented and should have.


public class RecordList{

	public Link first;
	public Link last;
	private Link placeholder; //holds returned values from delete functions

	public RecordList()
	{
		first = null;
		last = null;
		placeholder = null;
	}

	//Edited the inserts so that the booleans stay in App class
	public void insertFirst(Link newLink){

		//These 2 are in empty version too
		newLink.next = first;
		first = newLink;

	}

	public void insertLast(Link newLink)
	{
		//This is in empty version too
		last = newLink;
	}

	public Link deleteFirst()
	{
		//made a temp value to return in the deleted value in the end
		Link temp = first;

		if(first.next == null)
		{
			last = (null);
		}

		else { first.next.prev = null;}

		first = first.next;

		placeholder = temp;
		return temp;
	}

	public Link deleteLast()
	{

		Link temp = last;

		if (first.next == null)
		{
			last = null;
			first = null;
		}
		else{ last.prev.next = null;
		last = last.prev;
		}


		placeholder = temp;
		return temp;
	}

	//For the key functions, the key 
	//will be the ID because it is unique
	//These insert the newData after the key
	public boolean insertAfter(String key, Student newData)
	{
		Link current = first;
		//When key DOES NOT equal to ID (False = 1)
		int keyInt = Integer.valueOf(key);
		int currentInt = Integer.valueOf(current.Data.getID());


		while(keyInt != currentInt)
		{
			current = current.next;

			//if current == null, means if it reached the end
			if(current == null)
			{
				return false;
			}
			currentInt = Integer.valueOf(current.Data.getID()); 
			//Updates it once it finds key
		}


		Link newLink = new Link(newData);

		//if current == last, means if current is pointing to
		//the last Student/node, and it has the key. This is comparing
		//the ID's of the current and the last element. It is careful to
		int lastInt = Integer.valueOf(last.Data.getID());

		if(lastInt == keyInt)
		{
			newLink.next = null;
			last = newLink;
			newLink.prev = current;
		}

		//This else refers to when current is pointing to a Student
		//that is not the last node
		else 
		{
			newLink.next = current.next;
			newLink.prev = current;
			current.next.prev = newLink;
		}
		current.next = newLink;

		return true;
	}

	public boolean insertBefore(String key, Student newData) {
		Link current = first;
		//When key DOES NOT equal to ID (False = 1)

		int currentInt = Integer.valueOf(current.Data.getID());
		int keyInt = Integer.valueOf(key);
		while(currentInt != keyInt)
		{
			current = current.next;

			//if current == null, means if it reached the end
			if(current == null)
			{
				return false;
			}
			currentInt = Integer.valueOf(current.Data.getID());
		}


		Link newLink = new Link(newData);

		//Similar to insertAfter except it has to have a special case if
		//the key is in the first element instead of the last one.

		int firstInt = Integer.valueOf(first.Data.getID());
		if(currentInt == firstInt)
		{
			newLink.prev = null;
			first = newLink;
			newLink.next = current;
		}

		//This else refers to when current is pointing to a Student
		//that is not the first node
		else 
		{
			newLink.next = current;
			newLink.prev = current.prev;
			current.prev.next = newLink;
		}
		current.prev = newLink;

		return true;
	}

	//Sorting method
	//Assumes that the list isn't empty
	//If it's empty the app class will catch it
	//Sorts by ID number

	public void insertionSort()
	{
		Link marker = first.next;
		Link current = marker.prev;

		while(marker != null){
			current = marker.prev;
			while(current != null){
				//this converts the String into Int
				int tempCurrentNext = Integer.valueOf(current.next.Data.getID());
				int tempCurrent = Integer.valueOf(current.Data.getID());
				//if(tempID < temp2ID ) //DEBUG: Replaced

				if(tempCurrentNext < tempCurrent)
					//if Left side is bigger than Right
				{
					swap(current, current.next);
				}
				current = current.prev;
			}
			marker = marker.next;
		}
	}


	public void swap(Link LeftLink, Link NextLink)
	{      
		int firstID = Integer.valueOf(first.Data.getID());
		int lastID = Integer.valueOf(last.Data.getID());
		int LeftID = Integer.valueOf(LeftLink.Data.getID());
		int NextID = Integer.valueOf(NextLink.Data.getID());

		//if LeftLink == first, first value will be deleted than inserted
		//after the first variable
		if(firstID == LeftID)
		{
			deleteFirst();
			insertAfter(first.Data.getID(), placeholder.Data);	
			return;
		}

		//if NextLink == last, then last will be deleted and then inserted before
		//the second last variable

		if(lastID == NextID)
		{
			deleteLast();
			insertBefore(last.Data.getID(), placeholder.Data);
			return;
		}

		//else they will simply switch
		//did not create deleteID() so did it manually
		if(firstID != LeftID && lastID != NextID)
		{
			LeftLink.next = NextLink.next;
			LeftLink.prev.next = NextLink;
			NextLink.next.prev = LeftLink;
			NextLink.prev = LeftLink.prev;
			LeftLink.prev = NextLink;
			NextLink.next = LeftLink;
			return;
		}

	} // end swap


	//toString
	public String toString(){
		Link checkAll = first;
		String collection = "First\n\n";

		//collection keeps collecting the data until it hits the end
		while(checkAll != null)
		{
			collection += checkAll.displayDoublyLinkedList();

			checkAll = checkAll.next;
		}

		collection += "Last";

		return collection;
	}

	public boolean isEmpty()
	{
		return first == null;
	}

} //class end
