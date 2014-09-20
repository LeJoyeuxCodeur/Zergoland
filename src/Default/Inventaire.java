package Default;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class Inventaire {
	private Map<Item, Integer> inventaire;

	public Inventaire() {
		inventaire = new HashMap<Item, Integer>();
	}
	public void ajoutItem(Item i, int a) {
		inventaire.put(i, a);
	}
	public void retirerItem(Item i) {
		inventaire.remove(i);
	}
	public Map<Item, Integer> getInventaire() {
		return inventaire;
	}
	public void setHm(HashMap<Item, Integer> inventaire) {
		this.inventaire = inventaire;
	}
	public void initInventaire(final JInternalFrame frameInventaire, FenetreDeJeu f) {
		// Ajout des objets

		ajoutItem(Constante.potion_mana, 8);
		ajoutItem(Constante.potion_sante, 8);

		// Fin ajout
		frameInventaire.setBackground(Color.white);
		frameInventaire.addInternalFrameListener(new InternalFrameListener() {
			public void internalFrameOpened(InternalFrameEvent arg0) {
				int taille = 0;

				Map<Item, Integer> inv = inventaire;
				Set<Item> keysList = inv.keySet();
				Collection<Integer> valuesList = inv.values();
				Item[] keys = keysList.toArray(new Item[keysList.size()]);
				Integer[] values = valuesList.toArray(new Integer[valuesList.size()]);

				for (int i = 0; i < inv.size(); i++)
					taille += values[i];
				frameInventaire.setLayout(new GridLayout(3, taille));

				for (int i = 0; i < inv.size(); i++) {
					for (int j = 0; j < values[i]; j++) {
						JButton tmp = new JButton();
						tmp.setBackground(Color.white);
						tmp.setIcon(keys[i].getImage());

						tmp.setBorder(BorderFactory.createLineBorder(Color.black, 2));
						frameInventaire.add(tmp);
					}
				}
				frameInventaire.setLocation(775, 470);
				frameInventaire.setSize(500, 200);
			}
			public void internalFrameIconified(InternalFrameEvent arg0) {}
			public void internalFrameDeiconified(InternalFrameEvent arg0) {}
			public void internalFrameDeactivated(InternalFrameEvent arg0) {}
			public void internalFrameClosing(InternalFrameEvent arg0) {}
			public void internalFrameClosed(InternalFrameEvent arg0) {
				frameInventaire.setFocusable(true);
				frameInventaire.requestFocusInWindow();
			}
			public void internalFrameActivated(InternalFrameEvent arg0) {
				frameInventaire.setFocusable(true);
				frameInventaire.requestFocusInWindow();
			}
		});
		f.add(frameInventaire);
	}
}