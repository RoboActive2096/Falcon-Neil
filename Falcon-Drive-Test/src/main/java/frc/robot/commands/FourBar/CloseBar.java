/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.FourBar;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FourBar;

public class CloseBar extends CommandBase {
  /**
   * Creates a new CloseBar.
   */
  public FourBar m_FourBar;

  public CloseBar(final FourBar FourBarInput) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_FourBar = FourBarInput;
    addRequirements(FourBarInput);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_FourBar.setPositionMotorMove(-0.3);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_FourBar.setPositionMotorMove(0.0);
    m_FourBar.setIntake(0.0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(!m_FourBar.getClosePosition()){
      return false;
    }else{
      return true;
    }
  }
}
