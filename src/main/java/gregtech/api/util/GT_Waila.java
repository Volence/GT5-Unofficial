package gregtech.api.util;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public abstract class GT_Waila {

    public static String getMachineProgressString(boolean isActive, int maxProgresstime, int progresstime) {
        return getMachineProgressString(isActive, (long) maxProgresstime, (long) progresstime);
    }

    public static String getMachineProgressString(boolean isActive, long maxProgresstime, long progresstime) {

        if (!isActive) return "Idle";

        StringBuilder ret = new StringBuilder("In progress: ")
            .append(String.format("%,.2f", (double) progresstime / 20))
            .append("s / ")
            .append(String.format("%,.2f", (double) maxProgresstime / 20))
            .append("s (")
            .append(GT_Utility.formatNumbers((Math.round((double) progresstime / maxProgresstime * 1000) / 10.0)))
            .append("%)");

        return ret.toString();
    }

    public static String getMachineSpeedString(String langSelector, EnumChatFormatting color, String speed) {
        StringBuilder ret = new StringBuilder(StatCollector.translateToLocal("GT5U.multiblock.speed"))
            .append(": ")
            .append(color)
            .append(speed)
            .append("%");
        return ret.toString();
    }
}
