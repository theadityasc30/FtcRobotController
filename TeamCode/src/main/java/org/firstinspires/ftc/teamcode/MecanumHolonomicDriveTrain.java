package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumHolonomicDriveTrain {
    DcMotor left_front, right_front, left_back, right_back, shooter_left, shooter_right;

    private HardwareMap hardware_map;

    static String LEFT_FRONT = "left_front";
    static String RIGHT_FRONT = "right_front";
    static String LEFT_BACK = "left_back";
    static String RIGHT_BACK = "right_back";
    static String SHOOTER_LEFT = "shooter_left";
    static String SHOOTER_RIGHT = "shooter_right";

    public MecanumHolonomicDriveTrain(HardwareMap original_hardware_map)
    {
        hardware_map = original_hardware_map;
        left_front = hardware_map.get(DcMotor.class, "left_front");
        right_front = hardware_map.get(DcMotor.class, "right_front");
        left_back = hardware_map.get(DcMotor.class, "left_back");
        right_back = hardware_map.get(DcMotor.class, "right_back");
        shooter_left = hardware_map.get(DcMotor.class, "shooter_left");
        shooter_right = hardware_map.get(DcMotor.class, "shooter_right");


        left_front.setDirection(DcMotorSimple.Direction.FORWARD);
        right_front.setDirection(DcMotorSimple.Direction.REVERSE);
        left_back.setDirection(DcMotorSimple.Direction.FORWARD);
        right_back.setDirection(DcMotorSimple.Direction.REVERSE);
        shooter_left.setDirection(DcMotorSimple.Direction.FORWARD);
        shooter_right.setDirection(DcMotorSimple.Direction.REVERSE);

        left_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_front.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_back.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shooter_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void Drive(double vertical, double horizontal, double pivot)
    {
        double left_front_power, right_front_power, left_back_power, right_back_power;

        left_front_power = vertical - horizontal - pivot;
        right_front_power = vertical + horizontal + pivot;
        left_back_power = vertical + horizontal - pivot;
        right_back_power = vertical - horizontal + pivot;

        left_front.setPower(left_front_power);
        right_front.setPower(right_front_power);
        left_back.setPower(left_back_power);
        right_back.setPower(right_back_power);
    }

    public void Shoot(double leftPower, double rightPower){

        shooter_right.setPower(rightPower);
        shooter_left.setPower(leftPower);

    }

}
