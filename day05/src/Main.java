import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Vector;

public class Main {
	
	static Vector<String> regExPartOne = new Vector<String>();
	static Vector<String> regExPartTwo = null;

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line = br.readLine();

		// big chars
		for (int i = 65; i < 65 + 26; i++) {
			// small chars
			for (int k = 97; k < 97 + 26; k++) {
				if (k - 32 == i) {
					regExPartOne.add(((char) i) + "" + ((char) k));
				}
			}
		}
		// small chars
		for (int i = 97; i < 97 + 26; i++) {
			// big chars
			for (int k = 65; k < 65 + 26; k++) {
				if (i - 32 == k) {
					regExPartOne.add(((char) i) + "" + ((char) k));
				}
			}
		}
		
		line = processOne(line);		
		System.out.println("res = " + line.length());

		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 26; i++) {
			String line2 = line;
			regExPartTwo = new Vector<String>(regExPartOne.size());
			regExPartTwo.setSize(regExPartOne.size());
			Collections.copy(regExPartTwo, regExPartOne);
			regExPartTwo.add(Character.toString((char) (65 + i)));
			regExPartTwo.add(Character.toString((char) (97 + i)));
			
			line2 = processTwo(line2);
			
			if(min > line2.length()) {
				min = line2.length();
			}
		}
		
		System.out.println("res2 = " + min);
	}
	
	public static String processOne(String line) {
		boolean match = true;
		while (match) {
			match = false;
			for (int i = 0; i < regExPartOne.size(); i++) {
				String tmp = line;
				line = line.replace(regExPartOne.get(i), "");
				if (!tmp.equals(line)) {
					match = true;
				}
			}
			//System.out.println(line.length());
		}
		
		return line;
	}
	
	public static String processTwo(String line) {
		boolean match = true;
		while (match) {
			match = false;
			for (int i = 0; i < regExPartTwo.size(); i++) {
				String tmp = line;
				line = line.replace(regExPartTwo.get(i), "");
				if (!tmp.equals(line)) {
					match = true;
				}
			}
			//System.out.println(line.length());
		}
		
		return line;
	}
}
