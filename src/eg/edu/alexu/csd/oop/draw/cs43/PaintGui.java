package eg.edu.alexu.csd.oop.draw.cs43;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import eg.edu.alexu.csd.oop.draw.Shape;


public class PaintGui extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton btncircle, btnline, btnellipse, btntriangle,btnroundrectangle, btnrectangle, btnsquare,btndelete, 
	btnload, btnloadshape, btnundo, btnredo, btnsave,btnlinecolor,btnfillcolor,btnmove,btnresize,btncopy;
	public JPanel Panel, Panel_1, Panel_2;
	public ArrayList<Shape> shapes;
	public PaintGui(){
		setLayout(new BorderLayout());
		shapes=new ArrayList<Shape>();
	    Panel = new JPanel();
	    Panel.setBorder(new LineBorder(new Color(153, 51, 102)));
		Panel.setBackground(new Color(153, 51, 102));
		Panel.setBounds(0, 0, 1400, 169);
		getContentPane().add(Panel);
		Panel.setLayout(null);
		
		
		btntriangle = new JButton();
		btntriangle.setBackground(new Color(220, 220, 220));
		btntriangle.setVerticalTextPosition(SwingConstants.BOTTOM);
		btntriangle.setHorizontalTextPosition(SwingConstants.CENTER);
		btntriangle.setText("Triangle");
		btntriangle.setBounds(293, 12, 122, 127);
		btntriangle.setIcon(new ImageIcon("resources/Screenshot_36.png"));
		btntriangle.setForeground(new Color(255, 153, 153));
		btntriangle.setFont(new Font("Tahoma", Font.BOLD, 20));
		Panel.add(btntriangle);
		
		
		
		btnrectangle = new JButton();
		btnrectangle.setForeground(new Color(255, 153, 153));
		btnrectangle.setBackground(new Color(220, 220, 220));
		btnrectangle.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnrectangle.setHorizontalTextPosition(SwingConstants.CENTER);
		btnrectangle.setText("Rectangle");
		btnrectangle.setBounds(405, 12, 150, 127);
		btnrectangle.setIcon(new ImageIcon("resources/Screenshot_34.png"));
		btnrectangle.setFont(new Font("Tahoma", Font.BOLD, 20));
		Panel.add(btnrectangle);
		
		btnsquare = new JButton();
		btnsquare.setBackground(new Color(220, 220, 220));
		btnsquare.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnsquare.setHorizontalTextPosition(SwingConstants.CENTER);
		btnsquare.setText("Square");
		btnsquare.setBounds(553, 12, 115, 127);
		btnsquare.setIcon(new ImageIcon("resources/Screenshot_35.png"));
		btnsquare.setForeground(new Color(255, 153, 153));
		btnsquare.setFont(new Font("Tahoma", Font.BOLD, 20));
		Panel.add(btnsquare);
		
		btncircle = new JButton();
		btncircle.setBackground(new Color(220, 220, 220));
		btncircle.setVerticalTextPosition(SwingConstants.BOTTOM);
		btncircle.setHorizontalTextPosition(SwingConstants.CENTER);
		btncircle.setText("Circle");
		btncircle.setBounds(669, 12, 115, 126);
		btncircle.setIcon(new ImageIcon("resources/Screenshot_32.png"));
		btncircle.setForeground(new Color(255, 153, 153));
		btncircle.setFont(new Font("Tahoma", Font.BOLD, 20));
		Panel.add(btncircle);
		
		btnellipse = new JButton();
		btnellipse.setBackground(new Color(220, 220, 220));
		btnellipse.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnellipse.setHorizontalTextPosition(SwingConstants.CENTER);
		btnellipse.setText("Ellipse");
		btnellipse.setBounds(784, 12, 122, 125);
		btnellipse.setIcon(new ImageIcon("resources/Screenshot_31.png"));
		btnellipse.setForeground(new Color(255, 153, 153));
		btnellipse.setFont(new Font("Tahoma", Font.BOLD, 20));
		Panel.add(btnellipse);
		
		 btnline = new JButton();
			btnline.setBackground(new Color(220, 220, 220));
			btnline.setVerticalTextPosition(SwingConstants.BOTTOM);
			btnline.setHorizontalTextPosition(SwingConstants.CENTER);
			btnline.setText("Line");
			btnline.setBounds(198, 12, 94, 127);
			btnline.setIcon(new ImageIcon("resources/Screenshot_37.png"));
			btnline.setForeground(new Color(255, 153, 153));
			btnline.setFont(new Font("Tahoma", Font.BOLD, 20));
			Panel.add(btnline);
		
			 btnfillcolor = new JButton("FillColor");
				btnfillcolor.setBackground(new Color(211, 211, 211));
				btnfillcolor.setBounds(11, 78, 169, 61);
				btnfillcolor.setForeground(new Color(255, 153, 153));
				btnfillcolor.setFont(new Font("Tahoma", Font.BOLD, 20));
				Panel.add(btnfillcolor);
		
				btnlinecolor = new JButton("LineColor");
				btnlinecolor.setBackground(new Color(211, 211, 211));
				btnlinecolor.setBounds(11, 12, 169, 61);
				btnlinecolor.setForeground(new Color(255, 153, 153));
				btnlinecolor.setFont(new Font("Tahoma", Font.BOLD, 20));
				Panel.add(btnlinecolor);
				
				 btnroundrectangle = new JButton();
					btnroundrectangle.setBackground(new Color(220, 220, 220));
					btnroundrectangle.setVerticalTextPosition(SwingConstants.BOTTOM);
					btnroundrectangle.setHorizontalTextPosition(SwingConstants.CENTER);
					btnroundrectangle.setText("RoundRectangle");
					btnroundrectangle.setIcon(new ImageIcon("resources/Screenshot_33.png"));
					btnroundrectangle.setForeground(new Color(255, 153, 153));
					btnroundrectangle.setFont(new Font("Tahoma", Font.BOLD, 20));
					btnroundrectangle.setBounds(907, 12, 169, 124);
					Panel.add(btnroundrectangle);
		
	    Panel_1 = new JPanel();
	    Panel_1.setBackground(new Color(153, 51, 102));
		Panel_1.setBounds(0, 169, 199, 550);
		getContentPane().add(Panel_1);
		Panel_1.setLayout(null);
		
		 btnload = new JButton("Load");
			btnload.setBackground(new Color(211, 211, 211));
			btnload.setBounds(10, 55, 179, 43);
			Panel_1.setLayout(null);
			btnload.setForeground(new Color(255, 153, 153));
			btnload.setFont(new Font("Tahoma", Font.BOLD, 20));
			Panel_1.add(btnload);
		
			 btnundo = new JButton("Undo");
				btnundo.setBackground(new Color(211, 211, 211));
				btnundo.setBounds(10, 98, 179, 43);
				btnundo.setFont(new Font("Tahoma", Font.BOLD, 20));
				btnundo.setForeground(new Color(255, 153, 153));
				Panel_1.add(btnundo);
		
				btnredo = new JButton("Redo");
				btnredo.setBackground(new Color(211, 211, 211));
				btnredo.setBounds(10, 141, 179, 43);
				btnredo.setForeground(new Color(255, 153, 153));
				btnredo.setFont(new Font("Tahoma", Font.BOLD, 20));
				Panel_1.add(btnredo);
				
				btndelete = new JButton("Delete");
				btndelete.setBackground(new Color(211, 211, 211));
				btndelete.setBounds(10, 184, 179, 43);
				btndelete.setForeground(new Color(255, 153, 153));
				btndelete.setFont(new Font("Tahoma", Font.BOLD, 20));
				Panel_1.add(btndelete);
				
				 btnmove = new JButton("Move");
					btnmove.setBackground(new Color(211, 211, 211));
					btnmove.setBounds(10, 228, 179, 43);
					btnmove.setForeground(new Color(255, 153, 153));
					btnmove.setFont(new Font("Tahoma", Font.BOLD, 20));
					Panel_1.add(btnmove);
		
					btnsave = new JButton("Save");
					btnsave.setBackground(new Color(211, 211, 211));
					btnsave.setBounds(10, 11, 179, 43);
					btnsave.setForeground(new Color(255, 153, 153));
					btnsave.setFont(new Font("Tahoma", Font.BOLD, 20));
					Panel_1.add(btnsave);
		
					btnloadshape = new JButton("LoadShape");
					btnloadshape.setForeground(new Color(255, 153, 153));
					btnloadshape.setFont(new Font("Tahoma", Font.BOLD, 20));
					btnloadshape.setBackground(new Color(211, 211, 211));
					btnloadshape.setBounds(10, 354, 179, 43);
					Panel_1.add(btnloadshape);
					
					btnresize = new JButton("Resize");
					btnresize.setBackground(new Color(211, 211, 211));
					btnresize.setForeground(new Color(255, 153, 153));
					btnresize.setFont(new Font("Tahoma", Font.BOLD, 20));
					btnresize.setBounds(10, 267, 179, 49);
					Panel_1.add(btnresize);
					
					    btncopy = new JButton("Copy");
						btncopy.setBackground(new Color(211, 211, 211));
						btncopy.setBounds(10, 314, 179, 43);
						btncopy.setForeground(new Color(255, 153, 153));
						btncopy.setFont(new Font("Tahoma", Font.BOLD, 20));
						Panel_1.add(btncopy);
					
					
		 Panel_2 = new JPanel(new BorderLayout())
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			public void paintComponent(Graphics graphics) {
				Graphics2D g = (Graphics2D) graphics;
				super.paintComponent(g);
				Stroke oldStroke = g.getStroke();
				int thickness = 3;
				g.setStroke(new BasicStroke(thickness));
				if (!shapes.isEmpty()) {
					for (Shape sh : shapes) {
						sh.draw(g);
					}
				}
				
				g.setStroke(oldStroke);
			}
		};
		Panel_2.setBackground(new Color(255, 255, 255));
		Panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		Panel_2.setBounds(200, 169, 886, 512);
		getContentPane().add(Panel_2);
		//Panel_2.setLayout(null);
	}
		
		
	public void setshapes(ArrayList<Shape> shapes){
		this.shapes=shapes;
		
	}

	

}
