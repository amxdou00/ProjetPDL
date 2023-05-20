package other;

import java.awt.Color;

public class ResultMessage {
	private Color color;
	private String message;
	
	public ResultMessage(Color color, String message) {
		this.color = color;
		this.message = message;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public String getMessage() {
		return this.message;
	}
}
