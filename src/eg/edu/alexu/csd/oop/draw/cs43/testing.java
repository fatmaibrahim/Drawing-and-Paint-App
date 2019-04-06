package eg.edu.alexu.csd.oop.draw.cs43;

public class testing {

	public static void main(String[] args) {
		MyDrawingEngine obj = new MyDrawingEngine();
		Square sq = new Square();
		obj.addShape(sq);
		obj.addShape(sq);
		obj.undo();
		obj.redo();
		obj.save("txt.xml");
		obj.load("txt.xml");
		obj.undo();

	}
}


