package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class MecanumHolonomicDrive extends LinearOpMode {
    MecanumHolonomicDriveTrain bobot;

    @Override
    public void runOpMode() throws InterruptedException {

        bobot = new MecanumHolonomicDriveTrain(this.hardwareMap);

        this.waitForStart();

        while(opModeIsActive()){
            bobot.Drive(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
        }

    }
}

