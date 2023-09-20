package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "ThreeWheelTest")
public class ThreeWheelTest extends LinearOpMode {
	@Override
	public void runOpMode()  {
		DcMotor frontWheel = hardwareMap.get(DcMotor.class, "front_wheel");
		DcMotor frontRot = hardwareMap.get(DcMotor.class, "front_rot");
		DcMotor rightWheel = hardwareMap.get(DcMotor.class, "right_wheel");
		DcMotor rightRot = hardwareMap.get(DcMotor.class, "right_rot");
		DcMotor leftWheel = hardwareMap.get(DcMotor.class, "left_wheel");
		DcMotor leftRot = hardwareMap.get(DcMotor.class, "left_rot");

		frontRot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		rightRot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		leftRot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


		frontRot.setTargetPosition(0);
		rightRot.setTargetPosition(0);
		leftRot.setTargetPosition(0);

		frontRot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		rightRot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		leftRot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

		frontRot.setPower(1);
		rightRot.setPower(1);
		leftRot.setPower(1);


		frontWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		rightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		leftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		waitForStart();

		double rot = 0;

		final double TICKS_PER_ROTATION = 1613;

		while (opModeIsActive()){
			double power = gamepad1.left_stick_y;

			rot += gamepad1.right_stick_x * TICKS_PER_ROTATION / 100.;

			frontWheel.setPower(gamepad1.a ? power : 0);
			rightWheel.setPower(gamepad1.b ? power : 0);
			leftWheel.setPower(gamepad1.x ? power : 0);

			frontRot.setTargetPosition((int) rot);
			leftRot.setTargetPosition((int) rot);
			rightRot.setTargetPosition((int) rot);

			telemetry.addData("rot", rot);
			telemetry.update();

			sleep(10);
		}
	}
}
