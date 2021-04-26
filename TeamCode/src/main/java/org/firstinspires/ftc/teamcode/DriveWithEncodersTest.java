package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class DriveWithEncodersTest extends LinearOpMode {

    MecanumHolonomicDriveTrain robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);
        robot.left_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.right_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);


        robot.left_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.waitForStart();

        while (opModeIsActive()) {
        telemetry.addData("left back encoder ticks:", robot.left_back.getCurrentPosition());
        telemetry.addData("right back encoder ticks:", robot.right_back.getCurrentPosition());
        telemetry.addData("left front encoder ticks:", robot.left_front.getCurrentPosition());
        telemetry.addData("right front encoder ticks", robot.right_front.getCurrentPosition());
        telemetry.update();
         }
    }

}
