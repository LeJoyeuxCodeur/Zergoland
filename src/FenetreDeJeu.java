import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FenetreDeJeu extends JFrame {
	private static final long serialVersionUID = 1L;
	private Case[][] map;
	private JLabel[][] panels;
	private BufferedReader reader;
	private int[][] pattern;
	private MoveListener ecouteurDepl = new MoveListener();

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
		panels = new JLabel[Constante.CASES_X][Constante.CASES_Y];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (pattern[i][j] == 0)
					map[i][j] = new Case(Constante.caseVide);
				else if (pattern[i][j] == 1)
					map[i][j] = new Case(Constante.caseArbre);
				else
					map[i][j] = new Case(Constante.casePerso);

				panels[i][j] = new JLabel();
				panels[i][j].add(new JLabel(map[i][j].getSkin()));
				panels[i][j].setIcon(map[i][j].getSkin());
				add(panels[i][j]);
			}
		}
	}
	private class MoveListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyChar() == 'z')
				System.out.println('z');
			else if (e.getKeyChar() == 'q')
				System.out.println('q');
			else if (e.getKeyChar() == 's')
				System.out.println('s');
			else if (e.getKeyChar() == 'd')
				System.out.println('d');
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}