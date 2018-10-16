package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.core.Encoder;
import org.firstinspires.ftc.teamcode.core.Robot;

import static android.R.attr.left;
import static android.R.attr.right;
import static android.R.transition.move;

//make sure to change!
@TeleOp(name="tele2", group="test")
//@Disabled
public class TeleOpTest2 extends LinearOpMode{
    Robot robot;
    Encoder en;
    public boolean moveForwards(float speed){
        try{
            en.setBothMotorPower(speed);
        }
        catch(Throwable t){
            return false;
        }
        return true;
    }
    public boolean moveBackwards(float speed){
        try{
            en.setBothMotorPower(-speed);
        }
        catch(Throwable t){
            return false;
        }
        return true:
    }
    public boolean turnCounterclockwise(float speed){
        try{
            en.setRightMotorPower(speed);
            en.setLeftMotorPower(-speed);
        }
        catch(Throwable t){
            return false;
        }
        return true;
    }
    public boolean turnClockwise(float speed){
        try {
            en.setRightMotorPower(-speed);
            en.setLeftMotorPower(speed);
        }
        catch(Throwable t){
            return false;
        }
        return true;
    }
    public boolean quickTurn(float degree){
        return true;
    }
    public boolean pause(){
        en.setBothMotorPower(0);
        return true;
    }
    public boolean align(){
//        double margin = 0.50;
//        while(rDistanceSensor.getDistance(DistanceUnit.INCH))
        en.align();//margin is set within the method
    }
    @Override
    public void runOpMode() throws InterruptedException{
        robot = new Robot(hardwareMap);
        en = new Encoder(robot,this);
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            float speed = (gamepad1.y)? 1f : 0.5f;

            if(gamepad1.right_trigger != 0)
            {
                moveForwards(speed);
            }
            else if(gamepad1.left_trigger != 0)
            {
                moveBackwards(speed);
            }
            else if(gamepad1.left_trigger != 0 && gamepad1.right_trigger != 0){

            }
            else if(gamepad1.dpad_left)
            {
                turnCounterclockwise(speed);
            }
            else if(gamepad1.dpad_right)
            {
                turnClockwise(speed);
            }
            else {
                pause();
            }
            idle();
        }
    }
}