package webPlayer.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotoes extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static PanelBotoes instance;

	private JButton fecharBtn;

	private PanelBotoes() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		add(getFecharBtn());
	}

	private JButton getFecharBtn() {
		if (fecharBtn == null) {
			fecharBtn = new JButton("Fechar");
			fecharBtn.addActionListener(this);
		}
		return fecharBtn;
	}

	public static PanelBotoes getInstance() {
		if (instance == null) {
			instance = new PanelBotoes();
		}
		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getFecharBtn()) {
			System.exit(0);
		}
	}
}
