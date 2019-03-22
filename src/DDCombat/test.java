package DDCombat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblLetsTestThis = new JLabel("Lets Test This Shit out, yeah?");
		lblLetsTestThis.setHorizontalAlignment(SwingConstants.CENTER);
		lblLetsTestThis.setBounds(119, 69, 169, 14);
		frame.getContentPane().add(lblLetsTestThis);
		
		JButton btnSure = new JButton("Sure?");
		btnSure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				testRender test = new testRender();	
			}
		});
		btnSure.setBounds(144, 94, 121, 58);
		frame.getContentPane().add(btnSure);
	}
}
