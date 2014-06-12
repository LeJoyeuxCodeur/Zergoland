import java.awt.Dimension;
import java.awt.Toolkit;

public class Constante {
	static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	public static int X = d.width;
	public static int Y = d.height;
}