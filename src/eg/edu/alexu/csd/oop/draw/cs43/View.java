package eg.edu.alexu.csd.oop.draw.cs43;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class View extends JFrame{


	 /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	Point prev = new Point();
	 Point curr = new Point();
	 public void pos() {
		 addMouseListener(new MouseAdapter(){
			 @Override
			public void mousePressed(MouseEvent e){
				 curr.x = e.getX();
				 curr.y = e.getY();
			 }
		 });
		 setLayout(new FlowLayout());
		 setSize(1000,600);
		 this.setLocationRelativeTo(null);
		 setVisible(true);
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }
 }