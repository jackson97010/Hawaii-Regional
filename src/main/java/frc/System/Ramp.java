package frc.System;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Ramp {
    //Ramp馬達的ID
    private static final byte Port_RampMotor = 4;
    private static VictorSPX RampMotor;
    //初始化
    public static void Init(){
        RampMotor = new VictorSPX(Port_RampMotor);
        RampMotor.setNeutralMode(NeutralMode.Brake);
        RampMotor.setInverted(false);
    }
    //手動時間
    public static void Periodic(){
        SmartDashboard.putBoolean("Ramp initialized", true);
        if(Stick.lt>0.01){
            
            RampMotor.set(ControlMode.PercentOutput, 0.6);
        }
        else if (Stick.rt > 0.01){
            RampMotor.set(ControlMode.PercentOutput, -0.6);
        }
        else {
            RampMotor.set(ControlMode.PercentOutput, 0);
        }
    }

}