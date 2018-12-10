import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

	static Vector<CPoint> allPoints = new Vector<CPoint>();
	static boolean[][] res = new boolean[300][300];

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.replace("position=<", "");
			line = line.replace("> velocity=<", ",");
			line = line.replace(">", "");
			line = line.replace(" ", "");
			String[] lineSplit = line.split(",");
			allPoints.add(new CPoint(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1]),
					Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[3])));
		}

		//10519
		int counter = 0;
		long area = Long.MAX_VALUE;
		int[] minP;
		int[] maxP;
		boolean stop = false;
		while (counter != 10519) {
			for (int i = 0; i < allPoints.size(); i++) {
				allPoints.get(i).tick();
			}
			
			minP = minXY();
			maxP = maxXY();
			long tmpArea = (maxP[0] - minP[0]) * (maxP[1] - minP[1]);
			tmpArea = Math.abs(tmpArea);
			if(tmpArea < area) {
				area = tmpArea;
				//System.out.println(counter);
			}
			
			counter++;
		}

		printRes();
	}
	
	public static int[] minXY() {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for(int i = 0; i < allPoints.size(); i++) {
			if(minX > allPoints.get(i).posX) {
				minX = allPoints.get(i).posX;
			}
			if(minY > allPoints.get(i).posY) {
				minY = allPoints.get(i).posY;
			}
		}
		int[] tmp = {minX, minY};
		return tmp;
	}
	
	public static int[] maxXY() {
		int maxX = -1;
		int maxY = -1;
		for(int i = 0; i < allPoints.size(); i++) {
			if(maxX < allPoints.get(i).posX) {
				maxX = allPoints.get(i).posX;
			}
			if(maxY < allPoints.get(i).posY) {
				maxY = allPoints.get(i).posY;
			}
		}
		int[] tmp = {maxX, maxY};
		return tmp;
	}

	public static void printRes() {
		int[] minP = minXY();		
		for (int i = 0; i < allPoints.size(); i++) {
			allPoints.get(i).posX = allPoints.get(i).posX - minP[0];
			allPoints.get(i).posY = allPoints.get(i).posY - minP[1];
			res[allPoints.get(i).posX][allPoints.get(i).posY] = true;
		}

		boolean lineBreak = false;
		for (int i = 0; i < res.length; i++) {
			for (int j = 0; j < res[i].length; j++) {
				if (res[j][i]) {
					lineBreak = true;
					System.out.print("#");
				} else {
					if(lineBreak) {
						System.out.print(" ");
					}
				}
			}
			if (lineBreak) {
				System.out.println();
			}
			lineBreak = false;
		}
	}
}
