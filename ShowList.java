//Part: 2
// Written by: Patrick Keenan 40175307
// -----------------------------------
package Assignment_4;

import java.util.Scanner;
import java.util.NoSuchElementException;
/**
 * <p>Author: Patrick Keenan, Student ID: 40175307 </p>
 * <p>COMP 249 </p>
 * <p>Assignment 4 </p>
 * <p>Due Date: April 24 2021 </p>
 * <p>ShowList Class which acts as a linked list for shows, the ShowList object is made of the size and head attributes.
 * <p>Size is an integer, head is a ShowNode </p>
 */
public class ShowList {
/**
 * Private class ShowNode, contains one TVShow object (show) and one ShowNode object (node)
 * 
 */
	private class ShowNode{
		private TVShow show;
		private ShowNode node;
		/**
		 * Default constructor for ShowNode, creates a ShowNode with null attributes
		 */
		private ShowNode() {
			show = null;
			node = null;
		}
		/**
		 * Parameterized constructor for ShowNode, creates a ShowNode with a TVShow and ShowNode
		 * @param show
		 * @param node
		 */
		private ShowNode(TVShow show, ShowNode node) {
			this.show=show;
			this.node=node;
		}
		/**
		 * Copy constructor for ShowNode, copies an existing ShowNode object and makes an identical ShowNode object
		 * @param aNode
		 */
		private ShowNode(ShowNode aNode) {
			show=aNode.show;
			node=aNode.node;
		}
		/**
		 * Clone method for ShowNode
		 */
		public ShowNode clone() throws CloneNotSupportedException{
			return new ShowNode(this);
		}
		/**
		 * Returns show of the ShowNode
		 * @return
		 */
		public TVShow getShow() {
			return show;
		}
		/**
		 * Returns node of the ShowNode
		 * @return
		 */
		public ShowNode getNode() {
			return node;
		}
		/**
		 * Sets the show of the ShowNode
		 * @param show
		 */
		public void setShow(TVShow show) {
			this.show=show;
		}
		/**
		 * Sets the node of the ShowNode
		 * @param node
		 */
		public void setNode(ShowNode node) {
			this.node = node;
		}
		
	}
	private ShowNode head;
	private int size;
	/**
	 * Default constructor for ShowList, creates a ShowList of size 0 with a null head
	 */
	public ShowList() {
		head=null;
		size=0;
	}
	/**
	 * Copy constructor for ShowList
	 * @param aList
	 * @throws CloneNotSupportedException
	 */
	public ShowList(ShowList aList) throws CloneNotSupportedException {
		head = aList.head.clone();
	    size = aList.size;
	}
	
	/**
	 * Adds a TVShow to the begging of the ShowList
	 * @param aShow
	 */
	public void addToStart(TVShow aShow) {
		if (size==0) {
			ShowNode sn = new ShowNode(aShow, null);
			head=sn;
			sn=null;
		}
		else{
			ShowNode sn = new ShowNode(aShow,head);
			head = sn;
			sn=null;		
		}
		size++;
	}
	
	/**
	 * Inserts a TVShow at a certain spot in the ShowList's index
	 * @param s
	 * @param index
	 */
	public void insertAtIndex(TVShow s, int index)
	{
		if (index > size-1)
		{
			System.out.println("Error: Index is out of range.");
			throw new NoSuchElementException();
		}
		int i = 0;
		ShowNode headnode = head;
		
		// Handle the special case when insertion on head
		if (index == 0)
		{
			ShowNode newnode = new ShowNode(s, head);
			head = newnode;
			newnode = null;
		}
		else
		{
			while (i != index-1)	// Stop at the node that precedes index
			{
				headnode = headnode.node;
				i++;
			}
			// Now we are pointing at the node preceding index
			ShowNode newnode = new ShowNode(s, headnode.node);
			// Next will point to temp.next
			headnode.node = newnode;
			newnode = null;
		}
		size++;
	}
	
	/**
	 * Deletes a TVShow at a certain index from the ShowList
	 * @param index
	 */
	public void deleteFromIndex(int index) 
	{
		if (index > size-1)
		{
			System.out.println("Error: Index is out of range.");
			throw new NoSuchElementException();
		}

		int i = 0;
		ShowNode headnode = head;
		// Handle the special case when list has only one node
		if (size == 1)
		{
			head = null;
			size--;
			return;
		}
		
		// Handle the special case when deletion on head
		if (index == 0)
		{
			head = head.node;
		}
		// When deletion from the middle
		else	
		{
			while (i != index -1)	// Stop at the node that precedes index
			{
				headnode = headnode.node;
				i++;
			}
			headnode.node = headnode.node.node;
		}
		size--;

	}
	
	/**
	 * Deletes the first TVShow in the index of the ShowList
	 * @return
	 */
	public boolean deleteFromStart()
	{
		if (head != null)
		{
			head = head.node;
			return true;
		}
		else
			return false;
	}
	/**
	 * Replaces a TVShow at a certain point in the index with another TVShow 
	 * @param s
	 * @param index
	 */
	public void replaceAtIndex(TVShow s, int index)
	{
		if (index > size-1)
		{
			System.out.println("Error: index is out of range.");
			throw new NoSuchElementException();
		}
		
		int i = 0;
		ShowNode headnode = head;
		while(i != index)
		{
			headnode = headnode.node;
			i++;
		}
		headnode.show = s;
	}
	
	/**
	 * Finds a certain TVShow by its name or id in the ShowList and returns the ShowNode of that TVShow
	 * Also says how many iterations were made finding the specified show in the index.
	 * @param ShowNameorID
	 * @return
	 */
	public ShowNode find(String ShowNameorID) { //Supposed to check for ID, but checks for name too because of assignment issue
		int spot=1;
		ShowNode findNode = head;
		while (findNode!=null) {
			if (findNode.getShow().showName.equals(ShowNameorID) || findNode.getShow().showID.equals(ShowNameorID)) {
				System.out.println(spot +" iterations were made");
				return findNode;				
			}
			findNode = findNode.getNode();
			spot++;
		}
		return null;
	}
	/**
	 * Finds a certain TVShow by its id and returns the TVShow
	 * @param ShowID
	 * @return
	 */
	public TVShow findShow(String ShowID) { //Returns show instead of ShowNode when a show name is found
		ShowNode findNode = head;
		while (findNode!=null) {
			if (findNode.getShow().showID.equals(ShowID)) {
				return findNode.getShow();				
			}
			findNode = findNode.getNode();
		}
		return null;
	}
	
	/**
	 * Checks if the ShowList contains a certain show by its name or ID
	 * @param ShowName
	 * @return
	 */
	public boolean contains(String ShowNameorID) {
		if (find(ShowNameorID)!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Equals method for ShowList class
	 */
	public boolean equals (Object anObject) {
		if (anObject==null) {
			return false;
		}
		if (anObject instanceof ShowList) {
			ShowList aList = (ShowList)anObject;
			if (aList.head==null) {
				return false;
			}
			if (head.getShow().equals(aList.head.getShow())) {
				return true;
			}
			return false;
		}
		return false;
	}
}
