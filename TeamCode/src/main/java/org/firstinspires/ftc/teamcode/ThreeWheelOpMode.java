package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "ThreeWheel")
public class ThreeWheelOpMode extends LinearOpMode {
	@Override
	public void runOpMode() {

		ThreeWheelDrivingSystem drivingSystem = new ThreeWheelDrivingSystem(this);

		waitForStart();
		while (opModeIsActive()){
			float x = -gamepad1.left_stick_y;
			float y = gamepad1.left_stick_x;
			float rot = -gamepad1.right_stick_x;
			double scalar = (1 - gamepad1.right_trigger) * 0.8 + 0.2;

			x *= scalar;
			y *= scalar;
			rot *= scalar;
			if (gamepad1.right_bumper || gamepad1.left_bumper){
				x /= 3;
				y /= 3;
				rot /= 3;
			}
			drivingSystem.drive(x, y, rot);
			telemetry.update();
		}
	}
}
