/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.ArcadeDrive;

public class DriveBase extends SubsystemBase {
  TalonFX RightOne = new TalonFX(Constants.DriveBase.RightOne);
  TalonFX RightTwo = new TalonFX(Constants.DriveBase.RightTwo);
  TalonFX RightThree = new TalonFX(Constants.DriveBase.RightThree);
  
  TalonFX LeftOne = new TalonFX(Constants.DriveBase.LeftOne);
  TalonFX LeftTwo = new TalonFX(Constants.DriveBase.LeftTwo);
  TalonFX LeftThree = new TalonFX(Constants.DriveBase.LeftThree);


  CANCoder _coder1 = new CANCoder(0);
  CANCoderConfiguration _CanCoderConfiguration = new CANCoderConfiguration();
  final int printOutDelay = 100;
  /**
   * Creates a new DriveBase.
   */
  public DriveBase() {

  }

  public void setRightSide(double speed){
    RightOne.set(ControlMode.PercentOutput, speed);
    RightTwo.set(ControlMode.PercentOutput, speed);
    RightThree.set(ControlMode.PercentOutput, speed);
  }

  public void setLeftSide(double speed){
    LeftOne.set(ControlMode.PercentOutput, speed);
    LeftTwo.set(ControlMode.PercentOutput, speed);
    LeftThree.set(ControlMode.PercentOutput, speed);
  }

  public void ArcadeDrive(double X,double Y){
    double left = Y-X;
    double right = Y+X;
    
    setLeftSide(left);
    setRightSide(right);

  }

  public double getEncoderAvgRightSide(){
    int sum = Math.abs(RightOne.getSelectedSensorPosition() + RightTwo.getSelectedSensorPosition() + RightThree.getSelectedSensorPosition());
    double avg = sum/3;
    System.out.println("the right side:"+avg);
    return avg;
  }

  public double getEncoderAvgLeftSide(){
    int sum = Math.abs(LeftOne.getSelectedSensorPosition() + LeftTwo.getSelectedSensorPosition() + LeftThree.getSelectedSensorPosition());
    double avg = sum/3;
    System.out.println("the left side is:"+avg);
    return avg;
  }

  public double AvgTwoSides(){
    double avg = (getEncoderAvgLeftSide()+getEncoderAvgRightSide())/2;
    System.out.println("avg of two sides is:" + avg);
    return avg;
  }

  public void ResetEncoderRight(){
    RightOne.setSelectedSensorPosition(0);
    RightTwo.setSelectedSensorPosition(0);
    RightThree.setSelectedSensorPosition(0);
  }

  public void ResetEncoderLeft(){
    LeftOne.setSelectedSensorPosition(0);
    LeftTwo.setSelectedSensorPosition(0);
    LeftThree.setSelectedSensorPosition(0);
  }

  public void DriveByGivendistance(double target){
    ResetEncoderLeft();
    ResetEncoderRight();
    double now = AvgTwoSides();
    double speed = (target-now)/target;
    System.out.println(speed);
    
  }

  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
