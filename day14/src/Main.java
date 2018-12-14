package day14;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	static Vector<Integer> scoreboard = new Vector<Integer>();
	
	public static void main(String[] args) {
		//int input = 260321;
		int input = 26032100;
		char[] charArr = new char[input+10];
		String input2 = "260321";
		
		int currentRecA = 0;
		int currentRecB = 1;

		scoreboard.add(3);
		scoreboard.add(7);
		scoreboard.add(1);
		scoreboard.add(0);

		while (scoreboard.size() < input + 10) {
			int sum = scoreboard.get(currentRecA) + scoreboard.get(currentRecB);

			Vector<Integer> reverse = new Vector<Integer>();
			do {
				int tmpSum = sum % 10;
				reverse.add(tmpSum);
				sum = sum / 10;
			} while (sum != 0);
			currentRecA = (currentRecA + scoreboard.get(currentRecA) + 1) % (scoreboard.size() + reverse.size());
			currentRecB = (currentRecB + scoreboard.get(currentRecB) + 1) % (scoreboard.size() + reverse.size());
			
			for(int i = reverse.size() - 1; i >= 0; i--) {
				scoreboard.add(reverse.get(i));
			}
		}
		
		String res = "";
		for(int i = input; i < scoreboard.size(); i++) {
			res += scoreboard.get(i);
		}
		
		//printSolution();
		
		System.out.println("res  = " + res);
		
		for(int i = 0; i < scoreboard.size(); i++) {
			charArr[i] = Integer.toString(scoreboard.get(i).intValue()).toCharArray()[0];
		}		
		
		int res2 = 0;
		String toSearch = new String(charArr);
		if(toSearch.contains(input2)) {
		    Pattern pattern = Pattern.compile(input2);
		    Matcher matcher = pattern.matcher(toSearch);
		    // Check all occurrences
		    if (matcher.find()) {
		        res2 = matcher.start();
		    }
		}
		
		System.out.println("res2 = " + res2);

	}
	
	static int findOccurenceOne(int ignore, int ignore2) {
		int sum = 0;
		for(int i = 0; i < scoreboard.size(); i++) {
			if(scoreboard.get(i) == 1 && i != ignore && i != ignore2) {
				sum++;
			}
		}
		
		return sum;
	}
	
	static void printSolution() {
		for(int i = 0; i < scoreboard.size(); i++) {
			System.out.print(scoreboard.get(i) + " ");
		}
		System.out.println();
	}
}
