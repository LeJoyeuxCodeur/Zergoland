import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreDeJeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private Case[][] map;
	private JPanel[][] panels;

	public FenetreDeJeu() {
		super("ZergoLand");
		initMap();
		initFenetre();
	}
	private void initFenetre() {
		setLayout(new GridBagLayout());
		setSize(Constante.X, Constante.Y); // Nombre de pixels a retirer
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void initMap() {
		GridBagConstraints constraint = new GridBagConstraints();
		map = new Case[Constante.CASES_X][Constante.CASES_Y];
		panels = new JPanel[Constante.CASES_X][Constante.CASES_Y];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				constraint.gridx = i;
				constraint.gridy = j;
				map[i][j] = new Case(Constante.caseArbre);
				panels[i][j] = new JPanel();
				panels[i][j].add(new JLabel(map[i][j].getSkin()));
				getContentPane().add(panels[i][j], constraint);
			}
		}
	}
}