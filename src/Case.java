import java.awt.Image;

public class Case {
	private boolean obstacle;
	private Image skin;

	public Case(Image skin, boolean obstacle) {
		this.skin = skin;
		this.obstacle = obstacle;
	}
	public boolean isObstacle() {
		return obstacle;
	}
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}
	public Image getSkin() {
		return skin;
	}
	public void setSkin(Image skin) {
		this.skin = skin;
	}
}