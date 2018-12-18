import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	
	static char[][] charArr = new char[50][50];
	static char[][] charArrCopy = null;
	static int resArr[] = new int[10000];

	static int openCount = 0;
	static int treeCount = 0;
	static int lumberCount = 0;

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;

		for (int i = 0; ((line = br.readLine()) != null); i++) {
			charArr[i] = line.toCharArray();
		}

		for (int i = 0; i < 10000; i++) {

			charArrCopy = deepCopy(charArr);
			tick();
			charArr = deepCopy(charArrCopy);
			resArr[i] = result();

			/*
			 * printMap(); System.out.println();
			 */
		}

		System.out.println("res = " + result());
		
		for(int i = 0; i < resArr.length; i++) {
			System.out.println(resArr[i]);
		}
	}
	
	static boolean charArrEQ(char[][] a, char[][] b) {
		boolean eq = true;
		for(int i = 0; i < a.length; i++) {
			for(int j = 0; j < a[i].length; j++) {
				if(a[i][j] != b[i][j]) {
					eq = false;
					break;
				}
			}
		}
		
		if(eq) {
			System.out.println("ohhh yeah :-)");
		}
		
		return eq;
	}

	static char[][] deepCopy(char[][] toCopy) {
		char[][] copy = new char[toCopy.length][toCopy[0].length];

		for (int i = 0; i < toCopy.length; i++) {
			for (int j = 0; j < toCopy[i].length; j++) {
				copy[i][j] = toCopy[i][j];
			}
		}

		return copy;
	}

	static int result() {
		int tree = 0;
		int lumber = 0;
		for (int i = 0; i < charArr.length; i++) {
			for (int j = 0; j < charArr[i].length; j++) {
				if (charArr[i][j] == '|') {
					tree++;
				}
				if (charArr[i][j] == '#') {
					lumber++;
				}
			}
		}

		return tree * lumber;
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
