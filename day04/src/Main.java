import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Vector;

public class Main {

	public static void main(String[] args) throws IOException {

		Vector<Guard> guardVec = new Vector<Guard>();
		Vector<String> allLines = new Vector<String>();

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String line;
		while ((line = br.readLine()) != null) {
			allLines.add(line.replace("[", ""));
		}

		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

		for (int i = 0; i < allLines.size(); i++) {
			for (int k = 0; k < allLines.size(); k++) {
				if (i == k) {
					continue;
				}
				LocalDateTime dateTimeOne = LocalDateTime.from(f.parse(allLines.get(i).split("]")[0]));
				LocalDateTime dateTimeTwo = LocalDateTime.from(f.parse(allLines.get(k).split("]")[0]));

				if (dateTimeOne.getYear() < dateTimeTwo.getYear()) {
					Collections.swap(allLines, i, k);
				}
				if (dateTimeOne.getYear() == dateTimeTwo.getYear()) {
					if (dateTimeOne.getMonthValue() < dateTimeTwo.getMonthValue()) {
						Collections.swap(allLines, i, k);
					}
					if (dateTimeOne.getMonthValue() == dateTimeTwo.getMonthValue()) {
						if (dateTimeOne.getDayOfMonth() < dateTimeTwo.getDayOfMonth()) {
							Collections.swap(allLines, i, k);
						}
						if (dateTimeOne.getDayOfMonth() == dateTimeTwo.getDayOfMonth()) {
							if (dateTimeOne.getHour() < dateTimeTwo.getHour()) {
								Collections.swap(allLines, i, k);
							}
							if (dateTimeOne.getHour() == dateTimeTwo.getHour()) {
								if (dateTimeOne.getMinute() < dateTimeTwo.getMinute()) {
									Collections.swap(allLines, i, k);
								}
								if (dateTimeOne.getMinute() == dateTimeTwo.getMinute()) {
									// this should not happen
									System.out.println("THIS IS DEFINETLY NOT GOOD!");
								}
							}
						}
					}
				}
			}
		}

		LocalDateTime sleepTime = null;
		LocalDateTime wakeUpTime = null;
		Guard currentGuard = null;
		for (int i = 0; i < allLines.size(); i++) {
			if (allLines.get(i).matches(".* Guard .* begins shift")) {
				currentGuard = null;
				allLines.set(i, allLines.get(i).substring(18));
				allLines.set(i, allLines.get(i).replace("Guard ", ""));
				allLines.set(i, allLines.get(i).replace(" begins shift", ""));
				allLines.set(i, allLines.get(i).replace("#", ""));
				int guardId = Integer.parseInt(allLines.get(i));
				if (guardVec.size() == 0) {
					currentGuard = new Guard(guardId);
					guardVec.add(currentGuard);
				}
				for (int k = 0; k < guardVec.size(); k++) {
					if (guardVec.get(k).id == guardId) {
						currentGuard = guardVec.get(k);
					}
				}
				if (currentGuard == null) {
					currentGuard = new Guard(guardId);
					guardVec.add(currentGuard);
				}
				continue;
			}
			if (allLines.get(i).matches(".*falls asleep.*")) {
				sleepTime = LocalDateTime.from(f.parse(allLines.get(i).split("]")[0]));
				wakeUpTime = null;
				continue;
			}
			if (allLines.get(i).matches(".*wakes up.*")) {
				wakeUpTime = LocalDateTime.from(f.parse(allLines.get(i).split("]")[0]));
				currentGuard.fallsAsleep(sleepTime.getMinute(), wakeUpTime.getMinute());
				sleepTime = null;
			}
		}

		Guard laziestGuard = null;
		int sleepyMinutes = -1;
		for (int i = 0; i < guardVec.size(); i++) {
			if (guardVec.get(i).getMostMinutes() > sleepyMinutes) {
				sleepyMinutes = guardVec.get(i).getMostMinutes();
				laziestGuard = guardVec.get(i);
			}
		}

		System.out.println("res = " + laziestGuard.id * laziestGuard.getMostCommon()[1] + ", lazyGuard.id = "
				+ laziestGuard.id + ", minute = " + laziestGuard.getMostCommon()[1]);
		
		
		laziestGuard = null;
		int maxOccurence = -1;
		for (int i = 0; i < guardVec.size(); i++) {
			if (guardVec.get(i).getMostCommon()[0] > maxOccurence) {
				maxOccurence = guardVec.get(i).getMostCommon()[0];
				laziestGuard = guardVec.get(i);
			}
		}

		System.out.println("res2 = " + laziestGuard.id * laziestGuard.getMostCommon()[1] + ", lazyGuard.id = "
				+ laziestGuard.id + ", minute = " + laziestGuard.getMostCommon()[1] + ", occurence = " + maxOccurence);
	}
}
