import java.util.HashMap;


public class Inventaire {

	private HashMap<Item, Integer> hm;
	
	public Inventaire() {
		setHm(new HashMap<Item, Integer>());
	}
	public void ajoutItem(Item i, int a) {
		hm.put(i, a);
	}
	public void retirerItem(Item i) {
		hm.remove(i);
	}
	public HashMap<Item, Integer> getHm() {
		return hm;
	}
	public void setHm(HashMap<Item, Integer> hm) {
		this.hm = hm;
	}
}
