package eg.edu.alexu.csd.oop.draw.cs43;

import eg.edu.alexu.csd.oop.draw.Shape;

public interface Command {
	public String execute();
	public String goBack();
	public Shape getShape1();
	public Shape getShape2();

}
