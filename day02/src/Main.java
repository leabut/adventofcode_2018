import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {

		int duplicates = 0;
		int triplets = 0;

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			HashMap<Character, Integer> hashMap = new HashMap<Character, Integer>();
			int occurence = 0;
			char[] characters = line.toCharArray();
			for (int i = 0; i < characters.length; i++) {
				if (hashMap.get(characters[i]) != null) {
					occurence = hashMap.get(characters[i]);
				} else {
					occurence = 0;
				}
				hashMap.put(characters[i], ++occurence);
			}

			boolean stopCountDuplicates = false;
			boolean stopCountTriplets = false;
			Iterator<Character> hashMapIterator = hashMap.keySet().iterator();
			while (hashMapIterator.hasNext()) {
				int count = hashMap.get(hashMapIterator.next());
				if (count == 2 && !stopCountDuplicates) {
					duplicates++;
					stopCountDuplicates = true;
				}
				if (count == 3 && !stopCountTriplets) {
					triplets++;
					stopCountTriplets = true;
				}
			}
		}

		System.out.println("first res: " + duplicates + " * " + triplets + " = " + duplicates * triplets);

		char[] resOne = null;
		char[] resTwo = null;
		char[] totalRes = null;
		br = new BufferedReader(new FileReader("input.txt"));
		Vector<String> stringVec = new Vector<String>();
		while ((line = br.readLine()) != null) {
			stringVec.add(line);
		}

		for (int i = 0; i < stringVec.size(); i++) {
			for (int k = 0; k < stringVec.size(); k++) {
				if (i <= k) {
					continue;
				}
				char[] charArrayOne = stringVec.get(i).toCharArray();
				char[] charArrayTwo = stringVec.get(k).toCharArray();
				char[] charArrayDiff = new char[charArrayOne.length];

				for (int j = 0; j < charArrayDiff.length; j++) {
					charArrayDiff[j] = (char) (charArrayOne[j] - charArrayTwo[j]);
				}

				int diffCount = 0;
				for (int j = 0; j < charArrayDiff.length; j++) {
					if (charArrayDiff[j] != 0) {
						diffCount++;
					}
				}

				if (diffCount == 1) {
					resOne = charArrayOne;
					resTwo = charArrayTwo;
				}
			}
		}

		int diffPos = 0;
		for (int i = 0; i < resOne.length; i++) {
			if (resOne[i] != resTwo[i]) {
				diffPos = i;
				break;
			}
		}

		totalRes = new char[resOne.length - 1];
		for (int i = 0; i < resOne.length; i++) {
			if (i < diffPos) {
				totalRes[i] = resOne[i];
			} else {
				totalRes[i - 1] = resOne[i];
			}

		}

		System.out.println("second res: " + new String(resOne) + " " + new String(resTwo) + " differs by char: "
				+ resOne[diffPos] + "/" + resTwo[diffPos]);
		System.out.println("second total res: " + new String(totalRes));
	}

}
