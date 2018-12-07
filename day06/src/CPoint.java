
public class CPoint {
	int id = -1;
	int dist = Integer.MAX_VALUE;
	int x = 0;
	int y = 0;
	
	CPoint() {
		this.x = 0;
		this.y = 0;
	}
	
	CPoint(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
