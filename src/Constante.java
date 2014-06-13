import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Constante {
	static final Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	private final static int MARGE = 100;
	public static final int SIZE_X = d.width - MARGE;
	public static final int SIZE_Y = d.height - MARGE;
	public static int CASES_X = 15;
	public static int CASES_Y = 25;
	public static ImageIcon caseVide = new ImageIcon("ressources/images/cases/CaseVide.png");
	public static ImageIcon casePerso = new ImageIcon("ressources/images/cases/CasePerso.png");
	public static ImageIcon caseArbre = new ImageIcon("ressources/images/cases/CaseArbre.png");
	public static int HP_BASE = 100;
	public static int MP_BASE = 100;
	public static int XP_BASE = 0;
	public static int OR_BASE = 0;
	public static int LVL_BASE = 1;
}