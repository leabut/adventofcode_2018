import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	static char[][] map = new char[100][100];
	static String regex = null;

	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader("example.txt"));
		regex = br.readLine();
		
		process();
	}

	static void process() {
        Pattern pattern = Pattern.compile("\\|");
        Matcher matcher = pattern.matcher(regex);
        
        while(matcher.find()) {
        	System.out.println(matcher.start());
        }
	}
}