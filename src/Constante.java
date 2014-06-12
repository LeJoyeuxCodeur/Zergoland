import java.awt.Dimension;
import java.awt.Toolkit;

public class Constante {
	static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	final static int MARGE = 150;
	public static int X = d.width - MARGE;
	public static int Y = d.height - MARGE;
}