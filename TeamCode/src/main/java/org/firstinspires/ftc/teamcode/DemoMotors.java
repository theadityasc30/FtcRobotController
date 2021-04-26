package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class DemoMotors extends LinearOpMode {
    static final double TARGET_POWER = 0.25;
    static final int TARGET_POSITION = 1440;
    @Override
    public void runOpMode() throws InterruptedException {
        // get reference to motor.
        DcMotor dcMotor = hardwareMap.get(DcMotor.class, "testMotor");

        // wait for start command from driver.
        waitForStart();

        int startPos = dcMotor.getCurrentPosition();
        int currPos = 0;
        dcMotor.setTargetPosition(TARGET_POSITION);
        dcMotor.setMode((DcMotor.RunMode.RUN_TO_POSITION));
        dcMotor.setPower(TARGET_POWER);
        while(opModeIsActive()) {
            currPos = dcMotor.getCurrentPosition();
            dcMotor.isBusy();
            telemetry.addData("currPos", currPos);
            telemetry.addData("dist travelled", currPos - startPos);
            telemetry.update();
        }
    }
}
