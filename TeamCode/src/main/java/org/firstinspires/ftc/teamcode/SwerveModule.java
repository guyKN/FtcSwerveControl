package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.MathUtils.normalizedDif;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.opencv.core.Mat;

public class SwerveModule {
	public static double ROTATE_TICKS_PER_ROTATION = 1613;

	private final OpMode opMode;
	private DcMotor driveMotor;
	private DcMotor rotateMotor;
	public SwerveModule(OpMode opMode, String driveName, String rotateName) {
		this.opMode = opMode;

		driveMotor = opMode.hardwareMap.get(DcMotor.class, driveName);
		driveMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		driveMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

		rotateMotor = opMode.hardwareMap.get(DcMotor.class, rotateName);
		rotateMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		rotateMotor.setTargetPosition(0);
		rotateMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		rotateMotor.setPower(1);
	}

	public void setReversed(boolean reversed){
		if (reversed){
			driveMotor.setDirection(DcMotorSimple.Direction.REVERSE);
		}else {
			driveMotor.setDirection(DcMotorSimple.Direction.FORWARD);
		}
	}

	public void run(Vector2.Polar powerAndDirection){
		double power = powerAndDirection.r;
		double angle = powerAndDirection.theta;

		double currentRot = rotateMotor.getCurrentPosition() / ROTATE_TICKS_PER_ROTATION;
		double targetRot = angle / (2 * Math.PI);

		// always rotate the short way, and switch direction if necessary
		if (abs(normalizedDif(currentRot, targetRot)) > 0.25){
			targetRot += 0.5;
			power = -power;
		}

		// normalize so that targetRot is in the same range as
		double newTargetRot = normalizedDif(targetRot, currentRot) + currentRot;

		driveMotor.setPower(power);
		rotateMotor.setTargetPosition((int) (newTargetRot * ROTATE_TICKS_PER_ROTATION));
	}

	public void idle(){
		driveMotor.setPower(0);
		rotateMotor.setTargetPosition(rotateMotor.getCurrentPosition());
	}
}
