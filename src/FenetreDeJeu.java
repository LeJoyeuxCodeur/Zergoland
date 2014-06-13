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
	private JLabel[][] labels;
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
		labels = new JLabel[Constante.CASES_X][Constante.CASES_Y];

		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(i == 4 && j == 5)
					map[i][j] = new Case(Constante.casePerso);
				else if (pattern[i][j] == 0)
					map[i][j] = new Case(Constante.caseVide);
				else if (pattern[i][j] == 1)
					map[i][j] = new Case(Constante.caseArbre);

				labels[i][j] = new JLabel();
				labels[i][j].add(new JLabel(map[i][j].getSkin()));
				labels[i][j].setIcon(map[i][j].getSkin());
				add(labels[i][j]);
			}
		}
	}
	private class MoveListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_Z || e.getKeyCode() == KeyEvent.VK_UP)
				System.out.println('z');
			else if (e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_LEFT)
				System.out.println('q');
			else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN)
				System.out.println('s');
			else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT)
				System.out.println('d');
		}
		public void keyReleased(KeyEvent e) {}
		public void keyTyped(KeyEvent e) {}
	}
}