/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class nt_Test extends SubsystemBase {
  /**
   * Creates a new nt_Test.
   */
  private NetworkTableInstance inst;
  private NetworkTable table;
  public DriveBase m_DriveBase;
  private NetworkTableEntry trigger;
  public NetworkTableEntry xCenter;
  public NetworkTableEntry done;
  
  public nt_Test(frc.robot.subsystems.DriveBase driveBase) {
    m_DriveBase = driveBase;
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("SmartDashboard");
    xCenter = table.getEntry("center");
    done = table.getEntry("done");
  }

public void startProcess(){
  trigger = table.getEntry("activateProcess");
  trigger.setBoolean(true);
}

public void getCenter(){
  xCenter.getDouble(-1.0);
  //m_DriveBase.setLeftSide(0.5);
}

  @Override
public void periodic() {
    // This method will be called once per scheduler run
}
}
