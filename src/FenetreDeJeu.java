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
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class FenetreDeJeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private Case[][] map;
	private JLabel[][] labels;
	private BufferedReader reader;
	private int[][] pattern;
	private MoveListenerModeJeu listenerJeu = new MoveListenerModeJeu();
	private MoveListenerModeCombat listenerCombat = new MoveListenerModeCombat();
	private Dimension coord;
	private JPanel panelMap, panelCarac;
	private JInternalFrame frameInventaire;
	private Personnage perso = new Personnage("Personnage_Test");

	public FenetreDeJeu() {
		super("ZergoLand");
		setLayout(new BorderLayout());
		initCarac();
		initIventaire();
		initMapJeu();
		initFenetre();
	}
	private void initIventaire() {
		frameInventaire = new JInternalFrame("Inventaire", true, true, true);
		frameInventaire.setSize(500, 250);
		frameInventaire.setLocation(772, 420);
		frameInventaire.addInternalFrameListener(new InternalFrameListener() {
			public void internalFrameOpened(InternalFrameEvent arg0) {}
			public void internalFrameIconified(InternalFrameEvent arg0) {}
			public void internalFrameDeiconified(InternalFrameEvent arg0) {}
			public void internalFrameDeactivated(InternalFrameEvent arg0) {}
			public void internalFrameClosing(InternalFrameEvent arg0) {}
			public void internalFrameClosed(InternalFrameEvent arg0) {
				setFocusable(true);
				requestFocusInWindow();
			}
			public void internalFrameActivated(InternalFrameEvent arg0) {
				setFocusable(true);
				requestFocusInWindow();
			}
		});
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
		tmp = new JLabel("Or:    " + perso.getOr() + " pièces");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(new JLabel(" "));

		// Bouton inventaire
		inventaire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameInventaire.setVisible(true);
			}
		});
		panelCarac.add(inventaire);

		// Final add
		add(panelCarac);
	}
	private void initListenerJeu() {
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(listenerJeu);
	}
	private void initListenerCombat() {
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(listenerCombat);
	}
	private void initFenetre() {
		setSize(1280, 700);
		setResizable(false);
		setIconImage(Constante.LOGO);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void initReader(String map) {
		String[] patternTmp = null;
		int cpt = 0;
		try {
			pattern = new int[Constante.CASES_X][Constante.CASES_Y];
			reader = new BufferedReader(new FileReader(map));
			while (reader.ready()) {
				patternTmp = reader.readLine().split(",");
				for (int i = 0; i < patternTmp.length; i++)
					pattern[cpt][i] = Integer.parseInt(patternTmp[i]);
				cpt++;
			}
		}
		catch (IOException e) {}
	}
	private void initMapJeu() {
		panelMap = new JPanel();
		panelMap.setLayout(new GridLayout(Constante.CASES_X, Constante.CASES_Y));
		map = new Case[Constante.CASES_X][Constante.CASES_Y];
		labels = new JLabel[Constante.CASES_X][Constante.CASES_Y];

		initListenerJeu();
		initReader(Constante.MAP_DEPLACEMENT);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == 3 && j == 8) {
					map[i][j] = new Case(Constante.casePersoDeplacement);
					coord = new Dimension(i, j);
				}
				else if (pattern[i][j] == 0)
					map[i][j] = new Case(Constante.caseVideDeplacement);
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
				else if (pattern[i][j] == 7)
					map[i][j] = new Case(Constante.barriere);
				else if (pattern[i][j] == 8)
					map[i][j] = new Case(Constante.coffreOuvert);
				else if (pattern[i][j] == 9)
					map[i][j] = new Case(Constante.zombie_depl);

				labels[i][j] = new JLabel();
				labels[i][j].setIcon(map[i][j].getSkin());
				panelMap.add(labels[i][j]);
				add(panelMap, BorderLayout.WEST);
			}
		}
	}
	
	private class MoveListenerModeJeu implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int x = coord.width;
			int y = coord.height;

			try {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					initMapCombat();
				else if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP) {
					// Case apres
					if (pattern[x - 1][y] == 0) {
						map[x - 1][y].setSkin(Constante.casePersoDeplacement); // deplacement du perso
						coord = new Dimension(x - 1, y); // update coord

						// Ajout au label
						labels[x - 1][y].setIcon(map[x - 1][y].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideDeplacement);
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_LEFT) {
					// Case apres
					if (pattern[x][y - 1] == 0) {
						map[x][y - 1].setSkin(Constante.casePersoDeplacement); // deplacement du perso
						coord = new Dimension(x, y - 1); // update coord

						// Ajout au label
						labels[x][y - 1].setIcon(map[x][y - 1].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideDeplacement);
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
					// Case apres
					if (pattern[x + 1][y] == 0) {
						map[x + 1][y].setSkin(Constante.casePersoDeplacement); // deplacement du perso
						coord = new Dimension(x + 1, y); // update coord

						// Ajout au label
						labels[x + 1][y].setIcon(map[x + 1][y].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideDeplacement);
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					// Case apres
					if (pattern[x][y + 1] == 0) {
						map[x][y + 1].setSkin(Constante.casePersoDeplacement); // deplacement du perso
						coord = new Dimension(x, y + 1); // update coord

						// Ajout au label
						labels[x][y + 1].setIcon(map[x][y + 1].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideDeplacement);
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
	private class MoveListenerModeCombat implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int x = coord.width;
			int y = coord.height;

			try {
				if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP) {
					// Case apres
					if (pattern[x - 1][y] == 0) {
						map[x - 1][y].setSkin(Constante.casePersoAttaque); // deplacement du perso
						coord = new Dimension(x - 1, y); // update coord

						// Ajout au label
						labels[x - 1][y].setIcon(map[x - 1][y].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideCombat);
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_LEFT) {
					// Case apres
					if (pattern[x][y - 1] == 0) {
						map[x][y - 1].setSkin(Constante.casePersoAttaque); // deplacement du perso
						coord = new Dimension(x, y - 1); // update coord

						// Ajout au label
						labels[x][y - 1].setIcon(map[x][y - 1].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideCombat);
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
					// Case apres
					if (pattern[x + 1][y] == 0) {
						map[x + 1][y].setSkin(Constante.casePersoAttaque); // deplacement du perso
						coord = new Dimension(x + 1, y); // update coord

						// Ajout au label
						labels[x + 1][y].setIcon(map[x + 1][y].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideCombat);
					}
				}
				else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					// Case apres
					if (pattern[x][y + 1] == 0) {
						map[x][y + 1].setSkin(Constante.casePersoAttaque); // deplacement du perso
						coord = new Dimension(x, y + 1); // update coord

						// Ajout au label
						labels[x][y + 1].setIcon(map[x][y + 1].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideCombat);
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
	public void initMapCombat() {
		panelMap = new JPanel();
		panelMap.setLayout(new GridLayout(Constante.CASES_X, Constante.CASES_Y));
		map = new Case[Constante.CASES_X][Constante.CASES_Y];
		labels = new JLabel[Constante.CASES_X][Constante.CASES_Y];

		getContentPane().removeAll();
		removeKeyListener(listenerJeu);
		initListenerCombat();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == 3 && j == 8) {
					map[i][j] = new Case(Constante.casePersoAttaque);
					coord = new Dimension(i, j);
				}
				if (pattern[i][j] == 9)
					map[i][j] = new Case(Constante.zombie_att);
				else if (pattern[i][j] == 0)
					map[i][j] = new Case(Constante.caseVideCombat);
				else
					map[i][j] = new Case(Constante.caseObstacle);

				labels[i][j] = new JLabel();
				labels[i][j].setIcon(map[i][j].getSkin());
				panelMap.add(labels[i][j]);
				add(panelMap, BorderLayout.WEST);
			}
		}
	}
}