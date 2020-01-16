/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Vision;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.nt_Test;

public class ControlVision extends CommandBase {
  /**
   * Creates a new ControlVision.
   */
  private nt_Test m_vision;
  private Joystick m_Joystick;
  private DriveBase m_DriveBase;
  private Timer time;
  public ControlVision(nt_Test vision,Joystick joystick,DriveBase _DriveBase) {
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

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double now = m_vision.xCenter.getDouble(-1.0);
    final double target = 128.0;
    double sPeed = 0.0;
    double k=0.25;
    if(now != -1.0){
      sPeed = (target-now)/target;
      sPeed *= k;
      m_DriveBase.setLeftSide(sPeed);
      m_DriveBase.setRightSide(sPeed);
    }
    
    /*
    if((m_vision.xCenter.getDouble(-1.0)>118.0 && m_vision.xCenter.getDouble(-1.0)<138.0)){
      if(sPeed>0){
        m_DriveBase.setLeftSide(-0.05);
        m_DriveBase.setRightSide(-0.05);
      }else{
        m_DriveBase.setLeftSide(0.05);
        m_DriveBase.setRightSide(0.05);
      }
    }
*/
    System.out.println(m_vision.xCenter.getDouble(-1.0));

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
    if( ((m_vision.xCenter.getDouble(-1.0)>118.0 && m_vision.xCenter.getDouble(-1.0)<138.0) || inRangeForSeconds(2.0)|| m_vision.xCenter.getDouble(-1.0) == -2.0 || m_Joystick.getRawButton(3))){
      return true;
    }
    return false;
  }





public boolean inRangeForSeconds(double timeTarget){
  time.stop();
  time.reset();
  time.start();
  while(time.get()<timeTarget){
    if(!(m_vision.xCenter.getDouble(-1.0)>118.0 && m_vision.xCenter.getDouble(-1.0)<138.0)){
      break;
    }
  }
  if(time.get()> timeTarget){
    time.stop();
    return true;
  }else{
    time.stop();
    return false;
  }
}

}