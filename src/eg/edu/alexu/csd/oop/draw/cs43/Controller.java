package eg.edu.alexu.csd.oop.draw.cs43;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Controller implements ActionListener, ChangeListener {
	public PaintGui paintgui;
	private Class<?> loadedClass;
	public String typeshape;
	public String selectmode;
	public MyDrawingEngine engine;
	public Shape selectshape;
	public Shape oldshape;
	public Color fillcolor = Color.white;
	public Color color = Color.black;
	int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
	int dragshape = -1;
	int raduis,width,height;
	Point position,pointmove,pointtriangle2,pointtriangle3,pointline;

	public Controller() {
		paintgui = new PaintGui();
		engine = new MyDrawingEngine();
		paintgui.btnroundrectangle.addActionListener(this);
		paintgui.btnroundrectangle.setVisible(false);
		paintgui.btncircle.addActionListener(this);
		paintgui.btndelete.addActionListener(this);
		paintgui.btnellipse.addActionListener(this);
		paintgui.btnfillcolor.addActionListener(this);
		paintgui.btnline.addActionListener(this);
		paintgui.btnlinecolor.addActionListener(this);
		paintgui.btnload.addActionListener(this); 
		paintgui.btnloadshape.addActionListener(this);
		paintgui.btnrectangle.addActionListener(this);
		paintgui.btnredo.addActionListener(this);
		paintgui.btnsave.addActionListener(this);
		paintgui.btnsquare.addActionListener(this);
		paintgui.btntriangle.addActionListener(this);
		paintgui.btnundo.addActionListener(this);
		paintgui.btnresize.addActionListener(this);
		paintgui.btnmove.addActionListener(this);
		paintgui.btncopy.addActionListener(this);
		paintgui.Panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("kjkkk");
				dragshape = 1;
				x1 = e.getX();
				y1 = e.getY();
				x2 = x1;
				y2 = y1;
				if (selectmode.equals("draw")) {
					if (typeshape.equals("Circle")) {
						drawcircle();
					}
					if (typeshape.equals("Ellipse")) {
						drawellipse();
					}
					if (typeshape.equals("Line")) {
						drawline();
					}
					if (typeshape.equals("Rectangle")) {
						drawrectangle();
					}
					if (typeshape.equals("Triangle")) {
						drawtriangle();
					}
					if (typeshape.equals("Square")) {
						drawsquare();
					}
					if (typeshape.equals("RoundRectangle")) {
						try {
							drawroundrectangle();
						} catch (InstantiationException | IllegalAccessException e1) {
							JOptionPane.showMessageDialog(null, "there is error in loading class");
						}
					}
					paintgui.repaint();

				} else if (selectmode.equals("Move")) {
					if (selectshape != null) {
						if(selectshape.getClass().getName().contains("Triangle")){
							pointmove=new Point();
							pointmove.x=(int)selectshape.getProperties().get("x1").doubleValue();
							pointmove.y=(int)selectshape.getProperties().get("y1").doubleValue();
							pointtriangle2=new Point();
							pointtriangle2.x=(int)selectshape.getProperties().get("x2").doubleValue();
							pointtriangle2.y=(int)selectshape.getProperties().get("y2").doubleValue();
							pointtriangle3=new Point();
							pointtriangle3.x=(int)selectshape.getProperties().get("x3").doubleValue();
							pointtriangle3.y=(int)selectshape.getProperties().get("y3").doubleValue();
							Map<String, Double> properties = new HashMap<>();
							properties.put("x1", (double) (pointmove.x+x2-x1));
	                		properties.put("y1", (double) (pointmove.y+y2-y1));
							properties.put("x2", (double) (pointtriangle2.x+x2-x1));
	                		properties.put("y2", (double) (pointtriangle2.y+y2-y1));
	                		properties.put("x3", (double) (pointtriangle3.x+x2-x1));
	                		properties.put("y3", (double) (pointtriangle3.y+y2-y1));
	                		selectshape.setProperties(properties);
						}else if(selectshape.getClass().getName().contains("LineSegment")){
							pointmove=selectshape.getPosition();
							selectshape.setPosition(new Point((pointmove.x+x2-x1),(pointmove.y+y2-y1)));
							pointline=new Point();
							pointline.x=(int)selectshape.getProperties().get("endx").doubleValue();
							pointline.y=(int)selectshape.getProperties().get("endy").doubleValue();
							Map<String, Double> properties = new HashMap<>();
							properties.put("endx", (double) (pointline.x+x2-x1));
	                		properties.put("endy", (double)(pointline.y+y2-y1));
	                		selectshape.setProperties(properties);
						}else{
							pointmove=selectshape.getPosition();
							selectshape.setPosition(new Point((pointmove.x+x2-x1),(pointmove.y+y2-y1)));
						}
						engine.allShapes.add(selectshape);
						oldshape = selectshape;
						paintgui.setshapes(engine.allShapes);
						paintgui.repaint();
					}

				} else if (selectmode.equals("Resize")) {
					if (selectshape != null) {
					   Map<String, Double> properties = new HashMap<>();
					   if(selectshape.getClass().getName().contains("Circle")){
						 raduis=selectshape.getProperties().get("raduis").intValue();
					    position=selectshape.getPosition();
						properties.put("raduis", (double) Math.abs(raduis+(x2-x1)*Math.sqrt(2)));
					}
                    if(selectshape.getClass().getName().contains("Rectangle")&&(!selectshape.getClass().getName().contains("RoundRectangle"))){
                    	width=selectshape.getProperties().get("Width").intValue();
                    	height=selectshape.getProperties().get("height").intValue();
                    	properties.put("Width", (double) Math.abs(width+(x2 - x1)));
                		properties.put("height", (double) Math.abs(height+(y2 - y1)));
					}
                    if(selectshape.getClass().getName().contains("Ellipse")){
                    	width=selectshape.getProperties().get("Width").intValue();
                    	height=selectshape.getProperties().get("height").intValue();
                    	properties.put("Width", (double) Math.abs(width+(x2 - x1)));
                		properties.put("height", (double) Math.abs(height+(y2 - y1)));
					}
                    if(selectshape.getClass().getName().contains("Triangle")){
                    	pointmove=new Point();
						pointmove.x=(int)selectshape.getProperties().get("x1").doubleValue();
						pointmove.y=(int)selectshape.getProperties().get("y1").doubleValue();
                    	pointtriangle2=new Point();
						pointtriangle2.x=(int)selectshape.getProperties().get("x2").doubleValue();
						pointtriangle2.y=(int)selectshape.getProperties().get("y2").doubleValue();
						pointtriangle3=new Point();
						pointtriangle3.x=(int)selectshape.getProperties().get("x3").doubleValue();
						pointtriangle3.y=(int)selectshape.getProperties().get("y3").doubleValue();
						int x3, y3;
	               		if (pointmove.x > pointtriangle2.x+x2-x1) {
	                			x3 = (pointtriangle2.x+x2-x1 + (Math.abs(pointmove.x - pointtriangle2.x+x2-x1) * 2));
	                			y3 = pointtriangle2.y+y2-y1;
	                		} else {
	                			x3 = (pointtriangle2.x+x2-x1 - (Math.abs(pointmove.x - pointtriangle2.x+x2-x1) * 2));
	                			y3 = pointtriangle2.y+y2-y1;
	                		}
	                    		
	                		properties.put("x2", (double) (pointtriangle2.x+x2-x1));
	                		properties.put("y2", (double) (pointtriangle2.y+y2-y1));
	                		properties.put("x3", (double) x3);
	                		properties.put("y3", (double) y3);
                		
					}
                    if(selectshape.getClass().getName().contains("LineSegment")){
						pointline=new Point();
						pointline.x=(int)selectshape.getProperties().get("endx").doubleValue();
						pointline.y=(int)selectshape.getProperties().get("endy").doubleValue();
						properties.put("endx", (double) (pointline.x+x2-x1));
						properties.put("endy", (double)(pointline.y+y2-y1));
					}
                    if(selectshape.getClass().getName().contains("Square")){
                    	width=selectshape.getProperties().get("Length").intValue();
                    	properties.put("Length", (double) Math.abs(width+(x2 - x1)));
					}
                    if(selectshape.getClass().getName().contains("RoundRectangle")){
                    	width=selectshape.getProperties().get("Width").intValue();
                    	height=selectshape.getProperties().get("height").intValue();
                    	properties.put("Width", (double) Math.abs(width+(x2 - x1)));
                		properties.put("Length", (double) Math.abs(height+(y2 - y1)));
					}
                    selectshape.setProperties(properties);	
						engine.allShapes.add(selectshape);
						oldshape = selectshape;
						paintgui.setshapes(engine.allShapes);
						paintgui.repaint();
					}
				} else{
					checkselectshape(new Point(x2,y2));
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				dragshape = 3;
				x2 = e.getX();
				y2 = e.getY();
				if (selectmode.equals("draw")) {
					if (typeshape.equals("Circle")) {
						drawcircle();
					}
					if (typeshape.equals("Ellipse")) {
						drawellipse();
					}
					if (typeshape.equals("Line")) {
						drawline();
					}
					if (typeshape.equals("Rectangle")) {
						drawrectangle();
					}
					if (typeshape.equals("Triangle")) {
						drawtriangle();
					}
					if (typeshape.equals("Square")) {
						drawsquare();
					}
					if (typeshape.equals("RoundRectangle")) {
						try {
							drawroundrectangle();
						} catch (InstantiationException | IllegalAccessException e1) {
							JOptionPane.showMessageDialog(null, "there is error in loading class");
						}
					}
					paintgui.repaint();
					selectmode = "";
					selectshape = null;
				} else if (selectmode.equals("Move")) {
					if (selectshape != null) {
						if(selectshape.getClass().getName().contains("Triangle")){
							Map<String, Double> properties = new HashMap<>();
							properties.put("x1", (double) (pointmove.x+x2-x1));
	                		properties.put("y1", (double) (pointmove.y+y2-y1));
							properties.put("x2", (double) (pointtriangle2.x+x2-x1));
	                		properties.put("y2", (double) (pointtriangle2.y+y2-y1));
	                		properties.put("x3", (double) (pointtriangle3.x+x2-x1));
	                		properties.put("y3", (double) (pointtriangle3.y+y2-y1));
	                		selectshape.setProperties(properties);
						}else if(selectshape.getClass().getName().contains("LineSegment")){
							selectshape.setPosition(new Point((pointmove.x+x2-x1),(pointmove.y+y2-y1)));
							Map<String, Double> properties = new HashMap<>();
							properties.put("endx", (double) (pointline.x+x2-x1));
	                		properties.put("endy", (double)(pointline.y+y2-y1));
	                		selectshape.setProperties(properties);
						}else{
							selectshape.setPosition(new Point((pointmove.x+x2-x1),(pointmove.y+y2-y1)));
						}
						engine.allShapes.remove(oldshape);
						engine.addShape(selectshape);
						oldshape = selectshape;
						paintgui.setshapes(engine.allShapes);
						paintgui.repaint();
						selectmode = "";
					}
				}else if(selectmode.equals("Resize")){
					if (selectshape != null) {
					Map<String, Double> properties = new HashMap<>();
					if(selectshape.getClass().getName().contains("Circle")){ 
						properties.put("raduis", (double) Math.abs(raduis+(x2-x1)*Math.sqrt(2)));
				}
                if(selectshape.getClass().getName().contains("Rectangle")){
                	properties.put("Width", (double) Math.abs(width+(x2 - x1)));
            		properties.put("height", (double) Math.abs(height+(y2 - y1)));
				}
                if(selectshape.getClass().getName().contains("Ellipse")){
                	properties.put("Width", (double) Math.abs(width+(x2 - x1)));
            		properties.put("height", (double) Math.abs(height+(y2 - y1)));
				}
                if(selectshape.getClass().getName().contains("Triangle")){
                	int x3, y3;
               		if (pointmove.x > pointtriangle2.x+x2-x1) {
                			x3 = (pointtriangle2.x+x2-x1 + (Math.abs(pointmove.x - pointtriangle2.x+x2-x1) * 2));
                			y3 = pointtriangle2.y+y2-y1;
                		} else {
                			x3 = (pointtriangle2.x+x2-x1 - (Math.abs(pointmove.x - pointtriangle2.x+x2-x1) * 2));
                			y3 = pointtriangle2.y+y2-y1;
                		}
                    		
                		properties.put("x2", (double) (pointtriangle2.x+x2-x1));
                		properties.put("y2", (double) (pointtriangle2.y+y2-y1));
                		properties.put("x3", (double) x3);
                		properties.put("y3", (double) y3);
               	
				}
                if(selectshape.getClass().getName().contains("LineSegment")){
                	properties.put("endx", (double) (pointline.x+x2-x1));
					properties.put("endy", (double)(pointline.y+y2-y1));
				}
                if(selectshape.getClass().getName().contains("Square")){
                	properties.put("Length", (double) Math.abs(width+(x2 - x1)));
				}
                if(selectshape.getClass().getName().contains("RoundRectangle")){
                	properties.put("Width", (double) Math.abs(width+(x2 - x1)));
            		properties.put("Length", (double) Math.abs(height+(y2 - y1)));
				}
                
                selectshape.setProperties(properties);	
                engine.allShapes.remove(oldshape);
				engine.addShape(selectshape);
				oldshape = selectshape;
				paintgui.setshapes(engine.allShapes);
				paintgui.repaint();
				selectmode = "";
				}
			}
					paintgui.Panel_2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		});
		paintgui.Panel_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				dragshape = 2;
				x2 = e.getX();
				y2 = e.getY();
				if (selectmode.equals("draw")) {
					paintgui.Panel_2.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
					if (typeshape.equals("Circle")) {
						drawcircle();
					}
					if (typeshape.equals("Ellipse")) {

						drawellipse();
					}
					if (typeshape.equals("Line")) {
						drawline();
					}
					if (typeshape.equals("Rectangle")) {
						drawrectangle();
					}
					if (typeshape.equals("Triangle")) {
						drawtriangle();
					}
					if (typeshape.equals("Square")) {
						drawsquare();
					}
					if (typeshape.equals("RoundRectangle")) {
						try {
							drawroundrectangle();
						} catch (InstantiationException | IllegalAccessException e1) {
							JOptionPane.showMessageDialog(null, "there is error in loading class");
						}
					}
					paintgui.repaint();
				} else if (selectmode.equals("Move")) {
					if (selectshape != null) {
						paintgui.Panel_2.setCursor(new Cursor(Cursor.MOVE_CURSOR));
						if(selectshape.getClass().getName().contains("Triangle")){
							Map<String, Double> properties = new HashMap<>();
							properties.put("x1", (double) (pointmove.x+x2-x1));
	                		properties.put("y1", (double) (pointmove.y+y2-y1));
							properties.put("x2", (double) (pointtriangle2.x+x2-x1));
	                		properties.put("y2", (double) (pointtriangle2.y+y2-y1));
	                		properties.put("x3", (double) (pointtriangle3.x+x2-x1));
	                		properties.put("y3", (double) (pointtriangle3.y+y2-y1));
	                		selectshape.setProperties(properties);
						}else if(selectshape.getClass().getName().contains("LineSegment")){
							selectshape.setPosition(new Point((pointmove.x+x2-x1),(pointmove.y+y2-y1)));
							Map<String, Double> properties = new HashMap<>();
							properties.put("endx", (double) (pointline.x+x2-x1));
	                		properties.put("endy", (double)(pointline.y+y2-y1));
	                		selectshape.setProperties(properties);
						}else{
							selectshape.setPosition(new Point((pointmove.x+x2-x1),(pointmove.y+y2-y1)));
						}
						for (int i = 0; i < engine.allShapes.size(); i++) {
							if (engine.allShapes.get(i) == oldshape) {
								engine.allShapes.remove(i);
								engine.allShapes.add(selectshape);
								break;
							}
						}
						oldshape = selectshape;
						paintgui.setshapes(engine.allShapes);
						paintgui.repaint();
					}
				}else if(selectmode.equals("Resize")){
					if (selectshape != null) {
					Map<String, Double> properties = new HashMap<>();
					if(selectshape.getClass().getName().contains("Circle")){
						properties.put("raduis", (double) Math.abs(raduis+(x2-x1)*Math.sqrt(2)));
				}
                if(selectshape.getClass().getName().contains("Rectangle")){
                	properties.put("Width", (double) Math.abs(width+(x2 - x1)));
            		properties.put("height", (double) Math.abs(height+(y2 - y1)));
				}
                if(selectshape.getClass().getName().contains("Ellipse")){
                	properties.put("Width", (double) Math.abs(width+(x2 - x1)));
            		properties.put("height", (double) Math.abs(height+(y2 - y1)));
				}
                if(selectshape.getClass().getName().contains("Triangle")){
                	int x3, y3;
               		if (pointmove.x > pointtriangle2.x+x2-x1) {
                			x3 = (pointtriangle2.x+x2-x1 + (Math.abs(pointmove.x - pointtriangle2.x+x2-x1) * 2));
                			y3 = pointtriangle2.y+y2-y1;
                		} else {
                			x3 = (pointtriangle2.x+x2-x1 - (Math.abs(pointmove.x - pointtriangle2.x+x2-x1) * 2));
                			y3 = pointtriangle2.y+y2-y1;
                		}
                    		
                		properties.put("x2", (double) (pointtriangle2.x+x2-x1));
                		properties.put("y2", (double) (pointtriangle2.y+y2-y1));
                		properties.put("x3", (double) x3);
                		properties.put("y3", (double) y3);
				}
                if(selectshape.getClass().getName().contains("LineSegment")){
                	properties.put("endx", (double) (pointline.x+x2-x1));
					properties.put("endy", (double)(pointline.y+y2-y1));
				}
                if(selectshape.getClass().getName().contains("Square")){
                	properties.put("Length", (double) Math.abs(width+(x2 - x1)));
				}
                if(selectshape.getClass().getName().contains("RoundRectangle")){
                	properties.put("Width", (double) Math.abs(width+(x2 - x1)));
            		properties.put("Length", (double) Math.abs(height+(y2 - y1)));
				}
                selectshape.setProperties(properties);	
                paintgui.Panel_2.setCursor(new Cursor(Cursor.N_RESIZE_CURSOR));
				for (int i = 0; i < engine.allShapes.size(); i++) {
					if (engine.allShapes.get(i) == oldshape) {
						engine.allShapes.remove(i);
						engine.allShapes.add(selectshape);
						break;
					}
				}
				oldshape = selectshape;
				paintgui.setshapes(engine.allShapes);
				paintgui.repaint();
				}
			}
			}
		});

		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String buttonPressed = e.getActionCommand();
		if (buttonPressed == "Circle" || buttonPressed == "Rectangle" || buttonPressed == "Square"
				|| buttonPressed == "Ellipse" || buttonPressed == "Line" || buttonPressed == "Triangle"
				|| buttonPressed == "RoundRectangle") {
			selectmode = "draw";
			typeshape = buttonPressed;
		} else if (buttonPressed.equals("Delete")) {
			selectmode = "Delete";
			if (selectshape != null) {
				engine.removeShape(selectshape);
				paintgui.setshapes(engine.allShapes);
				paintgui.repaint();
				selectshape = null;
			}
			selectmode = "";
		} else if (buttonPressed.equals("Undo")) {
			selectmode = "Undo";
			selectshape = null;
			try {
				engine.undo();
				paintgui.setshapes(engine.allShapes);
				paintgui.repaint();
			} catch (Exception e0) {
				JOptionPane.showMessageDialog(null, "theere is no ellement undo");
			}
		} else if (buttonPressed.equals("Redo")) {
			selectmode = "Redo";
			selectshape = null;
			try {
				engine.redo();
				paintgui.setshapes(engine.allShapes);
				paintgui.repaint();
			} catch (Exception e0) {
				JOptionPane.showMessageDialog(null, "there is no ellement redo");
			}
		} else if (buttonPressed.equals("Save")) {
			selectmode = "Save";
			selectshape = null;
			try {
				save();
				selectmode = "";
			} catch (FileNotFoundException e0) {
				JOptionPane.showMessageDialog(null, "there an error in saving!");
			} catch (IOException e0) {
				JOptionPane.showMessageDialog(null, "there an error in saving!");
			}
		} else if (buttonPressed.equals("Load")) {
			selectmode = "Load";
			selectshape = null;
			try {
				load();
				selectmode = "";
			} catch (FileNotFoundException e0) {
				JOptionPane.showMessageDialog(null, "there an error in loading!");
			}
		} else if (buttonPressed.equals("LoadShape")) {
			selectmode = "LoadShape";
			try {
				loadclass();
				selectmode = "";
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, "there an error in loading!");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (buttonPressed.equals("LineColor")) {
			selectmode = "LineColor";
			color = JColorChooser.showDialog(null, "choose the line color", Color.BLACK);
		} else if (buttonPressed.equals("FillColor")) {
			selectmode = "FillColor";
			fillcolor = JColorChooser.showDialog(null, "choose the line color", Color.WHITE);
		} else if (buttonPressed.equals("Move")) {
			selectmode = "Move";

		} else if (buttonPressed.equals("Copy")) {
			selectmode = "Copy";
			if (selectshape != null) {
				try {
					Shape copyshape = (Shape) selectshape.clone();
					copyshape.setisselect(false);
					engine.addShape(copyshape);
					paintgui.setshapes(engine.allShapes);
					paintgui.repaint();
					selectmode = "";
				} catch (CloneNotSupportedException e1) {
					JOptionPane.showMessageDialog(null, "there an error in coping!");
				}

			}

		} else if (buttonPressed.equals("Resize")) {
			selectmode = "Resize";
		}

		paintgui.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				int confirmed = JOptionPane.showConfirmDialog(null, "Do you want to save?", "Exit Program ",
						JOptionPane.YES_NO_OPTION);

				if (confirmed == JOptionPane.YES_OPTION) {
					try {
						save();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(null, "there an erroe in saving!");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "there an erroe in saving!");
					}
				} else {
					System.exit(0);
				}
			}

		});
	}

	public void drawcircle() {
		selectshape = new Circle();
		selectshape.setColor(color);
		selectshape.setFillColor(fillcolor);
		Map<String, Double> properties = new HashMap<>();
		int cx = Math.min(x1, x2);
		int cy = Math.min(y1, y2);
		Point p = new Point(cx, cy);
		properties.put("raduis", (double) Math.abs(x1 - x2));
		selectshape.setPosition(p);
		selectshape.setProperties(properties);
		if (dragshape == 1) {
			engine.allShapes.add(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 2) {
			for (int i = 0; i < engine.allShapes.size(); i++) {
				if (engine.allShapes.get(i) == oldshape) {
					engine.allShapes.remove(i);
					engine.allShapes.add(selectshape);
					break;
				}
			}
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 3) {
			engine.allShapes.remove(oldshape);
			engine.addShape(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		}

	}

	public void drawline() {
		selectshape = new LineSegment();
		selectshape.setColor(color);
		Map<String, Double> properties = new HashMap<>();
		Point p = new Point(x1, y1);
		properties.put("endx", (double) x2);
		properties.put("endy", (double) y2);
		selectshape.setProperties(properties);
		selectshape.setPosition(p);
		if (dragshape == 1) {
			engine.allShapes.add(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 2) {
			for (int i = 0; i < engine.allShapes.size(); i++) {
				if (engine.allShapes.get(i) == oldshape) {
					engine.allShapes.remove(i);
					engine.allShapes.add(selectshape);
					break;
				}
			}
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 3) {
			engine.allShapes.remove(oldshape);
			engine.addShape(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		}

	}

	public void drawtriangle() {
		selectshape = new Triangle();
		selectshape.setColor(color);
		selectshape.setFillColor(fillcolor);
		Map<String, Double> properties = new HashMap<>();
		int x3, y3;
		if (x1 > x2) {
			x3 = (x2 + (Math.abs(x1 - x2)*2));
			y3 = y2;
		} else {
			x3 = (x2 - (Math.abs(x1 - x2)*2));
			y3 = y2;
		}
		properties.put("x1", (double) x1);
		properties.put("y1", (double) y1);
		properties.put("x2", (double) x2);
		properties.put("y2", (double) y2);
		properties.put("x3", (double) x3);
		properties.put("y3", (double) y3);
		selectshape.setProperties(properties);
		if (dragshape == 1) {
			engine.allShapes.add(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 2) {
			for (int i = 0; i < engine.allShapes.size(); i++) {
				if (engine.allShapes.get(i) == oldshape) {
					engine.allShapes.remove(i);
					engine.allShapes.add(selectshape);
					break;
				}
			}
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 3) {
			engine.allShapes.remove(oldshape);
			engine.addShape(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		}

	}

	public void drawrectangle() {
		selectshape = new Rectangle();
		selectshape.setColor(color);
		selectshape.setFillColor(fillcolor);
		Map<String, Double> properties = new HashMap<>();
		int cx = Math.min(x1, x2);
		int cy = Math.min(y1, y2);
		Point p = new Point(cx, cy);
		properties.put("Width", (double) Math.abs(x1 - x2));
		properties.put("height", (double) Math.abs(y1 - y2));
		selectshape.setPosition(p);
		selectshape.setProperties(properties);
		if (dragshape == 1) {
			engine.allShapes.add(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 2) {
			for (int i = 0; i < engine.allShapes.size(); i++) {
				if (engine.allShapes.get(i) == oldshape) {
					engine.allShapes.remove(i);
					engine.allShapes.add(selectshape);
					break;
				}
			}
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 3) {
			engine.allShapes.remove(oldshape);
			engine.addShape(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		}

	}

	public void drawroundrectangle() throws InstantiationException, IllegalAccessException {

		selectshape = (Shape) loadedClass.newInstance();
		engine.addShape(selectshape);
		engine.allShapes.add(selectshape);
		paintgui.setshapes(engine.allShapes);
//		selectshape.setColor(color);
//		selectshape.setFillColor(fillcolor);
//		Map<String, Double> properties = new HashMap<>();
//		int cx = Math.min(x1, x2);
//		int cy = Math.min(y1, y2);
//		Point p = new Point(cx, cy);
//		properties.put("Width", (double) Math.abs(x1 - x2));
//		properties.put("Length", (double) Math.abs(y1 - y2));
//		properties.put("ArcWidth", 2.0);
//		properties.put("ArcLength", 2.0);
//		selectshape.setPosition(p);
//		selectshape.setProperties(properties);
//		if (dragshape == 1) {
//			engine.allShapes.add(selectshape);
//			oldshape = selectshape;
//			paintgui.setshapes(engine.allShapes);
//		} else if (dragshape == 2) {
//			for (int i = 0; i < engine.allShapes.size(); i++) {
//				if (engine.allShapes.get(i) == oldshape) {
//					engine.allShapes.remove(i);
//					engine.allShapes.add(selectshape);
//					break;
//				}
//			}
//			oldshape = selectshape;
//			paintgui.setshapes(engine.allShapes);
//		} else if (dragshape == 3) {
//			engine.allShapes.remove(oldshape);
//			engine.addShape(selectshape);
//			oldshape = selectshape;
//			paintgui.setshapes(engine.allShapes);
//		}
	}

	public void drawsquare() {
		selectshape = new Square();
		selectshape.setColor(color);
		selectshape.setFillColor(fillcolor);
		Map<String, Double> properties = new HashMap<>();
		int cx = Math.min(x1, x2);
		int cy = Math.min(y1, y2);
		Point p = new Point(cx, cy);
		properties.put("Length", (double) Math.abs(x1 - x2));
		selectshape.setPosition(p);
		selectshape.setProperties(properties);
		if (dragshape == 1) {
			engine.allShapes.add(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 2) {
			for (int i = 0; i < engine.allShapes.size(); i++) {
				if (engine.allShapes.get(i) == oldshape) {
					engine.allShapes.remove(i);
					engine.allShapes.add(selectshape);
					break;
				}
			}
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 3) {
			engine.allShapes.remove(oldshape);
			engine.addShape(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		}

	}

	public void drawellipse() {
		selectshape = new Ellipse();
		selectshape.setColor(color);
		selectshape.setFillColor(fillcolor);
		Map<String, Double> properties = new HashMap<>();
		int cx = Math.min(x1, x2);
		int cy = Math.min(y1, y2);
		Point p = new Point(cx, cy);
		properties.put("Width", (double) Math.abs(x1 - x2));
		properties.put("height", (double) Math.abs(y1 - y2));
		selectshape.setPosition(p);
		selectshape.setProperties(properties);
		if (dragshape == 1) {
			engine.allShapes.add(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 2) {
			for (int i = 0; i < engine.allShapes.size(); i++) {
				if (engine.allShapes.get(i) == oldshape) {
					engine.allShapes.remove(i);
					engine.allShapes.add(selectshape);
					break;
				}
			}
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		} else if (dragshape == 3) {
			engine.allShapes.remove(oldshape);
			engine.addShape(selectshape);
			oldshape = selectshape;
			paintgui.setshapes(engine.allShapes);
		}

	}

	public PaintGui getpaintgui() {
		return paintgui;
	}

	public void save() throws IOException {
		String path = System.getProperty("user.home") + "/Desktop".replace("\\", "/");
		JFileChooser fileChooser = new JFileChooser(path);
		fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.json)", ".json"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.xml)", ".xml"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		int returnVal = fileChooser.showSaveDialog(paintgui);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			String s = fileChooser.getFileFilter().getDescription();
			String sec = "", returnpath;
			boolean flag = false;
			for (int i = 0; i < s.length() - 1; i++) {
				if (flag) {
					sec += s.charAt(i);
				}
				if (s.charAt(i) == '*') {
					flag = true;
				}

			}
			returnpath = file.getAbsolutePath() + sec;
			engine.save(returnpath);

		}

	}

	public void load() throws FileNotFoundException {
		String path = System.getProperty("user.home") + "/Desktop".replace("\\", "/");
		JFileChooser fileChooser = new JFileChooser(path);
		int returnVal = fileChooser.showOpenDialog(paintgui);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			engine.load(fileChooser.getSelectedFile().getAbsolutePath());
			System.out.println(engine.allShapes);
			paintgui.setshapes(engine.allShapes);
			paintgui.repaint();
		}
	}

	public void loadclass() throws ClassNotFoundException, IOException {
		String path = System.getProperty("user.home") + "/Desktop".replace("\\", "/");
		JFileChooser fileChooser = new JFileChooser(path);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.jar)", ".jar"));
		int returnVal = fileChooser.showOpenDialog(paintgui);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String nameclass=JOptionPane.showInputDialog(null,"ENTER YOUR LOAD CLASS NAME:");
			LoadClass load=new LoadClass();
			loadedClass = load.excute(fileChooser.getSelectedFile().getAbsolutePath(),nameclass);
				if(loadedClass!=null){
					paintgui.btnroundrectangle.setVisible(true);
				}
				
			}
			paintgui.repaint();
		}
	

	public void checkselectshape(Point p) {
		for (int i = 0; i < engine.allShapes.size(); i++) {
			if (engine.allShapes.get(i).contain(p)) {
				if (!engine.allShapes.get(i).getsetisselect()) {
					engine.allShapes.get(i).setisselect(true);
					selectshape = engine.allShapes.get(i);
					paintgui.setshapes(engine.allShapes);
					paintgui.repaint();
				} else {
					selectshape = null;
					engine.allShapes.get(i).setisselect(false);
					paintgui.setshapes(engine.allShapes);
					paintgui.repaint();
				}
				return;
			}
		}
	}
}
