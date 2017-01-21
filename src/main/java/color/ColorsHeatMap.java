package color;

import java.util.LinkedList;
import java.util.List;

public class ColorsHeatMap {
	public static List<RGBValues> colors;

	static {
		
//		/*
//		 * Step 1
//		 */
		int r = 255;
		int g = 255;
		int b = 255;

		colors = new LinkedList<RGBValues>();
		while (g != 0) {
			RGBValues rgbValues = new RGBValues(r, g, b);
			g--;
			r--;
			b--;
			colors.add(rgbValues);
		}
	}
}
