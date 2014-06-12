import java.awt.Image;


public class Case {
	
	private boolean franchissable;
	private Image i;
	
	public Case(Image i, boolean f) {
		this.i=i;
		this.franchissable=f;
	}

	public boolean isFranchissable() {
		return franchissable;
	}

	public void setFranchissable(boolean franchissable) {
		this.franchissable = franchissable;
	}

	public Image getImage() {
		return i;
	}

	public void setImage(Image i) {
		this.i = i;
	}
}
