import java.util.Vector;

public class Depend {
	char id;
	boolean done = false;
	int lockCount = -1;
	Vector<Depend> before = null;
	Vector<Depend> after = null;
}
