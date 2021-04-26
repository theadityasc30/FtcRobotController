package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestOpMode extends LinearOpMode {
    MecanumHolonomicDriveTrain robot;

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);

        this.waitForStart();
        if (this.opModeIsActive()){
            while (opModeIsActive()){
                //
            }
        }

    }
}
