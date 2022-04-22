// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import static frc.robot.Constants.Mouse.*;
import static frc.robot.util.Mouse.Button.*;
import static java.awt.event.MouseEvent.*;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/** Add your docs here. */
public class Mouse {

    public static final class Button {
        public static final int L1_ = BUTTON1;
        public static final int R2_ = BUTTON2;
        public static final int M3_ = BUTTON3;
        public static final int MX_ = 4;
        public static final int MY_ = 5;
        public static final int MS_ = 6;

    }

    public NetworkTableInstance inst;
    public NetworkTable table;
    public NetworkTableEntry keysEntries[];

    private Key[] keys;

    private double prevPosX;
    private double prevPosY;

    static Mouse instance;

    private Mouse() {
        configurateNetworkTable();

        keys = new Key[KEY_AMOUNT];

        for (int i = 0; i < KEY_AMOUNT; i++) {
            keys[i] = new Key();
        }

    }

    /**
     * Makes sure that there is only one {@link Mouse} object exists
     * @return The single {@link Mouse} that exists
     */
    public static Mouse getInstance() {
        if (instance == null)
            instance = new Mouse();
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
     * Gets the position of the mouse from a scale of -1.0 to 1.0
     * @return the position of the mouse from a scale of -1.0 to 1.0
     */
    public double getMouseX() {
        return ((keysEntries[MX_].getDouble(0.0) - AREA_WIDTH) / AREA_WIDTH);
    }

    /**
     * Gets the position of the mouse from a scale of -1.0 to 1.0
     * @return the position of the mouse from a scale of -1.0 to 1.0
     */
    public double getMouseY() {
        return ((keysEntries[MY_].getDouble(0.0) - AREA_LENGTH) / AREA_LENGTH);
    }

    /**
     * Gets velocity of the X-axis
     * @return velocity of {@link #getMouseX()}
     */
    public double getMouseXVel() {
        double currentPos = keysEntries[MX_].getDouble(0.0);
        double vel = (prevPosX - currentPos) / 0.02;
        prevPosX = currentPos;

        return Math.abs(vel) < VEL_X_DEADBAND ? vel : 0.0;
    }

    /**
     * Gets velocity of the Y-axis
     * @return velocity of {@link #getMouseY()}
     */
    public double getMouseYVel() {
        double currentPos = keysEntries[MY_].getDouble(0.0);
        double vel = (prevPosY - currentPos) / 0.02;
        prevPosY = currentPos;

        return Math.abs(vel) < VEL_Y_DEADBAND ? vel : 0.0;
    }

    /**
     * Sets up the NetworkTables for the Mouse
     */
    private void configurateNetworkTable() {
        inst = NetworkTableInstance.getDefault();
        table = inst.getTable("mouse");
        keysEntries = new NetworkTableEntry[KEY_AMOUNT];
        
        // Declare keys used this way
        keysEntries[L1_] = table.getEntry("a");

        // Declare key listener
        keysEntries[L1_].addListener(
            event -> {
                if (keysEntries[L1_].getBoolean(false)) 
                    this.keys[L1_].press();
                else 
                    this.keys[L1_].release();
            },             
            EntryListenerFlags.kNew | EntryListenerFlags.kUpdate

        );

        inst.startClientTeam(2557);

    }
}
