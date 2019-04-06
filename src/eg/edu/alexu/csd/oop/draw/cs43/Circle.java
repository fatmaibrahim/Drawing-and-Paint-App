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
public class Circle extends MyShape {
	public Circle() {
		this.properties = new HashMap<>();
		this.properties.put("raduis", 0.0);
	}

	@Override
	public void draw(Graphics canvas) {
		if(!this.isselect){
		((Graphics2D) canvas).setColor(getFillColor());
		((Graphics2D) canvas).fillOval((int) this.position.getX(), (int) this.position.getY(),
				this.properties.get("raduis").intValue(), this.properties.get("raduis").intValue());

		((Graphics2D) canvas).setStroke(new BasicStroke(2));
		((Graphics2D) canvas).setColor(getColor());
		((Graphics2D) canvas).drawOval((int) this.position.getX(), (int) this.position.getY(),
				this.properties.get("raduis").intValue(), this.properties.get("raduis").intValue());
		}
		if(this.isselect){
			((Graphics2D) canvas).setColor(getFillColor());
			((Graphics2D) canvas).fillOval((int) this.position.getX(), (int) this.position.getY(),
					this.properties.get("raduis").intValue(), this.properties.get("raduis").intValue());

			((Graphics2D) canvas).setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
			((Graphics2D) canvas).setColor(Color.CYAN);
			((Graphics2D) canvas).drawOval((int) this.position.getX(), (int) this.position.getY(),
					this.properties.get("raduis").intValue(), this.properties.get("raduis").intValue());
		}
		
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MyShape r = new Circle();
		r.setColor(this.color);
		r.setFillColor(this.fillcolor);
		r.setPosition(this.position);
		r.setProperties(this.properties);
		return r;
	}
		
	public Point getcenter(){
		Point center=new Point();
		center.x=(int) (this.position.getX()+this.properties.get("raduis").intValue()/2);
		center.y=(int) (this.position.getY()+this.properties.get("raduis").intValue()/2);
	return center;
	}
	
	public boolean contain(Point p){
		Point center=getcenter();
		double distance=Math.sqrt(Math.pow(center.x-p.x, 2)+Math.pow(center.y-p.y, 2));
		if(distance/(this.properties.get("raduis")/2)>1){
			this.iscontain=false;
			return this.iscontain;
		}else{
			this.iscontain=true;
			return this.iscontain;
		}
			
		}
			
}
