/*
	 Generate 500 random numbers and create a method to print the nth smallest number in a programming language of your choice.
*/

package question3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomNumGenerator {


	public static void main(String[] args) {
		
		Random rand = new Random();
		List<Integer> randList = new ArrayList<Integer>();
		for(int i=0; i<500;i++) {
			int randNumber = rand.nextInt(1000)+1;
			randList.add(randNumber);
		}
			
		printSmallestRandomNumbers(randList, 10);
		
	}
	
	public static void printSmallestRandomNumbers(List<Integer> randList, int nth) {
		
		Collections.sort(randList);
		System.out.println(randList.get(nth-1));

	}

}
