import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) throws IOException {

		String initialState = "";
		Vector<String> ruleString = new Vector<String>();
		Vector<Boolean> ruleBool = new Vector<Boolean>();

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line = br.readLine();
		initialState = line.replace("initial state: ", "");
		while ((line = br.readLine()) != null) {
			if (line.matches(".* => #")) {
				ruleBool.add(true);
			} else {
				ruleBool.add(false);
			}

			line = line.replace(" => #", "");
			line = line.replace(" => .", "");
			line = line.replace(".", "\\.");

			ruleString.add(line);
		}

		for (int i = 0; i < 2; i++) {
			initialState = "." + initialState + ".";
		}

		System.out.println(0 + " : " + initialState);
		for (int j = 0; j < 20; j++) {
			char[] tmp = new char[initialState.length()];
			for (int i = 0; i < ruleString.size(); i++) {
				Pattern pattern = Pattern.compile(ruleString.get(i));
				Matcher matcher = pattern.matcher(initialState);

				while (matcher.find()) {
					int pos = matcher.start();
					if (ruleBool.get(i)) {
						tmp[pos + 2] = '#';
					} else {
						tmp[pos + 2] = '.';
					}
				}
			}

			char[] arr = initialState.toCharArray();
			for (int k = 0; k < tmp.length; k++) {
				if (tmp[k] == '\0') {
					continue;
				}
				arr[k] = tmp[k];
			}

			initialState = new String(arr);

			initialState = appendDots(initialState);

			if (j <= 9) {
				System.out.println((j + 1) + " : " + initialState);
			} else {
				System.out.println((j + 1) + ": " + initialState);
			}
		}

		int sum = 0;
		for (int i = 0; i < initialState.toCharArray().length; i++) {
			if (initialState.toCharArray()[i] == '#') {
				sum += (i - 3);
			}
		}

		//3312
		System.out.println("res = " + sum);

	}

	public static String appendDots(String initialState) {
		char[] arr = initialState.toCharArray();

		if (arr[0] != '.') {
			initialState = "." + initialState;
			initialState = "." + initialState;
		} else if (arr[1] != '.') {
			initialState = "." + initialState;
		}

		if (arr[arr.length - 1] != '.') {
			initialState += ".";
			initialState += ".";
		} else if (arr[arr.length - 2] != '.') {
			initialState += ".";
		}

		return initialState;
	}
}
