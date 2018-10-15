package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.carson.Encoder;
import org.firstinspires.ftc.teamcode.carson.Robot;

@Autonomous(name="Auto Test", group="Robot")

public class ClaimZoneAuto extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    Robot robot;
    Encoder en;

    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);
        en = new Encoder(robot,this);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        ElapsedTime eTime = new ElapsedTime();
        while (eTime.time() <= 3) {
            robot.climb.setPower(-1);
        }
        robot.climb.setPower(0);
        robot.left_sensey.enableLed(true);
        robot.right_sensey.enableLed(true);

        if(!en.leftInBounds() || !en.rightInBounds()){
            if(!en.leftInBounds()){
                en.setLeftMotorPower(1);
                while(!en.leftInBounds()){}
                en.setLeftMotorPower(0);
            }
            en.setRightMotorPower(1);
            while(!en.rightInBounds()){}
            en.setRightMotorPower(0);
        }

        boolean[] minerals = new boolean[]{true,false,false};

        robot.lb_servo.setPosition(0);
        robot.rb_servo.setPosition(0);

        if (!minerals[1]) {
            en.encoderDrive(1, minerals[0] ? -5 : 5, minerals[2] ? -5 : 5, 1);
            en.encoderDrive(1, 5, 5 ,  1);
            en.align();
            //drive forward
            en.encoderDrive(1,5,5,1);
            //turn
            en.encoderDrive(1,5,-5,1);//TODO change betweening
            //drive to claim zone
            en.setBothMotorPower(1);
            while(!en.leftInBounds()){
                Thread.sleep(10);
            }
            en.setBothMotorPower(0);
            en.claim();
            en.align();
        } else {
            en.encoderDrive(1, 5, 5 ,  1);
            en.claim();
            en.encoderDrive(1,1,-1,1);
            en.align();
        }
        en.encoderDrive(10000,-288,-288,9999);
    }






}
