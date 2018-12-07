import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Main {

	static Vector<Depend> dependencies = new Vector<Depend>();
	static Vector<Character> order = new Vector<Character>();

	public static void main(String[] args) throws IOException {

		Vector<String[]> dependenciesStringVec = new Vector<String[]>();

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			line = line.replace("Step ", "");
			line = line.replace("must be finished before step ", "");
			line = line.replace(" can begin.", "");
			dependenciesStringVec.add(line.split(" "));
		}

		for (int i = 0; i < dependenciesStringVec.size(); i++) {
			if (!contains(dependenciesStringVec.get(i)[0].toCharArray()[0])) {
				Depend tmp = new Depend();
				tmp.id = dependenciesStringVec.get(i)[0].toCharArray()[0];
				tmp.after = new Vector<Depend>();
				tmp.before = new Vector<Depend>();
				dependencies.add(tmp);
			}
			if (!contains(dependenciesStringVec.get(i)[1].toCharArray()[0])) {
				Depend tmp = new Depend();
				tmp.id = dependenciesStringVec.get(i)[1].toCharArray()[0];
				tmp.after = new Vector<Depend>();
				tmp.before = new Vector<Depend>();
				dependencies.add(tmp);
			}
		}

		for (int i = 0; i < dependenciesStringVec.size(); i++) {
			Depend before = getDependByChar(dependenciesStringVec.get(i)[0].toCharArray()[0]);
			Depend after = getDependByChar(dependenciesStringVec.get(i)[1].toCharArray()[0]);
			before.after.add(after);
			after.before.add(before);
		}

		process();

		for (int i = 0; i < order.size(); i++) {
			System.out.print(order.get(i));
		}
	}

	public static void process() {
		// find Depend with an empty before list
		while (isOpen()) {
					Depend tmp = getSmallestOpenDepend();
					tmp.done = true;
					order.add(tmp.id);
					for (int j = 0; j < dependencies.size(); j++) {
						dependencies.get(j).before.remove(tmp);
					}
		}
	}

	public static Depend getSmallestOpenDepend() {
		Depend tmp = null;
		for (int i = 0; i < dependencies.size(); i++) {
			if (dependencies.get(i).before.size() == 0 && dependencies.get(i).done == false) {
				if (tmp == null) {
					tmp = dependencies.get(i);
					continue;
				}
				if(tmp.id > dependencies.get(i).id) {
					tmp = dependencies.get(i);
				}
			}
		}
		
		return tmp;
	}

	public static boolean isOpen() {
		for (int i = 0; i < dependencies.size(); i++) {
			if (dependencies.get(i).done == false) {
				return true;
			}
		}
		return false;
	}

	public static boolean contains(char id) {
		for (int i = 0; i < dependencies.size(); i++) {
			if (id == dependencies.get(i).id) {
				return true;
			}
		}
		return false;
	}

	public static Depend getDependByChar(char id) {
		for (int i = 0; i < dependencies.size(); i++) {
			if (dependencies.get(i).id == id) {
				return dependencies.get(i);
			}
		}
		return null;
	}
}
