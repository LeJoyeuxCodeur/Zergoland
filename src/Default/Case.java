package Default;
import javax.swing.ImageIcon;

public class Case {
	private ImageIcon skin;

	public Case(ImageIcon skin) {
		this.skin = skin;
	}
	public ImageIcon getSkin() {
		return skin;
	}
	public void setSkin(ImageIcon skin) {
		this.skin = skin;
	}
}