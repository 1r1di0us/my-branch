package lonelymod.cards;

import static lonelymod.LonelyMod.makeID;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import lonelymod.actions.CallMoveAction;
import lonelymod.companions.AbstractCompanion;
import lonelymod.powers.TargetPower;

public class Go extends AbstractEasyCard {
    public final static String ID = makeID("Go");

    public Go() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 0;
        this.tags.add(Enums.COMPANION);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            AbstractMonster targetMonster = AbstractDungeon.getRandomMonster();
            addToBot(new ApplyPowerAction(targetMonster, p, new TargetPower(targetMonster, this.magicNumber, false), this.magicNumber));
        }
        addToBot(new CallMoveAction(AbstractCompanion.ATTACK));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}