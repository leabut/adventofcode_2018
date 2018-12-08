import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

	static int[] allNumbers;

	static int currentId = 0;
	static int currentIndex = 0;

	static Vector<Node> allNodes = new Vector<Node>();

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));

		String[] allLines = br.readLine().split(" ");
		allNumbers = new int[allLines.length];

		for (int i = 0; i < allLines.length; i++) {
			allNumbers[i] = Integer.parseInt(allLines[i]);
		}

		Node root = new Node(allNumbers[0], allNumbers[1]);

		int sum = 0;
		for (int i = 0; i < allNodes.size(); i++) {
			for (int j = 0; j < allNodes.get(i).metaData.length; j++) {
				sum += allNodes.get(i).metaData[j];
			}
		}

		System.out.println("res = " + sum);

		int sum2 = 0;
		sum2 = root.calcValue();
		
		System.out.println("res2 = " + sum2);
	}
}
