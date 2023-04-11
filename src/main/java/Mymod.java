import basemod.BaseMod;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import relics.IdolEardrop;
import relics.MagicHat;

import java.nio.charset.StandardCharsets;

@SpireInitializer
public class Mymod implements
        EditCardsSubscriber,
        EditCharactersSubscriber,
        EditKeywordsSubscriber,
        EditStringsSubscriber,
        EditRelicsSubscriber {
    public Mymod() {
        BaseMod.subscribe(this);
    }

    public static void initialize(){
        Mymod mymod = new Mymod();
    }

    @Override
    public void receiveEditCards() {

    }

    @Override
    public void receiveEditCharacters() {

    }

    @Override
    public void receiveEditKeywords() {

    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelic(new MagicHat(), RelicType.SHARED);
        BaseMod.addRelic(new IdolEardrop(), RelicType.GREEN);
        //RelicLibrary.add();
    }

    @Override
    public void receiveEditStrings() {
        String relic = "";
        if (Settings.language == Settings.GameLanguage.ZHS){
            relic = "localization/ThMod_relics-zh.json";
        }

        String relicStrings = Gdx.files.internal(relic).readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
    }
}
