/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.Constants.ForBar;
import frc.robot.subsystems.FourBar;

public class FourBarCommand extends CommandBase {


  public FourBar m_ForBar;
  public Joystick m_joystick;
  public FourBarCommand(FourBar FourBarInput,Joystick joystick) {
    m_ForBar = FourBarInput;
    m_joystick = joystick;
    addRequirements(FourBarInput);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(m_joystick.getRawButton(1)){
      m_ForBar.setIntake(0.5);
    }else{
      m_ForBar.setIntake(0.0);
    }
    
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
