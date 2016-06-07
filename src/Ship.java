
public class Ship {
	public int xPos;
	public int yPos;
	public int shipNumber;
	public boolean overlap;
	public boolean safe;
	
	public Ship(int shipNumber){
		xPos = -1;
		yPos = -1;
		this.shipNumber = shipNumber;
		overlap = false;
		safe = true;
	}
	
	public Ship(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		overlap = false;
	}

	public boolean isOverlap() {
		return overlap;
	}

	public void setOverlap(boolean overlap) {
		this.overlap = overlap;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getShipNumber() {
		return shipNumber;
	}

	public boolean isSafe() {
		return safe;
	}

	public void setSafe(boolean safe) {
		this.safe = safe;
	}

	
	

}
