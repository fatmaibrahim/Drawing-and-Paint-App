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
public class Rectangle extends MyShape {

	public Rectangle() {
		this.properties = new HashMap<>();
		this.properties.put("Width", 0.0);
		this.properties.put("height", 0.0);
	}

	@Override
	public void draw(Graphics canvas) {
		if(!isselect){
		((Graphics2D) canvas).setColor(getFillColor());
		((Graphics2D) canvas).fillRect((int) this.position.getX(), (int) this.position.getY(),
				this.properties.get("Width").intValue(), this.properties.get("height").intValue());

		((Graphics2D) canvas).setStroke(new BasicStroke(2));
		((Graphics2D) canvas).setColor(getColor());
		((Graphics2D) canvas).drawRect((int) this.position.getX(), (int) this.position.getY(),
				this.properties.get("Width").intValue(), this.properties.get("height").intValue());
		}
		else{
			((Graphics2D) canvas).setColor(getFillColor());
			((Graphics2D) canvas).fillRect((int) this.position.getX(), (int) this.position.getY(),
					this.properties.get("Width").intValue(), this.properties.get("height").intValue());

			((Graphics2D) canvas).setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
			((Graphics2D) canvas).setColor(Color.CYAN);
			((Graphics2D) canvas).drawRect((int) this.position.getX(), (int) this.position.getY(),
					this.properties.get("Width").intValue(), this.properties.get("height").intValue());
			}
		}
		
	

	@Override
	public Object clone() throws CloneNotSupportedException {
		MyShape r = new Rectangle();
		r.setColor(this.color);
		r.setFillColor(this.fillcolor);
		r.setPosition(this.position);
		r.setProperties(this.properties);
		return r;
	}
	
	public boolean contain(Point p){
		double x=p.x-this.position.getX();
		double y=p.y-this.position.getY();	
		if(x<=this.properties.get("Width")&&y<=this.properties.get("height")&&x>=0&&y>=0){
			this.iscontain=true;
			return this.iscontain;
		    }
			else{
				this.iscontain=false;
				return this.iscontain;	
			}
	}		
		
}
