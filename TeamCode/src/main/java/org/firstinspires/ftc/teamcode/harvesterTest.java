/*package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class harvesterTest extends LinearOpMode {

    MecanumHolonomicDriveTrain robot;


    @Override
    public void runOpMode() throws InterruptedException {

        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);
        this.waitForStart();

        if (this.opModeIsActive()) {
            while (opModeIsActive()) {

                if (gamepad1.x == true) {
                    if (robot.shooter_left.isBusy() == true && robot.shooter_right.isBusy() == true){
                        robot.Shoot(0,0);
                    }
                    else{
                        robot.Shoot(1.0, 1.0);
                    }

                }

               if(gamepad1.b == true) {
                    robot.Shoot(0, 0);
                }

                robot.Drive(gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
               
                if (gamepad1.a == true){
                    robot.Harvester(- 0.75);

                }
                if (gamepad1.y == true){
                    robot.Harvester(0);
                }

            }
        }

    }
}
*/