package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleOp_2020_2021_Single_Control extends LinearOpMode {
    MecanumHolonomicDriveTrain robot;
    double shooterPower = 0.4;
    int iteration = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);

        this.waitForStart();

        boolean btnY = false;
        boolean btnA = false;
        boolean btnBack = false;
        boolean reverseDrive = false;

        while(opModeIsActive()){
            //Drive
            if (gamepad1.back == true && btnBack == false){
                btnBack = true;
                if(reverseDrive == false){
                    reverseDrive = true;
                }
                else if(reverseDrive == true){
                    reverseDrive = false;
                }

            }
            else if (gamepad1.back == false && btnBack == true){
                btnBack = false;
            }

            if (reverseDrive == false){
                robot.Drive(gamepad1.left_stick_y,-gamepad1.left_stick_x,gamepad1.right_stick_x);
//                telemetry.addData("direction", "shooter");
            }
            else if (reverseDrive == true){
                robot.Drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
//                telemetry.addData("direction", "harvester");
            }

            //Harvester
            if (gamepad1.dpad_up == true) {

                robot.Harvest(-1);
            }
            else if (gamepad1.dpad_down == true){

                robot.Harvest(1);
            }
            else {
                robot.Harvest(0);
            }
            //Shooter
            if (gamepad1.right_trigger >= 0.10){
                robot.Shoot(shooterPower, shooterPower);
                //telemetry.addData("actual shooter power", robot.shooter_left.getPower());

            }
            else if (gamepad1.left_trigger >= 0.10) {
                robot.Shoot(0, 0);
                //telemetry.addData("actual shooter power", robot.shooter_left.getPower());

            }

            if (gamepad1.y == true && btnY == false){
                btnY = true;
                shooterPower += 0.01;
                robot.Shoot(shooterPower, shooterPower);


                iteration++;
            }
            else if (gamepad1.y == false && btnY == true){
                btnY = false;
            }

            if (gamepad1.a == true && btnA == false){
                btnA = true;
                shooterPower -= 0.01;
                robot.Shoot(shooterPower, shooterPower);
                //telemetry.addData("shooter power", shooterPower);
                //telemetry.addData("actual shooter power", robot.shooter_left.getPower());
            }
            else if (gamepad1.a == false && btnA == true) {
                btnA = false;
            }

            telemetry.addData("is reversed", reverseDrive);
            telemetry.addData("shooter power variable", shooterPower);
            telemetry.addData("actual shooter power", robot.shooter_left.getPower());
            telemetry.addData("iteration", iteration);
            telemetry.update();
        }

    }
}
