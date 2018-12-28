import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	static String[] allLines = new String[32-1];
	static int[] registers = new int[6];
	static int pointer = 0;

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;

		for (int i = 0; ((line = br.readLine()) != null); i++) {
			if (i == 0) {
				continue;
			}
			allLines[i - 1] = line;
		}

		//registers[0] = 1;
		while (pointer < allLines.length) {
			registers[4] = pointer;
			execute();
			pointer = registers[4];
			pointer++;
			System.out.println(pointer);
			for (int i = 0; i < registers.length; i++) {
				System.out.print(registers[i] + " ");
			}
			System.out.println();
		}

		for (int i = 0; i < registers.length; i++) {
			System.out.print(registers[i] + " ");
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
			registers[Integer.parseInt(split[3])] = registers[Integer.parseInt(split[1])] + Integer.parseInt(split[2]);
			break;
		case "addr":
			registers[Integer.parseInt(split[3])] = registers[Integer.parseInt(split[1])]
					+ registers[Integer.parseInt(split[2])];
			break;
		case "muli":
			registers[Integer.parseInt(split[3])] = registers[Integer.parseInt(split[1])] * Integer.parseInt(split[2]);
			break;
		case "mulr":
			registers[Integer.parseInt(split[3])] = registers[Integer.parseInt(split[1])]
					* registers[Integer.parseInt(split[2])];
			break;
		case "bani":
			registers[Integer.parseInt(split[3])] = registers[Integer.parseInt(split[1])] & Integer.parseInt(split[2]);
			break;
		case "banr":
			registers[Integer.parseInt(split[3])] = registers[Integer.parseInt(split[1])]
					& registers[Integer.parseInt(split[2])];
			break;
		case "bori":
			registers[Integer.parseInt(split[3])] = registers[Integer.parseInt(split[1])] | Integer.parseInt(split[2]);
			break;
		case "borr":
			registers[Integer.parseInt(split[3])] = registers[Integer.parseInt(split[1])]
					| registers[Integer.parseInt(split[2])];
			break;
		case "gtir":
			if (Integer.parseInt(split[1]) > registers[Integer.parseInt(split[2])]) {
				registers[Integer.parseInt(split[3])] = 1;
			} else {
				registers[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "gtri":
			if (registers[Integer.parseInt(split[1])] > Integer.parseInt(split[2])) {
				registers[Integer.parseInt(split[3])] = 1;
			} else {
				registers[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "gtrr":
			if (registers[Integer.parseInt(split[1])] > registers[Integer.parseInt(split[2])]) {
				registers[Integer.parseInt(split[3])] = 1;
			} else {
				registers[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "eqir":
			if (Integer.parseInt(split[1]) == registers[Integer.parseInt(split[2])]) {
				registers[Integer.parseInt(split[3])] = 1;
			} else {
				registers[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "eqri":
			if (registers[Integer.parseInt(split[1])] == Integer.parseInt(split[2])) {
				registers[Integer.parseInt(split[3])] = 1;
			} else {
				registers[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "eqrr":
			if (registers[Integer.parseInt(split[1])] == registers[Integer.parseInt(split[2])]) {
				registers[Integer.parseInt(split[3])] = 1;
			} else {
				registers[Integer.parseInt(split[3])] = 0;
			}
			break;
		default:
			System.out.println("Should never happen");
		}
	}
	
	
	static int reg0, reg1, reg2, reg3, reg4, reg5;
	public static void untouchedMain(String[] args) {
		reg1 = 123;
		reg1 &= 456;

		if (reg1 != 72) {
			return;
		}

		//code line 5
		reg1 = 0;
		reg2 = reg1 | 65536;
		reg1 = 8725355;
		
		//code line 8
		reg5 = reg2 & 255;
		reg1 += reg5;
		reg1 &= 16777215;
		reg1 *= 65899;
		reg1 &= 16777215;

		if (256 > reg2) {
			reg5 = 1;
			reg4 = 27;
		} else {
			reg5 = 0;
			
			//code line 18
			reg3 = reg5 + 1;
			reg3 *= 256;

			if (reg3 > reg2) {
				reg3 = 1;
				reg4 = 25;
			} else {
				reg3 = 0;
				reg5 += 1;
				reg4 = 17;
				
				//code line 26
				reg2 = reg5;
				reg4 = 7;

				if (reg1 == reg0) {
					reg5 = 1;
					return;
				} else {
					reg5 = 0;
					//GOTO START
					reg4 = 5;
				}
			}
		}
		System.out.println("end of code");
	}
}