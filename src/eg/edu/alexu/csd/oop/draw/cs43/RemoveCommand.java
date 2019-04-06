package eg.edu.alexu.csd.oop.draw.cs43;

import eg.edu.alexu.csd.oop.draw.Shape;

public class RemoveCommand implements Command {


	Shape shape;

	public RemoveCommand(Shape shape){
		this.shape = shape;
	}
	@Override
	public String execute(){
		return "remove";
	}
	@Override
	public String goBack(){
		return "add";
	}
	@Override
	public Shape getShape1(){
		return shape;
	}
	@Override
	public Shape getShape2(){
		return null;
	}
}
