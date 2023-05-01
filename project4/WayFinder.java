/**
 * This class calculates the number of ways to reach the end
 * of an array that contains integers between 0 and 99, inclusive.
 * The calculation is performed recursively in a way that explores
 * both forward and backwards traversal through the array.
 * 
 * @author Megan Nicius
 * @version 4/10/2021
 */

package project4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class WayFinder{
	
	public WayFinder() {
		
	}
	
	private static ArrayList path = new ArrayList();
	private static ArrayList isVisited = new ArrayList();
	private static int counter = 0;
	
	/**
	 * This method stores -1 in each cell of an array list
	 * to build a structure that will eventually allow the program
	 * to keep track of which cells have been visited. 
	 * A cell containing the value -1 represents a cell that has not
	 * yet been visited.
	 * @param array String array that is made up of values from
	 * command line input.
	 * @return array list
	 */
	private static ArrayList visited(String[] array) {
		//create isVisited object
		ArrayList isVisited = new ArrayList();
		for(int i = 0; i < array.length; i++) {
			isVisited.add("-1");
		}
		return isVisited;
	}
	
	/**
	 * This method creates an array list that stores the pathway
	 * taken to reach the end of the array.
	 * @param array String array that is made up of values from
	 * command line input.
	 * @return array list that contains values from command line 
	 * input in proper format.
	 */
	private static ArrayList pathBuilder(String[] array) {
		//create new path object
		ArrayList path = new ArrayList();
		for(int i = 0; i < array.length; i++) {
			//store input into path object in desired format
			path.add(" " + array[i] + " ");
		}
		return path;
	}
	
	
	/**
	 * Checks to see if command line input meets conditions of valid
	 * input, namely that input are integers between 0 and 99, with
	 * the exception being that the last integer must be 0. Prints an
	 * error message to standard error stream if a condition for valid
	 * input is not met, or prints "No way through this puzzle." if
	 * the input values contain more than one 0.
	 * @param array String array that is made up of values from
	 * command line input.
	 */
	private static void checkConditions(String[] array){
		//search all values in input
		for(int i = 0; i < array.length; i++) {
			//check that all values are positive integers
			//if negative integers are present, print error
			//and terminate
			if(Integer.valueOf(array[i]) < 0) {
				System.err.println("ERROR: The puzzle values have to be"
						+ " positive integers.");
				System.exit(-1);
			}
			else if(Integer.valueOf(array[i]) > 99) {
				//check that values aren't greater than 99
				//if values greater than 99 are present, print
				//error and terminate
				System.err.println("ERROR: Values out of range. Values must"
						+ " not be greater than 99.");
				System.exit(-1);
			}
			else if(Integer.valueOf(array[array.length - 1]) != 0) {
				//check that last value is zero
				//if last value isn't zero, print error and exit
				System.err.println("ERROR: The last value in "
						+ "the puzzle has to be zero.");
				System.exit(-1);
		}
			else	
				continue;
		}
		
		for(int i = 0; i < array.length - 1; i++) {
			//verify that only the last value is 0
			//print "no way through this puzzle" and
			//exit program if more than one 0 is present
			if(Integer.valueOf(array[i]) == 0) {
				System.out.println("No way through this puzzle.");
				System.exit(-1);
			}
			else
				continue;
		}
	}
	
	/**
	 * This method traverses through the array by moving to the
	 * right and moving to the left until the end of the array of
	 * integers is reached. It keeps track of how many different
	 * ways it can traverse through the array before reaching the
	 * end. The distance that the method travels is dependent on
	 * the integer value located at the specified index of the
	 * array.
	 * @param array String array that is made up of values from
	 * command line input.
	 * @param index starting index of the array, initially set to
	 * 0 when the method is first called in the main method. The
	 * index changes as the array is traversed, and eventually 
	 * allows the recursive method to terminate.
	 */
	private static String traverse(String[] array, int index) {
		//set path and instantiate isVisited 
		path.set(index,"  " + array[index] + " ");
		isVisited = visited(array);
		int right; //position to travel to the right
		int left; //position to travel to the left
			if(isVisited.get(index) == "-1" && index != array.length - 1) {
				//set isVisited so program knows the index has already
				//been visited in array
				isVisited.set(index, Integer.valueOf(array[index]));
				right = index + Integer.valueOf(array[index]);
				//right is next index to the right after traveling
				//distance equal to value of current index
				left = index - Integer.valueOf(array[index]);
				//left is next index to the left after traveling
				//distance equal to value of current index
				path.set(0, "  " + array[0] + "R");
		
				if(right < array.length && isVisited.get(right) == "-1") {
					//if it is possible to go right and the program hasn't
					//already gone to that spot, try visiting the right
					path.set(right, "  " + array[right] + "R");
					return traverse(array, index + Integer.valueOf(array[index]));
				}
				else if(left > 0 && isVisited.get(left) == "-1") {
					//if it is possible to go left and the program hasn't
					//already gone to that spot, try visiting the left
					path.set(0, "  " + array[left] + "L");
					return traverse(array, index - Integer.valueOf(array[index]));	
				}
				else {
					//if path isn't found, print no way through puzzle
					//and exit
					path.set(0, "  " + array[0] + " ");
					System.out.println("No way through this puzzle.");
					System.exit(-1);
				}
			}
			//if a successful pathway is found, increase counter
			if(index == array.length - 1) {
				counter++;
			}
			//print paths and number of pathways that exist
			path.set(0, " " + array[0] + "R");
			path.set(array.length - 1, " " + 0 + " ");
			System.out.println(path);
			System.out.println("There are " + counter + 
					" ways through this puzzle.");
			return "No way through this puzzle.";
		}
	
	
	/**
	 * This main method takes in command line input that are used in
	 * the program. It calls four methods (checkConditions(),
	 * isVisited(), pathBuilder(), and traverse()). The traverse() method
	 * that is called is functioning, but it does not function entirely
	 * as expected.
	 * @param args a string array of command line input consisting of
	 * integer values
	 */
	public static void main(String[] args) {
		checkConditions(args);
		isVisited = visited(args);
		path = pathBuilder(args);
		traverse(args, 0);
	}	
}


