import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	static char[][] charArr = new char[10][10];
	static char[][] charArrCopy = null;

	static int openCount = 0;
	static int treeCount = 0;
	static int lumberCount = 0;

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("example.txt"));
		String line;

		for (int i = 0; ((line = br.readLine()) != null); i++) {
			charArr[i] = line.toCharArray();
		}

		for (int i = 0; i < 10; i++) {
			printMap();
			charArrCopy = charArr.clone();
			tick();
			charArr = charArrCopy;
			System.out.println();
		}
	}

	static void tick() {
		for (int i = 0; i < charArr.length; i++) {
			for (int j = 0; j < charArr[i].length; j++) {
				openCount = 0;
				treeCount = 0;
				lumberCount = 0;

				if (i + 1 < charArr.length) {
					countAdjacent(charArr[i + 1][j]);
					if (j + 1 < charArr[i].length) {
						countAdjacent(charArr[i + 1][j + 1]);
					}
					if (j - 1 >= 0) {
						countAdjacent(charArr[i + 1][j - 1]);
					}
				}
				if (i - 1 >= 0) {
					countAdjacent(charArr[i - 1][j]);
					if (j - 1 >= 0) {
						countAdjacent(charArr[i - 1][j - 1]);
					}
					if (j + 1 < charArr[i].length) {
						countAdjacent(charArr[i - 1][j + 1]);
					}
				}
				if (j - 1 >= 0) {
					countAdjacent(charArr[i][j - 1]);
				}
				if (j + 1 < charArr[i].length) {
					countAdjacent(charArr[i][j + 1]);
				}
				setAfterMinute(i, j);
			}
		}
	}

	static void setAfterMinute(int x, int y) {
		switch (charArr[x][y]) {
		case '.':
			if (treeCount >= 3) {
				charArrCopy[x][y] = '|';
			}
			break;
		case '|':
			if (lumberCount >= 3) {
				charArrCopy[x][y] = '#';
			}
			break;
		case '#':
			if (lumberCount >= 1 && treeCount >= 1) {

			} else {
				charArrCopy[x][y] = '.';
			}
			break;
		default:
			System.out.println("Should never happen 2");
		}
	}

	static void countAdjacent(char element) {
		switch (element) {
		case '.':
			openCount++;
			break;
		case '|':
			treeCount++;
			break;
		case '#':
			lumberCount++;
			break;
		default:
			System.out.println("Should never happen 1");
		}
	}

	static void printMap() {
		for (int i = 0; i < charArr.length; i++) {
			for (int j = 0; j < charArr[i].length; j++) {
				System.out.print(charArr[i][j]);
			}
			System.out.println();
		}
	}
}
