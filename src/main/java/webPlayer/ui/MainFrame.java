package webPlayer.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPane;
	private JPanel paneLog;
	private JTextArea logTextArea;

	public MainFrame() {
		setContentPane(getMainPane());
		setSize(640, 480);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		InternalOutputStream os = new InternalOutputStream(System.out, getLogTextArea());
		System.setOut(os);
		InternalOutputStream es = new InternalOutputStream(System.err, getLogTextArea());
		System.setErr(es);

		setVisible(true);
	}

	private JPanel getMainPane() {
		if (mainPane == null) {
			mainPane = new JPanel(new BorderLayout(2, 2));
			mainPane.add(getPaneBotoes(), BorderLayout.NORTH);
			mainPane.add(getPaneLog(), BorderLayout.CENTER);
		}
		return mainPane;
	}

	private JPanel getPaneLog() {
		if (paneLog == null) {
			paneLog = new JPanel(new BorderLayout());
			JTextArea text = getLogTextArea();
			paneLog.add(new JScrollPane(text), BorderLayout.CENTER);
		}
		return paneLog;
	}

	private JTextArea getLogTextArea() {
		if (logTextArea == null) {
			logTextArea = new JTextArea();
		}
		return logTextArea;
	}

	private Component getPaneBotoes() {
		return PanelBotoes.getInstance();
	}

	public class InternalOutputStream extends PrintStream {

		private JTextArea log;

		public InternalOutputStream(PrintStream out, JTextArea log) {
			super(out);
			this.log = log;
		}

		@Override
		public void print(String s) {
			super.print(s);
			log.append(s + "\n");
		}
	}

}
