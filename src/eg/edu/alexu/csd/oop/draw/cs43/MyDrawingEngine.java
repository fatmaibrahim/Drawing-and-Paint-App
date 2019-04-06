package eg.edu.alexu.csd.oop.draw.cs43;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author omid
 *
 */
public class MyDrawingEngine implements DrawingEngine {

	final static int limit = 20;
	final static int first = 0;
	List<Class<? extends Shape>> listshapes;
	public ArrayList<Shape> allShapes ;
	// used in undo function
	public ArrayList<Command> steps;
	// used in redo function
	public ArrayList<Command> todoAgain ;

	public MyDrawingEngine() {
		allShapes = new ArrayList<Shape>();
		steps = new ArrayList<Command>();
		todoAgain = new ArrayList<Command>();
		listshapes = new ArrayList<Class<? extends Shape>>();
		listshapes.add(Rectangle.class);
		listshapes.add(Square.class);
		listshapes.add(Ellipse.class);
		listshapes.add(Circle.class);
		listshapes.add(LineSegment.class);
		listshapes.add(Triangle.class);

	}

	public void refresh(Graphics canvas) {
		for (int i = 0; i < allShapes.size(); i++) {
			allShapes.get(i).draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		allShapes.add(shape);
		AddCommand obj = new AddCommand(shape);
		steps.add(obj);
		todoAgain.clear();

	}

	@Override
	public void removeShape(Shape shape) {
		allShapes.remove(shape);
		RemoveCommand obj = new RemoveCommand(shape);
		steps.add(obj);
		todoAgain.clear();
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		int flag = 0;
		for (int i = 0; i < allShapes.size(); i++) {
			if (allShapes.get(i) == oldShape) {
				allShapes.remove(i);
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
			allShapes.add(newShape);
			UpdateCommand obj = new UpdateCommand(oldShape, newShape);
			steps.add(obj);
			todoAgain.clear();
		}

	}

	@Override
	public Shape[] getShapes() {
		Shape[] array = new Shape[allShapes.size()];
		for (int i = 0; i < allShapes.size(); i++) {
			array[i] = allShapes.get(i);
		}
		return array;
	}

	public void supportedshapes(Class<? extends Shape> supportedshape) {
		listshapes.add(supportedshape);
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		return listshapes;
	}

	@Override
	public void undo() {
		if (steps.size() > limit) {
			int difference = steps.size() - limit;
			for (int i = 0; i < difference; i++) {
				steps.remove(i);
			}
		}
		String action = steps.get(steps.size() - 1).goBack();
		if (action.equals("add")) {
			allShapes.add(steps.get(steps.size() - 1).getShape1());
		} else if (action.equals("remove")) {
			allShapes.remove(steps.get(steps.size() - 1).getShape1());
		} else {
			allShapes.remove(steps.get(steps.size() - 1).getShape2());
			allShapes.add(steps.get(steps.size() - 1).getShape1());
		}
		todoAgain.add(steps.get(steps.size() - 1));
		steps.remove(steps.size() - 1);
	}

	@Override
	public void redo() {
		String action = todoAgain.get(todoAgain.size() - 1).execute();
		if (action.equals("add")) {
			allShapes.add(todoAgain.get(todoAgain.size() - 1).getShape1());
		} else if (action.equals("remove")) {
			allShapes.remove(todoAgain.get(todoAgain.size() - 1).getShape1());
		} else {
			allShapes.remove(todoAgain.get(todoAgain.size() - 1).getShape1());
			allShapes.add(todoAgain.get(todoAgain.size() - 1).getShape2());
		}
		steps.add(todoAgain.get(todoAgain.size() - 1));
		todoAgain.remove(todoAgain.size() - 1);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(String path) {
		if (path.toLowerCase().contains("xml")) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				Document d = db.newDocument();
				Element root = d.createElement("Shapes");
				d.appendChild(root);
				for (int i = 0; i < allShapes.size(); i++) {
					Element e1 = d.createElement("Shape");
					root.appendChild(e1);
					Element type = d.createElement("type");
					type.appendChild(d.createTextNode(allShapes.get(i).toString()));
					e1.appendChild(type);

					Element xPos = d.createElement("xPos");
					if (allShapes.get(i).getPosition() != null) {
						xPos.appendChild(d.createTextNode(Double.toString(allShapes.get(i).getPosition().getX())));
						e1.appendChild(xPos);
					}
					Element yPos = d.createElement("yPos");
					if (allShapes.get(i).getPosition() != null) {
						yPos.appendChild(d.createTextNode(Double.toString(allShapes.get(i).getPosition().getY())));
						e1.appendChild(yPos);
					}
					Element color = d.createElement("color");
					if (allShapes.get(i).getColor() != null) {
						color.appendChild(d.createTextNode(Integer.toString(allShapes.get(i).getColor().getRGB())));
						e1.appendChild(color);
					}
					Element Fcolor = d.createElement("Fcolor");
					if (allShapes.get(i).getFillColor() != null) {
						Fcolor.appendChild(d.createTextNode(Integer.toString(allShapes.get(i).getFillColor().getRGB())));
						e1.appendChild(Fcolor);
					}
					Element prop = d.createElement("prop");
					if (allShapes.get(i).getProperties() != null) {
						for (Map.Entry<String, Double> entry : allShapes.get(i).getProperties().entrySet()) {
							if (entry.getKey() != null) {
								Element e2 = d.createElement(entry.getKey());
								if (entry.getValue() != null) {
									e2.appendChild(d.createTextNode(Double.toString(entry.getValue())));
									prop.appendChild(e2);
								}
							}
						}
					}
					e1.appendChild(prop);

				}
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer t = tf.newTransformer();
				DOMSource ds = new DOMSource(d);
				StreamResult sr = new StreamResult(new File(path));
				t.transform(ds, sr);
			} catch (ParserConfigurationException e1) {
				e1.printStackTrace();
			} catch (TransformerConfigurationException e1) {
				e1.printStackTrace();
			} catch (TransformerException e1) {
				e1.printStackTrace();
			}
		} else if (path.toLowerCase().contains("json")) {
			JSONArray arofShapes = new JSONArray();
			for (int i = 0; i < allShapes.size(); i++) {
				JSONObject ShapeObj = new JSONObject();
				ShapeObj.put("type", allShapes.get(i).toString());
				if (allShapes.get(i).getPosition() != null) {
					ShapeObj.put("xPos", Double.toString(allShapes.get(i).getPosition().getX()));
					ShapeObj.put("yPos", Double.toString(allShapes.get(i).getPosition().getY()));
				}
				if (allShapes.get(i).getColor() != null) {
					ShapeObj.put("color", Integer.toString(allShapes.get(i).getColor().getRGB()));
				}
				if (allShapes.get(i).getFillColor() != null) {
					ShapeObj.put("Fcolor", Integer.toString(allShapes.get(i).getFillColor().getRGB()));
				}
				if (allShapes.get(i).getProperties() != null) {
					JSONArray mapKeys = new JSONArray();
					JSONArray mapValues = new JSONArray();
					for (Map.Entry<String, Double> entry : allShapes.get(i).getProperties().entrySet()) {
						if (entry.getKey() != null) {
							JSONObject keyObj = new JSONObject();
							keyObj.put("key", entry.getKey());
							mapKeys.add(keyObj);
						}
						if (entry.getValue() != null) {
							JSONObject valObj = new JSONObject();
							valObj.put("value", Double.toString(entry.getValue()));
							mapValues.add(valObj);
						}
					}
					ShapeObj.put("mapKeys", mapKeys);
					ShapeObj.put("mapValues", mapValues);
				}
				arofShapes.add(ShapeObj);
			}
			try {
				FileWriter file = new FileWriter(path);
				file.write(arofShapes.toJSONString());
				file.flush();
				file.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void load(String path) {
		allShapes.clear();
		steps.clear();
		todoAgain.clear();
		if (path.toLowerCase().contains("xml")) {
			File xmlFile = new File(path);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document d = db.parse(xmlFile);
			
				NodeList Shapelist = d.getElementsByTagName("Shape");
				for (int i = 0; i < Shapelist.getLength(); i++) {
					Shape loadedShape = null;
					Point pos = new Point();
					Double x = null,y = null;
					Node n = Shapelist.item(i);
					if (n.getNodeType() == Node.ELEMENT_NODE) {
						Element Shape = (Element) n;
						NodeList properties = Shape.getChildNodes();
						for (int j = 0; j < properties.getLength(); j++) {

							Node node = properties.item(j);
							if (node.getNodeType() == Node.ELEMENT_NODE) {
								Element pro = (Element) node;
								if (pro.getTagName().equals("type")) {
									String str = pro.getTextContent();
									if(str.contains("Circle")){
										loadedShape = new Circle();
									}if(str.contains("Ellipse")){
										loadedShape = new Ellipse();
									}if(str.contains("Rectangle")){
										loadedShape = new Rectangle();
									}if(str.contains("Triangle")){
										loadedShape = new Triangle();
									}if(str.contains("Square")){
										loadedShape = new Square();
									}if(str.contains("LineSegment")){
										loadedShape = new LineSegment();
									}
								}
								if(pro.getTagName().equals("xPos")){
									x = Double.parseDouble(pro.getTextContent());
								}
								if(pro.getTagName().equals("yPos")){
									y = Double.parseDouble(pro.getTextContent());
									pos.setLocation(x, y);
									loadedShape.setPosition(pos);
								}

								if (pro.getTagName().equals("color")) {
									Color color;
									String c = pro.getTextContent();
									color = new Color(Integer.parseInt(c));
									loadedShape.setColor(color);
								}
								if (pro.getTagName().equals("Fcolor")) {
									Color color;
									String c = pro.getTextContent();
									color = new Color(Integer.parseInt(c));
									loadedShape.setFillColor(color);
								}

								if (pro.getTagName().equals("prop")) {
									Map<String,Double> loadedMap = new HashMap<String,Double>();
									NodeList map = pro.getChildNodes();
									for (int k = 0; k < map.getLength(); k++) {
										Node m = map.item(k);
										if (m.getNodeType() == Node.ELEMENT_NODE) {
											Element mapProp = (Element) m;
											loadedMap.put(mapProp.getTagName(),
													Double.parseDouble(mapProp.getTextContent()));
										}
									}
									loadedShape.setProperties(loadedMap);
								}
							}
						}
					}
					allShapes.add(loadedShape);
				}
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (path.toLowerCase().contains("json")) {
			JSONParser parser = new JSONParser();
			try {
				JSONArray arofShapes = (JSONArray) parser.parse(new FileReader(path));
				for (int i = 0; i < arofShapes.size(); i++) {
					Shape loadedShape = null;
					JSONObject shape = (JSONObject) arofShapes.get(i);
					String str = (String) shape.get("type");
					if(str.contains("Circle")){
						loadedShape = new Circle();
					}else if(str.contains("Ellipse")){
						loadedShape = new Ellipse();
					}else if(str.contains("Rectangle")){
						loadedShape = new Rectangle();
					}else if(str.contains("Triangle")){
						loadedShape = new Triangle();
					}else if(str.contains("Square")){
						loadedShape = new Square();
					}else if(str.contains("LineSegment")){
						loadedShape = new LineSegment();
					}
					String xi = (String) shape.get("xPos");

						 Double x = Double.parseDouble(xi);
					String yi = (String) shape.get("yPos");

						Double y = Double.parseDouble(yi);
						Point pos = new Point();
						pos.setLocation(x, y);
						loadedShape.setPosition(pos);

					String c = (String) shape.get("color");
					Color color = new Color(Integer.parseInt(c));
					loadedShape.setColor(color);
					String fc = (String) shape.get("Fcolor");
					Color fcolor = new Color(Integer.parseInt(fc));
					loadedShape.setFillColor(fcolor);
					JSONArray mapKeys = (JSONArray) shape.get("mapKeys");
					JSONArray mapValues = (JSONArray) shape.get("mapValues");
					if (mapKeys != null && mapValues != null) {
						Map<String,Double> loadedMap = new HashMap<String,Double>();
						for (int j = 0; j < mapKeys.size(); j++) {
							JSONObject Keyobj = (JSONObject) mapKeys.get(j);
							String key = (String) Keyobj.get("key");
							JSONObject valObj = (JSONObject) mapValues.get(j);
							String value = (String) valObj.get("value");
							loadedMap.put(key, Double.parseDouble(value));
						}
						loadedShape.setProperties(loadedMap);
					}
					allShapes.add(loadedShape);

				}
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
