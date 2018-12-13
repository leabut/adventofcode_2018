public class Cart {
	int X = 0;
	int Y = 0;
	boolean crashed = false;
	char oldCurve = '\0';

	enum Direction {
		LEFT, UP, RIGHT, DOWN, STRAIGHT
	}

	Direction lastIntDirection = Direction.RIGHT;

	Cart(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	void tick() {
		boolean restoreCurve = oldCurve != '\0';
		char tmpCurve = '\0';

		char arrow = Main.charArr[X][Y];
		Direction arrowDirection = Direction.LEFT;
		int newX = 0;
		int newY = 0;

		switch (arrow) {
		case '<':
			arrowDirection = Direction.LEFT;
			newX = X;
			newY = Y - 1;
			Main.charArr[X][Y] = '-';
			if (isArrowElement(Main.charArr[newX][newY])) {
				this.crashed = true;
				Main.charArr[newX][newY] = 'X';
			} else if (isCurve(Main.charArr[newX][newY])) {
				tmpCurve = Main.charArr[newX][newY];
				Main.charArr[newX][newY] = getDirectionAfterCurve(arrowDirection, tmpCurve);
			} else if (Main.charArr[newX][newY] == '+') {
				tmpCurve = '+';
				Main.charArr[newX][newY] = handleIntersection(arrowDirection);
			} else {
				Main.charArr[X][Y] = '-';
				Main.charArr[newX][newY] = '<';
				tmpCurve = '\0';
			}
			break;
		case '^':
			arrowDirection = Direction.UP;
			newX = X - 1;
			newY = Y;
			Main.charArr[X][Y] = '|';
			if (isArrowElement(Main.charArr[newX][newY])) {
				this.crashed = true;
				Main.charArr[newX][newY] = 'X';
			} else if (isCurve(Main.charArr[newX][newY])) {
				tmpCurve = Main.charArr[newX][newY];
				Main.charArr[newX][newY] = getDirectionAfterCurve(arrowDirection, tmpCurve);
			} else if (Main.charArr[newX][newY] == '+') {
				tmpCurve = '+';
				Main.charArr[newX][newY] = handleIntersection(arrowDirection);
			} else {
				Main.charArr[X][Y] = '|';
				Main.charArr[newX][newY] = '^';
				tmpCurve = '\0';
			}
			break;
		case '>':
			arrowDirection = Direction.RIGHT;
			newX = X;
			newY = Y + 1;
			Main.charArr[X][Y] = '-';
			if (isArrowElement(Main.charArr[newX][newY])) {
				this.crashed = true;
				Main.charArr[newX][newY] = 'X';
			} else if (isCurve(Main.charArr[newX][newY])) {
				tmpCurve = Main.charArr[newX][newY];
				Main.charArr[newX][newY] = getDirectionAfterCurve(arrowDirection, tmpCurve);
			} else if (Main.charArr[newX][newY] == '+') {
				tmpCurve = '+';
				Main.charArr[newX][newY] = handleIntersection(arrowDirection);
			} else {
				Main.charArr[X][Y] = '-';
				Main.charArr[newX][newY] = '>';
				tmpCurve = '\0';
			}
			break;
		case 'v':
			arrowDirection = Direction.DOWN;
			newX = X + 1;
			newY = Y;
			Main.charArr[X][Y] = '|';
			if (isArrowElement(Main.charArr[newX][newY])) {
				this.crashed = true;
				Main.charArr[newX][newY] = 'X';
			} else if (isCurve(Main.charArr[newX][newY])) {
				tmpCurve = Main.charArr[newX][newY];
				Main.charArr[newX][newY] = getDirectionAfterCurve(arrowDirection, tmpCurve);
			} else if (Main.charArr[newX][newY] == '+') {
				tmpCurve = '+';
				Main.charArr[newX][newY] = handleIntersection(arrowDirection);
			} else {
				Main.charArr[X][Y] = '|';
				Main.charArr[newX][newY] = 'v';
				tmpCurve = '\0';
			}
			break;
		case 'X':
			this.crashed = true;
			System.out.println("Y = " + X + ", X = " + Y);
			return;
		default:
			System.out.println("This should not happen 1");
		}

		if (restoreCurve) {
			Main.charArr[X][Y] = oldCurve;
		}

		oldCurve = tmpCurve;
		X = newX;
		Y = newY;
	}

	private char handleIntersection(Direction arrowDirection) {
		switch (lastIntDirection) {
		case LEFT:
			lastIntDirection = Direction.STRAIGHT;
			switch (arrowDirection) {
			case LEFT:
				return '<';
			case UP:
				return '^';
			case RIGHT:
				return '>';
			case DOWN:
				return 'v';
			default:
				System.out.println("This should not happen 2");
				return '\0';
			}
		case STRAIGHT:
			lastIntDirection = Direction.RIGHT;
			switch (arrowDirection) {
			case LEFT:
				return '^';
			case UP:
				return '>';
			case RIGHT:
				return 'v';
			case DOWN:
				return '<';
			default:
				System.out.println("This should not happen 3");
				return '\0';
			}
		case RIGHT:
			lastIntDirection = Direction.LEFT;
			switch (arrowDirection) {
			case LEFT:
				return 'v';
			case UP:
				return '<';
			case RIGHT:
				return '^';
			case DOWN:
				return '>';
			default:
				System.out.println("This should not happen 4");
				return '\0';
			}
		default:
			System.out.println("This should not happen 5");
			return '\0';
		}
	}

	private boolean isArrowElement(char element) {
		switch (element) {
		case 'X':
			return true;
		case '<':
			return true;
		case '^':
			return true;
		case '>':
			return true;
		case 'v':
			return true;
		default:
			return false;
		}
	}

	private char getDirectionAfterCurve(Direction currentDirection, char curve) {
		switch (currentDirection) {
		case LEFT:
			if (curve == '/') {
				return 'v';
			} else {
				return '^';
			}
		case UP:
			if (curve == '/') {
				return '>';
			} else {
				return '<';
			}
		case RIGHT:
			if (curve == '/') {
				return '^';
			} else {
				return 'v';
			}
		case DOWN:
			if (curve == '/') {
				return '<';
			} else {
				return '>';
			}
		default:
			System.out.println("This should not happen 6");
			return '\0';
		}
	}

	private boolean isCurve(char element) {
		switch (element) {
		case '/':
			return true;
		case '\\':
			return true;
		default:
			return false;
		}
	}
}
