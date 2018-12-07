import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

	public static int correctX = 0;
	public static int correctY = 0;

	public static void main(String[] args) throws IOException {

		Vector<CPoint> allPoints = new Vector<CPoint>();

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			String[] coordsString = line.split(", ");
			allPoints.add(new CPoint(Integer.parseInt(coordsString[1]), Integer
					.parseInt(coordsString[0])));
		}

		int xMin = Integer.MAX_VALUE;
		int yMin = Integer.MAX_VALUE;
		for (int i = 0; i < allPoints.size(); i++) {
			if (allPoints.get(i).getX() < xMin) {
				xMin = allPoints.get(i).getX();
			}
			if (allPoints.get(i).getY() < yMin) {
				yMin = allPoints.get(i).getY();
			}
		}

		correctX = xMin;
		correctY = yMin;

		correctX = 0;
		correctY = 0;

		for (int i = 0; i < allPoints.size(); i++) {
			int newX = allPoints.get(i).getX() - correctX;
			int newY = allPoints.get(i).getY() - correctY;
			allPoints.get(i).setLocation(newX, newY);
		}

		int xMax = -1;
		int yMax = -1;
		for (int i = 0; i < allPoints.size(); i++) {
			if (allPoints.get(i).getX() > xMax) {
				xMax = allPoints.get(i).getX();
			}
			if (allPoints.get(i).getY() > yMax) {
				yMax = allPoints.get(i).getY();
			}
		}

		CPoint[][] arr = new CPoint[xMax + 1][yMax + 1];

		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < arr[i].length; k++) {
				arr[i][k] = new CPoint();
			}
		}

		for (int i = 0; i < allPoints.size(); i++) {
			CPoint tmp = arr[allPoints.get(i).getX()][allPoints.get(i).getY()];
			tmp.id = i;
			tmp.dist = 0;
			allPoints.get(i).id = i;
		}

		for (int i = 0; i < allPoints.size(); i++) {
			for (int k = 0; k < arr.length; k++) {
				for (int m = 0; m < arr[k].length; m++) {
					int dist = manhattanDist(allPoints.get(i).getX(), allPoints
							.get(i).getY(), k, m);
					if (dist == 0) {
						continue;
					}
					if (arr[k][m].dist == dist) {
						arr[k][m].dist = 0;
						arr[k][m].id = -1;
						continue;
					}
					if (arr[k][m].dist > dist) {
						arr[k][m].dist = dist;
						arr[k][m].id = i;
					}
				}
			}
		}

		Vector<Integer> infinite = new Vector<Integer>();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (i == 0 || j == 0 || i == arr.length - 1
						|| j == arr[i].length - 1) {
					if (!infinite.contains(arr[i][j].id)) {
						infinite.add(arr[i][j].id);
					}
				}
			}
		}

		int[] fieldCount = new int[allPoints.size()];
		for (int i = 0; i < arr.length; i++) {
			for (int k = 0; k < arr[i].length; k++) {
				if (arr[i][k].id != -1 && !infinite.contains(arr[i][k].id)) {
					fieldCount[arr[i][k].id]++;
				}
			}
		}

		int max = -1;
		int maxPos = 0;
		for (int i = 0; i < fieldCount.length; i++) {
			if (fieldCount[i] > max) {
				max = fieldCount[i];
				maxPos = i;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print((char) (arr[i][j].id + 65));
			}
			System.out.println();
		}

		System.out.println("res = " + max);
	}

	public static int manhattanDist(int x, int y, int x2, int y2) {
		return Math.abs(x - x2) + Math.abs(y - y2);
	}
}
