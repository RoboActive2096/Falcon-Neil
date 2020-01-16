/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.DriveBase;



import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveBase;

public class turnForSeconds extends CommandBase {
  /**
   * Creates a new turnForSeconds.
   */
  DriveBase m_DriveBase;
  Timer time;
  double _timeToSet;
  public turnForSeconds(double timeToSet,DriveBase driveBase) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_DriveBase = driveBase;
    time = new Timer();
    _timeToSet = timeToSet;
    addRequirements();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    time.stop();
    time.reset();
    time.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_DriveBase.setLeftSide(-0.1);
    m_DriveBase.setRightSide(-0.1);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_DriveBase.setRightSide(0.0);
    m_DriveBase.setLeftSide(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(time.get()<_timeToSet){
      return false;
    }
    return true;
  }
}
