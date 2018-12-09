import java.io.IOException;
import java.util.Vector;

public class Main {

	static Vector<Long> marbles = new Vector<Long>();

	public static void main(String[] args) throws IOException {

		int nrPlayers = 446;
		int lastMarble = 71522 * 100;

		long[] playerValue = new long[nrPlayers];

		marbles.add((long) 0);
		marbles.add((long) 1);
		int marbleId = 2;

		int currentMarble = 1;
		int currentPlayer = 1;
		while (marbleId < lastMarble) {
			if (marbleId % 23 != 0) {
				currentMarble = (currentMarble + 2) % marbles.size();
				if (currentMarble == 0) {
					currentMarble = marbles.size();
				}
				marbles.add(currentMarble, (long) marbleId);
			} else {
				playerValue[currentPlayer] += marbleId;

				currentMarble -= 7;
				currentMarble = Math.floorMod(currentMarble, marbles.size());

				playerValue[currentPlayer] += marbles.get(currentMarble);

				marbles.remove(currentMarble);

				currentMarble = Math.floorMod(currentMarble, marbles.size());
			}
			
			/*
			System.out.print("[" + (currentPlayer+1) + "] ");
			for(int i = 0; i < marbles.size(); i++) {
				System.out.print(marbles.get(i) + " ");
			}
			System.out.println();
			*/
			
			currentPlayer = (currentPlayer + 1) % nrPlayers;
			marbleId++;

		}

		long max = -1;
		int player = 0;
		for (int i = 0; i < playerValue.length; i++) {
			if (playerValue[i] > max) {
				max = playerValue[i];
				player = i + 1;
			}
		}

		System.out.println("res = " + max + " player = " + player);
	}
}
