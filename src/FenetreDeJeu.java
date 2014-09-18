import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.TitledBorder;

public class FenetreDeJeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private int[][] pattern;
	private Case[][] map;
	private JLabel[][] labels;
	private JInternalFrame frameInventaire = new JInternalFrame("Inventaire", false, true, false);
	private ListenerModeJeu listenerJeu = new ListenerModeJeu();
	private ListenerModeCombat listenerCombat = new ListenerModeCombat();
	private Dimension coordPerso, coordEnnemi;
	private JPanel panelMap = new JPanel(), panelCarac = new JPanel();
	private JLabel lvlPerso = new JLabel(), orPerso = new JLabel();
	private Personnage perso = new Personnage("Personnage_Test");
	private Personnage ennemi = new Personnage("Ennemi");
	private Inventaire inventaire = new Inventaire();
	private BarreRapide barreRapide = new BarreRapide(inventaire, this);
	private boolean tourDuJoueur = true;
	private JButton abandon = new JButton("Abandonner");

	public FenetreDeJeu() {
		super("ZergoLand");
		setLayout(new BorderLayout());
		initGeneral();
		addGeneral();
		initFenetre();
	}
	private void addGeneral() {
		addCaracPerso();
		addCaracEnemi();
		add(panelCarac);
	}
	private void initGeneral() {
		initTerrain();
		inventaire.initInventaire(frameInventaire, this);
		barreRapide.initBarreRapide();
		initMapJeu();
		initCaracPerso();
		initCaracEnnemi();
	}
	private void addCaracPerso() {
		String s = "Caractéristiques";
		Font f = new Font("Arial", Font.PLAIN, 18);
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
		lvlPerso.setFont(new Font("Arial", Font.ITALIC, 22));

		// Vita
		perso.getBarreVita().setStringPainted(true);
		perso.getBarreVita().setForeground(Color.RED);
		perso.getBarreVita().setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));
		tmp = new JLabel("Vitalité");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(perso.getBarreVita());
		panelCarac.add(new JLabel(" "));

		// Mana
		perso.getBarreMana().setForeground(Color.blue);
		perso.getBarreMana().setStringPainted(true);
		perso.getBarreMana().setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));
		tmp = new JLabel("Mana");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(perso.getBarreMana());
		panelCarac.add(new JLabel(" "));

		// xp
		perso.getBarreXp().setValue(perso.getXp() / Constante.RAPPORT_XP_LVL1);
		perso.getBarreXp().setForeground(Color.black);
		perso.getBarreXp().setStringPainted(true);
		perso.getBarreXp().setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));
		tmp = new JLabel("XP");
		tmp.setFont(f);
		panelCarac.add(tmp);
		panelCarac.add(perso.getBarreXp());
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

		// espacements
		panelCarac.add(new JLabel(" "));
		panelCarac.add(new JLabel(" "));
	}
	private void addCaracEnemi() {
		// Vita
		ennemi.getBarreVita().setStringPainted(true);
		ennemi.getBarreVita().setForeground(Color.RED);
		ennemi.getBarreVita().setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));

		// Mana
		ennemi.getBarreMana().setForeground(Color.blue);
		ennemi.getBarreMana().setStringPainted(true);
		ennemi.getBarreMana().setBorder(BorderFactory.createEtchedBorder(Color.BLUE, Color.black));
	}
	private void initCaracEnnemi() {
		// Vita
		ennemi.getBarreVita().setString(ennemi.getHp() + "/" + perso.getHpMax());
		ennemi.getBarreVita().setValue(ennemi.getHp());

		// Mana
		ennemi.getBarreMana().setString(ennemi.getMp() + "/" + ennemi.getManaMax());
		ennemi.getBarreMana().setValue(ennemi.getMp());
	}
	private void initCaracPerso() {
		// Lvl
		lvlPerso.setText("Lvl " + perso.getLvl());

		// Vita
		perso.getBarreVita().setString(perso.getHp() + "/" + perso.getHpMax());
		perso.getBarreVita().setValue(perso.getHp());

		// Mana
		perso.getBarreMana().setString(perso.getMp() + "/" + perso.getManaMax());
		perso.getBarreMana().setValue(perso.getMp());

		// xp
		perso.getBarreXp().setString(perso.getXp() + "/" + 500);
		perso.getBarreXp().setValue(perso.getXp() / Constante.RAPPORT_XP_LVL1);

		// Or
		orPerso.setText("Or:    " + perso.getOr() + " pièces");
	}
	private void initTerrain() {
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
	private void initListenerJeu() {
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(listenerJeu);
		panelMap.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JPopupMenu popMenu = new JPopupMenu();
				JMenuItem action = new JMenuItem();
				int i = e.getY() / 45;
				int j = e.getX() / 50;

				popMenu.add(action);
				action.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (e.getActionCommand().equals("Attaquer"))
							initMapCombat();
					}
				});

				// test coffre
				if (labels[i][j].getIcon() == Constante.coffre) {
					popMenu.add(new JMenuItem("Examiner"));
					action.setText("Ouvrir");
					popMenu.show(panelMap, e.getX(), e.getY());
				}
				// test porte
				else if (labels[i][j].getIcon() == Constante.maisonBG) {
					action.setText("Entrer");
					popMenu.show(panelMap, e.getX(), e.getY());
				}
				// test ennemi
				else if (labels[i][j].getIcon() == Constante.zombie_depl) {
					action.setText("Attaquer");
					popMenu.show(panelMap, e.getX(), e.getY());
				}
			};
		});
	}
	private void initListenerCombat() {
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(listenerCombat);
		panelMap.addMouseMotionListener(listenerCombat);
		panelMap.addMouseListener(listenerCombat);
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
		BufferedReader reader;
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
		panelCarac.revalidate();
		panelCarac.repaint();

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == 3 && j == 9) {
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
				else if (pattern[i][j] == 10)
					map[i][j] = new Case(Constante.chateauPorte);
				else if (pattern[i][j] == 11)
					map[i][j] = new Case(Constante.hautPorteChateau);
				else if (pattern[i][j] == 12)
					map[i][j] = new Case(Constante.TourBD);
				else if (pattern[i][j] == 13)
					map[i][j] = new Case(Constante.TourHD);
				else if (pattern[i][j] == 14)
					map[i][j] = new Case(Constante.TourHG);
				else if (pattern[i][j] == 15)
					map[i][j] = new Case(Constante.TourBG);
				else if (pattern[i][j] == 16)
					map[i][j] = new Case(Constante.TourHH);
				else if (pattern[i][j] == 17)
					map[i][j] = new Case(Constante.rock);

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

		// Ajout bouton abandon
		panelCarac.add(abandon);
		panelCarac.revalidate();
		panelCarac.repaint();

		// listener bouton abandon
		abandon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelCarac.remove(abandon);
				abandon.removeActionListener(this);
				ennemi.setHp(ennemi.getHpMax());
				perso.setHp(perso.getHpMax());
				initCaracEnnemi();
				initCaracPerso();
				initMapJeu();
			}
		});
	}
	public void tourIA() {
		while (!tourDuJoueur) {
			Random r = new Random();
			int choix = r.nextInt(4) + 1;
			int x = coordEnnemi.width;
			int y = coordEnnemi.height;

			if (r.nextBoolean()) { // Déplacement
				if (choix == 1) {
					// Case apres
					if (labels[x - 1][y].getIcon() == Constante.caseVideCombat) {
						map[x - 1][y].setSkin(Constante.zombie_att); // deplacement du perso
						coordEnnemi = new Dimension(x - 1, y); // update coord

						// Ajout au label
						labels[x - 1][y].setIcon(map[x - 1][y].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideCombat);
						tourDuJoueur = true;
					}
				}
				else if (choix == 2) {
					// Case apres
					if (labels[x][y - 1].getIcon() == Constante.caseVideCombat) {
						map[x][y - 1].setSkin(Constante.zombie_att); // deplacement du perso
						coordEnnemi = new Dimension(x, y - 1); // update coord

						// Ajout au label
						labels[x][y - 1].setIcon(map[x][y - 1].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideCombat);
						tourDuJoueur = true;
					}
				}
				else if (choix == 3) {
					// Case apres
					if (labels[x + 1][y].getIcon() == Constante.caseVideCombat) {
						map[x + 1][y].setSkin(Constante.zombie_att); // deplacement du perso
						coordEnnemi = new Dimension(x + 1, y); // update coord

						// Ajout au label
						labels[x + 1][y].setIcon(map[x + 1][y].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideCombat);
						tourDuJoueur = true;
					}
				}
				else {
					// Case apres
					if (labels[x][y + 1].getIcon() == Constante.caseVideCombat) {
						map[x][y + 1].setSkin(Constante.zombie_att); // deplacement du perso
						coordEnnemi = new Dimension(x, y + 1); // update coord

						// Ajout au label
						labels[x][y + 1].setIcon(map[x][y + 1].getSkin());

						// Case avant
						map[x][y].setSkin(Constante.caseVideCombat);
						tourDuJoueur = true;
					}
				}
				// Ajout au label
				labels[x][y].setIcon(map[x][y].getSkin());
			}
			if (r.nextBoolean()) { // Attaque
				tourDuJoueur = true;
				perso.setHp(perso.getHp() - 1);
				initCaracPerso();
			}
		}
	}

	private class ListenerModeJeu implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int x = coordPerso.width;
			int y = coordPerso.height;

			try {
				if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP) {
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

	private class ListenerModeCombat extends MouseAdapter implements KeyListener {
		public void mouseClicked(MouseEvent e) {
			int x = e.getY() / 45;
			int y = e.getX() / 50;

			if (combatFini()) { // Fin du combat
				if (perso.isWinner())
					perso.setXp(perso.getXp() + 100);
				ennemi.setHp(ennemi.getHpMax());
				perso.setHp(perso.getHpMax());
				initCaracEnnemi();
				initCaracPerso();
				initMapJeu();
			}
			else if (labels[x][y].getIcon() == Constante.zombie_att) {
				// Joueur
				tourDuJoueur = false;
				ennemi.setHp(ennemi.getHp() - 10);
				initCaracEnnemi();

				// pause entre les deux joueurs
				try {
					Thread.sleep(200);
				}
				catch (InterruptedException e1) {}

				// IA
				tourIA();
			}
		}
		public void mouseMoved(MouseEvent e) {
			JPopupMenu panel = new JPopupMenu();
			int x = e.getY() / 45;
			int y = e.getX() / 50;

			if (labels[x][y].getIcon() == Constante.zombie_att) {
				panel.add(ennemi.getBarreVita());
				panel.add(ennemi.getBarreMana());
				panel.show(panelMap, e.getX() - 50, e.getY() - 100);
			}
			else {
				panel.removeAll();
				panel.show(panelMap, e.getX() - 50, e.getY() - 100);
			}
			setFocusable(true);
			requestFocusInWindow();
		}
		public void keyPressed(KeyEvent e) {
			try {
				if (combatFini()) { // Fin du combat
					if (perso.isWinner())
						perso.setXp(perso.getXp() + 100);
					ennemi.setHp(ennemi.getHpMax());
					perso.setHp(perso.getHpMax());
					initCaracEnnemi();
					initCaracPerso();
					initMapJeu();
				}
				else if (tourDuJoueur) {
					int x = coordPerso.width;
					int y = coordPerso.height;
					tourDuJoueur = false;

					if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP) {
						// Case apres
						if (labels[x - 1][y].getIcon() == Constante.caseVideCombat) {
							map[x - 1][y].setSkin(Constante.casePersoAttaque); // deplacement du perso
							coordPerso = new Dimension(x - 1, y); // update coord

							// Ajout au label
							labels[x - 1][y].setIcon(map[x - 1][y].getSkin());

							// Case avant
							map[x][y].setSkin(Constante.caseVideCombat);
						}
						else
							tourDuJoueur = true;
					}
					else if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_LEFT) {
						// Case apres
						if (labels[x][y - 1].getIcon() == Constante.caseVideCombat) {
							map[x][y - 1].setSkin(Constante.casePersoAttaque); // deplacement du perso
							coordPerso = new Dimension(x, y - 1); // update coord

							// Ajout au label
							labels[x][y - 1].setIcon(map[x][y - 1].getSkin());

							// Case avant
							map[x][y].setSkin(Constante.caseVideCombat);
						}
						else
							tourDuJoueur = true;
					}
					else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
						// Case apres
						if (labels[x + 1][y].getIcon() == Constante.caseVideCombat) {
							map[x + 1][y].setSkin(Constante.casePersoAttaque); // deplacement du perso
							coordPerso = new Dimension(x + 1, y); // update coord

							// Ajout au label
							labels[x + 1][y].setIcon(map[x + 1][y].getSkin());

							// Case avant
							map[x][y].setSkin(Constante.caseVideCombat);
						}
						else
							tourDuJoueur = true;
					}
					else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
						// Case apres
						if (labels[x][y + 1].getIcon() == Constante.caseVideCombat) {
							map[x][y + 1].setSkin(Constante.casePersoAttaque); // deplacement du perso
							coordPerso = new Dimension(x, y + 1); // update coord

							// Ajout au label
							labels[x][y + 1].setIcon(map[x][y + 1].getSkin());

							// Case avant
							map[x][y].setSkin(Constante.caseVideCombat);
						}
						else
							tourDuJoueur = true;
					}
					else
						tourDuJoueur = true;
					// Ajout au label
					labels[x][y].setIcon(map[x][y].getSkin());

					// repaint
					panelMap.paintImmediately(0, 0, panelMap.getWidth(), panelMap.getHeight());

					// pause entre les deux joueurs
					Thread.sleep(200);

					// ----- Tour IA -------------------------
					tourIA();
				}
			}
			catch (java.lang.ArrayIndexOutOfBoundsException | InterruptedException ex) {}
		}
		public void keyReleased(KeyEvent arg0) {}
		public void keyTyped(KeyEvent arg0) {}
		private boolean combatFini() {
			return (ennemi.getHp() - 1 < 1 || perso.getHp() - 1 < 1);
		}
	}

}