
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class ArmExtenderTest extends LinearOpMode {

    MecanumHolonomicDriveTrain robot;

    enum ARM_STATE {
        START,
        GRAB,
        RAISE,
        TUCK
    }
    enum CLAW_STATE{

        OPEN,
        CLOSE
    }

    public final int START_POSITON = 0;
    public final int GRAB_POSITION = -1290;
    public final int RAISE_POSITION = -790;
    public final int TUCK_POSITION = -1754;
    public final double MOTOR_POWER = 0.15;
    public final double CLAW_OPEN = 0.5;
    public final double CLAW_CLOSE = 0;

    ARM_STATE arm_state = ARM_STATE.START;

    CLAW_STATE claw_state = CLAW_STATE.CLOSE;

    public void UpdatePosition() {
        switch (arm_state) {
            case START:
                robot.arm_motor.setTargetPosition(START_POSITON);
                break;
            case GRAB:
                robot.arm_motor.setTargetPosition(GRAB_POSITION);
                break;
            case RAISE:
                robot.arm_motor.setTargetPosition(RAISE_POSITION);
                break;
            case TUCK:
                robot.arm_motor.setTargetPosition(TUCK_POSITION);
        }
        switch (claw_state) {
            case OPEN:
                robot.claw.setPosition(CLAW_OPEN);
                break;
            case CLOSE:
                robot.claw.setPosition(CLAW_CLOSE);
                break;
        }
    }

    public void InitArm(){
        robot.arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm_state = ARM_STATE.START;
        UpdatePosition();
        robot.arm_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.arm_motor.setPower(MOTOR_POWER);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);
        InitArm();
        this.waitForStart();

        if(opModeIsActive()){
            robot.DriveWithIMU();
            if(gamepad1.dpad_right == true){
                arm_state = ARM_STATE.START;
            }
            if(gamepad1.dpad_left == true){
                arm_state = ARM_STATE.GRAB;
            }
            if(gamepad1.dpad_up == true) {
                arm_state = ARM_STATE.RAISE;
            }
            if (gamepad1.dpad_down == true) {
                arm_state = ARM_STATE.TUCK;
            }

            if (gamepad1.x == true) {
                claw_state = CLAW_STATE.OPEN;
            }
            if (gamepad1.b == true){
                claw_state = CLAW_STATE.CLOSE;
            }
            UpdatePosition();

            telemetry.addData("target arm position", arm_state);
            telemetry.addData("arm position", robot.arm_motor.getCurrentPosition());
            telemetry.addData("servo position", robot.claw.getPosition());
            telemetry.update();

        }

    }
}
