package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

@TeleOp
public class TestIMU extends LinearOpMode {
    MecanumHolonomicDriveTrain robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);
        robot.initIMU();
        this.waitForStart();
        if (this.opModeIsActive()){
            while (opModeIsActive()){

                telemetry.addData("corrective pivot", robot.correction_pivot);
                telemetry.addData("added power", robot.left_front.getPower());
               // telemetry.addData("target angle of robot", );


                robot.updateAngles();
                telemetry.addData("angle", robot.integratedAngle);


                robot.DriveWithIMU(0,0.4,0, true);
                this.sleep(3000);
               // robot.stop();

                telemetry.update();

            }
        }

    }

}

