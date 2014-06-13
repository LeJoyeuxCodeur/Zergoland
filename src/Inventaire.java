import java.util.HashMap;
import java.util.Map;

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
}