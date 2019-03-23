package frc.System;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.System.Stick;

public class Panel{


    private static boolean solenoid,restore,defand,autoed ;
    private static byte position;

    //variables to the position of bar.
    private static double BarAuto, BarLay, BarTake, BarLay_b, BarTake_b;
    private static double BarUnder, BarUp; 
    private static double BarDegree;
    
  

    private static byte Y_Panel_Encoder=2,B_Panel_Encoder=3;
    //The ID of Panel Motor
    private static final byte Port_PanelMotor = 5;
    private static VictorSPX PanelMotor;

    //Panel Solenoid
    private static Solenoid left,right;
    private static final byte Port_LeftSolenoid = 0,Port_RightSolenoid=1;
    
    //PanelCoder
    private static Encoder PanelCoder;




    //Initialize
    public static void Init(){
        
        //Encoder
        PanelCoder = new Encoder(Y_Panel_Encoder, B_Panel_Encoder, true);
        left = new Solenoid(Port_LeftSolenoid);
        right = new Solenoid(Port_RightSolenoid);
        
        PanelMotor = new VictorSPX(Port_PanelMotor);
        PanelMotor.setInverted(false);
        
        //set NeutralMode
        PanelMotor.setNeutralMode(NeutralMode.Brake);


        // The degree of position of the bar.
        BarLay_b = 40;
        BarLay = 57;

        BarTake_b = 20;
        BarTake  = 13;

        BarUp = 0;
        BarUnder = -13;
    }

    public static void autoInit(){
        
        PanelCoder.reset();


        BarDegree = 0;
        position = 0;
        autoed = false ;
        solenoid = false;
        restore = false;
        defand = false;


      

    }


    //Panel in automous Mode
    public static void autoPeriodic()  {

        //BarDegree
        BarDegree = PanelCoder.getDistance();
       
        
        //Solenoid output
        if(Stick.x && position == 1){
            solenoid =! solenoid;
        }
            if (solenoid){
                left.set(true);
                right.set(true);
                SmartDashboard.putBoolean("Solenoid", true);
            }
            else {
                left.set(false);
                right.set(false);
                SmartDashboard.putBoolean("Solenoid", false);
            }

        //Auto PanelBar
        if(autoed == false){
            SmartDashboard.putString("PanelBar", "autoModed");
            //0.3 is the power to down, and also 0.1 is the value of kp
            PanelMotor.set(ControlMode.PercentOutput, 0.3+0.1*(BarAuto-BarDegree));
        }

        

        //the position of the bar.
        if(Stick.lb){
            
            //the value of position wil add one.
            position += 1;

            //whether solenoids are supposed to be out or not.
            solenoid = false;
            
            //whether the bar is on defand or not.
            
            defand = false;
            
            //whether restore or not.
            restore = false;

            //the variable to automous that the bar should hang there and not down.
            autoed = true;

        }

        //Change the position

        switch (position){
            case 0:
                // the barmotor does nothing in here.   
                SmartDashboard.putString("Donothing", "true");
                break;
                
            case 1:
                SmartDashboard.putString("PanelBar", "lay");
                SmartDashboard.putString("Donothing", "false");
                
                if(BarDegree < BarLay_b){
                    PanelMotor.set(ControlMode.PercentOutput, 0.25);
                } else {
                    position ++;
                }
                    
                break;

            case 2:
                
                PanelMotor.set(ControlMode.PercentOutput, (BarLay-BarDegree)*0.05);

                SmartDashboard.putString("PanelBar", "Take");
                SmartDashboard.putString("Donothing", "false");
                break;
            case 3:
                if(BarDegree > BarTake_b){
                    PanelMotor.set(ControlMode.PercentOutput, -0.4 );
                } else {
                    position++;
                } 
                break;
            
            case 4:
                
                PanelMotor.set(ControlMode.PercentOutput,-( BarDegree-BarTake) * 0.03);
                
                break;
            
            case 5:
                position = 1;
                break;
        }

        //Panel bar defand
        if(Stick.b){
            defand =true;
            position = 0 ;
        }
            if(defand){
                PanelMotor.set(ControlMode.PercentOutput, (BarUp - BarDegree) * 0.03);
                SmartDashboard.putString("PanelBar", "defand");
            }

        //Panel Bar back down to the board
        if(Stick.rb){
            restore = true;
        }
            if(restore){
                PanelMotor.set(ControlMode.PercentOutput,  (BarDegree-BarUnder)*0.08); 
                SmartDashboard.putString("PanelBar", "back");
            }
        SmartDashboard.putNumber("automode", 1);
        
    }
    public static void teleoP(){
        BarDegree = PanelCoder.getDistance();
       
        
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
        //the position of the bar.
        if(Stick.lb){
            
            //the value of position wil add one.
            position += 1;

            //whether solenoids are supposed to be out or not.
            solenoid = false;
            
            //whether the bar is on defand or not.
            
            defand = false;
        
            restore = false;

            //the variable to automous that the bar should hang there and not down.
            
            autoed = true;

        }

        //Change the position

        switch (position){

            // the BarMotor does nothing in here.
            case 0:   
                SmartDashboard.putString("Donothing", "true");
                break;
            
            //This is the first period that this bar will dwon
            case 1:
                SmartDashboard.putString("PanelBar", "Lay");
                if(BarDegree < BarLay_b){
                    PanelMotor.set(ControlMode.PercentOutput, 0.25);
                } else {
                    position ++;
                }
                break;
            
            //This is the second period that this bar will down
            case 2:
                
                PanelMotor.set(ControlMode.PercentOutput, (BarLay-BarDegree)*0.05);
                SmartDashboard.putString("PanelBar", "Take");
                break;

            //This is the first period that this bar will take the hatchpanel    
            case 3:
                if(BarDegree > BarTake_b){
                    PanelMotor.set(ControlMode.PercentOutput, -0.4 );
                } else {
                    position++;
                } 
                break;
            
            //This is the second period that this bar will take the hatchpanel
            case 4:
                
                PanelMotor.set(ControlMode.PercentOutput,-( BarDegree-BarTake) * 0.03);    
                break;
            
            //HatchPanel Bar sits on the aluminum
            case 5:
                position = 1;
                break;
        }

        //Panel bar defand
        if(Stick.b){
            defand =true;
            position = 0 ;
        }
            if(defand){
                PanelMotor.set(ControlMode.PercentOutput, (BarUp - BarDegree) * 0.03);
                SmartDashboard.putString("PanelBar", "Defand");
            }

        //Panel Bar back down to the board
        if(Stick.rb){
            restore = true;
        }
            if(restore){
                PanelMotor.set(ControlMode.PercentOutput,  (BarDegree-BarUnder)*0.08);

                SmartDashboard.putString("PanelBar", "Back");
            }
        
    }
}

