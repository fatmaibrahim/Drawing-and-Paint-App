package eg.edu.alexu.csd.oop.draw.cs43;



import javax.swing.JFrame;



/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		Controller controller = new Controller();
		controller.getpaintgui().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller.getpaintgui().setTitle("Paint");
		controller.getpaintgui().setBounds(100, 100, 1102, 711);
		controller.getpaintgui().setVisible(true);

	}

}

