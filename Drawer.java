import java.awt.*;
import javax.swing.*;

public class Drawer extends JPanel {

    int level = 0;

    public Drawer( int a ) {
	level = a;
    }

    public void doDrawing(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	g2d.setColor(Color.cyan);
	g2d.setStroke(new BasicStroke(6));
	if (level == 0)
	    g2d.drawOval(20, 280, 100, 70);
	else if (level == 1)
	    g2d.drawLine(80, 310, 240, 310);
	else if (level == 2)
	    g2d.drawLine(240, 310, 540, 320);
	else if (level == 3)
	    g2d.drawLine(80, 310, 240, 310);
	else if (level == 4)
	    g2d.drawLine(80, 310, 240, 310);
	else if (level == 5)
	    g2d.drawLine(80, 310, 240, 310);
	else if (level == 6)
	    g2d.drawLine(80, 310, 240, 310);
	else if (level == 7)
	    g2d.drawLine(80, 310, 240, 310);
	else if (level == 8)
	    g2d.drawLine(80, 310, 240, 310);
    }

    public void paintComponent(Graphics g) {
	super.paintComponent(g);
	doDrawing(g);
    }

}
