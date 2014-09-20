package Default;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class BarreRapide {
	private Inventaire inventaire;
	private FenetreDeJeu fenetre;
	
	public BarreRapide(Inventaire inventaire, FenetreDeJeu fenetre){
		this.inventaire = inventaire;
		this.fenetre = fenetre;
	}
	public void initBarreRapide() {
		JPanel panelBarreRapide = new JPanel();
		Font f = new Font("Arial", Font.PLAIN, 18);
		Map<Item, Integer> inv = inventaire.getInventaire();
		Set<Item> keysList = inv.keySet();
		Collection<Integer> valuesList = inv.values();
		Item[] keys = keysList.toArray(new Item[keysList.size()]);
		Integer[] values = valuesList.toArray(new Integer[valuesList.size()]);
		int taille = 0;
		JButton[] tab;

		for (int i = 0; i < inv.size(); i++)
			taille += values[i];
		tab = new JButton[taille];

		panelBarreRapide.setPreferredSize(new Dimension(45, 45));
		panelBarreRapide.setBorder(BorderFactory.createTitledBorder(null, null, TitledBorder.CENTER, TitledBorder.TOP, f, Color.BLACK));
		panelBarreRapide.setBackground(new Color(250, 240, 230));

		for (int i = 0; i < keys.length; i++) {
			for (int j = 0; j < values[i]; j++) {
				tab[j] = new JButton();
				tab[j].setPreferredSize(new Dimension(35, 35));
				tab[j].setIcon(keys[i].getImage());
				panelBarreRapide.add(tab[j]);
			}
		}
		fenetre.add(panelBarreRapide, BorderLayout.SOUTH);
	}
}
