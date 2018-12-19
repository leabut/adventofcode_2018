import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	static String[] allLines = new String[7];
	static int[] registers = new int[6];
	static int pointer = 0;

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("example.txt"));
		String line;

		for (int i = 0; ((line = br.readLine()) != null); i++) {
			if (i == 0) {
				continue;
			}
			allLines[i - 1] = line;
		}

		while (pointer < allLines.length) {
			execute();
			pointer = registers[0];
			pointer++;
		}
	}

	static void execute() {
		String cmd = allLines[pointer];
		String[] split = cmd.split(" ");

		switch (split[0]) {
		case "seti":
			 registers[Integer.parseInt(split[3])] = Integer.parseInt(split[1]);
			break;
		case "setr":
			 registers[Integer.parseInt(split[3])] = registers[Integer.parseInt(split[1])];
			break;
		case "addi":
			registers[Integer.parseInt(split[3])] += Integer.parseInt(split[1]);
			break;
		case "addr":
			registers[Integer.parseInt(split[3])] += registers[Integer.parseInt(split[1])];
			break;
		default:
			System.out.println("Should never happen");
		}
	}
}