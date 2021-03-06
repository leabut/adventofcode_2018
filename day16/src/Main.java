import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

	static int[] registersBefore = new int[4];
	static int[] registersAfter = new int[4];
	static int[] tmpRegister = new int[4];
	static HashMap<Integer, String> idToOpcode = new HashMap<Integer, String>();

	@SuppressWarnings({ "resource", "rawtypes" })
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		String cmd = null;
		String[] split = null;

		int res = 0;

		int newLineCount = 0;

		while ((line = br.readLine()) != null) {
			if (newLineCount > 2) {
				break;
			}
			if (line.matches("")) {
				newLineCount++;
				continue;
			} else {
				newLineCount = 0;
			}

			split = null;
			if (line.matches(".*Before.*")) {
				line = line.replace("Before: [", "");
				line = line.replace("]", "");
				line = line.replace(" ", "");
				split = line.split(",");

				for (int i = 0; i < split.length; i++) {
					registersBefore[i] = Integer.parseInt(split[i]);
				}
				continue;
			}
			if (line.matches(".*After.*")) {
				line = line.replace("After:  [", "");
				line = line.replace("]", "");
				line = line.replace(" ", "");
				split = line.split(",");

				for (int i = 0; i < split.length; i++) {
					registersAfter[i] = Integer.parseInt(split[i]);
				}
				if (execute(cmd) >= 3) {
					res++;
				}
				// part2
				executeAndFind(cmd);
				continue;
			}
			cmd = line;
		}

		System.out.println("res = " + res);

		Iterator it = idToOpcode.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
		}

		// initialize registers with 0
		for (int i = 0; i < tmpRegister.length; i++) {
			tmpRegister[i] = 0;
		}
		// parse opcode
		while ((line = br.readLine()) != null) {
			if(line.matches("")) {
				continue;
			}
			execCode(line);
		}
		
		System.out.print("Register content\n[");
		for(int i = 0; i < tmpRegister.length; i++) {
		System.out.print(tmpRegister[i] + " , ");
		}
		System.out.print("]\n");
	}

	static void execCode(String cmd) {
		String[] split = cmd.split(" ");
		int id = Integer.parseInt(split[0]);
		String opcode = idToOpcode.get(id);

		switch (opcode) {
		case "seti":
			tmpRegister[Integer.parseInt(split[3])] = Integer.parseInt(split[1]);
			break;

		case "setr":
			tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])];
			break;
		case "addi":
			tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
					+ Integer.parseInt(split[2]);
			break;
		case "addr":
			tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
					+ tmpRegister[Integer.parseInt(split[2])];
			break;
		case "muli":
			tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
					* Integer.parseInt(split[2]);
			break;
		case "mulr":
			tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
					* tmpRegister[Integer.parseInt(split[2])];
			break;
		case "bani":
			tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
					& Integer.parseInt(split[2]);
			break;
		case "banr":
			tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
					& tmpRegister[Integer.parseInt(split[2])];
			break;
		case "bori":
			tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
					| Integer.parseInt(split[2]);
			break;
		case "borr":
			tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
					| tmpRegister[Integer.parseInt(split[2])];
			break;
		case "gtir":
			if (Integer.parseInt(split[1]) > tmpRegister[Integer.parseInt(split[2])]) {
				tmpRegister[Integer.parseInt(split[3])] = 1;
			} else {
				tmpRegister[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "gtri":
			if (tmpRegister[Integer.parseInt(split[1])] > Integer.parseInt(split[2])) {
				tmpRegister[Integer.parseInt(split[3])] = 1;
			} else {
				tmpRegister[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "gtrr":
			if (tmpRegister[Integer.parseInt(split[1])] > tmpRegister[Integer.parseInt(split[2])]) {
				tmpRegister[Integer.parseInt(split[3])] = 1;
			} else {
				tmpRegister[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "eqir":
			if (Integer.parseInt(split[1]) == tmpRegister[Integer.parseInt(split[2])]) {
				tmpRegister[Integer.parseInt(split[3])] = 1;
			} else {
				tmpRegister[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "eqri":
			if (tmpRegister[Integer.parseInt(split[1])] == Integer.parseInt(split[2])) {
				tmpRegister[Integer.parseInt(split[3])] = 1;
			} else {
				tmpRegister[Integer.parseInt(split[3])] = 0;
			}
			break;
		case "eqrr":
			if (tmpRegister[Integer.parseInt(split[1])] == tmpRegister[Integer.parseInt(split[2])]) {
				tmpRegister[Integer.parseInt(split[3])] = 1;
			} else {
				tmpRegister[Integer.parseInt(split[3])] = 0;
			}
			break;
		default:
			System.out.println("This should not happen!");
		}
	}

	static void executeAndFind(String cmd) {
		int id = 0;
		String opcode = "";

		tmpRegister = registersBefore.clone();

		String[] split = cmd.split(" ");
		id = Integer.parseInt(split[0]);

		if (idToOpcode.containsKey(id)) {
			return;
		}

		int counter = 0;

		// case "seti":
		tmpRegister[Integer.parseInt(split[3])] = Integer.parseInt(split[1]);
		if (testArrEq() && !idToOpcode.containsValue("seti")) {
			opcode = "seti";
			counter++;
		}

		// case "setr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])];
		if (testArrEq() && !idToOpcode.containsValue("setr")) {
			opcode = "setr";
			counter++;
		}
		// case "addi":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])] + Integer.parseInt(split[2]);
		if (testArrEq() && !idToOpcode.containsValue("addi")) {
			opcode = "addi";
			counter++;
		}
		// case "addr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
				+ tmpRegister[Integer.parseInt(split[2])];
		if (testArrEq() && !idToOpcode.containsValue("addr")) {
			opcode = "addr";
			counter++;
		}
		// case "muli":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])] * Integer.parseInt(split[2]);
		if (testArrEq() && !idToOpcode.containsValue("muli")) {
			opcode = "muli";
			counter++;
		}
		// case "mulr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
				* tmpRegister[Integer.parseInt(split[2])];
		if (testArrEq() && !idToOpcode.containsValue("mulr")) {
			opcode = "mulr";
			counter++;
		}
		// case "bani":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])] & Integer.parseInt(split[2]);
		if (testArrEq() && !idToOpcode.containsValue("bani")) {
			opcode = "bani";
			counter++;
		}
		// case "banr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
				& tmpRegister[Integer.parseInt(split[2])];
		if (testArrEq() && !idToOpcode.containsValue("banr")) {
			opcode = "banr";
			counter++;
		}
		// case "bori":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])] | Integer.parseInt(split[2]);
		if (testArrEq() && !idToOpcode.containsValue("bori")) {
			opcode = "bori";
			counter++;
		}
		// case "borr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
				| tmpRegister[Integer.parseInt(split[2])];
		if (testArrEq() && !idToOpcode.containsValue("borr")) {
			opcode = "borr";
			counter++;
		}
		// case "gtir":
		if (Integer.parseInt(split[1]) > tmpRegister[Integer.parseInt(split[2])]) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq() && !idToOpcode.containsValue("gtir")) {
			opcode = "gtir";
			counter++;
		}
		// case "gtri":
		if (tmpRegister[Integer.parseInt(split[1])] > Integer.parseInt(split[2])) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq() && !idToOpcode.containsValue("gtri")) {
			opcode = "gtri";
			counter++;
		}
		// case "gtrr":
		if (tmpRegister[Integer.parseInt(split[1])] > tmpRegister[Integer.parseInt(split[2])]) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq() && !idToOpcode.containsValue("gtrr")) {
			opcode = "gtrr";
			counter++;
		}
		// case "eqir":
		if (Integer.parseInt(split[1]) == tmpRegister[Integer.parseInt(split[2])]) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq() && !idToOpcode.containsValue("eqir")) {
			opcode = "eqir";
			counter++;
		}
		// case "eqri":
		if (tmpRegister[Integer.parseInt(split[1])] == Integer.parseInt(split[2])) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq() && !idToOpcode.containsValue("eqri")) {
			opcode = "eqri";
			counter++;
		}
		// case "eqrr":
		if (tmpRegister[Integer.parseInt(split[1])] == tmpRegister[Integer.parseInt(split[2])]) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq() && !idToOpcode.containsValue("eqrr")) {
			opcode = "eqrr";
			counter++;
		}

		if (counter == 1) {
			idToOpcode.put(id, opcode);
		}
	}

	static int execute(String cmd) {
		tmpRegister = registersBefore.clone();
		String[] split = cmd.split(" ");

		int counter = 0;

		// case "seti":
		tmpRegister[Integer.parseInt(split[3])] = Integer.parseInt(split[1]);
		if (testArrEq()) {
			counter++;
		}
		// case "setr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])];
		if (testArrEq()) {
			counter++;
		}
		// case "addi":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])] + Integer.parseInt(split[2]);
		if (testArrEq()) {
			counter++;
		}
		// case "addr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
				+ tmpRegister[Integer.parseInt(split[2])];
		if (testArrEq()) {
			counter++;
		}
		// case "muli":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])] * Integer.parseInt(split[2]);
		if (testArrEq()) {
			counter++;
		}
		// case "mulr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
				* tmpRegister[Integer.parseInt(split[2])];
		if (testArrEq()) {
			counter++;
		}
		// case "bani":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])] & Integer.parseInt(split[2]);
		if (testArrEq()) {
			counter++;
		}
		// case "banr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
				& tmpRegister[Integer.parseInt(split[2])];
		if (testArrEq()) {
			counter++;
		}
		// case "bori":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])] | Integer.parseInt(split[2]);
		if (testArrEq()) {
			counter++;
		}
		// case "borr":
		tmpRegister[Integer.parseInt(split[3])] = tmpRegister[Integer.parseInt(split[1])]
				| tmpRegister[Integer.parseInt(split[2])];
		if (testArrEq()) {
			counter++;
		}
		// case "gtir":
		if (Integer.parseInt(split[1]) > tmpRegister[Integer.parseInt(split[2])]) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq()) {
			counter++;
		}
		// case "gtri":
		if (tmpRegister[Integer.parseInt(split[1])] > Integer.parseInt(split[2])) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq()) {
			counter++;
		}
		// case "gtrr":
		if (tmpRegister[Integer.parseInt(split[1])] > tmpRegister[Integer.parseInt(split[2])]) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq()) {
			counter++;
		}
		// case "eqir":
		if (Integer.parseInt(split[1]) == tmpRegister[Integer.parseInt(split[2])]) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq()) {
			counter++;
		}
		// case "eqri":
		if (tmpRegister[Integer.parseInt(split[1])] == Integer.parseInt(split[2])) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq()) {
			counter++;
		}
		// case "eqrr":
		if (tmpRegister[Integer.parseInt(split[1])] == tmpRegister[Integer.parseInt(split[2])]) {
			tmpRegister[Integer.parseInt(split[3])] = 1;
		} else {
			tmpRegister[Integer.parseInt(split[3])] = 0;
		}
		if (testArrEq()) {
			counter++;
		}

		return counter;
	}

	static boolean testArrEq() {
		boolean res = false;
		if (Arrays.equals(registersAfter, tmpRegister)) {
			res = true;
		}
		tmpRegister = registersBefore.clone();

		return res;
	}
}