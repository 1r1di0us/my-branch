package lonelymod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import lonelymod.LonelyMod;
import lonelymod.util.TexLoader;

import static lonelymod.LonelyMod.makeID;

public class MechanicPower extends AbstractEasyPower implements CloneablePowerInterface, NonStackablePower {

    public static final String POWER_ID = makeID("MechanicPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TexLoader.getTexture(LonelyMod.modID + "Resources/images/powers/ExampleTwoAmountPower84.png");
    private static final Texture tex32 = TexLoader.getTexture(LonelyMod.modID + "Resources/images/powers/ExampleTwoAmountPower32.png");

    public MechanicPower(AbstractCreature owner) {
        super(POWER_ID, NAME, AbstractPower.PowerType.BUFF, false, owner, 1);

        this.owner = owner;

        type = AbstractPower.PowerType.BUFF;
        isTurnBased = false;

        if (tex84 != null) {
            region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, tex84.getWidth(), tex84.getHeight());
            if (tex32 != null)
                region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, tex32.getWidth(), tex32.getHeight());
        } else if (tex32 != null) {
            this.img = tex32;
            region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, tex32.getWidth(), tex32.getHeight());
        }

        updateDescription();
    }

    @Override
    public void onSpecificTrigger() {
        if (AbstractDungeon.player.hasPower(FocusPower.POWER_ID)) {
            if (this.owner.hasPower(StrengthPower.POWER_ID))
                addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, (AbstractDungeon.player.getPower(FocusPower.POWER_ID).amount - this.owner.getPower(StrengthPower.POWER_ID).amount))));
            if (this.owner.hasPower(CompanionDexterityPower.POWER_ID))
                addToBot(new ApplyPowerAction(this.owner, this.owner, new CompanionDexterityPower(this.owner, (AbstractDungeon.player.getPower(FocusPower.POWER_ID).amount - this.owner.getPower(CompanionDexterityPower.POWER_ID).amount))));
        } else {
            if (this.owner.hasPower(StrengthPower.POWER_ID))
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.owner.getPower(StrengthPower.POWER_ID)));
            if (this.owner.hasPower(CompanionDexterityPower.POWER_ID))
                addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.owner.getPower(CompanionDexterityPower.POWER_ID)));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new MechanicPower(this.owner);
    }
}
