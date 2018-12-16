import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

	static char[][] map = null;
	static Vector<Player> playersVec = new Vector<Player>();

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		Vector<char[]> lineArrVec = new Vector<char[]>();

		while ((line = br.readLine()) != null) {
			lineArrVec.add(line.toCharArray());
		}
		
		char[][] tmpArr = new char[lineArrVec.size()][];
		for(int i = 0; i < lineArrVec.size(); i++) {
			tmpArr[i] = lineArrVec.get(i);
		}
		
		map = new char[tmpArr[0].length][tmpArr.length];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map[i].length; j++) {
				map[i][j] = tmpArr[j][i];
			}
		}
		
		while(!isGameEnd()) {
			for(int i = 0; i < playersVec.size(); i++) {
				playersVec.get(i).tick();
				printMap();
			}
		}
		
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				if(map[j][i] == 'E') {
					playersVec.add(new Player(j, i, 'E'));
				} else if(map[j][i] == 'G') {
					playersVec.add(new Player(j, i, 'G'));
					
				}
			}
		}
	}
	
	static boolean isGameEnd() {
		int teamCount = 0;
		for(int i = 0; i < playersVec.size(); i++) {
			if(playersVec.get(i).team == 'E') {
				teamCount |= 0x01;
			}
			if(playersVec.get(i).team == 'G') {
				teamCount |= 0x02;
			}
		}
		
		if(teamCount == 0x03) {
			return true;
		} else {
			return false;
		}
	}

	static void printMap() {
		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[j][i]);
			}
			System.out.println();
		}
	}
}
