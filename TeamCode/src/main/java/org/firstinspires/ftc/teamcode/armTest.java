package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class armTest extends LinearOpMode {

    MecanumHolonomicDriveTrain robot;

    enum ARM_STATE {
        START,
        GRAB,
        RAISE,
    }

    public final int START_POSITION = 0;
    public final int GRAB_POSITION = -1290;
    public final int RAISE_POSITION = -790;

    ARM_STATE arm_state = ARM_STATE.START;

    public void initArm(){

        robot.arm_motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.arm_motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.arm_motor.setTargetPosition(START_POSITION);
    }
    public void updateARMPosition(){
        switch (arm_state){
            case START:
                robot.arm_motor.setTargetPosition(START_POSITION);
                break;
            case GRAB:
                robot.arm_motor.setTargetPosition(GRAB_POSITION);
                break;
            case RAISE:
                robot.arm_motor.setTargetPosition(RAISE_POSITION);
                break;
        }
    }

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new MecanumHolonomicDriveTrain(this.hardwareMap);
        initArm();
        this.waitForStart();
        while (opModeIsActive()){
            if (gamepad1.x == true){
                ARM_STATE arm_state = ARM_STATE.START;
            }
            if (gamepad1.y == true){
                ARM_STATE grab_state = ARM_STATE.GRAB;
            }
            if(gamepad1.b){
                ARM_STATE raise_state = ARM_STATE.RAISE;

            }
            updateARMPosition();
        }
    }
}
