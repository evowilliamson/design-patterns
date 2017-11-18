package jabberpoint.model.accessor;

import jabberpoint.model.Slide;
import jabberpoint.model.SlideFactory;
import jabberpoint.model.Slideshow;
import jabberpoint.model.Theme;
import jabberpoint.model.action.AbsoluteNavigationAction;
import jabberpoint.model.action.ActionFactory;
import jabberpoint.model.action.AuxiliaryAction;
import jabberpoint.model.exception.NotImplementedException;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.SlideItemFactory;
import jabberpoint.model.slideitems.TextItem;
import jabberpoint.model.util.Parameters;

import java.util.Arrays;

/**
 * Demo accessor knows how to load and save a {@link Slideshow} to and from an in-memory structure.
 * the in-memory structure is a fixed, predefined structure
 * @author Ivo Willemsen
 */
public class DemoAccessor implements Accessor {

    private static final String DEMO_TITLE = "Demo Presentation";

    @Override
    public void save(final Parameters parameters, final Slideshow slideShow) {
        throw new NotImplementedException("Saving a Demo slideshow is not implemented");
    }

    @Override
    public Slideshow load(final Parameters parameters) {

        // Create a new instance and store it in the Singleton Slideshow
        Slideshow slideShow = Slideshow.createInstance(Theme.CLASSIC);
        slideShow.setName(parameters.getString(Parameters.Parameter.SLIDESHOW_NAME));
        slideShow.setTitle(DEMO_TITLE);
        Slide slide = SlideFactory.createSlide("JabberPoint");
        slide.addComponent(SlideItemFactory.createTextItem(1, "Het Java Presentatie Tool"));
        slide.addComponent(SlideItemFactory.createTextItem(2, "Copyright (c) 1996-2000: Ian Darwin"));
        slide.addComponent(SlideItemFactory.createTextItem(2, "Copyright (c) 2000-now:"));
        slide.addComponent(SlideItemFactory.createTextItem(2, "Gert Florijn en Sylvia Stuurman"));
        slide.addComponent(SlideItemFactory.createTextItem(4, "JabberPoint aanroepen zonder bestandsnaam"));
        slide.addComponent(SlideItemFactory.createTextItem(4, "laat deze presentatie zien"));
        slide.addComponent(SlideItemFactory.createTextItem(1, "Navigeren:"));
        slide.addComponent(SlideItemFactory.createTextItem(3, "Volgende slide: PgDn of Enter"));
        slide.addComponent(SlideItemFactory.createTextItem(3, "Vorige slide: PgUp of up-arrow"));
        slide.addComponent(SlideItemFactory.createTextItem(4, "Laatste slide: End"));
        slide.addComponent(SlideItemFactory.createTextItem(4, "Eerste slide: Home"));
        slide.addComponent(SlideItemFactory.createTextItem(3, "Stoppen: q or Q"));
        slideShow.addComponent(slide);

        slide = SlideFactory.createSlide("Demonstratie van levels en stijlen");
        slide.addComponent(SlideItemFactory.createTextItem(1, "Theme van deze presentatie: " + slideShow.getTheme().toString()));
        slide.addComponent(SlideItemFactory.createTextItem(1, "Level 1"));
        slide.addComponent(SlideItemFactory.createTextItem(2, "Level 2"));
        slide.addComponent(SlideItemFactory.createTextItem(1, "Nogmaals level 1"));
        slide.addComponent(SlideItemFactory.createTextItem(1, "Level 1 heeft stijl nummer 1"));
        slide.addComponent(SlideItemFactory.createTextItem(2, "Level 2 heeft stijl nummer 2"));
        slide.addComponent(SlideItemFactory.createTextItem(3, "Zo ziet level 3 er uit"));
        slide.addComponent(SlideItemFactory.createTextItem(4, "En dit is level 4"));
        slideShow.addComponent(slide);

        slide = SlideFactory.createSlide("De derde slide");
        slide.addComponent(SlideItemFactory.createTextItem(1, "Om een nieuwe presentatie te openen,"));
        slide.addComponent(SlideItemFactory.createTextItem(2, "gebruik File->Open uit het menu."));
        slide.addComponent(SlideItemFactory.createTextItem(1, " "));
        slide.addComponent(SlideItemFactory.createTextItem(1, "Dit is het einde van de presentatie."));
        slide.addComponent(SlideItemFactory.createBitmapItem(1, "JabberPoint.jpg"));
        slideShow.addComponent(slide);

        slide = SlideFactory.createSlide("De vierde slide");
        slide.addComponent(SlideItemFactory.createTextItem(1, "Deze slide bevat een Action"));
        slide.addComponent(SlideItemFactory.createActionItemDecorator(SlideItemFactory.createTextItem(3, "Go to first slide"),
                        Arrays.asList(
                                ActionFactory.createAbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition.FIRST),
                                ActionFactory.createAuxiliaryAction(AuxiliaryAction.AuxAction.BEEP))));

        slideShow.addComponent(slide);

        slideShow.setCurrentSlideNumber(0);

        return slideShow;

    }

}
