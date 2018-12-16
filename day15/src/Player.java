import java.util.Vector;

public class Player {
	enum Direction {
		LEFT, UP, RIGHT, DOWN
	}

	char[][] mapCopy = null;
	int X = 0;
	int Y = 0;
	char team = 'F';
	Direction lastDirection = Direction.LEFT;
	int hp = 200;
	int attackPower = 3;

	Player(int X, int Y, char team) {
		this.X = X;
		this.Y = Y;
		this.team = team;
	}

	void tick() {
		int[] nextPos = calcDistances();

		if (nextPos != null) {
			Main.map[X][Y] = '.';
			int nextX = nextPos[0];
			int nextY = nextPos[1];
			Main.map[nextX][nextY] = team;
		}

		Player target = getTarget();
		if (target != null) {
			target.getDamaged(this.attackPower);
		}
	}

	private int[] calcDistances() {
		mapCopy = Main.map.clone();
		int lastX = X;
		int lastY = Y;
		
		int[] openPlace = getOpenPlace(lastX, lastY);
		while(openPlace != null) {
			lastX = openPlace[0];
			lastY = openPlace[1];
			openPlace = getOpenPlace(lastX, lastY);
		}
		
		int[] res = { 0, 0 };
		return res;
	}

	private int[] getOpenPlace(int startX, int startY) {
		if(mapCopy[startX][startY+1] == '.') {
			int[] res = {startX, startY+1};
			return res;
		}
		if(mapCopy[startX][startY-1] == '.') {
			int[] res = {startX, startY-1};
			return res;
		}
		if(mapCopy[startX+1][startY] == '.') {
			int[] res = {startX+1, startY};
			return res;
		}
		if(mapCopy[startX-1][startY+1] == '.') {
			int[] res = {startX-1, startY};
			return res;
		}
		return null;
	}

	private Player getTarget() {
		Player target = null;
		Vector<Player> possibleTarget = new Vector<Player>();
		for (int i = 0; i < Main.playersVec.size(); i++) {
			if (!isEnemy(Main.playersVec.get(i))) {
				continue;
			}
			if (Main.playersVec.get(i).X == X && Main.playersVec.get(i).Y == Y - 1) {
				possibleTarget.add(Main.playersVec.get(i));
			}
			if (Main.playersVec.get(i).X == X && Main.playersVec.get(i).Y == Y + 1) {
				possibleTarget.add(Main.playersVec.get(i));
			}
			if (Main.playersVec.get(i).X == X - 1 && Main.playersVec.get(i).Y == Y) {
				possibleTarget.add(Main.playersVec.get(i));
			}
			if (Main.playersVec.get(i).X == X + 1 && Main.playersVec.get(i).Y == Y) {
				possibleTarget.add(Main.playersVec.get(i));
			}
		}

		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for (int i = 0; i < possibleTarget.size(); i++) {
			if (possibleTarget.get(i).Y < minY) {
				minY = possibleTarget.get(i).Y;
				minX = possibleTarget.get(i).X;
				target = possibleTarget.get(i);
			}
			if (possibleTarget.get(i).Y <= minY && possibleTarget.get(i).X < minX) {
				minX = possibleTarget.get(i).X;
				target = possibleTarget.get(i);
			}
		}

		return target;
	}

	private boolean isEnemy(Player player) {
		if (this.team == player.team) {
			return false;
		} else {
			return true;
		}
	}

	public void getDamaged(int damage) {
		hp -= damage;
		if (hp <= 0) {
			Main.map[X][Y] = '.';
			Main.playersVec.remove(this);
		}
	}
}
