public class Worker {
	boolean free = true;
	Depend workItem = null;

	void work() {
		if (!free) {
			workItem.lockCount--;
			if (workItem.lockCount == 0) {
				clearDepend();
				workItem.done = true;
				free = true;
				workItem = null;
			}
		}
	}

	void tryAssignWork(Depend depend) {
		if (free && depend != null) {
			workItem = depend;
			depend.lockCount = 60 + (depend.id - 64);
			free = false;
		}
	}

	private void clearDepend() {
		Main2.order.add(workItem.id);
		for (int j = 0; j < Main2.dependencies.size(); j++) {
			Main2.dependencies.get(j).before.remove(workItem);
		}
	}
}
