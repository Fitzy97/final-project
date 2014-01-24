import java.awt.*;
import javax.swing.*;

public class Drawer extends JPanel {

    int level = 0;

    public Drawer( int a ) {
	level = a;
    }

    public void doDrawing(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.black);
	g2d.drawString("START", 30, 250);

	int[] c = new int[3];
	c[0] = 20;
	c[1] = 80;
	c[2] = 50;

	int[] d = new int[3];
	d[0] = 290;
	d[1] = 290;
	d[2] = 310;

	g2d.fillPolygon( c, d, 3 );
	g2d.fillRect(40, 250, 20, 40);
	g2d.drawString("GOLD AND GLORY", 760, 140);

	int[] a = new int[3];
	a[0] = 780;
	a[1] = 840;
	a[2] = 810;

	int[] b = new int[3];
	b[0] = 180;
	b[1] = 180;
	b[2] = 200;

	g2d.fillPolygon( a, b, 3);
	g2d.fillRect(800, 140, 20, 40);
	g2d.setColor(Color.DARK_GRAY);
	g2d.setStroke(new BasicStroke(6));
	if (level == 0)
	    g2d.drawOval(20, 280, 100, 70);
	else if (level == 1) {
	    g2d.drawLine(80, 310, 210, 310);
	    g2d.drawOval(210, 290, 45, 50);
	}
	else if (level == 2) {
	    g2d.drawLine(255, 310, 405, 290);
	    g2d.drawOval(405, 260, 50, 60);
	}
	else if (level == 3) {
	    g2d.drawLine(455, 280, 470, 250);
	    g2d.drawOval(470, 220, 55, 60);
	}
	else if (level == 4) {
	    g2d.drawLine(525, 250, 540, 250);
	    g2d.drawOval(540, 230, 50, 40);
	}
	else if (level == 5) {
	    g2d.drawLine(580, 260, 605, 275);
	    g2d.drawOval(600, 270, 40, 50);
	}
	else if (level == 6) {
	    g2d.drawLine(640, 275, 705, 275);
	    g2d.drawOval(705, 250, 70, 60);
	}
	else if (level == 7) {
	    g2d.drawLine(770, 295, 790, 300);
	    g2d.drawOval(790, 290, 80, 40);
	}
	else if (level == 8) {
	    g2d.drawLine(810, 290, 805, 275);
	    g2d.drawOval(760, 180, 180, 120);
	}
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	doDrawing(g);
    }

}
