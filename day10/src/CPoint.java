
public class CPoint {
	static int gId = 0;
	int id, posX, posY, velX, velY;

	CPoint(int posX, int posY, int velX, int velY) {
		this.posX = posX;
		this.posY = posY;
		this.velX = velX;
		this.velY = velY;
		
		id = gId;
		gId++;
	}
	
	void tick() {
		posX += velX;
		posY += velY;
	}
}
