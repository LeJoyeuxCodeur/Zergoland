import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.TitledBorder;

public class FenetreDeJeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private Case[][] map;
	private JLabel[][] labels;
	private BufferedReader reader;
	private int[][] pattern;
	private MoveListener ecouteurDepl = new MoveListener();
	private Dimension coord;
	private JPanel panelMap, panelCarac;
	private JInternalFrame frameInventaire;
	private Personnage perso;

	public FenetreDeJeu() {
		super("ZergoLand");
		setLayout(new BorderLayout());
		initListener();
		initIventaire();
		initReader();
		initMap();
		initCarac();
		initFenetre();
	}
	private void initIventaire(){
		frameInventaire = new JInternalFrame("Inventaire", true, true, true, true);
		frameInventaire.setSize(500, 250);
		frameInventaire.setLocation(772, 420);
		add(frameInventaire);
	}
	private void initCarac() {
		String s = "Caractéristiques";
		Font f = new Font("Arial", Font.PLAIN, 18);
		JProgressBar vita = new JProgressBar(0, perso.getHpMax());
		JProgressBar mana = new JProgressBar(0, perso.getManaMax());
		JLabel tmp;
		JButton inventaire = new JButton("Inventaire");
		
		// Panel Carac
		panelCarac = new JPanel();
		panelCarac.setLayout(new BoxLayout(panelCarac, BoxLayout.Y_AXIS));
		panelCarac.setBorder(BorderFactory.createTitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP, f, Color.BLACK));
		panelCarac.add(new JLabel(" "));
		
		// Nom
		tmp = new JLabel(perso.getNom());
		tmp.setForeground(Color.RED);
		tmp.setFont(new Font("Arial", Font.ITALIC, 15));
		panelCarac.add(tmp);
		panelCarac.add(new JLabel(" "));
		
		// Lvl
		tmp = new JLabel("Lvl " + perso.getLvl());
		tmp.setFont(new Font("Arial", Font.ITALIC, 22));
		panelCarac.add(tmp);
		panelCarac.add(new JLabel(" "));
		
		// Vita
		vita.setValue(perso.getHp());
		tmp = new JLabel("Vitalité");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(vita);
		panelCarac.add(new JLabel(" "));
		
		// Mana
		tmp = new JLabel("Mana");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(mana);
		panelCarac.add(new JLabel(" "));
		
		// Or
		tmp = new JLabel("Or:    "+ perso.getOr()+ " pièces");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(new JLabel(" "));
		
		// Bouton inventaire
		inventaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frameInventaire.setVisible(true);
			}
		});
		panelCarac.add(inventaire);
		
		// Final add
		add(panelCarac);
	}
	private void initListener() {
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(ecouteurDepl);
	}
	private void initFenetre() {
		setSize(1280, 700);
		setResizable(false);
		setIconImage(Constante.LOGO);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void initReader() {
		String[] patternTmp = null;
		int cpt = 0;
		try {
			pattern = new int[Constante.CASES_X][Constante.CASES_Y];
			reader = new BufferedReader(new FileReader(Constante.MAP));
			while (reader.ready()) {
				patternTmp = reader.readLine().split(",");
				for (int i = 0; i < patternTmp.length; i++)
					pattern[cpt][i] = Integer.parseInt(patternTmp[i]);
				cpt++;
			}
		}
		catch (IOException e) {}
	}
	private void initMap() {
		panelMap = new JPanel();
		panelMap.setLayout(new GridLayout(Constante.CASES_X, Constante.CASES_Y));
		map = new Case[Constante.CASES_X][Constante.CASES_Y];
		labels = new JLabel[Constante.CASES_X][Constante.CASES_Y];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == 3 && j == 8) {
					perso = new Personnage("Personnage_Test");
					map[i][j] = new Case(Constante.casePerso);
					coord = new Dimension(i, j);
				}
				else if (pattern[i][j] == 0)
					map[i][j] = new Case(Constante.caseVide);
				else if (pattern[i][j] == 1)
					map[i][j] = new Case(Constante.caseArbre);
				else if (pattern[i][j] == 2)
					map[i][j] = new Case(Constante.maisonBG);
				else if (pattern[i][j] == 3)
					map[i][j] = new Case(Constante.maisonBD);
				else if (pattern[i][j] == 4)
					map[i][j] = new Case(Constante.maisonHG);
				else if (pattern[i][j] == 5)
					map[i][j] = new Case(Constante.maisonHD);
				else if (pattern[i][j] == 6)
					map[i][j] = new Case(Constante.coffre);

				labels[i][j] = new JLabel();
				labels[i][j].setIcon(map[i][j].getSkin());
				panelMap.add(labels[i][j]);
				add(panelMap, BorderLayout.WEST);
			}
		}
	}

	private class MoveListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int x = coord.width;
			int y = coord.height;

			try {
				if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP) {
					// Case apres
					if (pattern[x - 1][y] == 0) {
						map[x - 1][y].setSkin(Constante.casePerso); // deplacement du perso
						coord = new Dimension(x - 1, y); // update coord

						// Ajout au label
						labels[x - 1][y].setIcon(map[x - 1][y].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVide);
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_LEFT) {
					// Case apres
					if (pattern[x][y - 1] == 0) {
						map[x][y - 1].setSkin(Constante.casePerso); // deplacement du perso
						coord = new Dimension(x, y - 1); // update coord

						// Ajout au label
						labels[x][y - 1].setIcon(map[x][y - 1].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVide);
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
					// Case apres
					if (pattern[x + 1][y] == 0) {
						map[x + 1][y].setSkin(Constante.casePerso); // deplacement du perso
						coord = new Dimension(x + 1, y); // update coord

						// Ajout au label
						labels[x + 1][y].setIcon(map[x + 1][y].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVide);
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					// Case apres
					if (pattern[x][y + 1] == 0) {
						map[x][y + 1].setSkin(Constante.casePerso); // deplacement du perso
						coord = new Dimension(x, y + 1); // update coord

						// Ajout au label
						labels[x][y + 1].setIcon(map[x][y + 1].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVide);
					}
				}
				// Ajout au label
				labels[x][y].setIcon(map[x][y].getSkin());
			}
			catch (java.lang.ArrayIndexOutOfBoundsException ex) {}
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}