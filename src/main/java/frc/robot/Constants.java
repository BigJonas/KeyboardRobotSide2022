// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static frc.robot.util.Keyboard.Keys.*;
import static java.awt.event.KeyEvent.*;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class Keyboard {
        public static final int FIRST_KEY_OFFSET = VK_0; // Definitly change these numbers if you want to not 
        public static final int KEY_AMOUNT = Z_;         // overload the FMS

    }

    public static final class Mouse {
        /**
         * X Position
         * Y Position
         * Mouse Button 1
         * Mouse Button 2
         * Mouse Button 3
         * Scroll wheel
         */
        public static final int KEY_AMOUNT = 8;

        public static final int AREA_WIDTH = 1280; // This is the size of the window in the program
        public static final int AREA_LENGTH = 720; // I made the window unresizeable so you have to re-compile it on your own to change the size

        public static final double VEL_X_DEADBAND = 0.2;
        public static final double VEL_Y_DEADBAND = 0.2;
    }

}
