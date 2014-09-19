import javax.swing.JFrame;


public class Test {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setTitle("Level Editor");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new EditeurMap());
		f.setSize(1200, 700);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
