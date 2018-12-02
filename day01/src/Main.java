import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {

	public static void main(String[] args) throws IOException {
		int res = 0;

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			res += Integer.parseInt(line);
		}

		System.out.println("first res: " + res);

		HashMap<Integer, Boolean> hashMap = new HashMap<Integer, Boolean>();
		hashMap.put(0, true);

		boolean duplicate = false;
		int iterations = 0;
		res = 0;

		while (!duplicate) {
			iterations++;
			br = new BufferedReader(new FileReader("input.txt"));
			while ((line = br.readLine()) != null) {
				res += Integer.parseInt(line);

				if (hashMap.get(res) != null) {
					duplicate = true;
					break;
				}

				hashMap.put(res, true);
			}
		}

		System.out.println("second res: " + res + " after " + iterations + " iterations");
	}

}
