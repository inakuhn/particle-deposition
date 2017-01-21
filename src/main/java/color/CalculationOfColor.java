package color;

import java.awt.*;
import java.util.List;

public class CalculationOfColor {
	double max;
	double min;
	private static CalculationOfColor calculationOfColor = null;
	private CalculationOfColor() {

	}
	public static CalculationOfColor getCalculation(){
		if(calculationOfColor == null){
			calculationOfColor = new CalculationOfColor();
		}
		return calculationOfColor;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public Color getColorForValue(double valueOfYourDataPoint) {
		List<RGBValues> list = ColorsHeatMap.colors;
		double value = ((list.size()-1) * (valueOfYourDataPoint - min) / (max -min));
		//double value = ((valueOfYourDataPoint * (list.size() - 1)) / max);
		int index = (int) Math.round(value);

		RGBValues rgbValues = ColorsHeatMap.colors.get(index);
		Color color = new Color(rgbValues.getR(), rgbValues.getG(), rgbValues.getB());

		return color;
	}

}
