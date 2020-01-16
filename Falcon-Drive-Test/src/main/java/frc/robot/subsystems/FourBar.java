/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class FourBar extends SubsystemBase {
  VictorSP Intake = new VictorSP(2);
  VictorSP MotorMoveFourBar = new VictorSP(3);
  DigitalInput close = new DigitalInput(1);
  DigitalInput open = new DigitalInput(0);

  public FourBar() {

  }


  public boolean getClosePosition(){
    System.out.println(close.get());
    return close.get();
  }

  public boolean getOpenPoition(){
    System.out.println(open.get());
    return open.get();
  }
  
  public void setIntake(double speed){
    Intake.set(speed);
  }

  public void setPositionMotorMove(double speed){
    MotorMoveFourBar.set(-speed);
  }

  

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
