import java.util.LinkedList;

public class Coffre {
	private LinkedList<Item> contenuCoffre;
	private String libelle;

	public Coffre(LinkedList<Item> l, String libelle) {
		contenuCoffre = l;
		setLibelle(libelle);
	}
	public void ajoutItem(Item i) {
		contenuCoffre.add(i);
	}
	public void ajoutItem(LinkedList<Item> l) {
		contenuCoffre.addAll(l);
	}
	public void removeItem(Item i) {
		if (contenuCoffre.contains(i))
			contenuCoffre.remove(i);
	}
	public void removeAllItem() {
		contenuCoffre.clear();
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
