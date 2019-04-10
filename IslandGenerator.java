import java.awt.*;
import java.util.*;
import javax.swing.*;

public class IslandGenerator extends JComponent
{
   public static final int HEIGHT = 1000;
   public static final int WIDTH = 1000;
   public static int levelMax = 20;
   public static int coeffBase = 200;
   public static double coeffExpo = 0.5;
   
   public static void main(String[] a)
   {
      JFrame f = new JFrame();
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setSize(HEIGHT, WIDTH);
      f.getContentPane().add(new IslandGenerator());
      f.setVisible(true);
   }
   
   public void paint(Graphics g)
   {
      Point[] startSquare = {new Point(200,200), new Point(800,200), new Point(800,800), new Point(200,800)};
      generate(0, startSquare, g);
   }
   
   public static Point midpoint(Point p1, Point p2) 
   {
      return new Point((p1.x+p2.x)/2,(p1.y+p2.y)/2);
   }
  
   public void generate(int level, Point[] points, Graphics g)
   {
      if (level == levelMax)
      {
         Polygon p = new Polygon();
         for (Point x : points)
            p.addPoint(Math.round((float) x.getX()), Math.round((float) x.getY()));
         g.fillPolygon(p);
      }
      else
      {
         Point[] newPoints = new Point[points.length*2];
         double transAmount1 = Math.pow(coeffExpo, level)*coeffBase;
         double transAmount2 = Math.pow(coeffExpo, level)*coeffBase;
         for (int i = 0;i<points.length;i++)
            newPoints[i*2] = points[i];
         for (int i = 1;i<points.length*2-2;i += 2)
         {
            newPoints[i] = midpoint(newPoints[i-1], newPoints[i+1]);
            newPoints[i].translate((int)(Math.random()*(2*transAmount1)-transAmount1),(int)(Math.random()*(2*transAmount2)-transAmount2));
         }
         newPoints[newPoints.length-1] = midpoint(newPoints[newPoints.length-2], newPoints[0]);
         newPoints[newPoints.length-1].translate((int)(Math.random()*(2*transAmount1)-transAmount1),(int)(Math.random()*(2*transAmount2)-transAmount2));
         generate(level+1, newPoints, g);
      }
   }
}
