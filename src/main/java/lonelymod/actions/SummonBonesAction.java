package lonelymod.actions;

//import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import lonelymod.companions.AbstractCompanion;
import lonelymod.companions.Bones;
import lonelymod.companions.Omen;
import lonelymod.fields.CompanionField;
import lonelymod.interfaces.RelicOnSummonInterface;
import lonelymod.relics.TreatBox;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SummonBonesAction extends AbstractGameAction {
    private static final Logger logger = LogManager.getLogger(SummonBonesAction.class.getName());
    private AbstractCompanion c;
    private boolean alreadySummoned = false;

    public SummonBonesAction() {
        if (Settings.FAST_MODE) {
            this.startDuration = Settings.ACTION_DUR_FAST;
        } else {
            this.startDuration = Settings.ACTION_DUR_LONG;
        }
        this.duration = this.startDuration;
        if (CompanionField.currCompanion.get(AbstractDungeon.player) != null) {
            if (CompanionField.currCompanion.get(AbstractDungeon.player) instanceof Bones) {
                alreadySummoned = true;
                return;
            }
            //AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect, false));
            //CompanionField.currCompanion.get(AbstractDungeon.player).damage(new DamageInfo(AbstractDungeon.player, 1, DamageInfo.DamageType.THORNS));
            CompanionField.currCompanion.set(AbstractDungeon.player, null);
        }
        this.c = new Bones(-750, -40);
        CompanionField.currCompanion.set(AbstractDungeon.player, this.c);
        this.c.init();
    }

    public void update() {
        if (!this.alreadySummoned) {
            if (this.duration == this.startDuration) {
                //this.c.animX = 1200.0F * Settings.xScale;
                this.c.applyPowers();
            }
            tickDuration();
            if (this.isDone) {
                //this.c.animX = 0.0F;
                this.c.showHealthBar();
                this.c.usePreBattleAction();
                for (AbstractRelic r : AbstractDungeon.player.relics) {
                    if (r instanceof RelicOnSummonInterface) {
                        ((RelicOnSummonInterface) r).onSummon(this.c, true);
                    }
                }
            }// else {
            //this.c.animX = Interpolation.fade.apply(0.0F, 1200.0F * Settings.xScale, this.duration);
            //}
        } else {
            this.isDone = true;
        }
    }
}
