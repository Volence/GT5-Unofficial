package gregtech.api.interfaces.tileentity;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import java.text.DecimalFormat;
import java.util.List;

public class WailaTip {
    private List<String> lines = new java.util.ArrayList<>();
    private List<String> errors = new java.util.ArrayList<>();

    private static final DecimalFormat formatterNoDecimalPlaces = new DecimalFormat("#");
    private static final DecimalFormat formatterTwoDecimalPlaces = new DecimalFormat("0.00");

    public void add(String statLocalizationKey, EnumChatFormatting color, String value) {
        this.lines.add(StatCollector.translateToLocal(statLocalizationKey) + ": " + color + value);
    }

    public void addNumberTip(String statLocalizationKey, EnumChatFormatting color, int value) {
        this.lines.add(StatCollector.translateToLocal(statLocalizationKey) + ": " + color + value);
    }

    public void addErrors(String value) {
        errors.add(EnumChatFormatting.RED + value);
    }

    public void clearList() {
        this.lines.clear();
    }

    public void clearErrors() {
        errors.clear();
    }

    public List<String> getList() {
        if(errors.size() > 0) {
            return errors;
        }
        System.out.println("test" + this.lines);
        return this.lines;
    }
}
