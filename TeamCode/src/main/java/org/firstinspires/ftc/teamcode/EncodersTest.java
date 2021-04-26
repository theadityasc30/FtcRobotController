package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class EncodersTest extends LinearOpMode {
    MecanumHolonomicDriveTrain robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);
        this.waitForStart();
        robot.DriveWithEncoders(0.5,11177);

        while(opModeIsActive()){
            robot.left_front.isBusy();
        }
        if (robot.left_front.isBusy()){
            this.sleep(10);
        }
    }
}
