package relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MagicHat extends CustomRelic {
    public static final String ID = "MagicHat";
    private static final String IMG = "img/relic/test7.png";
    private static final String IMG_OTL = "img/outline/test7.png";
    public MagicHat() {
        super(ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, LandingSound.CLINK);
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        if (targetCard.type == AbstractCard.CardType.SKILL){
            this.counter++;
            if (this.counter % 2 == 0){
                this.counter = 0;
                flash();
                addToTop((AbstractGameAction)new DrawCardAction((AbstractCreature) AbstractDungeon.player, 1));
                addToTop((AbstractGameAction)new RelicAboveCreatureAction((AbstractCreature)AbstractDungeon.player, this));

            }
        }
    }

    @Override
    public void atTurnStart() {
        this.counter = 0;
    }

    @Override
    public void onVictory() {
        //在胜利时触发
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return (AbstractRelic)new MagicHat();
    }
}
