/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.nt_Test;

public class ControlVisionTestTwo extends CommandBase {
  /**
   * Creates a new ControlVisionTestTwo.
   */
  
  private nt_Test m_vision;
  private Joystick m_Joystick;
  private DriveBase m_DriveBase;
  private Timer time;
  private double now;
  private final double target = 128.0;

  private final double P = 0.00007*47; //0.00007*40
  private final double I = 0.000008;
  private final double D = 0.00007*47*0.015; //P*0.01

  private double integral , previous_error;

  public ControlVisionTestTwo(nt_Test vision,Joystick joystick,DriveBase _DriveBase) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Joystick = joystick;
    m_vision = vision;
    m_DriveBase = _DriveBase;
    time = new Timer();
    addRequirements(vision);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_vision.done.setBoolean(false);
    m_DriveBase.setLeftSide(0.0);
    m_DriveBase.setRightSide(0.0);
    m_vision.startProcess();
    time.stop();
    time.reset();
    time.start();
    System.out.println("start process");
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    now = m_vision.xCenter.getDouble(-1.0);
    double diff = target-now;
    double speed =  0.0;//(diff/target)*0.1;
    /*
    if(speed<0.1 && speed >0.0){
      speed = 0.085;
    }else if( speed >-0.1 && speed <0.0){
      speed = -0.085;
    }*/


    if(now == -1.0 || now==-2.0){
      setSpeedMotor(0.0);
    }else{
      speed = PID_calc(now);
      setSpeedMotor(speed);
    }


    //working slow without PID
    /*

    if(now == -1.0){
      setSpeedMotor(0.0);
    }else if(speed < 0.0 && speed >-0.065){
      setSpeedMotor(0.065);
    }else if(speed > 0.0 && speed <0.065){
      setSpeedMotor(-0.065);
    }else{
      setSpeedMotor(-speed);
    }
    */


    /*else if(now>=97.0){
      setSpeedMotor(speed);
    }else if(now<97.0){
     */
    
    SmartDashboard.putNumber("speed", speed);
  }

  public double PID_calc(double _now){
    double spd = 0.0;
    double error = this.target - _now;
    this.integral +=(error *I);
    double derivative = (error - this.previous_error) / 0.02;

    if(error*this.previous_error <0.0){
      this.integral = 0.0;
    }

    SmartDashboard.putNumber("P", -P*error);
    SmartDashboard.putNumber("I", this.integral);
    SmartDashboard.putNumber("D", D*derivative);

    spd = -P*error - this.integral + D*derivative;
    
    return spd;
  }

  public void setSpeedMotor(double speed){
    
    m_DriveBase.setLeftSide(speed);
    m_DriveBase.setRightSide(speed);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_vision.done.setBoolean(true);
    m_DriveBase.setRightSide(0.0);
    m_DriveBase.setLeftSide(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(m_Joystick.getRawButton(3)){
      return true;
    }
    return false;

  }
}
