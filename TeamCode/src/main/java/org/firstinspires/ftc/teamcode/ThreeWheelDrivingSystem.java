package org.firstinspires.ftc.teamcode;

import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class ThreeWheelDrivingSystem {
	private final LinearOpMode opMode;
	private final SwerveModule front;
	private final SwerveModule backRight;
	private final SwerveModule backLeft;
	private final IMU imu;


	public ThreeWheelDrivingSystem(LinearOpMode opMode) {
		this.opMode = opMode;
		front = new SwerveModule(opMode, "front_wheel", "front_rot");
		backRight = new SwerveModule(opMode, "right_wheel", "right_rot");
		backLeft = new SwerveModule(opMode, "left_wheel", "left_rot");
		backLeft.setReversed(true);


		// imu
		IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
		imu = opMode.hardwareMap.get(IMU.class, "imu");
		imu.initialize(parameters);

		imu.resetYaw();


	}

	public void drive(double x, double y, double rotPower) {
		Vector2 forward = new Vector2(x, y);

		double angle = getAngle();
		forward = forward.rotate(-angle);
		Vector2.Polar forwardPolar = forward.toPolar();

		opMode.telemetry.addData("angle: ", toDegrees(angle));

		if (Math.abs(forwardPolar.r) < 0.001 && Math.abs(rotPower) < 0.001) {
			front.idle();
			backRight.idle();
			backLeft.idle();
			return;
		}

		Vector2 frontRot = Vector2.fromPolar(rotPower, toRadians(90));
		Vector2 backRightRot = Vector2.fromPolar(rotPower, toRadians(210));
		Vector2 backLeftRot = Vector2.fromPolar(rotPower, toRadians(330));

		front.run(Vector2.add(forward, frontRot).toPolar());
		backRight.run(Vector2.add(forward, backRightRot).toPolar());
		backLeft.run(Vector2.add(forward, backLeftRot).toPolar());
	}

	public double getAngle() {
		YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();
		return -angles.getYaw(AngleUnit.RADIANS);
	}
}
