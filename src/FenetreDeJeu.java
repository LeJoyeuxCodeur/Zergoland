import javax.swing.JFrame;

public class FenetreDeJeu extends JFrame {
	private static final long serialVersionUID = 1L;

	public FenetreDeJeu() {
		super("ZergoLand");
		setSize(Constante.X - 150, Constante.Y - 150); // Nombre de pixels a retirer
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}