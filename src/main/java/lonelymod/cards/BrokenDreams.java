package lonelymod.cards;

import static lonelymod.LonelyMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class BrokenDreams extends AbstractEasyCard {
    public final static String ID = makeID("BrokenDreams");

    public BrokenDreams() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new Dominate();
        this.baseMagicNumber = this.magicNumber = 3;
        this.baseSecondMagic = this.secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new WeakPower(p, this.magicNumber, false), this.magicNumber));
        addToBot(new MakeTempCardInDrawPileAction(new Dominate(), this.secondMagic, true, true));
    }

    public void upp() {
        upgradeSecondMagic(1);
        uDesc();
    }
}
