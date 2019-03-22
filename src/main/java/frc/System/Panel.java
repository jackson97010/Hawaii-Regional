package frc.System;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.System.Stick;
public class Panel{
    private static boolean solenoid,restore,defand,autoed ;
    private static byte position;
    private static long BarAuto, BarLay, Bar45, BarLay_b, Bar45_b;
    private static long BarUnder, BarUp; 

    
    //Initialize
    public static void Init(){
        


    }
    //Auto

    public static void autoInit(){
        position = 0;
        autoed = false ;
        solenoid = false;
        restore = false;
        defand = false;
    }


    //Panel in automous Mode
    public static void autoPeriodic()  {
        
        // PanelCoderRate = PanelCoder.get();
        // Rate = Rate - LastRate;
        // Rate = Math.abs(Rate);        
        
        //Solenoid output
        if(Stick.x && position == 1){
            solenoid =! solenoid;
        }
            if (solenoid){
                SmartDashboard.putBoolean("Solenoid", true);
            }
            else {
                SmartDashboard.putBoolean("Solenoid", false);
            }

        //Auto PanelBar
        if(autoed == false){
            SmartDashboard.putString("PanelBar", "autoModed");
            if(Rate   ){

            }
            autoed = true;
        }

        

        //PanelBar position
        if(Stick.lb){
            position += 1;
            solenoid = false;
            defand = false;
            restore = false;
        }

        //Change the position

        switch (position){
            case 0:
                
                SmartDashboard.putString("Donothing", "true");
                break;
            case 1:
                SmartDashboard.putString("PanelBar", "lay");
                SmartDashboard.putString("Donothing", "false");

                break;
            case 2:
                SmartDashboard.putString("PanelBar", "Take");
                SmartDashboard.putString("Donothing", "false");
                break;
            case 3:
                position = 1;
                break;
        }

        //Panel bar defand
        if(Stick.b){
            defand =true;
            position = 0 ;
        }
            if(defand){
                SmartDashboard.putString("PanelBar", "defand");
            }

        //Panel Bar back down to the board
        if(Stick.rb){
            restore = true;
        }
            if(restore ){
                SmartDashboard.putString("PanelBar", "back");
            }
        SmartDashboard.putNumber("automode", 1);
        
    }
    public static void teleoP(){

    }
}
