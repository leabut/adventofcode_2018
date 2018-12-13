import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

	static char[][] charArr = null;

	public static void main(String[] args) throws IOException {

		Vector<Cart> cartsVec = new Vector<Cart>();

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		Vector<char[]> lineArrVec = new Vector<char[]>();

		while ((line = br.readLine()) != null) {
			lineArrVec.add(line.toCharArray());
		}

		charArr = new char[lineArrVec.size()][lineArrVec.get(0).length];
		for (int i = 0; i < lineArrVec.size(); i++) {
			charArr[i] = lineArrVec.get(i);
		}

		for (int i = 0; i < charArr.length; i++) {
			for (int j = 0; j < charArr[i].length; j++) {
				if (isArrowElement(charArr[i][j])) {
					cartsVec.add(new Cart(i, j));
				}
			}
		}

		boolean crashed = false;
		Vector<Cart> tmpCartVec = new Vector<Cart>();

		/*
		while (!crashed) {
			/*
			System.out.println();
			printMap();
			System.out.println();
			/
			
			while(cartsVec.size() != 0) {
				Cart tmpCart = getMinCart(cartsVec);
				tmpCart.tick();
				tmpCartVec.add(tmpCart);
				cartsVec.remove(tmpCart);
			}
			cartsVec = tmpCartVec;
			tmpCartVec = new Vector<Cart>();
			for (int i = 0; i < cartsVec.size(); i++) {
				if (cartsVec.get(i).crashed) {
					crashed = true;
				}
			}
		}
		
		System.out.println();
		printMap();
		System.out.println();
		*/
		
		tmpCartVec = new Vector<Cart>();
		int counter = 0;
		//while (cartsVec.size() != 1) {
		for(int j = 0; j < 14048 - 1; j++) {
			while(cartsVec.size() != 0) {
				Cart tmpCart = getMinCart(cartsVec);
				if(!tmpCart.crashed) {
					tmpCart.tick();
					tmpCartVec.add(tmpCart);
				}
				cartsVec.remove(tmpCart);
			}
			cartsVec = tmpCartVec;
			tmpCartVec = new Vector<Cart>();
			for (int i = 0; i < cartsVec.size(); i++) {
				if (cartsVec.get(i).crashed) {
					cartsVec.remove(cartsVec.get(i));
				}
			}
			counter++;
		}
		
		System.out.println();
		printMap();
		System.out.println();
		
		System.out.println("X = " + cartsVec.get(0).Y + ", Y = " + cartsVec.get(0).X);
		System.out.println(counter);
	}

	static Cart getMinCart(Vector<Cart> cartsVec) {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		for (int i = 0; i < cartsVec.size(); i++) {
			if (cartsVec.get(i).Y < minY) {
				minY = cartsVec.get(i).Y;
			}
		}

		for (int i = 0; i < cartsVec.size(); i++) {
			if (cartsVec.get(i).Y == minY) {
				if (cartsVec.get(i).X < minX) {
					minX = cartsVec.get(i).X;
				}
			}
		}

		for (int i = 0; i < cartsVec.size(); i++) {
			if (cartsVec.get(i).X == minX && cartsVec.get(i).Y == minY) {
				return cartsVec.get(i);
			}
		}

		return null;
	}

	static boolean isArrowElement(char element) {
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

	static void printMap() {
		for (int i = 0; i < charArr.length; i++) {
			for (int j = 0; j < charArr[i].length; j++) {
				System.out.print(charArr[i][j]);
			}
			System.out.println();
		}
	}
}
