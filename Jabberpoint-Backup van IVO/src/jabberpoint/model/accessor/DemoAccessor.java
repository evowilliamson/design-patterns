package jabberpoint.model.accessor;

import jabberpoint.model.Slide;
import jabberpoint.model.Slideshow;
import jabberpoint.model.Theme;
import jabberpoint.model.action.AbsoluteNavigationAction;
import jabberpoint.model.action.AuxilaryAction;
import jabberpoint.model.exception.NotImplementedExcpeption;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.TextItem;
import jabberpoint.model.util.Parameters;

import java.util.Arrays;

/**
 * Demo accessor knows how to load and save a {@link Slideshow} to and from an in-memory structure.
 * the in-memory structure is a fixed, predefined structure
 */
public class DemoAccessor implements Accessor {

    private static final String DEMO_TITLE = "Demo Presentation";

    @Override
    public void save(final Parameters parameters, final Slideshow slideShow) {
        throw new NotImplementedExcpeption("Saving a Demo slideshow is not implemented");
    }

    @Override
    public Slideshow load(final Parameters parameters) {

        // Create a new instance and store it in the Singleton Slideshow
        Slideshow slideShow = Slideshow.createInstance(Theme.CLASSIC);
        slideShow.setName(parameters.getString(Parameters.Parameter.SLIDESHOW_NAME));
        slideShow.setTitle(DEMO_TITLE);
        Slide slide = new Slide("JabberPoint");
        slide.addComponent(new TextItem(1, "Het Java Presentatie Tool"));
        slide.addComponent(new TextItem(2, "Copyright (c) 1996-2000: Ian Darwin"));
        slide.addComponent(new TextItem(2, "Copyright (c) 2000-now:"));
        slide.addComponent(new TextItem(2, "Gert Florijn en Sylvia Stuurman"));
        slide.addComponent(new TextItem(4, "JabberPoint aanroepen zonder bestandsnaam"));
        slide.addComponent(new TextItem(4, "laat deze presentatie zien"));
        slide.addComponent(new TextItem(1, "Navigeren:"));
        slide.addComponent(new TextItem(3, "Volgende slide: PgDn of Enter"));
        slide.addComponent(new TextItem(3, "Vorige slide: PgUp of up-arrow"));
        slide.addComponent(new TextItem(4, "Laatste slide: End"));
        slide.addComponent(new TextItem(4, "Eerste slide: Home"));
        slide.addComponent(new TextItem(3, "Stoppen: q or Q"));
        slideShow.addComponent(slide);

        slide = new Slide("Demonstratie van levels en stijlen");
        slide.addComponent(new TextItem(1, "Theme van deze presentatie: " + slideShow.getTheme().toString()));
        slide.addComponent(new TextItem(1, "Level 1"));
        slide.addComponent(new TextItem(2, "Level 2"));
        slide.addComponent(new TextItem(1, "Nogmaals level 1"));
        slide.addComponent(new TextItem(1, "Level 1 heeft stijl nummer 1"));
        slide.addComponent(new TextItem(2, "Level 2 heeft stijl nummer 2"));
        slide.addComponent(new TextItem(3, "Zo ziet level 3 er uit"));
        slide.addComponent(new TextItem(4, "En dit is level 4"));
        slideShow.addComponent(slide);

        slide = new Slide("De derde slide");
        slide.addComponent(new TextItem(1, "Om een nieuwe presentatie te openen,"));
        slide.addComponent(new TextItem(2, "gebruik File->Open uit het menu."));
        slide.addComponent(new TextItem(1, " "));
        slide.addComponent(new TextItem(1, "Dit is het einde van de presentatie."));
        slide.addComponent(new BitmapItem(1, "JabberPoint.jpg"));
        slideShow.addComponent(slide);

        slide = new Slide("De vierde slide");
        slide.addComponent(new TextItem(1, "Deze slide bevat een Action"));
        slide.addComponent(new ActionItemDecorator(new TextItem(3, "Go to first slide"),
                        Arrays.asList(
                                new AbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition.FIRST),
                                new AuxilaryAction(AuxilaryAction.Action.BEEP))));

        slideShow.addComponent(slide);

        slideShow.setCurrentSlideNumber(0);

        return slideShow;

    }

}
