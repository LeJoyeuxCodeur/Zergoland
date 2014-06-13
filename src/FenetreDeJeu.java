import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FenetreDeJeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private Case[][] map;
	private JLabel[][] labels;
	private BufferedReader reader;
	private int[][] pattern;
	private MoveListener ecouteurDepl = new MoveListener();
	private Dimension coord;

	public FenetreDeJeu() {
		super("ZergoLand");
		setLayout(new GridLayout(Constante.CASES_X, Constante.CASES_Y));
		initListener();
		initReader();
		initMap();
		initFenetre();
	}
	private void initListener() {
		setFocusable(true);
		requestFocusInWindow();
		addKeyListener(ecouteurDepl);
	}
	private void initFenetre() {
		setSize(1100, 600);
		setResizable(false);
		setIconImage(new ImageIcon("ressources/images/logo/logo.png").getImage());
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void initReader() {
		String[] patternTmp = null;
		int cpt = 0;
		try {
			pattern = new int[Constante.CASES_X][Constante.CASES_Y];
			reader = new BufferedReader(new FileReader("ressources/mapsPattern/mapTest.txt"));
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
		map = new Case[Constante.CASES_X][Constante.CASES_Y];
		labels = new JLabel[Constante.CASES_X][Constante.CASES_Y];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (i == 3 && j == 8) {
					map[i][j] = new Case(Constante.casePerso);
					coord = new Dimension(i, j);
				}
				else if (pattern[i][j] == 0)
					map[i][j] = new Case(Constante.caseVide);
				else if (pattern[i][j] == 1)
					map[i][j] = new Case(Constante.caseArbre);

				labels[i][j] = new JLabel();
				labels[i][j].setIcon(map[i][j].getSkin());
				add(labels[i][j]);
			}
		}
	}

	private class MoveListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			int x = coord.width;
			int y = coord.height;

			try {
				// Case avant
				if (pattern[x][y] == 0)
					map[x][y].setSkin(Constante.caseVide);
				else
					map[x][y].setSkin(Constante.caseArbre);

				if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP) {
					// Case apres
					map[x - 1][y].setSkin(Constante.casePerso); // deplacement du perso
					coord = new Dimension(x - 1, y); // update coord

					// Ajout au label
					labels[x - 1][y].setIcon(map[x - 1][y].getSkin());
				}
				else if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_LEFT) {
					// Case apres
					map[x][y - 1].setSkin(Constante.casePerso); // deplacement du perso
					coord = new Dimension(x, y - 1); // update coord

					// Ajout au label
					labels[x][y - 1].setIcon(map[x][y - 1].getSkin());
				}
				else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
					// Case apres
					map[x + 1][y].setSkin(Constante.casePerso); // deplacement du perso
					coord = new Dimension(x + 1, y); // update coord

					// Ajout au label
					labels[x + 1][y].setIcon(map[x + 1][y].getSkin());
				}
				else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					// Case apres
					map[x][y + 1].setSkin(Constante.casePerso); // deplacement du perso
					coord = new Dimension(x, y + 1); // update coord

					// Ajout au label
					labels[x][y + 1].setIcon(map[x][y + 1].getSkin());
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