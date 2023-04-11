package relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class IdolEardrop extends CustomRelic {
    public static final String ID = "IdolEardrop";
    private static final String IMG = "img/relic/IdolEardrop.png";
    private static final String IMG_OTL = "img/outline/IdolEardrop.png";

    public IdolEardrop() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        this.counter = 0;
        AbstractPlayer p = AbstractDungeon.player;
        for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters){
            this.counter++;
        }

        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, -this.counter), -this.counter));
    }

    @Override
    public void atTurnStart() {
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
        addToBot((AbstractGameAction)new GainEnergyAction(this.counter));
        addToBot((AbstractGameAction)new DrawCardAction(this.counter));
    }
    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }
    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new IdolEardrop();
    }

    @Override
    public void onMonsterDeath(AbstractMonster m) {
        this.counter--;
//        恢复力量
        AbstractPlayer p = AbstractDungeon.player;
        addToBot((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));
        addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, 1), 1));
    }

    @Override
    public void onVictory() {
        this.counter = -1;
        flash();
    }
}
