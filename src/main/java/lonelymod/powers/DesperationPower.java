package lonelymod.powers;

import static lonelymod.ModFile.makeID;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import basemod.interfaces.CloneablePowerInterface;
import lonelymod.ModFile;
import lonelymod.util.TexLoader;

public class DesperationPower extends AbstractEasyPower implements CloneablePowerInterface {

    public static final String POWER_ID = makeID("DesperationPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/ExampleTwoAmountPower84.png");
    private static final Texture tex32 = TexLoader.getTexture(ModFile.modID + "Resources/images/powers/ExampleTwoAmountPower32.png");

    private int turnAmount;

    public DesperationPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, AbstractPower.PowerType.BUFF, true, owner, amount);

        this.owner = owner;

        type = PowerType.BUFF;
        isTurnBased = true;
        this.amount = amount;
        this.turnAmount = this.amount;

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
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if (EnergyPanel.getCurrentEnergy() - usedCard.cost == 0) {
            if (this.turnAmount > 0) {
                this.turnAmount--;
                flash();
                addToBot(new GainEnergyAction(1));
                if (!this.owner.hasPower(makeID("DesperatePower"))) {
                    addToBot(new ApplyPowerAction(this.owner, this.owner, new DesperatePower(this.owner)));
                }
            }
        }
    }

    @Override
    public void onEnergyRecharge() {
        this.turnAmount = this.amount;
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (this.amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DesperationPower(this.owner, this.amount);
    }
    
}
