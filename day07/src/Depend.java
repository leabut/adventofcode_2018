import java.util.Vector;

public class Depend {
	char id;
	boolean done = false;
	Vector<Depend> before = null;
	Vector<Depend> after = null;
}
