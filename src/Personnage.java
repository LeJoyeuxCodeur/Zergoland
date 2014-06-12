
public class Personnage {

	private String nom;
	private int xp, hp, mp, hpMax, or;
	private Inventaire bag;
	

	public Personnage(String nom) {
		this.setNom(nom);
		this.setXp(Constante.XP_INI);
		this.hpMax=Constante.HP_INI;
		this.setHp(this.hpMax);
		this.setMp(Constante.MP_INI);
		this.or=Constante.OR_INI;
	}
	
	public int getHpMax() {
		return hpMax;
	}

	public void setHpMax(int hpMax) {
		this.hpMax = hpMax;
	}

	public int getOr() {
		return or;
	}

	public void setOr(int or) {
		this.or = or;
	}

	public Inventaire getBag() {
		return bag;
	}

	public void setBag(Inventaire bag) {
		this.bag = bag;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}
}
