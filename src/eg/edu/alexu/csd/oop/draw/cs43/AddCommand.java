package eg.edu.alexu.csd.oop.draw.cs43;

import eg.edu.alexu.csd.oop.draw.Shape;

public class AddCommand implements Command  {

	Shape shape;

	public AddCommand(Shape shape){
		this.shape = shape;
	}
	@Override
	public String execute(){
		return "add";
	}
	@Override
	public String goBack(){
		return "remove";
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
