package DDCombat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreatureInfo extends JPanel{

	JFrame frame;
	private int hp;
	private int ac;
	private String name;
	private int locx;
	private int locy;

	/**
	 * Create the application.
	 */
	public CreatureInfo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JLabel lblCreatureName = new JLabel("Creature Name: ");
		lblCreatureName.setBounds(21, 21, 104, 14);
		super.add(lblCreatureName);
		
		JLabel lblCurrentHp = new JLabel("Current HP:");
		lblCurrentHp.setBounds(21, 45, 69, 14);
		super.add(lblCurrentHp);
		
		JLabel lblAc = new JLabel("AC:");
		lblAc.setToolTipText("How easy it is to hit a creature");
		lblAc.setBounds(21, 70, 46, 14);
		super.add(lblAc);
		
		JLabel lblCurrentLocation = new JLabel("Current Location: ");
		lblCurrentLocation.setBounds(21, 95, 104, 14);
		super.add(lblCurrentLocation);
		
		JLabel lblLoc = new JLabel(locx + "," + locy);
		lblLoc.setBounds(144, 95, 69, 14);
		super.add(lblLoc);
		
		JLabel lblAC = new JLabel("");
		lblAC.setBounds(144, 70, 69, 14);
		super.add(lblAC);
		
		JLabel lblHP = new JLabel("");
		lblHP.setBounds(144, 45, 69, 14);
		super.add(lblHP);
		
		JLabel lblCName = new JLabel("");
		lblCName.setBounds(113, 21, 106, 14);
		super.add(lblCName);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getLoc() {
		return locx;
	}

	public void setLoc(int locx, int locy) {
		this.locx = locx;
		this.locy = locy;
	}
}
