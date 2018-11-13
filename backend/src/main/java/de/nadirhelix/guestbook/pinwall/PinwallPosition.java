package de.nadirhelix.guestbook.pinwall;

/**
 * The absolute position at the pinwall.
 * 
 * @author Phil
 */
public class PinwallPosition {
	
	private static int instanceCount = 0;
	
	private final int index;
	
	private int posX;
	
	private int posY;
	
	private float rotation;
	
	public PinwallPosition(int xPos, int yPos) {
		this(createIndex(), xPos, yPos, rotate());
	}

	private static int createIndex() {
		return instanceCount++;
	}
	
	private static float rotate() {
		return (float) Math.random() * 20 - 10;
	}

	private PinwallPosition(int index, int xPos, int yPos, float rotation){
		this.index = index;
		this.posX = xPos;
		this.posY = yPos;
		this.rotation = rotation;
	}
	
	public int getIndex() {
		return index;
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public float getRotation() {
		return rotation;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	/**
	 * Creates a new {@link PinwallPosition} with empty values for the given index.
	 * 
	 * @param index 
	 * 			the index
	 * @return empty {@link PinwallPosition}
	 */
	public static PinwallPosition empty(int index) {
		return new PinwallPosition(index, 0, 0, 0);
	}
	
	
	
	
}
