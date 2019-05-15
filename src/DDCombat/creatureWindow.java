package DDCombat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class creatureWindow extends JPanel{

	private JTextField textField;


	/**
	 * Create the application.
	 */
	public creatureWindow() {
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		this.setLayout(null);
		
		JLabel lblSelectedCombatant = new JLabel("Selected Combatant");
		lblSelectedCombatant.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectedCombatant.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectedCombatant.setBounds(54, 11, 148, 14);
		this.add(lblSelectedCombatant);
		
		JLabel lblscName = new JLabel("Creature 1 ");
		lblscName.setHorizontalAlignment(SwingConstants.CENTER);
		lblscName.setFont(new Font("Tekton Pro", Font.BOLD, 15));
		lblscName.setBounds(33, 32, 185, 35);
		this.add(lblscName);
		
		JLabel lblAc = new JLabel("AC: ");
		lblAc.setBounds(21, 104, 64, 14);
		this.add(lblAc);
		
		JLabel lblCurrentHp = new JLabel("Current HP:");
		lblCurrentHp.setBounds(21, 80, 97, 14);
		this.add(lblCurrentHp);
		
		JRadioButton rdbtnAiControlled = new JRadioButton("AI Controlled");
		rdbtnAiControlled.setBounds(21, 125, 109, 23);
		this.add(rdbtnAiControlled);
		
		JButton btnEditHp = new JButton("Edit Hp");
		btnEditHp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnEditHp.setBounds(186, 78, 76, 18);
		this.add(btnEditHp);
		
		textField = new JTextField();
		textField.setBounds(112, 77, 64, 20);
		this.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 164, 280, 282);
		add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
	}
	
	//public void
}
