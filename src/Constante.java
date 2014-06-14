import java.awt.Image;

import javax.swing.ImageIcon;

public class Constante {
	public static final Image LOGO = new ImageIcon("ressources/images/logo/logo.png").getImage();
	public static final int CASES_X = 15;
	public static final int CASES_Y = 22;
	public static final int HP_BASE = 100;
	public static final int MP_BASE = 100;
	public static final int XP_BASE = 0;
	public static final int OR_BASE = 0;
	public static final int LVL_BASE = 1;
	public static final int MP_MAX = 100;
	
	// Mode Jeu
	public static final String MAP_DEPLACEMENT = "ressources/mapsPattern/mapTest2.txt";
	public static final ImageIcon caseVideDeplacement = new ImageIcon("ressources/images/cases/deplacement/CaseVide.png");
	public static final ImageIcon casePersoDeplacement = new ImageIcon("ressources/images/cases/deplacement/Bryan2.png");
	public static final ImageIcon caseArbre = new ImageIcon("ressources/images/cases/deplacement/CaseArbre.png");
	public static final ImageIcon maisonHG = new ImageIcon("ressources/images/cases/deplacement/MaisonHG.png");
	public static final ImageIcon maisonHD = new ImageIcon("ressources/images/cases/deplacement/MaisonHD.png");
	public static final ImageIcon maisonBG = new ImageIcon("ressources/images/cases/deplacement/MaisonBG.png");
	public static final ImageIcon maisonBD = new ImageIcon("ressources/images/cases/deplacement/MaisonBD.png");
	public static final ImageIcon coffre = new ImageIcon("ressources/images/cases/deplacement/coffre.png");
	public static final ImageIcon coffreOuvert = new ImageIcon("ressources/images/cases/deplacement/coffreOuvert.png");
	public static final ImageIcon barriere = new ImageIcon("ressources/images/cases/deplacement/barriere.png");
	public static final ImageIcon zombie_depl = new ImageIcon("ressources/images/cases/deplacement/zombie.png");
	
	// Mode combat
	public static final ImageIcon casePersoAttaque = new ImageIcon("ressources/images/cases/attaque/Bryan2.png");
	public static final ImageIcon caseVideCombat = new ImageIcon("ressources/images/cases/attaque/caseVideCombat.png");
	public static final ImageIcon caseObstacle = new ImageIcon("ressources/images/cases/attaque/caseObstacle.png");
	public static final ImageIcon zombie_att = new ImageIcon("ressources/images/cases/attaque/zombie.png");

}