package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class Auto01 extends LinearOpMode {
    MecanumHolonomicDriveTrain robot;
    double shooterPower = 0.5;
    int iteration = 0;
    @Override
    public void runOpMode() throws InterruptedException {

        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);

        this.waitForStart();

        robot.DriveWithEncoders(0.5, 6520);
        this.sleep(5000);
        robot.left_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.left_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_back.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.right_front.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Drive(0, 0.4, 0);
        this.sleep(5000);
        robot.Drive(0,0,0);
        robot.Shoot(0.45, 0.45);
        this.sleep(2000);
        robot.Harvest(-1.0);
        this.sleep(3000);
        robot.DriveWithEncoders(0.2, 2000);
      /*  boolean btnY = false;
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
            if (gamepad2.dpad_up == true) {

                robot.Harvest(-1);
            }
            else if (gamepad2.dpad_down == true){

                robot.Harvest(1);
            }
            else {
                robot.Harvest(0);
            }
            //Shooter
            if (gamepad2.right_trigger >= 0.10){
                robot.Shoot(shooterPower, shooterPower);
                //telemetry.addData("actual shooter power", robot.shooter_left.getPower());

            }
            else if (gamepad2.left_trigger >= 0.10) {
                robot.Shoot(0, 0);
                //telemetry.addData("actual shooter power", robot.shooter_left.getPower());

            }

            if (gamepad2.y == true && btnY == false){
                btnY = true;
                shooterPower += 0.05;
                robot.Shoot(shooterPower, shooterPower);


                iteration++;
            }
            else if (gamepad2.y == false && btnY == true){
                btnY = false;
            }

            if (gamepad2.a == true && btnA == false){
                btnA = true;
                shooterPower -= 0.05;
                robot.Shoot(shooterPower, shooterPower);
                //telemetry.addData("shooter power", shooterPower);
                //telemetry.addData("actual shooter power", robot.shooter_left.getPower());
            }
            else if (gamepad2.a == false && btnA == true) {
                btnA = false;
            }

            telemetry.addData("is reversed", reverseDrive);
            telemetry.addData("shooter power variable", shooterPower);
            telemetry.addData("actual shooter power", robot.shooter_left.getPower());
            telemetry.addData("iteration", iteration);
            telemetry.update();
        }

    }

       */
    }
}
