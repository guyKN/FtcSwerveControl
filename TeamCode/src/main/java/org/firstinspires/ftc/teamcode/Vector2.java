package org.firstinspires.ftc.teamcode;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toDegrees;

import java.util.Locale;

public class Vector2 {

	public static class Polar{
		public double r;
		public double theta;

		public Polar(double r, double theta) {
			this.r = r;
			this.theta = theta;
		}

		public String toString(){
			return String.format(Locale.US, "(r = %f, theta = %f)", r, toDegrees(theta));
		}
	}

	public double x;
	public double y;

	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public String toString(){
		return String.format("(%s, %s)", x, y);
	}

	public static Vector2 fromPolar(Polar polar){
		return new Vector2(polar.r * cos(polar.theta), polar.r * sin(polar.theta));
	}

	public static Vector2 fromPolar(double r, double theta){
		return fromPolar(new Polar(r, theta));
	}

	public Polar toPolar(){
		double r = Math.hypot(x, y);
		double theta = Math.atan2(y, x);
		return new Polar(r, theta);
	}

	public Vector2 rotate(double theta){
		double cosTheta = Math.cos(theta);
		double sinTheta = Math.sin(theta);

		double xTag = x * cosTheta - y * sinTheta;
		double yTag = x * sinTheta + y * cosTheta;
		return new Vector2(xTag, yTag);
	}

	public static Vector2 add(Vector2 v1, Vector2 v2){
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}


}
