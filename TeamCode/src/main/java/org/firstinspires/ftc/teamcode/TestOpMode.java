package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Test")
public class TestOpMode extends LinearOpMode {
	@Override
	public void runOpMode() throws InterruptedException {
		DcMotor frontRightWheel = hardwareMap.get(DcMotor.class, "front_right_wheel");
		DcMotor frontRightRot = hardwareMap.get(DcMotor.class, "front_right_rot");
		DcMotor backRightWheel = hardwareMap.get(DcMotor.class, "back_right_wheel");
		DcMotor backRightRot = hardwareMap.get(DcMotor.class, "back_right_rot");
		DcMotor frontLeftWheel = hardwareMap.get(DcMotor.class, "front_left_wheel");
		DcMotor frontLeftRot = hardwareMap.get(DcMotor.class, "front_left_rot");
		DcMotor backLeftWheel = hardwareMap.get(DcMotor.class, "back_left_wheel");
		DcMotor backLeftRot = hardwareMap.get(DcMotor.class, "back_left_rot");

		frontRightRot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		backRightRot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		frontLeftRot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
		backLeftRot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


		frontRightRot.setTargetPosition(0);
		backRightRot.setTargetPosition(0);
		frontLeftRot.setTargetPosition(0);
		backLeftRot.setTargetPosition(0);

		frontRightRot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		backRightRot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		frontLeftRot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
		backLeftRot.setMode(DcMotor.RunMode.RUN_TO_POSITION);

		frontRightRot.setPower(1);
		backRightRot.setPower(1);
		frontLeftRot.setPower(1);
		backLeftRot.setPower(1);


		frontRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		backRightWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		frontLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		backLeftWheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		waitForStart();

		double rot = 0;

		final double TICKS_PER_ROTATION = 1613;

		while (opModeIsActive()){
			double power = gamepad1.left_stick_y;

			rot += gamepad1.right_stick_x * TICKS_PER_ROTATION / 100.;

			if (gamepad1.a)
				frontRightWheel.setPower(power);
			if (gamepad1.b)
				backRightWheel.setPower(power);
			if (gamepad1.x)
				frontLeftWheel.setPower(power);
			if (gamepad1.y)
				backLeftWheel.setPower(power);

			frontRightRot.setTargetPosition((int) rot);
			frontLeftRot.setTargetPosition((int) rot);
			backRightRot.setTargetPosition((int) rot);
			backLeftRot.setTargetPosition((int) rot);

			telemetry.addData("rot", rot);
			telemetry.update();

			sleep(10);
		}
	}
}
