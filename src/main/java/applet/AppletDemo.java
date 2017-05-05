package applet;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class AppletDemo extends Applet {
	 int squareSize = 50;// 初始化默认大小
	   public void init () {
		   String squareSizeParam = getParameter ("squareSize");
		   parseSquareSize (squareSizeParam);
		   String colorParam = getParameter ("color");
		   Color fg = parseColor (colorParam);
		   setBackground (Color.black);
		   setForeground (fg);
	   }
	   private void parseSquareSize (String param) {
		   if (param == null) return;
		   try {
		      squareSize = Integer.parseInt (param);
		   }
		   catch (Exception e) {
		     
		   }
	   }
	   private Color parseColor (String param) {
		   return Color.getColor(param);
	   }
	   public void paint (Graphics g) {}
}
