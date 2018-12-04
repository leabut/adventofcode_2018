import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {

		Vector<Tile> tileVec = new Vector<Tile>();
		int[][] map = new int[1000][1000];
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.replace("#", "");
			line = line.replace("@", "");
			line = line.replace(":", "");
			String[] elements = line.split(" ");
			String[] posXYString = elements[2].split(",");
			String[] widthHeightString = elements[3].split("x");
			
			int id = Integer.parseInt(elements[0]);
			int posX = Integer.parseInt(posXYString[0]);
			int posY = Integer.parseInt(posXYString[1]);
			int width = Integer.parseInt(widthHeightString[0]);
			int height = Integer.parseInt(widthHeightString[1]);
			
			tileVec.add(new Tile(id, posX, posY, width, height));
		}
		
		for(int i = 0; i < tileVec.size(); i++) {
			for(int k = tileVec.get(i).posX; k < tileVec.get(i).width + tileVec.get(i).posX; k++) {
				for(int m = tileVec.get(i).posY; m < tileVec.get(i).height + tileVec.get(i).posY; m++) {
					map[k][m]++;
				}
			}
		}
		
		int res = 0;
		
		for(int i = 0; i < map.length; i++) {
			for(int k = 0; k < map[i].length; k++) {
				if(map[i][k] > 1) {
					res++;
				}
			}
		}
		
		System.out.println("first res = " + res);
		
		int res2 = 0;
		boolean overlap = false;
		
		for(int i = 0; i < tileVec.size(); i++) {
			overlap = false;
			for(int k = tileVec.get(i).posX; k < tileVec.get(i).width + tileVec.get(i).posX; k++) {
				for(int m = tileVec.get(i).posY; m < tileVec.get(i).height + tileVec.get(i).posY; m++) {
					if(map[k][m] > 1) {
						overlap = true;
					}
				}
			}
			
			if(!overlap) {
				res2 = tileVec.get(i).id;
			}
		}
		
		System.out.println("second res = " + res2);
		
	}
}
