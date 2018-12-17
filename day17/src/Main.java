import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

	static char[][] charArr = new char[14][20];
	static int lastLine = 0;

	public static void main(String[] args) throws IOException {
		Vector<String[]> allLines = new Vector<String[]>();

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;

		while ((line = br.readLine()) != null) {
			allLines.add(line.split(", "));
		}

		for (int i = 0; i < charArr[0].length; i++) {
			for (int j = 0; j < charArr.length; j++) {
				charArr[j][i] = '.';
			}
		}

		// charArr[500][0] = '+'
		// charArr[500][1] = '|'
		charArr[5][0] = '+';
		charArr[5][1] = '|';

		for (int i = 0; i < allLines.size(); i++) {
			boolean isXDyn = false;
			String stringX = "";
			String stringY = "";
			if (allLines.get(i)[0].matches("x=.*")) {
				stringX = allLines.get(i)[0].replace("x=", "");
				stringY = allLines.get(i)[1].replace("y=", "");
			} else {
				stringY = allLines.get(i)[0].replace("y=", "");
				stringX = allLines.get(i)[1].replace("x=", "");
				isXDyn = true;
			}

			if (isXDyn) {
				stringX = stringX.replace("..", ".");
				String[] split = stringX.split("\\.");
				int start = Integer.parseInt(split[0]);
				int stop = Integer.parseInt(split[1]);

				int Y = Integer.parseInt(stringY);

				for (int j = start; j <= stop; j++) {
					charArr[j][Y] = '#';
				}

			} else {
				stringY = stringY.replace("..", ".");
				String[] split = stringY.split("\\.");
				int start = Integer.parseInt(split[0]);
				int stop = Integer.parseInt(split[1]);

				int X = Integer.parseInt(stringX);

				for (int j = start; j <= stop; j++) {
					charArr[X][j] = '#';
				}
			}
		}

		while (checkBounds()) {
			tick();
			printMap();
			System.out.println("---------------------------------");
		}

	}

	static void tick() {
		fallWater();
		lastLine++;
	}

	static void fallWater() {
		for (int i = 0; i < charArr.length; i++) {
			if (charArr[i][lastLine] == '|') {
				if (charArr[i][lastLine + 1] == '.') {
					charArr[i][lastLine + 1] = '|';
				} else {
					levelWater(i, lastLine);
					break;
				}
			}
		}
	}

	static void levelWater(int startX, int startY) {
		boolean noBorders = false;
		for (int i = startX; i < charArr.length-1; i++) {
			if (charArr[i][startY] == '#') {
				break;
			}
			if (charArr[i + 1][startY + 1] == '.') {
				noBorders = true;
			}
		}
		for (int i = startX - 1; i >= 0 + 1; i--) {
			if (charArr[i][startY] == '#') {
				break;
			}
			if (charArr[i - 1][startY + 1] == '.' && charArr[i][startY] != '#') {
				noBorders = true;
			}
		}

		if (noBorders) {
			lastLine = startY;
			for (int i = startX; i < charArr.length-1; i++) {
				if (charArr[i][startY] == '#') {
					break;
				}
				if (charArr[i - 1][startY + 1] != '.') {
					charArr[i][startY] = '|';
				}
			}

			for (int i = startX - 1; i >= 0 + 1; i--) {
				if (charArr[i][startY] == '#') {
					break;
				}
				if (charArr[i + 1][startY + 1] != '.') {
					charArr[i][startY] = '|';
				}
			}
			return;
		}

		for (int i = startX; i < charArr.length; i++) {
			if (charArr[i][startY] == '#') {
				break;
			}
			charArr[i][startY] = '~';
		}

		for (int i = startX - 1; i >= 0; i--) {
			if (charArr[i][startY] == '#') {
				break;
			}
			charArr[i][startY] = '~';
		}

		levelWater(startX, startY - 1);
	}

	static boolean checkBounds() {
		if (lastLine >= charArr.length) {
			return false;
		}
		return true;
	}

	static void printMap() {
		for (int i = 0; i < charArr[0].length; i++) {
			for (int j = 0; j < charArr.length; j++) {
				System.out.print(charArr[j][i]);
			}
			System.out.println();
		}
	}
}
