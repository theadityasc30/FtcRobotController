package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class AdityaSimpleOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        this.waitForStart();
        telemetry.addData(">>", "Press start to continue");
        telemetry.update();

        if (this.opModeIsActive()) {
            while (opModeIsActive()) {
                telemetry.addData("Runtime", this.getRuntime());
                telemetry.update();
            }
        }
    }
}
