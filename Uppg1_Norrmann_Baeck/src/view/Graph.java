//Cred for this code to "Hovercraft Full Of Eels" at StackOverflow. Edited by us. He probably copied it from somewhere else too.
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

@SuppressWarnings("serial")
public class Graph extends JPanel {
   private double MAX_SCORE = 2000;
   private static final int PREF_W = 900;
   private static final int PREF_H = 850;
   private static final int BORDER_GAP = 100;
   private static final int LABEL_GAP = 0;
   private static final Color GRAPH_COLOR = Color.cyan;
   private static Color GRAPH_POINT_COLOR = new Color(0, 0, 250, 180);
   private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
   private static final int GRAPH_POINT_WIDTH = 5;
   private static final int Y_HATCH_CNT = 10;
   public List<Double> scores;
   
   private String firstX = "THIS IS AN";
   private String lastX = "EXAMPLE DATASET";

   
   public Graph() { 
	   /* fun stuff
	   try {
		   GRAPH_POINT_COLOR = new Color(main.Main.inis.getKeyValue("GRAPH_POINT_COLOR"));
	   }
	   catch(NullPointerException npe) {
		   
	   }
	   */
	   //Generates default set
	   double temp = 27;
	   List<Double> tempList = new ArrayList<Double>();
	   for(int i = 0; i < 27; i++) {
		   tempList.add(temp);
	   }
	   
	   scores = tempList;
   }
   
   /*public Graph(List<Double> scores) {
	      this.scores = scores;
	   }*/

   
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (scores.size() - 1);
      double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

      List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < scores.size(); i++) {
         int x1 = (int) (i * xScale + BORDER_GAP);
         int y1 = (int) ((MAX_SCORE - scores.get(i)) * yScale + BORDER_GAP);
         graphPoints.add(new Point(x1, y1));
      }

      
      
      // create x and y axes 
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, BORDER_GAP, BORDER_GAP);
      g2.drawLine(BORDER_GAP, getHeight() - BORDER_GAP, getWidth() - BORDER_GAP, getHeight() - BORDER_GAP);

      
      // create hatch marks for y axis.
      for (int i = 0; i < Y_HATCH_CNT + 1; i++) {
          int x0 = BORDER_GAP + LABEL_GAP;
          int x1 = GRAPH_POINT_WIDTH + BORDER_GAP + LABEL_GAP;
          int y0 = getHeight() - ((i * (getHeight() - BORDER_GAP * 2 - LABEL_GAP)) / Y_HATCH_CNT + BORDER_GAP + LABEL_GAP);
          int y1 = y0;
          
          if (scores.size() > 0) {
              //g2.setColor(gridColor);
              g2.drawLine(BORDER_GAP + LABEL_GAP + 1 + GRAPH_POINT_WIDTH, y0, getWidth() - BORDER_GAP, y1);
              g2.setColor(Color.BLACK);
              
              String yLabel = ((int) ((getMinScore() + (getMaxScore() - getMinScore()) * ((i * 1.0) / Y_HATCH_CNT)) * 100)) / 100.0 + "";
              FontMetrics metrics = g2.getFontMetrics();
              int labelWidth = metrics.stringWidth(yLabel);
              
              g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
          }
          
          g2.drawLine(x0, y0, x1, y1);
      }
      
      /*Not needed:
         for (int i = 0; i < Y_HATCH_CNT; i++) {
         int x0 = BORDER_GAP;
         int x1 = GRAPH_POINT_WIDTH + BORDER_GAP;
         int y0 = getHeight() - (((i + 1) * (getHeight() - BORDER_GAP * 2)) / Y_HATCH_CNT + BORDER_GAP);
         int y1 = y0;
         g2.drawLine(x0, y0, x1, y1);
      }*/
      
      

      // and for x axis
      for (int i = 0; i < scores.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (scores.size() - 1) + BORDER_GAP;
         int x1 = x0;
         int y0 = getHeight() - BORDER_GAP;
         int y1 = y0 - GRAPH_POINT_WIDTH;

         if (i == 0) {
             //g2.setColor(gridColor);
             g2.drawLine(BORDER_GAP + LABEL_GAP + 1 + GRAPH_POINT_WIDTH, y0, getWidth() - BORDER_GAP, y1);
             g2.setColor(Color.BLACK);
             String space = "";
             for (int spaceFor = 0; spaceFor < 155; spaceFor++) {
            	 space += " ";
             }
             String xLabel = firstX + space + lastX;
             
             /*Nod needed:
               FontMetrics metrics = g2.getFontMetrics();
               int labelWidth = metrics.stringWidth(xLabel);
               */
             g2.drawString(xLabel, x0, y0 + 15);
         }
         
         g2.drawLine(x0, y0, x1, y1);
      }
      
      Stroke oldStroke = g2.getStroke();
      g2.setColor(GRAPH_COLOR);
      g2.setStroke(GRAPH_STROKE);
      
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         
         g2.drawLine(x1, y1, x2, y2);         
      }

      g2.setStroke(oldStroke);      
      g2.setColor(GRAPH_POINT_COLOR);
      
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - GRAPH_POINT_WIDTH / 2;
         int y = graphPoints.get(i).y - GRAPH_POINT_WIDTH / 2;;
         int ovalW = GRAPH_POINT_WIDTH;
         int ovalH = GRAPH_POINT_WIDTH;
         
         g2.fillOval(x, y, ovalW, ovalH);
      }
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(PREF_W, PREF_H);
   }

   /* Not needed:
   private static void createAndShowGui() {
      List<Integer> scores = new ArrayList<Integer>();
      Random random = new Random();
      int maxDataPoints = 100;
      int maxScore = 2000;
      
      for (int i = 0; i < maxDataPoints ; i++) {
         scores.add(random.nextInt(maxScore));
      }
      
      Graph mainPanel = new Graph(scores);

      JFrame frame = new JFrame("DrawGraph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }*/
   
   
   //OUR MAIN EDITS:
   
   //sets score
   public void setScore(List<Double> scores) {
	   this.scores = scores;
   }
   
   //resets score
   public void resetScore() {
	   scores.clear();
   }
   
   //sets maxScore
   public void setMaxScore(double max) {
	   this.MAX_SCORE = max;
   }
   
   //gets maxScore
   public double getMaxScore(){
	   return MAX_SCORE;
   }
   
   //gets minScore
   public double getMinScore() {
    	return 0;
   }
   
   //sets first x value
   public void setFirstX(String firstX) {
 	   this.firstX = firstX;
   }
   
   //sets last x value
   public void setLastX(String lastX) {
	   this.lastX = lastX;
   }
}