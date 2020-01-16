/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Sensors;

public class ColorSensor extends CommandBase {
  /**
   * Creates a new ColorSensor.
   */

  String colorString;
  Sensors m_Sensors;
  public ColorSensor(Sensors sensor) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_Sensors = sensor;
    addRequirements(sensor);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Color detectedColor = m_Sensors.getColorSensor().getColor();
    ColorMatchResult match = m_Sensors.m_colorMatcher.matchClosestColor(detectedColor);
    if (match.color == m_Sensors.kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == m_Sensors.kRedTarget) {
      colorString = "Red";
    } else if (match.color == m_Sensors.kGreenTarget) {
      colorString = "Green";
    } else if (match.color == m_Sensors.kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }
    
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);


  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
