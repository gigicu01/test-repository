import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Driver {
    private final GUI gui;
   
    public static void main(String[] args) {
	new Driver();
    }
    
    public Driver() {
	final JFrame app = new JFrame();
	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	app.setLayout(new BorderLayout());
	gui = new GUI(0, 0);
	app.setVisible(true);
	app.add(gui);
	app.setForeground(Color.RED);

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	app.setSize(screenSize.width - 400, screenSize.height - 400);
	app.setLocationRelativeTo(null);
	app.setResizable(false);
	app.setTitle("MARSHMELLO SONG GENERATOR");
    }

}