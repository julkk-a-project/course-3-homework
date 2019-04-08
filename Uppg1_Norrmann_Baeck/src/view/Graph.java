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

	private static final Color GRAPH_COLOR_2 = Color.red;
	private static Color GRAPH_POINT_COLOR_2 = new Color(125, 0, 0, 180);
	private static final Stroke GRAPH_STROKE_2 = new BasicStroke(2f);


	private static final int GRAPH_POINT_WIDTH = 5;
	private static final int Y_HATCH_CNT = 10;
	public List<Double> scores1;
	public List<Double> scores2;

	private String firstX = "THIS IS AN";
	private String lastX = "EXAMPLE DATASET";

	private int biggerSize;
	private boolean isScore1Bigger;
	private int sizeDif;

	private boolean should1BeDrawn;
	private boolean should2BeDrawn;


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
		double temp2 = 55;
		List<Double> tempList = new ArrayList<Double>();
		List<Double> tempList2 = new ArrayList<Double>();
		for(int i = 0; i < 27; i++) {
			tempList.add(temp);
			tempList2.add(temp2);

		}

		scores1 = tempList;
		scores2 = tempList2;
	}

	/*public Graph(List<Double> scores) {
	      this.scores = scores;
	   }*/


	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);



		dataComparer();



		double xScale = ((double) getWidth() - 2 * BORDER_GAP) / (getBiggerScore().size() - 1); //should try to look for a scores list that has data in it.
		double yScale = ((double) getHeight() - 2 * BORDER_GAP) / (MAX_SCORE - 1);

		List<Point> graphPoints1 = new ArrayList<Point>();
		List<Point> graphPoints2 = new ArrayList<Point>();
		if(!isScore1Bigger) {
			for(int i = 0; i < sizeDif; i++) {
				graphPoints1.add(new Point((int)(i*xScale + BORDER_GAP), 0));
			}
		}else {
			for(int i = 0; i < sizeDif; i++) {
				graphPoints2.add(new Point((int)(i*xScale + BORDER_GAP), 0));
			}
		}

		if(should1BeDrawn) {
			for (int i = 0; i < scores1.size(); i++) {
				int j = i;
				if (!isScore1Bigger) {
					j += sizeDif;
				}
				int x1 = (int) (j * xScale + BORDER_GAP);
				int y1 = (int) ((MAX_SCORE - scores1.get(i)) * yScale + BORDER_GAP);
				graphPoints1.add(new Point(x1, y1));
			}      
		}

		if(should2BeDrawn) {
			for (int i = 0; i < scores2.size(); i++) {
				int j = i;
				if (isScore1Bigger) {
					j += sizeDif;
				}
				int x1 = (int) (j * xScale + BORDER_GAP);
				int y1 = (int) ((MAX_SCORE - scores2.get(i)) * yScale + BORDER_GAP);
				graphPoints2.add(new Point(x1, y1));
			}    	  
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

			if (getAScore().size() > 0) {
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
		for (int i = 0; i < getAScore().size() - 1; i++) {
			int x0 = (i + 1) * (getWidth() - BORDER_GAP * 2) / (getAScore().size() - 1) + BORDER_GAP;
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

				/*Not needed because of above hack :)
               FontMetrics metrics = g2.getFontMetrics();
               int labelWidth = metrics.stringWidth(xLabel);
				 */
				g2.drawString(xLabel, x0, y0 + 15);
			}

			g2.drawLine(x0, y0, x1, y1);
		}


		//DRAWS POints AND Lines foR SCOREs1
		if(should1BeDrawn) {

			Stroke oldStroke1 = g2.getStroke();
			g2.setColor(GRAPH_COLOR);
			g2.setStroke(GRAPH_STROKE);

			for (int i = 0; i < graphPoints1.size() - 1; i++) {
				int x1 = graphPoints1.get(i).x;
				int y1 = graphPoints1.get(i).y;
				int x2 = graphPoints1.get(i + 1).x;
				int y2 = graphPoints1.get(i + 1).y;

				if((graphPoints1.get(i).y) != 0) {
					g2.drawLine(x1, y1, x2, y2);	
				}
				         
			}

			g2.setStroke(oldStroke1);      
			g2.setColor(GRAPH_POINT_COLOR);

			for (int i = 0; i < graphPoints1.size(); i++) {
				int x = graphPoints1.get(i).x - GRAPH_POINT_WIDTH / 2;
				int y = graphPoints1.get(i).y - GRAPH_POINT_WIDTH / 2;;
				int ovalW = GRAPH_POINT_WIDTH;
				int ovalH = GRAPH_POINT_WIDTH;

				if((graphPoints1.get(i).y) != 0) {
					g2.fillOval(x, y, ovalW, ovalH);
				}
				
			}

		}
		//DRAWS POints AND Lines foR SCOREs2
		if(should2BeDrawn) {
			Stroke oldStroke2 = g2.getStroke();
			g2.setColor(GRAPH_COLOR_2);
			g2.setStroke(GRAPH_STROKE_2);

			for (int i = 0; i < graphPoints2.size() - 1; i++) {
				int x1 = graphPoints2.get(i).x;
				int y1 = graphPoints2.get(i).y;
				int x2 = graphPoints2.get(i + 1).x;
				int y2 = graphPoints2.get(i + 1).y;
				
				if((graphPoints2.get(i).y) != 0) {
					g2.drawLine(x1, y1, x2, y2);	
				}

				         
			}

			g2.setStroke(oldStroke2);      
			g2.setColor(GRAPH_POINT_COLOR_2);

			for (int i = 0; i < graphPoints2.size(); i++) {
				int x = graphPoints2.get(i).x - GRAPH_POINT_WIDTH / 2;
				int y = graphPoints2.get(i).y - GRAPH_POINT_WIDTH / 2;;
				int ovalW = GRAPH_POINT_WIDTH;
				int ovalH = GRAPH_POINT_WIDTH;

				
				if((graphPoints2.get(i).y) != 0) {
					g2.fillOval(x, y, ovalW, ovalH);	
				}
				
			}

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



	private void dataComparer() {
		if (scores1.size() >= scores2.size()) {
			isScore1Bigger = true;

		} else {
			isScore1Bigger = false;
		}

		sizeDif = Math.abs((scores1.size() - scores2.size()));

		if (scores1.isEmpty()) {
			should1BeDrawn = false;
		}else {
			should1BeDrawn = true;
		}

		if (scores2.isEmpty()) {
			should2BeDrawn = false;
		}else {
			should2BeDrawn = true;
		}
	}


	//sets score
	public void setScore(Boolean isSymbol2, List<Double> scores) {
		if(isSymbol2) {
			this.scores2 = scores;
		}else {
			this.scores1 = scores;   
		}

	}

	//resets score
	public void resetScore() {
		scores1.clear();
		scores2.clear();
		MAX_SCORE = 0;
	}

	//sets maxScore
	public void setMaxScore(double max) {
		if (MAX_SCORE < max) {
			MAX_SCORE = max;
		}
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

	public List<Double> getAScore() {
		if(scores1.isEmpty()) {
			return scores2;
		} else {
			return scores1;
		}
	}

	public List<Double> getBiggerScore() {
		if(isScore1Bigger) {
			return scores1;
		} else {
			return scores2;
		}
	}

	public List<Double> getSmallerScore() {
		if(isScore1Bigger) {
			return scores2;
		} else {
			return scores1;
		}
	}

	public static Color getOvalColor1() {
		return GRAPH_COLOR;
	}
	public static Color getOvalColor2() {
		return GRAPH_COLOR_2;
	}
	public static Color getStrokeColor1() {
		return GRAPH_POINT_COLOR;
	}
	public static Color getStrokeColor2() {
		return GRAPH_POINT_COLOR_2;
	}
}