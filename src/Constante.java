import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Constante {
	static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	final static int MARGE = 150;
	public static int X = d.width - MARGE;
	public static int Y = d.height - MARGE;
	public static int CASES_X = 15;
	public static int CASES_Y = 25;
	public static ImageIcon caseVide = new ImageIcon("ressources/images/cases/CaseVide.png");
	public static ImageIcon casePerso = new ImageIcon("ressources/images/cases/CasePerso.png");
	public static ImageIcon caseArbre = new ImageIcon("ressources/images/cases/CaseArbre.png");
}