package eg.edu.alexu.csd.oop.draw.cs43;

import java.awt.Color;
import java.awt.Graphics;

import java.awt.Point;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author omid
 *
 */
public class MyShape implements Shape {

	protected Point position;
	protected Map<String, Double> properties;
	protected Color color;
	protected Color fillcolor;
	protected boolean iscontain;
	protected boolean isselect;
	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;
	}

	@Override
	public Map<String, Double> getProperties() {
		return properties;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setFillColor(Color color) {
		this.fillcolor = color;
	}

	@Override
	public Color getFillColor() {
		return this.fillcolor;
	}

	@Override
	public void draw(Graphics canvas) {
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean contain(Point p) {
		return this.iscontain;
	}
	@Override
	public void setisselect(boolean contains){
		this.isselect=contains;
	}
	@Override
	public boolean getsetisselect(){
		return this.isselect;
	}
	
}
