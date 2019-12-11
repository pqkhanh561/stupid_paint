import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Main{
	JFrame frame;
	ArrayList<DrawObject> ListObject = new ArrayList<DrawObject>();

	public Main(){
		MyShape poly= new MyRect(new Point[]{new Point(0,0), new Point(10,50)});
		//MyShape poly= new MyCircle(50,50,50);
		ListObject.add(new DrawObject(poly));
		//System.out.println(ob);
		Gui();
	}


	public static void main(String[] args){
		new Main();	
		
	}

	public void Gui(){
		frame = new JFrame();
		frame.setLayout(new GridLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new DrawPanel());
		frame.setSize(500,500);
		frame.setVisible(true);
	}

	public class DrawPanel extends JPanel{
		//TODO: Many shape need to be selected
		private DrawObject dragged;
		private Point offset;

		public DrawPanel(){

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (DrawObject ob: ListObject){
					if (ob.contains(e.getPoint())){
						System.out.println("Object is clicked");
						dragged = ob;
						offset = new Point(e.getX(), e.getY());
						repaint();
						break;
					}	
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (dragged != null) {
					repaint();
				}
				dragged = null;
				offset = null;
			}
		    });

	    	addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (dragged != null & offset !=null){
					dragged.setLocation(offset, e.getPoint());
				}
				repaint();
			}
		    });
		}

		@Override
		public void paintComponent(Graphics g){
			Graphics2D g2d = (Graphics2D) g.create();
			for (DrawObject ob : ListObject){
				ob.draw(g2d);
			}
			g2d.dispose();
			//ob.setSize();
			//ob.draw(g2d);
			//g2d.draw(new Rectangle(10,10,50,50));
		}
	}
}
