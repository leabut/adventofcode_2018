import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	static int[][] matrix = new int[300][300];

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line = br.readLine();
		int serialNumber = Integer.parseInt(line);

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				int tmp = i + 1 + 10;
				tmp *= (j + 1);
				tmp += serialNumber;
				tmp *= (i + 1 + 10);
				tmp /= 100;
				tmp %= 10;
				tmp -= 5;

				matrix[i][j] = tmp;
			}
		}

		int max = -1;
		int posX = 0;
		int posY = 0;
		for (int i = 0; i < matrix.length - 3; i++) {
			for (int j = 0; j < matrix[i].length - 3; j++) {
				int sum = 0;
				if (i + 3 >= matrix.length || j + 3 >= matrix[i].length) {
					continue;
				}
				for (int k = 0; k < 3; k++) {
					for (int m = 0; m < 3; m++) {
						sum += matrix[i + k][j + m];
					}
				}
				if (max < sum) {
					posX = i + 1;
					posY = j + 1;
					max = sum;
				}
			}
		}

		System.out.println("max = " + max + ", X = " + posX + ", Y = " + posY);

		max = -1;
		posX = 0;
		posY = 0;
		int size = 0;
		for (int f = 0; f < matrix.length; f++) {
			for (int i = 0; i < matrix.length - f; i++) {
				for (int j = 0; j < matrix[i].length - f; j++) {
					int sum = 0;
					if (i + f >= matrix.length || j + f >= matrix[i].length) {
						continue;
					}
					for (int k = 0; k < f; k++) {
						for (int m = 0; m < f; m++) {
							sum += matrix[i + k][j + m];
						}
					}
					if (max < sum) {
						posX = i + 1;
						posY = j + 1;
						max = sum;
						size = f;
					}
				}
			}
		}
		
		System.out.println("max = " + max + ", size = " + size + ", X = " + posX + ", Y = " + posY);
	}
}
