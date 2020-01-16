/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ColorSensor;
import frc.robot.commands.FourBarCommand;
import frc.robot.commands.FourBar.CloseBar;
import frc.robot.commands.FourBar.OpenBar;
import frc.robot.commands.Vision.ControlVisionTestTwo;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.FourBar;
import frc.robot.subsystems.Sensors;
import frc.robot.subsystems.nt_Test;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveBase m_driveBase = new DriveBase();
  private final Sensors m_sensors = new Sensors();
  private final FourBar m_FourBar = new FourBar();
  private final nt_Test m_vision = new nt_Test(m_driveBase);
  Joystick m_joystick = new Joystick(Constants.OIConstants.joystick);
  XboxController mController = new XboxController(Constants.OIConstants.xboxcontroller);



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    //m_FourBar.setDefaultCommand(new FourBarCommand(m_FourBar, m_joystick));
    m_driveBase.setDefaultCommand(new RunCommand(()->m_driveBase.ArcadeDrive(JgetY()*1.0, -JgetX()*1.0), m_driveBase));
    
    configureButtonBindings();
  }


  public double JgetX(){
    if(Math.abs(m_joystick.getX())>0.15){
      return -m_joystick.getX()*Math.abs(m_joystick.getX());
    }else{
      return 0.0;
    }
  }

  
  public double JgetY(){
    if(Math.abs(m_joystick.getY())>0.15){
      return m_joystick.getY()*Math.abs(m_joystick.getY());
    }else{
      return 0.0;
    }
  }
  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //new JoystickButton(m_joystick, 1).whenPressed(m_driveBase::ResetEncoderRight);
    new JoystickButton(m_joystick, 5).whenPressed(new OpenBar(m_FourBar));
    new JoystickButton(m_joystick, 6).whenPressed(new CloseBar(m_FourBar));
    new JoystickButton(m_joystick, 1).whenPressed(new ControlVisionTestTwo(m_vision, m_joystick, m_driveBase));
    new JoystickButton(m_joystick, 9).whenPressed(new ColorSensor(m_sensors));

    //new JoystickButton(m_joystick, 6).whenPressed(m_FourBar::getClosePosition);


    //.whenPressed(m_driveBase::ResetEncoderLeft);
    //new JoystickButton(m_joystick, 2).whenPressed(m_driveBase::AvgTwoSides);
    //new JoystickButton(m_joystick, 3).whenPressed(()->m_driveBase.DriveByGivendistance(2.0));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   *//*
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;

  }*/
}
