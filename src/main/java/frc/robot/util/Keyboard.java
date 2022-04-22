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

    public static final class Keys {
        // Alphanumerics
        public static final int A_ = VK_A - FIRST_KEY_OFFSET;
        public static final int B_ = VK_B - FIRST_KEY_OFFSET;
        public static final int C_ = VK_C - FIRST_KEY_OFFSET;
        public static final int D_ = VK_D - FIRST_KEY_OFFSET;
        public static final int E_ = VK_E - FIRST_KEY_OFFSET;
        public static final int F_ = VK_F - FIRST_KEY_OFFSET;
        public static final int G_ = VK_G - FIRST_KEY_OFFSET;
        public static final int H_ = VK_H - FIRST_KEY_OFFSET;
        public static final int I_ = VK_I - FIRST_KEY_OFFSET;
        public static final int J_ = VK_J - FIRST_KEY_OFFSET;
        public static final int K_ = VK_K - FIRST_KEY_OFFSET;
        public static final int L_ = VK_L - FIRST_KEY_OFFSET;
        public static final int M_ = VK_M - FIRST_KEY_OFFSET;
        public static final int N_ = VK_N - FIRST_KEY_OFFSET;
        public static final int O_ = VK_O - FIRST_KEY_OFFSET;
        public static final int P_ = VK_P - FIRST_KEY_OFFSET;
        public static final int Q_ = VK_Q - FIRST_KEY_OFFSET;
        public static final int R_ = VK_R - FIRST_KEY_OFFSET;
        public static final int S_ = VK_S - FIRST_KEY_OFFSET;
        public static final int T_ = VK_T - FIRST_KEY_OFFSET;
        public static final int U_ = VK_U - FIRST_KEY_OFFSET;
        public static final int V_ = VK_V - FIRST_KEY_OFFSET;
        public static final int W_ = VK_W - FIRST_KEY_OFFSET;
        public static final int X_ = VK_X - FIRST_KEY_OFFSET;
        public static final int Y_ = VK_Y - FIRST_KEY_OFFSET;
        public static final int Z_ = VK_Z - FIRST_KEY_OFFSET;

        public static final int N0_ = VK_0 - FIRST_KEY_OFFSET;
        public static final int N1_ = VK_1 - FIRST_KEY_OFFSET;
        public static final int N2_ = VK_2 - FIRST_KEY_OFFSET;
        public static final int N3_ = VK_3 - FIRST_KEY_OFFSET;
        public static final int N4_ = VK_4 - FIRST_KEY_OFFSET;
        public static final int N5_ = VK_5 - FIRST_KEY_OFFSET;
        public static final int N6_ = VK_6 - FIRST_KEY_OFFSET;
        public static final int N7_ = VK_7 - FIRST_KEY_OFFSET;
        public static final int N8_ = VK_8 - FIRST_KEY_OFFSET;
        public static final int N9_ = VK_9 - FIRST_KEY_OFFSET;

    }

    public NetworkTableInstance inst;
    public NetworkTable table;
    public NetworkTableEntry keysEntries[];

    static Keyboard instance;

    // An array of the keys you can use
    private Key keys[];

    private Keyboard() {
        configurateNetworkTable();
        
        keys = new Key[KEY_AMOUNT];

        // Initilizing the keys from A-9
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new Key();
        }

    }

    /**
     * Makes sure that there is only one {@link Keyboard} object exists
     * @return The single {@link Keyboard} that exists
     */
    public static Keyboard getInstance() {
        if (instance == null)
            instance = new Keyboard();
        return instance; 
    }

    /**
     * Gets the Key at a certian index
     * @param id the id of the Key
     * @return The Key at the index of id
     */
    public Key getKey(int id) {
        return keys[id];
    }

    /**
     * Configures the NetworkTables for the 
     */
    private void configurateNetworkTable() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("keyboard");
        keysEntries = new NetworkTableEntry[KEY_AMOUNT];
        
        // Declare keys used this way
        keysEntries[A_] = table.getEntry("a");

        // Declare key listener
        keysEntries[A_].addListener(
            event -> {
                if (keysEntries[A_].getBoolean(false)) 
                    this.keys[A_].press();
                else 
                    this.keys[A_].release();
            }, 
            
            EntryListenerFlags.kNew | EntryListenerFlags.kUpdate

        );

        inst.startClientTeam(2557);

    }
}
