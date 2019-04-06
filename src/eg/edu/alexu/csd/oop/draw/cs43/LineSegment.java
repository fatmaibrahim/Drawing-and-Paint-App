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
public class LineSegment extends MyShape {

	public LineSegment() {
		this.properties = new HashMap<>();
		this.properties.put("endx", 0.0);
		this.properties.put("endy", 0.0);
	}

	@Override
	public void draw(Graphics canvas) {
if(!isselect){
		((Graphics2D) canvas).setStroke(new BasicStroke(2));
		((Graphics2D) canvas).setColor(getColor());
		((Graphics2D) canvas).drawLine((int) this.position.getX(), (int) this.position.getY(),
				this.properties.get("endx").intValue(), this.properties.get("endy").intValue());
}else{
	((Graphics2D) canvas).setStroke(new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0));
	((Graphics2D) canvas).setColor(Color.CYAN);
	((Graphics2D) canvas).drawLine((int) this.position.getX(), (int) this.position.getY(),
			this.properties.get("endx").intValue(), this.properties.get("endy").intValue());
}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		MyShape r = new LineSegment();
		r.setColor(this.color);
		r.setFillColor(this.fillcolor);
		r.setPosition(this.position);
		r.setProperties(this.properties);
		return r;
		
	}
	public boolean contain(Point p){
		int slope= (int)((this.position.getY()-this.properties.get("endy"))/(this.position.getX()-this.properties.get("endx")));
		int  newslope=(int) ((this.position.getY()-p.getY())/(this.position.getX()-p.getX()));
		if((slope==newslope)&&(p.getY()<=this.properties.get("endy")&&p.getY()>=this.position.getY()&&p.getX()<=this.properties.get("endx")&&p.getX()>=this.position.getX())){
			this.iscontain=true;
			return this.iscontain;	
		}else{
			this.iscontain=false;
			return this.iscontain;	
		}
			
		}

}
