package DDCombat;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;

public class mainWindow {

	private JFrame frame;
	private JFileChooser fileChooser;

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
		fileChooser = new JFileChooser();
		
		frame = new JFrame();
		frame.setSize(450,300);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
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
				frame.setSize(800,800);
				//frame.setBounds(100, 100, 800, 800);
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
				cards.show(MAIN_WINDOW, "name_1251653242400");
				frame.setBounds(100, 100, 800, 800);
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
		
		JPanel quickCombat = new JPanel();
		MAIN_WINDOW.add(quickCombat, "name_1251653242400");
		quickCombat.setLayout(null);
		
		JScrollPane scrollPaneMW = new JScrollPane();
		scrollPaneMW.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMW.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneMW.setBounds(10, 11, 770, 720);
		quickCombat.add(scrollPaneMW);
		
		testCombat testC = new testCombat();
		testC.setPreferredSize(new Dimension(960,960));
		scrollPaneMW.setViewportView(testC);
		
		JPanel editorWindow = new JPanel();
		MAIN_WINDOW.add(editorWindow, "name_6906489067600");
		editorWindow.setLayout(null);
		
		JButton btnGoBack = new JButton("Back to Main Menu");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cards.show(MAIN_WINDOW, "name_6893870138000");
				frame.setSize(450,300);
			}
		});
		btnGoBack.setBounds(10, 740, 150, 20);
		editorWindow.add(btnGoBack);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setSize(770, 720);
		editorWindow.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(400, 400));
		
		editorWindow editor = new editorWindow();
		editor.setPreferredSize(new Dimension(960,960));
		scrollPane.add(editor);
		scrollPane.setViewportView(editor);
		
		JFileChooser fileChooser = new JFileChooser();
		JButton btnSave = new JButton("Save Map");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				fileChooser.setBounds(0, 0, 582, 397);
				editorWindow.add(fileChooser);
				fileChooser.setDialogTitle("Save Map");
				int returnValue = fileChooser.showDialog(null, "Save");
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					try {
						editor.saveMap((fileChooser.getSelectedFile().getPath()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}	
			}
		});
		btnSave.setBounds(300, 740, 100, 20);
		editorWindow.add(btnSave);
		
		
	}
}
