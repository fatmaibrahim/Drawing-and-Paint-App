package eg.edu.alexu.csd.oop.draw.cs43;

import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author omid
 *
 */
public class Reciever {
	//MyDrawingEngine obj = MyDrawingEngine.getInstance();

	public void add(Shape shape) {
		//obj.allShapes.add(shape);
	}

	public void remove(Shape shape) {
		/*for (int i = 0; i < obj.allShapes.size(); i++) {
			if (obj.allShapes.get(i) == shape) {
				obj.allShapes.remove(i);
			}
		}*/
	}

	public void update(Shape oldS, Shape newS) {
		/*int flag = 0;
		for (int i = 0; i < obj.allShapes.size(); i++) {
			if (obj.allShapes.get(i) == oldS) {
				obj.allShapes.remove(i);
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			obj.allShapes.add(newS);
		}*/
	}
}
