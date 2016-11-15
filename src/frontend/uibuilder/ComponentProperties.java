package frontend.uibuilder;

public class ComponentProperties {

	protected double x;
	protected double y;
	protected double width;
	protected double height;
	
	protected String message;
	
	public ComponentProperties(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public ComponentProperties width(double width) {
		this.width = width;
		return this;
	}
	
	public ComponentProperties height(double height) {
		this.height = height;
		return this;
	}
	
	public ComponentProperties message(String message) {
		this.message = message;
		return this;
	}
	
}
