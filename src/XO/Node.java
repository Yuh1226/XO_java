package XO;

public class Node {
	private int x;
	private int y;
	private int value;
	
	public Node(int x, int y, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	
	public String toString() {
        return value == 0 ? " " : String.valueOf(value);
    }
}
