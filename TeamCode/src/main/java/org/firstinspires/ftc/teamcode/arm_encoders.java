package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class arm_encoders extends LinearOpMode {
    MecanumHolonomicDriveTrain robot;



    @Override
    public void runOpMode() throws InterruptedException {
        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);

        robot.arm_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        this.waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("arm encoder ticks:", robot.arm_motor.getCurrentPosition());
            telemetry.update();
        }
    }
}
