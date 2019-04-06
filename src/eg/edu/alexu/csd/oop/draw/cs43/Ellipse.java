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
public class Ellipse extends MyShape {

	public Ellipse() {
		this.properties = new HashMap<>();
		this.properties.put("Width", 0.0);
		this.properties.put("height", 0.0);
	}

	@Override
	public void draw(Graphics canvas) {
		if(!isselect){
		((Graphics2D) canvas).setColor(getFillColor());
		((Graphics2D) canvas).fillOval((int) this.position.getX(), (int) this.position.getY(),
				this.properties.get("Width").intValue(), this.properties.get("height").intValue());

		((Graphics2D) canvas).setStroke(new BasicStroke(2));
		((Graphics2D) canvas).setColor(getColor());
		((Graphics2D) canvas).drawOval((int) this.position.getX(), (int) this.position.getY(),
				this.properties.get("Width").intValue(), this.properties.get("height").intValue());
		}else{
			((Graphics2D) canvas).setColor(getFillColor());
			((Graphics2D) canvas).fillOval((int) this.position.getX(), (int) this.position.getY(),
					this.properties.get("Width").intValue(), this.properties.get("height").intValue());

			((Graphics2D) canvas).setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
			((Graphics2D) canvas).setColor(Color.CYAN);
			((Graphics2D) canvas).drawOval((int) this.position.getX(), (int) this.position.getY(),
					this.properties.get("Width").intValue(), this.properties.get("height").intValue());
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MyShape r = new Ellipse();
		r.setColor(this.color);
		r.setFillColor(this.fillcolor);
		r.setPosition(this.position);
		r.setProperties(this.properties);
		return r;
	}
	
	public Point getcenter(){
		Point center=new Point();
		center.setLocation(this.position.getX()+this.properties.get("Width")/2,this.position.getY()+this.properties.get("height")/2);
	return center;
	}
	
		public boolean contain(Point p){
			double equation = ((((Math.pow(p.getX() - getcenter().getX(), 2)) / Math.pow(this.properties.get("Width") / 2, 2)))
					+ ((Math.pow((p.getY() - this.getcenter().getY()), 2)) / Math.pow(this.properties.get("height")/ 2, 2)));
			if(equation<1.0){
				this.iscontain=true;
				return this.iscontain;
			    }
				else{
					this.iscontain=false;
					return this.iscontain;	
				}
		}		
}
