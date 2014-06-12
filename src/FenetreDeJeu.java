import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreDeJeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private Case[][] map;
	private ImageIcon caseVide = new ImageIcon("ressources/images/cases/CaseVide.png");
	private ImageIcon casePerso = new ImageIcon("ressources/images/cases/CasePerso.png");
	private ImageIcon caseArbre = new ImageIcon("ressources/images/cases/CaseArbre.png");

	public FenetreDeJeu() {
		super("ZergoLand");
		setLayout(new GridLayout(Constante.CASES_X, Constante.CASES_Y));
		setSize(Constante.X, Constante.Y); // Nombre de pixels a retirer
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initMap();
		addMap();
	}
	public void initMap() {
		map = new Case[Constante.CASES_X][Constante.CASES_Y];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = new Case(caseVide);
			}
		}
	}
	public void addMap() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				add(new JPanel().add(new JLabel(map[i][j].getSkin())));
			}
		}
	}
}