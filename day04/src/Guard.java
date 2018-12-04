public class Guard {
	int id = 0;
	int posX = 0;
	int posY = 0;
	int width = 0;
	int height = 0;

	int[] sleepTimeHist = new int[60];

	public Guard(int id) {
		this.id = id;
	}

	public void fallsAsleep(int start, int stop) {
		for (int i = start; i < stop; i++) {
			sleepTimeHist[i]++;
		}
	}

	public int getMostCommon() {
		int max = -1;
		int maxPos = 0;
		for (int i = 0; i < sleepTimeHist.length; i++) {
			if (sleepTimeHist[i] > max) {
				max = sleepTimeHist[i];
				maxPos = i;
			}
		}
		return maxPos;
	}
}
