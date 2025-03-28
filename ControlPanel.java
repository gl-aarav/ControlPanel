/* 
 * Aarav Goyal
 * 3/26/2025
 * ControlPanel.java
 */ 

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;	
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel
{
	public static void main(String[] args) 
	{
		ControlPanel ce = new ControlPanel();
		ce.run();
	}

	public void run() 
	{
		JFrame frame = new JFrame("Control Panel for Picture");
		frame.setSize(800, 600);
		frame.setLocation(10, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CpPanelHolder cph = new CpPanelHolder();
		
		frame.getContentPane().add(cph);
		frame.setVisible(true);
	}


}

class CpPanelHolder extends JPanel
{
	private int selected;  					// the index for the picture selected to draw
	private JTextArea tAComponentInfo;		// text area in the PictPanel, but changed in RightControlPanel2
	private JLabel welcome;					// label in the PictPanel, but changed in RightControlPanel2
	private Font font;  					// most fonts are the same, so there is one
	private PictPanel pp; 					// the variables in the RightControlPanel2 need access to use repaint
	private int val; 						// value of the slider to change the picture size
	private int width;
	private int height;
	private int [] widthOfImages; 			// stores the width of each image
	private int [] heightOfImages; 	 		// stores the height of each image

	public CpPanelHolder()
	{	
		PictPanel pict = new PictPanel();
		RightControlPanel rcp = new RightControlPanel();

	}

	class PictPanel extends JPanel
	{
		private String[] names;			// the names of the pictures
		private Image[] images;			// array of images to be drawn

		public PictPanel()
		{

			names = new String[] {"mountains.jpg", "shanghai.jpg", "trees.jpg", "water.jpg"};
			images = new Image[names.length];
			widthOfImages = new int[names.length];
			heightOfImages = new int [names.length];

			// load all of the pictures
			for (int i = 0; i < names.length; i++)
			{
				images[i] = getMyImage("pictures/" + names[i]);
				widthOfImages[i] = images[i].getWidth(this);
				heightOfImages[i] = images[i].getHeight(this);	
			}


		}

		public Image getMyImage(String pictName) 
		{
			Image picture = null;
			File pictFile = new File(pictName);
			try 
			{
				picture = ImageIO.read(pictFile);	
			}

			catch (IOException e)
			{
				System.err.print("\n\n\n" + pictName + " can't be found.\n\n\n");
				e.printStackTrace();
			}

			return picture;
		}

		// draw the image on a blank screen with the top left corner at (20,20)
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(images[selected], 20, 20 + heightOfImages[selected], this);
		}
	}	

	class RightControlPanel extends JPanel
	{
		private JTextField tfName; 						// text field for user to type in their name
		private ButtonGroup bg;							// to select the color so only one is selected
		private JRadioButton color1, color2, color3;	// color choices
		private JSlider sSize;							// slider for changing the size of the picture

		public RightControlPanel()
		{

		}

		public JMenuBar makePictureMenuBar()
		{
			JMenuBar bar = new JMenuBar();
			JMenu picture = new JMenu("Pictures");

			JMenuItem water = new JMenuItem("water");
			JMenuItem mountains = new JMenuItem("mountains");
			JMenuItem shangai = new JMenuItem("shanghai");
			JMenuItem trees = new JMenuItem("trees");

			PictureMenuHandler cmh = new PictureMenuHandler();		
			water.addActionListener(cmh);
			mountains.addActionListener(cmh);
			shangai.addActionListener(cmh);
			trees.addActionListener(cmh);

			picture.add(water);
			picture.add(mountains);
			picture.add(shangai);
			picture.add(trees);

			bar.add(picture);

			return bar;
		}
		
		class PictureMenuHandler implements ActionListener 
		{
			public void actionPerformed( ActionEvent evt ) 
			{
				String command = evt.getActionCommand();	

				if (command.equals("mountains")) 
					selected = 0;	

				else if (command.equals("shanghai"))	
					selected = 1;		

				else if (command.equals("trees"))
					selected = 2;

				else if (command.equals("water"))
					selected = 3;	

				repaint();
			}

		}



		// write the Listener/Handler class for the text field

		// write the Listener/Handler class for the slider

		// write Listener/Handler class for the JRadioButtons	
	}
}	
