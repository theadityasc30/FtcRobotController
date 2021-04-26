package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


public class MecanumHolonomicDriveTrain {
    DcMotor left_front, right_front, left_back, right_back, shooter_left, shooter_right, harvester_motor, arm_motor;

    Servo claw;

    BNO055IMU imu;
    Orientation angles;
    Acceleration gravity;
    double previousAngle = 0;
    double integratedAngle = 0;
    double correction_pivot = 0;

    boolean strafeFlag = false;

    private HardwareMap hardware_map;

    static String LEFT_FRONT = "left_front";
    static String RIGHT_FRONT = "right_front";
    static String LEFT_BACK = "left_back";
    static String RIGHT_BACK = "right_back";
    static String SHOOTER_LEFT = "shooter_left";
    static String SHOOTER_RIGHT = "shooter_right";
    static String HARVESTER_MOTOR = "harvester_motor";
    static String ARM_MOTOR = "arm_motor";
    static String CLAW = "claw";
    public MecanumHolonomicDriveTrain(HardwareMap original_hardware_map) {
        hardware_map = original_hardware_map;
        left_front = hardware_map.get(DcMotor.class, "left_front");
        right_front = hardware_map.get(DcMotor.class, "right_front");
        left_back = hardware_map.get(DcMotor.class, "left_back");
        right_back = hardware_map.get(DcMotor.class, "right_back");
        shooter_left = hardware_map.get(DcMotor.class, "shooter_left");
        shooter_right = hardware_map.get(DcMotor.class, "shooter_right");
        harvester_motor = hardware_map.get(DcMotor.class, "harvester_motor");
        arm_motor = hardware_map.get(DcMotor.class, "arm_motor");
        claw = hardware_map.get(Servo.class, "claw");

        left_front.setDirection(DcMotorSimple.Direction.FORWARD);
        right_front.setDirection(DcMotorSimple.Direction.REVERSE);
        left_back.setDirection(DcMotorSimple.Direction.FORWARD);
        right_back.setDirection(DcMotorSimple.Direction.REVERSE);
        shooter_left.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter_right.setDirection(DcMotorSimple.Direction.REVERSE);
        harvester_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        arm_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        left_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        arm_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void Drive(double vertical, double horizontal, double pivot) {
        double left_front_power, right_front_power, left_back_power, right_back_power;

        left_front_power = vertical + horizontal - pivot;
        right_front_power = vertical - horizontal + pivot;
        left_back_power = vertical - horizontal - pivot;
        right_back_power = vertical + horizontal + pivot;

        left_front.setPower(left_front_power);
        right_front.setPower(right_front_power);
        left_back.setPower(left_back_power);
        right_back.setPower(right_back_power);
    }

    public void Shoot(double leftPower, double rightPower) {
        shooter_right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooter_left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shooter_right.setPower(rightPower);
        shooter_left.setPower(leftPower);

    }

    public void DriveWithEncoders(double power, int distance) {
        left_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_front.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_back.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        left_front.setTargetPosition(distance);
        left_back.setTargetPosition(distance);
        right_front.setTargetPosition(distance);
        right_back.setTargetPosition(distance);

        left_front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_front.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right_back.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        left_front.setPower(power);
        left_back.setPower(power);
        right_front.setPower(power);
        right_back.setPower(power);

    }

    public void stopRight() {
        right_front.setPower(0);
        right_back.setPower(0);
    }
    public void stopLeft() {
        left_front.setPower(0);
        left_back.setPower(0);
    }

    public void Harvest(double power) {
        harvester_motor.setPower(power);
    }
    public void initArm(){

    }
    public void initIMU() {
        // Set up the parameters with which we will use our IMU. Note that integration
        // algorithm here just reports accelerations to the logcat log; it doesn't actually
        // provide positional information.
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = false;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardware_map.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    public void updateAngles() {
        float currAngle;
        double deltaAngle;


        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        currAngle = angles.firstAngle;
        deltaAngle = currAngle - previousAngle;
        if (deltaAngle < -180) {
            // Went from +180 to -180 (direction).
            deltaAngle = deltaAngle + 360;
        } else if (deltaAngle > 180) {
            // Went from -180 to +180 (direction).
            deltaAngle = deltaAngle - 360;
        }
        integratedAngle = integratedAngle + deltaAngle;
        previousAngle = currAngle;
    }

    public void resetAngles() {
        integratedAngle = 0;
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        previousAngle = angles.firstAngle;
    }

    public double getCurrentAngle() {
        return integratedAngle;
    }

    public double getPreviousAngle() {
        return previousAngle;

    }

    public void DriveWithIMU(double horizontal, double vertical, double pivot, boolean useAutoCorrect) {
        double left_front_power, right_front_power, left_back_power, right_back_power;

        if (useAutoCorrect == true) {
            // are the vertical or pivot components non zero?
            if (Math.abs(vertical) > 0.05 || Math.abs(pivot) > 0.05) {
                // don't strafe.
                correction_pivot = 0;
            } else {
                if (Math.abs(horizontal) > 0.1) {
                    if (strafeFlag == false) {
                        strafeFlag = true;
                        resetAngles();
                    }
                    correction_pivot = -1 * integratedAngle;
                } else {
                    if (strafeFlag == true) {
                        strafeFlag = false;
                    }
                    correction_pivot = 0;
                }
            }
        } else {
            correction_pivot = 0;
        }

        // adjust the pivot component.
        pivot = pivot + correction_pivot;


        // Set motor powers
        left_front_power = vertical + horizontal - pivot;
        right_front_power = vertical - horizontal + pivot;
        left_back_power = vertical - horizontal - pivot;
        right_back_power = vertical + horizontal + pivot;

        left_front.setPower(left_front_power);
        right_front.setPower(right_front_power);
        left_back.setPower(left_back_power);
        right_back.setPower(right_back_power);

    }

}

