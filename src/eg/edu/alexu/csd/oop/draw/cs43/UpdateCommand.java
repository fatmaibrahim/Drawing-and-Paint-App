package eg.edu.alexu.csd.oop.draw.cs43;

import eg.edu.alexu.csd.oop.draw.Shape;

public class UpdateCommand implements Command {


	Shape shape1,shape2;

	public UpdateCommand(Shape shape1,Shape shape2){
		this.shape1 = shape1;
		this.shape2 = shape2;
	}
	@Override
	public String execute(){
		return "update";
	}
	@Override
	public String goBack(){
		return "update";
	}
	@Override
	public Shape getShape1(){
		return shape1;
	}
	@Override
	public Shape getShape2(){
		return shape2;
	}

}
