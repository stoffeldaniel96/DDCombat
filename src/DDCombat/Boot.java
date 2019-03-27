package DDCombat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Boot {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Boot window = new Boot();
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
	public Boot() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("D&D 5e Combat Program");
		frame.setBounds(100, 100, 299, 184);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblGenerateTestGridmap = new JLabel("Generate 30 x 30 Test Gridmap?");
		lblGenerateTestGridmap.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenerateTestGridmap.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblGenerateTestGridmap.setBounds(10, 11, 263, 43);
		frame.getContentPane().add(lblGenerateTestGridmap);
		
		JButton btnYes = new JButton("Generate");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				testRender test = new testRender();
				frame.setVisible(false);
			}
		});
		btnYes.setBounds(78, 65, 147, 45);
		frame.getContentPane().add(btnYes);
	}
}
