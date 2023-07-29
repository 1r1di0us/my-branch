package lonelymod.cards;

import static lonelymod.LonelyMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

public class BrokenSpirit extends AbstractEasyCard {
    public final static String ID = makeID("BrokenSpirit");

    public BrokenSpirit() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.cardsToPreview = new Primal();
        this.baseMagicNumber = this.magicNumber = 2;
        this.tags.add(Enums.COMPANION);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FrailPower(p, this.magicNumber, false), this.magicNumber));
        if (!upgraded) addToBot(new MakeTempCardInDrawPileAction(new Primal(), 1, true, true));
        else addToBot(new MakeTempCardInDrawPileAction(new Primal(), 1, false, true));
    }

    public void upp() {
        uDesc();
    }
}
