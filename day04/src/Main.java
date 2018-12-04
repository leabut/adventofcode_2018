import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
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
		
		for(int i = 0; i < allLines.size(); i++) {
			for(int k = 0; k < allLines.size(); k++) {
				if(i == k) {
					continue;
				}
				LocalDateTime dateTimeOne = LocalDateTime.from(f.parse(allLines.get(i).split("]")[0]));
				LocalDateTime dateTimeTwo = LocalDateTime.from(f.parse(allLines.get(k).split("]")[0]));
				
				if(dateTimeOne.getYear() < dateTimeTwo.getYear()) {
					Collections.swap(allLines, i, k);
				}
				if(dateTimeOne.getYear() == dateTimeTwo.getYear()) {
					if(dateTimeOne.getMonthValue() < dateTimeTwo.getMonthValue()) {
						Collections.swap(allLines, i, k);
					}
					if(dateTimeOne.getMonthValue() == dateTimeTwo.getMonthValue()) {
						if(dateTimeOne.getDayOfMonth() < dateTimeTwo.getDayOfMonth()) {
							Collections.swap(allLines, i, k);
						}
						if(dateTimeOne.getDayOfMonth() == dateTimeTwo.getDayOfMonth()) {
							if(dateTimeOne.getHour() < dateTimeTwo.getHour()) {
								Collections.swap(allLines, i, k);
							}
							if(dateTimeOne.getHour() == dateTimeTwo.getHour()) {
								if(dateTimeOne.getMinute() < dateTimeTwo.getMinute()) {
									Collections.swap(allLines, i, k);
								}
								if(dateTimeOne.getMinute() == dateTimeTwo.getMinute()) {
									//this should not happen
									System.out.println("THIS IS DEFINETLY NOT GOOD!");
								}
							}
						}
					}
				}
			}
		}
		
		for(int i = 0; i < allLines.size(); i++) {
			String tmp = allLines.get(i).substring(18);
			allLines.set(i, tmp);
			if(allLines.get(i).matches("Guard .* begins shift")) {
				allLines.get(i).replace("Guard ", "");
				allLines.get(i).
			}
		}
	}
}
