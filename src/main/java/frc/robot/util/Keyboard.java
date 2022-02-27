// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import static frc.robot.Constants.Keyboard.*;
import static frc.robot.util.Keyboard.Keys.*;
import static java.awt.event.KeyEvent.*;

import edu.wpi.first.networktables.*;

/** Add your docs here. */
public class Keyboard {

    public class Keys {
        public static final int firstKeyOffSet = VK_SEMICOLON;

        public static final int SC_ = VK_SEMICOLON - firstKeyOffSet;
        public static final int EQ_ = VK_EQUALS - firstKeyOffSet;

        public static final int A_ = VK_A - firstKeyOffSet;
        public static final int B_ = VK_B - firstKeyOffSet;
        public static final int C_ = VK_C - firstKeyOffSet;
        public static final int D_ = VK_D - firstKeyOffSet;
        public static final int E_ = VK_E - firstKeyOffSet;
        public static final int F_ = VK_F - firstKeyOffSet;
        public static final int G_ = VK_G - firstKeyOffSet;
        public static final int H_ = VK_H - firstKeyOffSet;
        public static final int I_ = VK_I - firstKeyOffSet;
        public static final int J_ = VK_J - firstKeyOffSet;
        public static final int K_ = VK_K - firstKeyOffSet;
        public static final int L_ = VK_L - firstKeyOffSet;
        public static final int M_ = VK_M - firstKeyOffSet;
        public static final int N_ = VK_N - firstKeyOffSet;
        public static final int O_ = VK_O - firstKeyOffSet;
        public static final int P_ = VK_P - firstKeyOffSet;
        public static final int Q_ = VK_Q - firstKeyOffSet;
        public static final int R_ = VK_R - firstKeyOffSet;
        public static final int S_ = VK_S - firstKeyOffSet;
        public static final int T_ = VK_T - firstKeyOffSet;
        public static final int U_ = VK_U - firstKeyOffSet;
        public static final int V_ = VK_V - firstKeyOffSet;
        public static final int W_ = VK_W - firstKeyOffSet;
        public static final int X_ = VK_X - firstKeyOffSet;
        public static final int Y_ = VK_Y - firstKeyOffSet;
        public static final int Z_ = VK_Z - firstKeyOffSet;

    }

    public static NetworkTableInstance inst;
    public static NetworkTable table;
    public static NetworkTableEntry keysEntries[];

    // An array of the keys you can use
    public Key keys[];

    public Keyboard() {
        configurateNetworkTable();
        
        keys = new Key[KEY_AMOUNT];

        // Initilizing the keys from ;-Z
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new Key(this, i);
        }

    }

    public boolean getKeyPressed(int id) {

        return keys[id].isPressed;

    }

    private void configurateNetworkTable() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("keyboard");
        keysEntries = new NetworkTableEntry[KEY_AMOUNT];
        
        // Declare keys used this way
        keysEntries[A_] = table.getEntry("a");

        // Declare key listener
        keysEntries[A_].addListener(
            event -> {
                this.keys[A_].isPressed = keysEntries[A_].getBoolean(false);
            }, 
            
            EntryListenerFlags.kNew | EntryListenerFlags.kUpdate

        );

        inst.startClientTeam(2557);

    }
}
