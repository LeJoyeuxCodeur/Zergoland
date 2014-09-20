package Default;
public class Porte {

	private int destination; //code de teleportation

	private String libelle;

	public Porte(int destination, String libelle) {
		this.destination = destination;
		this.setLibelle(libelle);
	}
	public int getDestination() {
		return destination;
	}

	public void setDestination(int destination) {
		this.destination = destination;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
}
