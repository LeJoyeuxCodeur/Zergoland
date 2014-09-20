package MapEditor;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Default.Constante;


public class EditeurMap extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener{

	private static final long serialVersionUID = 1L;
	private JPanel menu = new JPanel() , map = new JPanel(), selectBoard = new JPanel();
	private JButton save = new JButton("Sauvegarder"), load = new JButton("Charger");
	private int[][] pattern = new int[22][14];
	private ImageIcon[] imgs = new ImageIcon[19];
	private int mx=0, my=0, imageCourante=5;
	
	
	public EditeurMap() {

		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.SOUTH);
		this.add(map, BorderLayout.CENTER);
		this.add(selectBoard, BorderLayout.EAST);
		initMenu();
		initMap();
		initTab();
		map.addMouseListener(this);
		map.addMouseMotionListener(this);
		map.addMouseWheelListener(this);
	}
	
	
	private void initTab() {
		imgs[0] = Constante.caseVideDeplacement;
		imgs[1] = Constante.caseArbre;
		imgs[2] = Constante.maisonBG;
		imgs[3] = Constante.maisonBD;
		imgs[4] = Constante.maisonHG;
		imgs[5] = Constante.maisonHD;
		imgs[6] = Constante.coffre;
		imgs[7] = Constante.barriere;
		imgs[8] = Constante.coffreOuvert;
		imgs[9] = Constante.zombie_depl;
		imgs[10] = Constante.chateauPorte;
		imgs[11] = Constante.hautPorteChateau;
		imgs[12] = Constante.TourBD;
		imgs[13] = Constante.TourHD;
		imgs[14] = Constante.TourHG;
		imgs[15] = Constante.TourBG;
		imgs[16] = Constante.TourHH;
		imgs[17] = Constante.rock;
		imgs[18] = Constante.casePersoDeplacement;
		selectBoard.setLayout(new BoxLayout(selectBoard ,BoxLayout.Y_AXIS));
		for(int i=0 ; i<10 ; i++) {
			Bouton bou = new Bouton(i, imgs[i]);
			bou.addActionListener(new BoutonListener());
			selectBoard.add(bou);
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i=0 ; i<Constante.CASES_Y ; i++) {
			for (int j = 0; j < Constante.CASES_X; j++) {
				if (pattern[i][j] == 0)
					g2d.drawImage(Constante.caseVideDeplacement.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 1)
					g2d.drawImage(Constante.caseArbre.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 2)
					g2d.drawImage(Constante.maisonBG.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 3)
					g2d.drawImage(Constante.maisonBD.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 4)
					g2d.drawImage(Constante.maisonHG.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 5)
					g2d.drawImage(Constante.maisonHD.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 6)
					g2d.drawImage(Constante.coffre.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 7)
					g2d.drawImage(Constante.barriere.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 8)
					g2d.drawImage(Constante.coffreOuvert.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 9)
					g2d.drawImage(Constante.zombie_depl.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 10)
					g2d.drawImage(Constante.chateauPorte.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 11)
					g2d.drawImage(Constante.hautPorteChateau.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 12)
					g2d.drawImage(Constante.TourBD.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 13)
					g2d.drawImage(Constante.TourHD.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 14)
					g2d.drawImage(Constante.TourHG.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 15)
					g2d.drawImage(Constante.TourBG.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 16)
					g2d.drawImage(Constante.TourHH.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 17)
					g2d.drawImage(Constante.rock.getImage(), i*50, j*45, null);
				else if (pattern[i][j] == 18)
					g2d.drawImage(Constante.casePersoDeplacement.getImage(), i*50, j*45, null);
			}
		}
		if (imageCourante == 0)
			g2d.drawImage(Constante.caseVideDeplacement.getImage(), mx, my, null);
		else if (imageCourante == 1)
			g2d.drawImage(Constante.caseArbre.getImage(), mx, my, null);
		else if (imageCourante == 2)
			g2d.drawImage(Constante.maisonBG.getImage(), mx, my, null);
		else if (imageCourante == 3)
			g2d.drawImage(Constante.maisonBD.getImage(), mx, my, null);
		else if (imageCourante == 4)
			g2d.drawImage(Constante.maisonHG.getImage(), mx, my, null);
		else if (imageCourante == 5)
			g2d.drawImage(Constante.maisonHD.getImage(), mx, my, null);
		else if (imageCourante == 6)
			g2d.drawImage(Constante.coffre.getImage(), mx, my, null);
		else if (imageCourante == 7)
			g2d.drawImage(Constante.barriere.getImage(), mx, my, null);
		else if (imageCourante == 8)
			g2d.drawImage(Constante.coffreOuvert.getImage(), mx, my, null);
		else if (imageCourante == 9)
			g2d.drawImage(Constante.zombie_depl.getImage(),mx, my, null);
		else if (imageCourante == 10)
			g2d.drawImage(Constante.chateauPorte.getImage(),mx, my, null);
		else if (imageCourante == 11)
			g2d.drawImage(Constante.hautPorteChateau.getImage(), mx, my, null);
		else if (imageCourante == 12)
			g2d.drawImage(Constante.TourBD.getImage(), mx, my, null);
		else if (imageCourante == 13)
			g2d.drawImage(Constante.TourHD.getImage(), mx, my, null);
		else if (imageCourante == 14)
			g2d.drawImage(Constante.TourHG.getImage(), mx, my, null);
		else if (imageCourante == 15)
			g2d.drawImage(Constante.TourBG.getImage(), mx, my, null);
		else if (imageCourante == 16)
			g2d.drawImage(Constante.TourHH.getImage(), mx, my, null);
		else if (imageCourante == 17)
			g2d.drawImage(Constante.rock.getImage(), mx, my, null);
		else if (imageCourante == 18)
			g2d.drawImage(Constante.casePersoDeplacement.getImage(), mx, my, null);
	}
	private void initMap() {
		map.setFocusable(true);
		for(int i=0 ; i<Constante.CASES_Y ; i++) {
			for (int j = 0; j < Constante.CASES_X; j++) {
				pattern[i][j]=1;
			}
		}
	}
	private void initMenu() {
		menu.setFocusable(true);
		menu.add(save);
		menu.add(load);
	}
	public static void main(String[] args) {
		new EditeurMap();
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
	}
	@Override
	public void mouseMoved(MouseEvent arg0) {
		mx = arg0.getX() - 25;
		my = arg0.getY() - 22;
		System.out.println(mx+" "+my);
		repaint();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		System.out.println("x = "+arg0.getX()+",y = "+arg0.getY());
		int x = arg0.getY() / 45;
		int y = arg0.getX() / 50;
		if(arg0.getButton()==MouseEvent.BUTTON1) {
			pattern[y][x]=imageCourante;
			System.out.println(x+","+y);
		}
		if(arg0.getButton()==MouseEvent.BUTTON3) {
			pattern[y][x]=0;
		}
		repaint();
	}
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		System.out.println("entre");
		if(arg0.getWheelRotation()<0) {
			selectBoard.removeAll();
			selectBoard.setLayout(new BoxLayout(selectBoard ,BoxLayout.Y_AXIS));
			for(int i=0 ; i<10 ; i++) {
				Bouton bou = new Bouton(i, imgs[i]);
				bou.addActionListener(new BoutonListener());
				selectBoard.add(bou);
			}
		}
		if(arg0.getWheelRotation()>=0) {
			selectBoard.removeAll();
			selectBoard.setLayout(new BoxLayout(selectBoard ,BoxLayout.Y_AXIS));
			for(int i=10 ; i<imgs.length ; i++) {
				Bouton bou = new Bouton(i, imgs[i]);
				bou.addActionListener(new BoutonListener());
				selectBoard.add(bou);
			}
		}
		selectBoard.validate();
		selectBoard.repaint();
	}
	
	private class BoutonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			Bouton b = (Bouton) o;
			imageCourante=b.a;
			}
		}
		
	}
	class Bouton extends JButton {

		private static final long serialVersionUID = 1L;
		public int a;
		public Bouton(int a, ImageIcon b) {
			super(b);
			this.a =a;
		}
}
