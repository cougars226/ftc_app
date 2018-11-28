package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.teamcode.core.Encoder;

@Autonomous(name="Crater Auto", group="Robot")
public class CraterAuto extends LinearOpMode {

    public DcMotor r_motor;
    public DcMotor l_motor;



    public DcMotor climb;
    public DistanceSensor left_distance;
    public DistanceSensor right_distance;

    Encoder en;

    public static final int SPEED = 1;

    private void initMap() {
        r_motor = hardwareMap.dcMotor.get("Right Drive Motor");
        l_motor = hardwareMap.dcMotor.get("Left Drive Motor");
        climb = hardwareMap.dcMotor.get("Lifting Motor");
        left_distance = DistanceSensor.getLeft(hardwareMap);
        right_distance = DistanceSensor.getRight(hardwareMap);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        en = new Encoder(this);
        initMap();
        telemetry.addData("Status", "Ready to run");
        telemetry.update();
        waitForStart();
//        en.lower(climb, SPEED);


        //to test pixy


        boolean[] minerals = new MineralReader(hardwareMap).read();
        telemetry.addData("Guess", "[" + minerals[0] + "," + minerals[1] + "," + minerals[2] + "]");
        telemetry.update();

        if(minerals[0] || minerals[2]) {
            en.encoderDrive(l_motor, r_motor, SPEED, minerals[0] ? -0.1 : 7, minerals[0] ? 7 : -0.1);
        }
        int dist = minerals[1] ? 24 : 30;//move forward more if sideways
        en.encoderDrive(l_motor, r_motor, SPEED,dist);
        en.encoderDrive(l_motor, r_motor, SPEED,-(dist-2));
        int turn;
        if(minerals[0]) turn = 2;
        else if(minerals[1]) turn = 10;
        else turn = 15;

        en.encoderDrive(l_motor,r_motor,SPEED,-turn,turn);
        en.align(l_motor, r_motor, left_distance, right_distance);
        en.encoderDrive(l_motor, r_motor, SPEED,40);//drive to wall
        en.encoderDrive(l_motor, r_motor, SPEED,-20,20);//turn to claim
//        en.align(l_motor, r_motor, left_distance, right_distance);//may be needed, may waste time
        en.encoderDrive(l_motor, r_motor, SPEED,70);
        en.claim();
        en.encoderDrive(l_motor, r_motor, SPEED,-144);
    }
}