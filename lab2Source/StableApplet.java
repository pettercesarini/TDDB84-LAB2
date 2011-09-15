/*
 * Created on 2005-mar-21
 *
 */
package lab2Source;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 
 * @author Peter Sunnergren
 */
public class StableApplet extends JApplet implements ActionListener, KeyListener {
	private StablePanel stablePanel;
	
	/**
	 * Method called on initiation of the applet.
	 */
	public void init() {
		this.setSize(400, 400);
		
		makeMenu();
		
		stablePanel = new StablePanel(this);
		this.getContentPane().add(stablePanel);
		
		Images.setApplet(this);
		
		stablePanel.defaultConstruction();
		
		addKeyListener(this);
		
		setFocusable(true);
		setVisible(true);
		requestFocus();
	}
	
	/**
	 * Makes the menu.
	 */
	private void makeMenu() {		
		JMenuBar myMenuBar;
		JMenu chooseMenu;
		JMenuItem defaultItem;
		JMenuItem factoryItem; 
		JMenuItem builderItem;
		JMenuItem prototypeItem; 
		
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		
		myMenuBar = new JMenuBar();
		setJMenuBar(myMenuBar);
		
		chooseMenu = new JMenu("Creator");
		myMenuBar.add(chooseMenu);
		
		defaultItem = new JMenuItem("Default");
		defaultItem.addActionListener(this);
		chooseMenu.add(defaultItem);
		
		factoryItem = new JMenuItem("Factory");
		factoryItem.addActionListener(this);
		chooseMenu.add(factoryItem);
		
		builderItem = new JMenuItem("Builder");
		builderItem.addActionListener(this);
		chooseMenu.add(builderItem);
		
		prototypeItem = new JMenuItem("Prototype");
		prototypeItem.addActionListener(this);
		chooseMenu.add(prototypeItem);
	}

	/**
	 * Handles the menu events.
	 */
	public void actionPerformed(ActionEvent event) {
		if ("Default" == event.getActionCommand()) {
			stablePanel.defaultConstruction();
		} else if ("Factory" == event.getActionCommand()) {
			stablePanel.factoryConstruction();
		} else if ("Builder"== event.getActionCommand()) {
			stablePanel.builderConstruction();
		} else if ("Prototype"== event.getActionCommand()) {
			stablePanel.prototypeConstruction();
		} 
		else {
			System.out.print("Menu error");
		}
		//System.out.println(event.getActionCommand());
		repaint();
	}
	
	public void keyPressed(KeyEvent evt) {
		switch (evt.getKeyCode()) {
		case 10: //'\n' ;
			Person.instance().operateDoor();
			break;
		case 72: //'h'
			stablePanel.addHorse(this);
			break;	
		case KeyEvent.VK_LEFT :
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_UP   :
		case KeyEvent.VK_DOWN :
			Person.instance().go(evt.getKeyCode());
		break;
		}
		//System.out.print(evt.getKeyCode());
		repaint();
	}
	
	/**
	 * Method not used but required by the keylistener interface.
	 */
	public void keyTyped(KeyEvent evt) { }
	
	/**
	 * Method not used but required by the keylistener interface.
	 */
	public void keyReleased(KeyEvent evt) { }	
}
