package com.noxcrew.noxesium.feature.rule;

import com.noxcrew.noxesium.feature.rule.impl.AdventureModeCheckServerRule;
import com.noxcrew.noxesium.feature.rule.impl.CameraLockedRule;
import com.noxcrew.noxesium.feature.rule.impl.CustomAdventureModeCheck;
import com.noxcrew.noxesium.feature.rule.impl.EnableMusicRule;

/**
 * A class that stores all known server rules.
 */
public class ServerRules {
    /**
     * If `true` disables the riptide spin attack on the trident from colliding with any entities,
     * useful for non-pvp mini-games where the trident is used as a movement tool.
     */
    public static ServerRule<Boolean> DISABLE_AUTO_SPIN_ATTACK = new BooleanServerRule(0, false);

    /**
     * A global value for the "CanPlaceOn" item tag that applies to players in adventure mode,
     * allowing the server to define which blocks are placable globally regardless of tool.
     */
    public static ServerRule<CustomAdventureModeCheck> GLOBAL_CAN_PLACE_ON = new AdventureModeCheckServerRule(1);

    /**
     * A global value for the "CanDestroy" item tag that applies to players in adventure mode,
     * allowing the server to define which blocks are breakable globally regardless of tool.
     */
    public static ServerRule<CustomAdventureModeCheck> GLOBAL_CAN_DESTROY = new AdventureModeCheckServerRule(2);

    /**
     * An integer pixel amount to vertically offset the HUD held item name.
     * Useful for avoiding overlapping faction icons. Positive values move the text up.
     */
    public static ServerRule<Integer> HELD_ITEM_NAME_OFFSET = new IntegerServerRule(3, 0);

    /**
     * Whether the player should currently prevent any mouse inputs from moving their camera.
     */
    public static ServerRule<Boolean> CAMERA_LOCKED = new CameraLockedRule(4);

    /**
     * Whether the custom music system should be enabled. When enabled vanilla background music is fully disabled and
     * additional music sliders become available to play music in. This can be used by servers that wish to play their
     * own music.
     */
    public static ServerRule<Boolean> ENABLE_CUSTOM_MUSIC = new EnableMusicRule(5);
}
