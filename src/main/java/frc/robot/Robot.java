package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import frc.System.Chassis;

import frc.System.Panel;
import frc.System.Ramp;
import frc.System.Stick;


public class Robot extends TimedRobot {
  private Compressor com;
 
  private Timer timer;
  @Override
  public void robotInit() {
    CameraServer.getInstance().startAutomaticCapture("Ground",0);
    CameraServer.getInstance().startAutomaticCapture("Front", 1);
    CameraServer.getInstance().startAutomaticCapture("Defand",2);

    Chassis.Init();
    Panel.Init();
    Stick.Init();
    Ramp.Init();

    com = new Compressor();
    timer = new Timer();
  }

  @Override
  public void autonomousInit() {
    Chassis.PeriodicInit();
    Panel.autoInit();
    timer.reset();
    timer.start();
    
    if(timer.get() < 15.0){
      com.start();
    }
    else {
      com.stop();
    }
  }

  @Override
  public void autonomousPeriodic() {
    
    Ramp.Periodic();
    Stick.telop();
    Panel.autoPeriodic();
    Chassis.tankDrive();
  }
  @Override
  public void teleopInit(){

  }
  @Override
  public void teleopPeriodic(){
    Ramp.Periodic();
    Stick.telop();
    Panel.teleoP();
    Chassis.tankDrive();
  }



}
