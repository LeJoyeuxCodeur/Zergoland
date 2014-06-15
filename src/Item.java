import javax.swing.ImageIcon;

public class Item {
	private String nom;
	private ImageIcon i;

	public Item(String nom, ImageIcon i) {
		this.nom = nom;
		this.i = i;
	}
	public ImageIcon getImage() {
		return i;
	}
	public String toString(){
		return nom;
	}
}