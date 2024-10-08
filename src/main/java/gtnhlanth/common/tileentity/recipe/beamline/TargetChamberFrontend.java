package gtnhlanth.common.tileentity.recipe.beamline;

import static gregtech.api.util.GTUtility.trans;

import java.util.List;

import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

import com.gtnewhorizons.modularui.api.math.Pos2d;

import gregtech.api.recipe.BasicUIPropertiesBuilder;
import gregtech.api.recipe.NEIRecipePropertiesBuilder;
import gregtech.api.recipe.RecipeMapFrontend;
import gregtech.api.util.GTUtility;
import gregtech.api.util.OverclockCalculator;
import gregtech.common.gui.modularui.UIHelper;
import gregtech.nei.GTNEIDefaultHandler;
import gregtech.nei.RecipeDisplayInfo;
import gtnhlanth.util.Util;

public class TargetChamberFrontend extends RecipeMapFrontend {

    public TargetChamberFrontend(BasicUIPropertiesBuilder uiPropertiesBuilder,
        NEIRecipePropertiesBuilder neiPropertiesBuilder) {
        super(uiPropertiesBuilder, neiPropertiesBuilder);
    }

    public void drawDescription(RecipeDisplayInfo recipeInfo) {
        drawEnergyInfo(recipeInfo);
        // drawDurationInfo(recipeInfo);
        drawSpecialInfo(recipeInfo);
        drawMetadataInfo(recipeInfo);
        drawRecipeOwnerInfo(recipeInfo);
    }

    @Override
    protected void drawNEIOverlayForInput(GTNEIDefaultHandler.FixedPositionedStack stack) {
        if (stack.isNotConsumed()) { // The stack actually takes damage, but is technically still not considered to be
                                     // consumed by the code
            drawNEIOverlayText("PC", stack);
        }
    }

    @Override
    protected List<String> handleNEIItemInputTooltip(List<String> currentTip,
        GTNEIDefaultHandler.FixedPositionedStack pStack) {
        if (pStack.isNotConsumed()) { // See above
            currentTip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("gtnhlanth.tt.pc")); // Partially
                                                                                                         // consumed:
                                                                                                         // Takes damage
                                                                                                         // in the
                                                                                                         // process
        }
        return currentTip;
    }

    @Override
    public void drawEnergyInfo(RecipeDisplayInfo recipeInfo) {
        if (recipeInfo.calculator.getConsumption() <= 0) return;

        // recipeInfo.drawText(trans("152", "Total: ") + getTotalPowerString(recipeInfo.calculator));

        recipeInfo.drawText(trans("153", "Usage: ") + getEUtDisplay(recipeInfo.calculator));
        recipeInfo.drawText(trans("154", "Voltage: ") + getVoltageString(recipeInfo.calculator));
        recipeInfo.drawText(trans("155", "Amperage: ") + getAmperageString(recipeInfo.calculator));

    }

    @Override
    public List<Pos2d> getItemOutputPositions(int itemOutputCount) {
        return UIHelper.getGridPositions(itemOutputCount, 128, 24, 1, 3); // Make output items display vertically, not
                                                                          // in a square
    }

    @Override
    public List<Pos2d> getItemInputPositions(int itemInputCount) {

        /*
         * Pos2d posParticle = new Pos2d(8, 28); // Particle item ArrayList<Pos2d> posList = new ArrayList<>();
         * posList.add(posParticle); posList.addAll(UIHelper.getGridPositions(itemInputCount - 1, 36, 28, 3));
         */

        List<Pos2d> posList = Util.getGridPositions(itemInputCount, 8, 20, 3, 1, 20);
        return posList;
    }

    private String getEUtDisplay(OverclockCalculator calculator) {
        return getEUtWithoutTier(calculator);
    }

    private String getEUtWithoutTier(OverclockCalculator calculator) {
        return GTUtility.formatNumbers(calculator.getConsumption()) + " EU/t";
    }

    private String getVoltageString(OverclockCalculator calculator) {
        long voltage = computeVoltageForEURate(calculator.getConsumption());
        return GTUtility.formatNumbers(voltage) + " EU/t" + GTUtility.getTierNameWithParentheses(voltage);
    }

    private long computeVoltageForEURate(long euPerTick) {
        return euPerTick;
    }

    private String getAmperageString(OverclockCalculator calculator) {
        return GTUtility.formatNumbers(1);
    }

}
