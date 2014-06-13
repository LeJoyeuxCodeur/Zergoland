import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPrincipal extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton nouveau = new JButton("Nouveau Jeu"), continuer = new JButton("Continuer"), exit = new JButton("Quitter");

	public MenuPrincipal() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		nouveau.setAlignmentX(Component.CENTER_ALIGNMENT);
		continuer.setAlignmentX(Component.CENTER_ALIGNMENT);
		exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		ButtonListener al = new ButtonListener();
		nouveau.addActionListener(al);
		continuer.addActionListener(al);
		exit.addActionListener(al);
		add(nouveau);
		add(continuer);
		add(exit);
		
	}
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==nouveau) {
				
			}
			if(e.getSource()==continuer) {
				
			}
			if(e.getSource()==exit) {
				System.exit(0);
			}
		}	
	}
}
