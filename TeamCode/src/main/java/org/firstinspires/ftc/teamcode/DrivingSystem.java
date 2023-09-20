package org.firstinspires.ftc.teamcode;

import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class DrivingSystem {
	private final LinearOpMode opMode;
	private final SwerveModule frontRight;
	private final SwerveModule frontLeft;
	private final SwerveModule backRight;
	private final SwerveModule backLeft;
	private final IMU imu;


	public DrivingSystem(LinearOpMode opMode) {
		this.opMode = opMode;
		frontRight = new SwerveModule(opMode, "front_right_wheel", "front_right_rot");
		frontLeft = new SwerveModule(opMode, "front_left_wheel", "front_left_rot");
		backRight = new SwerveModule(opMode, "back_right_wheel", "back_right_rot");
		backLeft = new SwerveModule(opMode, "back_left_wheel", "back_left_rot");
		frontLeft.setReversed(true);
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
			frontRight.idle();
			frontLeft.idle();
			backRight.idle();
			backLeft.idle();
			return;
		}

		Vector2 frontLeftRot = Vector2.fromPolar(rotPower, toRadians(45));
		Vector2 frontRightRot = Vector2.fromPolar(rotPower, toRadians(135));
		Vector2 backRightRot = Vector2.fromPolar(rotPower, toRadians(225));
		Vector2 backLeftRot = Vector2.fromPolar(rotPower, toRadians(315));

		frontRight.run(Vector2.add(forward, frontRightRot).toPolar());
		frontLeft.run(Vector2.add(forward, frontLeftRot).toPolar());
		backRight.run(Vector2.add(forward, backRightRot).toPolar());
		backLeft.run(Vector2.add(forward, backLeftRot).toPolar());
	}

	public double getAngle() {
		YawPitchRollAngles angles = imu.getRobotYawPitchRollAngles();
		return -angles.getYaw(AngleUnit.RADIANS);
	}
}
