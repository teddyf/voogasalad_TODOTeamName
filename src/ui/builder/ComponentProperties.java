package ui.builder;

public class ComponentProperties {

    protected String id;
	protected double x;
	protected double y;
	protected double width;
	protected double height;
	protected String message;



	public ComponentProperties(double x, double y) {
		this.x = x;
		this.y = y;
	}

    public ComponentProperties id(String id) {
        this.id = id;
        return this;
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
