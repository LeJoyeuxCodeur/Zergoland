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
	private Dimension coordPerso, coordEnnemi;
	private JPanel panelMap = new JPanel(), panelCarac = new JPanel();
	private JInternalFrame frameInventaire;
	private Personnage perso = new Personnage("Personnage_Test");
	private Personnage ennemi = new Personnage("Ennemi");
	private JProgressBar vitaEnnemi = new JProgressBar(0, ennemi.getHpMax());
	private JProgressBar manaEnnemi = new JProgressBar(0, ennemi.getManaMax());

	public FenetreDeJeu() {
		super("ZergoLand");
		setLayout(new BorderLayout());
		initComposants();
		initIventaire();
		initMapJeu();
		initCaracPerso();
		initCaracEnnemi();
		initFenetre();
	}
	private void initCaracEnnemi() {
		JLabel tmp;
		Font f = new Font("Arial", Font.PLAIN, 18);

		panelCarac.add(new JLabel(" "));
		panelCarac.add(new JLabel("- - - - - - - - - - - - - - - - - - - - - - -"));

		// Nom
		tmp = new JLabel(ennemi.getNom());
		tmp.setForeground(Color.RED);
		tmp.setFont(new Font("Arial", Font.ITALIC, 15));
		panelCarac.add(tmp);
		panelCarac.add(new JLabel(" "));

		// Vita
		vitaEnnemi.setValue(ennemi.getHp());
		vitaEnnemi.setStringPainted(true);
		vitaEnnemi.setString(ennemi.getHp() + "/" + perso.getHpMax());
		vitaEnnemi.setForeground(Color.RED);
		vitaEnnemi.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));
		tmp = new JLabel("Vitalité");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(vitaEnnemi);
		panelCarac.add(new JLabel(" "));

		// Mana
		manaEnnemi.setValue(ennemi.getMp());
		manaEnnemi.setForeground(Color.blue);
		manaEnnemi.setStringPainted(true);
		manaEnnemi.setString(ennemi.getMp() + "/" + ennemi.getManaMax());
		manaEnnemi.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));
		tmp = new JLabel("Mana");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(manaEnnemi);
		panelCarac.add(new JLabel(" "));
	}
	private void initComposants() {
		panelMap.setLayout(new GridLayout(Constante.CASES_X, Constante.CASES_Y));
		map = new Case[Constante.CASES_X][Constante.CASES_Y];
		labels = new JLabel[Constante.CASES_X][Constante.CASES_Y];
		initReader(Constante.MAP);

		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[0].length; j++) {
				labels[i][j] = new JLabel();
			}
		}
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
	private void initCaracPerso() {
		String s = "Caractéristiques";
		Font f = new Font("Arial", Font.PLAIN, 18);
		JProgressBar vita = new JProgressBar(0, perso.getHpMax());
		JProgressBar mana = new JProgressBar(0, perso.getManaMax());
		JProgressBar xp = new JProgressBar(0, 100);
		JLabel tmp;
		JButton inventaire = new JButton("Inventaire");

		// Panel Carac
		panelCarac = new JPanel();
		panelCarac.setLayout(new BoxLayout(panelCarac, BoxLayout.Y_AXIS));
		panelCarac.setBorder(BorderFactory.createTitledBorder(null, s, TitledBorder.CENTER, TitledBorder.TOP, f, Color.BLACK));
		panelCarac.setBackground(new Color(250, 240, 230));
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
		vita.setStringPainted(true);
		vita.setString(perso.getHp() + "/" + perso.getHpMax());
		vita.setForeground(Color.RED);
		vita.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));
		tmp = new JLabel("Vitalité");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(vita);
		panelCarac.add(new JLabel(" "));

		// Mana
		mana.setValue(perso.getMp());
		mana.setForeground(Color.blue);
		mana.setStringPainted(true);
		mana.setString(perso.getMp() + "/" + perso.getManaMax());
		mana.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));
		tmp = new JLabel("Mana");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(mana);
		panelCarac.add(new JLabel(" "));

		// xp
		xp.setValue(perso.getXp());
		xp.setForeground(Color.black);
		xp.setStringPainted(true);
		xp.setString(perso.getXp() + "/100");
		xp.setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));
		tmp = new JLabel("XP");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(xp);
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
		panelMap.removeAll();
		removeKeyListener(listenerCombat);
		initListenerJeu();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == 3 && j == 8) {
					map[i][j] = new Case(Constante.casePersoDeplacement);
					coordPerso = new Dimension(i, j);
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

				labels[i][j].setIcon(map[i][j].getSkin());
				panelMap.add(labels[i][j]);
				add(panelMap, BorderLayout.WEST);
			}
		}
		panelMap.repaint();
	}
	private void initMapCombat() {
		panelMap.removeAll();
		removeKeyListener(listenerJeu);
		initListenerCombat();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == 3 && j == 8) {
					map[i][j] = new Case(Constante.casePersoAttaque);
					coordPerso = new Dimension(i, j);
				}
				else if (pattern[i][j] == 9) {
					map[i][j] = new Case(Constante.zombie_att);
					coordEnnemi = new Dimension(i, j);
				}
				else if (pattern[i][j] == 0)
					map[i][j] = new Case(Constante.caseVideCombat);
				else
					map[i][j] = new Case(Constante.caseObstacle);

				labels[i][j].setIcon(map[i][j].getSkin());
				panelMap.add(labels[i][j]);
			}
		}
		panelMap.repaint();
	}

	private class MoveListenerModeJeu implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int x = coordPerso.width;
			int y = coordPerso.height;

			try {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					initMapCombat();
				else if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP) {
					// Case apres
					if (pattern[x - 1][y] == 0) {
						map[x - 1][y].setSkin(Constante.casePersoDeplacement); // deplacement du perso
						coordPerso = new Dimension(x - 1, y); // update coord

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
						coordPerso = new Dimension(x, y - 1); // update coord

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
						coordPerso = new Dimension(x + 1, y); // update coord

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
						coordPerso = new Dimension(x, y + 1); // update coord

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
			int x = coordPerso.width;
			int y = coordPerso.height;

			try {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					initMapJeu();
				else if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP) {
					// Case apres
					if (pattern[x - 1][y] == 0) {
						map[x - 1][y].setSkin(Constante.casePersoAttaque); // deplacement du perso
						coordPerso = new Dimension(x - 1, y); // update coord

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
						coordPerso = new Dimension(x, y - 1); // update coord

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
						coordPerso = new Dimension(x + 1, y); // update coord

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
						coordPerso = new Dimension(x, y + 1); // update coord

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
}