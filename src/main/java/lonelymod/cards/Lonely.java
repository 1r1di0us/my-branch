package lonelymod.cards;

import static lonelymod.LonelyMod.makeID;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FastingEffect;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import lonelymod.actions.LonelyAction;

public class Lonely extends AbstractEasyCard {
    public static final String ID = makeID("Lonely");
    
    public Lonely() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        //MultiCardPreview looks ugly as heck, but I'll use it anyway.
        MultiCardPreview.add(this, new Bravery(), new Resolve(), new Desperation());
    }
    
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new FastingEffect(p.hb.cX, p.hb.cY, Color.YELLOW)));
        addToBot(new LonelyAction());
    }
    
    public void upp() {
        upgradeBaseCost(1);
    }
}
