/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final class DriveBase{
        public static final int RightOne = 10;
        public static final int RightTwo = 11;
        public static final int RightThree = 12;

        public static final int LeftOne = 13;
        public static final int LeftTwo = 14;
        public static final int LeftThree = 15;
    }

    public final class OIConstants{
        public static final int joystick = 0;
        public static final int xboxcontroller = 1;
    }
    public final class ForBar{
        public static final int MotorOne = 1;
        public static final int MotorTow = 2;
    }
}
