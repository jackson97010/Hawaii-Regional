package frc.System;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Stick{
    private static byte Port = 0;
    private static XboxController stick;
    public static boolean a, b, x, y, lb, rb,mushroom,lb_Released;
    public static double lx,ly,rx,ry,lt,rt;
    public static void Init(){
        stick = new XboxController(Port);
        System.out.println("Stick Initialized");
    }
    public static void telop(){
        //buttom
        a = stick.getRawButtonPressed(1);
        b = stick.getRawButtonPressed(2);
        x = stick.getRawButtonPressed(3);
        lb = stick.getRawButtonPressed(5);
        lb_Released = stick.getRawButtonReleased(5);     
        rb = stick.getRawButtonPressed(6);
        mushroom= stick.getRawButtonPressed(10);

        //AXIS
        lx = stick.getX(Hand.kLeft);
        rx = stick.getX(Hand.kRight);
        ly = -stick.getY(Hand.kLeft);
        ry = -stick.getY(Hand.kRight);
        lt = stick.getTriggerAxis(Hand.kLeft);
        rt = stick.getTriggerAxis(Hand.kRight);
    }
   
}
