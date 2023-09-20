package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;

public class MathUtils {

	public static double normalize(double a){
		return a - Math.floor(a);
	}

	/** Returns the difference between 2 numbers (a-b) modulo 1, in the range -0.5 to 0.5.
	 */
	public static double normalizedDif(double a, double b){
		double c = normalize(a - b);
		if (c > 0.5){
			c -= 1;
		}
		return c;
	}
}
