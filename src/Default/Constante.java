package Default;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Constante {
	public static final Image LOGO = new ImageIcon("ressources/images/logo/logo.png").getImage();
	public static final int CASES_X = 14;
	public static final int CASES_Y = 22;
	public static final int HP_BASE = 100;
	public static final int MP_BASE = 20;
	public static final int XP_BASE = 20;
	public static final int XPMAX_LVL1 = 500;
	public static final int RAPPORT_XP_LVL1 = 5;
	public static final int OR_BASE = 0;
	public static final int LVL_BASE = 1;
	public static final int MP_MAX = 100;
	public static final String MAP = "ressources/mapsPattern/mapTest3.txt";
	
	// Mode Jeu
	public static final ImageIcon caseVideDeplacement = new ImageIcon("ressources/images/cases/modeJeu/CaseVide.png");
	public static final ImageIcon casePersoDeplacement = new ImageIcon("ressources/images/cases/modeJeu/Bryan2.png");
	public static final ImageIcon caseArbre = new ImageIcon("ressources/images/cases/modeJeu/CaseArbre.png");
	public static final ImageIcon maisonHG = new ImageIcon("ressources/images/cases/modeJeu/MaisonHG.png");
	public static final ImageIcon maisonHD = new ImageIcon("ressources/images/cases/modeJeu/MaisonHD.png");
	public static final ImageIcon maisonBG = new ImageIcon("ressources/images/cases/modeJeu/MaisonBG.png");
	public static final ImageIcon maisonBD = new ImageIcon("ressources/images/cases/modeJeu/MaisonBD.png");
	public static final ImageIcon coffre = new ImageIcon("ressources/images/cases/modeJeu/coffre.png");
	public static final ImageIcon coffreOuvert = new ImageIcon("ressources/images/cases/modeJeu/coffreOuvert.png");
	public static final ImageIcon barriere = new ImageIcon("ressources/images/cases/modeJeu/barriere.png");
	public static final ImageIcon zombie_depl = new ImageIcon("ressources/images/cases/modeJeu/zombie.png");
	public static final ImageIcon chateauPorte = new ImageIcon("ressources/images/cases/modeJeu/PorteChateau.png");
	public static final ImageIcon hautPorteChateau = new ImageIcon("ressources/images/cases/modeJeu/PorteChateauHaut.png");
	public static final ImageIcon TourBD = new ImageIcon("ressources/images/cases/modeJeu/TourBD.png");
	public static final ImageIcon TourHD = new ImageIcon("ressources/images/cases/modeJeu/TourHD.png");
	public static final ImageIcon TourHG = new ImageIcon("ressources/images/cases/modeJeu/TourHG.png");
	public static final ImageIcon TourBG = new ImageIcon("ressources/images/cases/modeJeu/TourBG.png");
	public static final ImageIcon TourHH = new ImageIcon("ressources/images/cases/modeJeu/TourHH.png");
	public static final ImageIcon rock = new ImageIcon("ressources/images/cases/modeJeu/Rock.png");
	
	// Mode combat
	public static final ImageIcon casePersoAttaque = new ImageIcon("ressources/images/cases/modeCombat/Bryan2.png");
	public static final ImageIcon caseVideCombat = new ImageIcon("ressources/images/cases/modeCombat/caseVideCombat.png");
	public static final ImageIcon caseObstacle = new ImageIcon("ressources/images/cases/modeCombat/caseObstacle.png");
	public static final ImageIcon zombie_att = new ImageIcon("ressources/images/cases/modeCombat/zombie.png");
	
	// Items
	public static final Item potion_sante = new Item("Potion de santé", new ImageIcon("ressources/images/item/potionSante.png"));
	public static final Item potion_mana = new Item("Potion de mana", new ImageIcon("ressources/images/item/potionMana.png"));
}