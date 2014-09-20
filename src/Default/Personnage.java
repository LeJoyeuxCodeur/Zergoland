package Default;
import javax.swing.JProgressBar;

public class Personnage {
	private String nom;
	private int xp, hp, mp, hpMax, mpMax, or, lvl;
	private Inventaire bag;
	private JProgressBar barreVita, barreMana, barreXp;

	public Personnage(String nom) {
		this.nom = nom;
		xp = Constante.XP_BASE;
		hp = Constante.HP_BASE;
		lvl = Constante.LVL_BASE;
		hpMax = 90 + 10 * lvl;
		mp = Constante.MP_BASE;
		mpMax = Constante.MP_MAX;
		or = Constante.OR_BASE;
		barreVita = new JProgressBar(0, getHpMax());
		barreMana = new JProgressBar(0, getManaMax());
		barreXp = new JProgressBar(0, getHpMax());
		bag = new Inventaire();
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
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getMp() {
		return mp;
	}
	public void setMp(int mp) {
		this.mp = mp;
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
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public Inventaire getBag() {
		return bag;
	}
	public void setBag(Inventaire bag) {
		this.bag = bag;
	}
	public void setManaMax(int mpMax) {
		this.mpMax = mpMax;
	}
	public int getManaMax() {
		return mpMax;
	}
	public boolean isWinner() {
		return (getHp() > 1);
	}
	public JProgressBar getBarreVita() {
		return barreVita;
	}
	public JProgressBar getBarreMana() {
		return barreMana;
	}
	public JProgressBar getBarreXp() {
		return barreXp;
	}
}