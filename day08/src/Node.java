
public class Node {
	int id;
	Node[] childs;
	int[] metaData;
	int value = -1;

	Node(int nrChilds, int nrMetaData) {
		Main.allNodes.add(this);

		id = Main.currentId;
		Main.currentId++;

		metaData = new int[nrMetaData];

		if (nrChilds == 0) {
			addMeta(Main.currentIndex + 2);
			Main.currentIndex = Main.currentIndex + 2 + Main.allNumbers[Main.currentIndex + 1];
			childs = null;
			return;
		}

		// update index
		Main.currentIndex = Main.currentIndex + 2;
		childs = new Node[nrChilds];
		for (int i = 0; i < childs.length; i++) {
			addChild();
		}

		addMeta(Main.currentIndex);
		Main.currentIndex += metaData.length;
	}

	public void addChild() {
		// create new Node
		Node tmp = new Node(Main.allNumbers[Main.currentIndex], Main.allNumbers[Main.currentIndex + 1]);

		// set new Node tmp as child of this node
		for (int i = 0; i < childs.length; i++) {
			if (childs[i] == null) {
				childs[i] = tmp;
				break;
			}
		}
	}

	public void addMeta(int startIdx) {
		for (int i = startIdx; i < metaData.length + startIdx; i++) {
			metaData[i - startIdx] = Main.allNumbers[i];
		}
	}

	public int calcValue() {
		int value = 0;
		if (this.childs == null && this.value == -1) {
			for (int i = 0; i < metaData.length; i++) {
				value += metaData[i];
			}
		} else {
			for (int i = 0; i < metaData.length; i++) {
				if (metaData[i]-1 < childs.length) {
					value += childs[metaData[i]-1].calcValue();
				}
			}
		}
		return value;
	}
}
