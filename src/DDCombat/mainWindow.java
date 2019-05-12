package DDCombat;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

public class mainWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainWindow window = new mainWindow();
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
	public mainWindow() {
		initialize();
		frame.setTitle("C.C.E.M - Complex Combat Encounter Manager");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getClass().getResource("/res/CCEM.png/");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel MAIN_WINDOW = new JPanel();
		frame.getContentPane().add(MAIN_WINDOW, BorderLayout.CENTER);
		CardLayout cards = new CardLayout (0,0);
		MAIN_WINDOW.setLayout(cards);
		
		JPanel mainMenu = new JPanel();
		MAIN_WINDOW.add(mainMenu, "name_6893870138000");
		mainMenu.setLayout(null);
		
		JButton btnMapEditor = new JButton("Map Editor");
		btnMapEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(MAIN_WINDOW, "name_6906489067600");
			}
		});
		btnMapEditor.setBounds(10, 82, 123, 23);
		mainMenu.add(btnMapEditor);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setBounds(151, 0, 283, 261);
		mainMenu.add(lblLogo);
		ImageIcon logo = new ImageIcon("res/CCEM.png");
		lblLogo.setIcon(logo);
		
		JButton btnTestEncounter = new JButton("Test Encounter");
		btnTestEncounter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				testCombat test = new testCombat();
				frame.dispose();
			}
		});
		btnTestEncounter.setBounds(10, 48, 123, 23);
		mainMenu.add(btnTestEncounter);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnExit.setBounds(10, 119, 123, 23);
		mainMenu.add(btnExit);
		
		JPanel editorWindow = new JPanel();
		MAIN_WINDOW.add(editorWindow, "name_6906489067600");
		editorWindow.setLayout(null);
		
		JButton btnGoBack = new JButton("Main Menu");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(MAIN_WINDOW, "name_6893870138000");
			}
		});
		btnGoBack.setBounds(10, 227, 99, 23);
		editorWindow.add(btnGoBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(10, 11, 414, 211);
		editorWindow.add(scrollPane);
	}
}
