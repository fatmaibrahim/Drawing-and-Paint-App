package eg.edu.alexu.csd.oop.draw.cs43;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;

/**
 * @author omid
 *
 */
public class Triangle extends MyShape {

	public Triangle() {
		this.properties = new HashMap<>();
		this.properties.put("x1", 0.0);
		this.properties.put("y1", 0.0);
		this.properties.put("x2", 0.0);
		this.properties.put("y2", 0.0);
		this.properties.put("x3", 0.0);
		this.properties.put("y3", 0.0);
	}

	@Override
	public void draw(Graphics canvas) {
if(!isselect){
		((Graphics2D) canvas).setColor(getFillColor());
		((Graphics2D) canvas).fillPolygon(
				new int[] { properties.get("x1").intValue(), this.properties.get("x2").intValue(),
						this.properties.get("x3").intValue() },
				new int[] { this.properties.get("y1").intValue(), this.properties.get("y2").intValue(),
						this.properties.get("y3").intValue() },
				3);

		((Graphics2D) canvas).setStroke(new BasicStroke(2));
		((Graphics2D) canvas).setColor(getColor());
		((Graphics2D) canvas).drawPolygon(
				new int[] { properties.get("x1").intValue(), this.properties.get("x2").intValue(),
						this.properties.get("x3").intValue() },
				new int[] { this.properties.get("y1").intValue(), this.properties.get("y2").intValue(),
						this.properties.get("y3").intValue() },3);
}else{
	((Graphics2D) canvas).setColor(getFillColor());
	((Graphics2D) canvas).fillPolygon(
			new int[] { properties.get("x1").intValue(), this.properties.get("x2").intValue(),
					this.properties.get("x3").intValue() },
			new int[] { this.properties.get("y1").intValue(), this.properties.get("y2").intValue(),
					this.properties.get("y3").intValue() },
			3);

	((Graphics2D) canvas).setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
	((Graphics2D) canvas).setColor(Color.CYAN);
	((Graphics2D) canvas).drawPolygon(
			new int[] { properties.get("x1").intValue(), this.properties.get("x2").intValue(),
					this.properties.get("x3").intValue() },
			new int[] { this.properties.get("y1").intValue(), this.properties.get("y2").intValue(),
					this.properties.get("y3").intValue() },3);
}

	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MyShape r = new Triangle();
		r.setColor(this.color);
		r.setFillColor(this.fillcolor);
		r.setPosition(this.position);
		r.setProperties(this.properties);
		return r;
	}
	public boolean contain(Point p){
		double ABC = Math.abs (this.properties.get("x1") * (this.properties.get("y2") - this.properties.get("y3")) + this.properties.get("x2") * (this.properties.get("y3") - this.properties.get("y1")) + this.properties.get("x3") * (this.properties.get("y1") - this.properties.get("y2")));
		double ABP = Math.abs (this.properties.get("x1") * (this.properties.get("y2") - p.getY()) + this.properties.get("x2") * (p.getY() - this.properties.get("y1")) + p.getX() * (this.properties.get("y1") - this.properties.get("y2")));
		double APC = Math.abs (this.properties.get("x1") * (p.getY() - this.properties.get("y3")) + p.getX() * (this.properties.get("y3") - this.properties.get("y1")) + this.properties.get("x3") * (this.properties.get("y1") - p.getY()));
		double PBC = Math.abs (p.getX() * (this.properties.get("y2") - this.properties.get("y3")) + this.properties.get("x2") * (this.properties.get("y3") - p.getY()) + this.properties.get("x3") * (p.getY() - this.properties.get("y2")));

		if( ABP + APC + PBC == ABC){
			this.iscontain=true;
			return this.iscontain;	
		}else{
			this.iscontain=false;
			return this.iscontain;	
		}
			
		}
	
}
