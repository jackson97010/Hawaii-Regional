package frc.System;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Chassis {


    //The ID of Port and checkt the MotorController ID
    private static final byte L_FrontMotor_ID = 0; 
    private static final byte L_RearMotor_ID = 1;
    private static final byte R_FrontMotor_ID = 2;
    private static final byte R_RearMotor_ID = 3;
    
    //Create VictorSPX
    private static VictorSPX  L_RearMotor, R_RearMotor;
    private static WPI_VictorSPX L_FrontMotor, R_FrontMotor;
    
    //Create DifferentailDrive
    private static DifferentialDrive m_Robot;
    
    //whether Arcade
    private static boolean isSlowed;
    public static void Init(){
        L_FrontMotor = new WPI_VictorSPX(L_FrontMotor_ID);
        L_RearMotor = new VictorSPX(L_RearMotor_ID);
        R_FrontMotor = new WPI_VictorSPX(R_FrontMotor_ID);
        R_RearMotor = new VictorSPX(R_RearMotor_ID);
        
	//DifferentialDrive is left or right
        m_Robot = new DifferentialDrive(L_FrontMotor, R_FrontMotor);
        
	//Inverted or not
        L_FrontMotor.setInverted(false);
	    L_RearMotor.setInverted(false);
	    R_FrontMotor.setInverted(false);
        R_RearMotor.setInverted(false);

        L_RearMotor.setNeutralMode(NeutralMode.Brake);
        L_FrontMotor.setNeutralMode(NeutralMode.Brake);
        R_RearMotor.setNeutralMode(NeutralMode.Brake);
        R_FrontMotor.setNeutralMode(NeutralMode.Brake);

        L_RearMotor.set(ControlMode.Follower, L_FrontMotor_ID);
	    R_RearMotor.set(ControlMode.Follower, R_FrontMotor_ID);  
    }

    public static void PeriodicInit(){
        isSlowed = true;
        SmartDashboard.setDefaultBoolean("Chassis",true);
    }
    
    
    public static void tankDrive(){
        
        //should task whether it can work or not.要使用需先去Stcik改狀態
	    if(Stick.a){
                isSlowed =! isSlowed;
        }
        if(isSlowed){
            SmartDashboard.putNumber("Chassis", 1);
		    m_Robot.tankDrive(Stick.ly/2, Stick.ry/2);

        }
        else {
            SmartDashboard.putNumber("Chassis", 2);
            m_Robot.tankDrive(Stick.ly*0.95, Stick.ry*0.95);
        }

        
    }        
}



 
    


    




    
    
    
