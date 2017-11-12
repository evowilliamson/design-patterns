package jabberpoint.model.accessor;

import jabberpoint.model.Slide;
import jabberpoint.model.SlideShow;
import jabberpoint.model.action.AbsoluteNavigationAction;
import jabberpoint.model.action.AuxilaryAction;
import jabberpoint.model.exception.NotImplementedExcpeption;
import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.BorderActionIndicator;
import jabberpoint.model.slideitems.NoActionIndicator;
import jabberpoint.model.slideitems.TextItem;
import jabberpoint.model.util.Parameters;

/**
 * Demo accessor knows how to load and save a {@link SlideShow} to and from an in-memory structure.
 * the in-memory structure is a fixed, predefined structure
 */
public class DemoAccessor implements Accessor {

    private static final String DEMO_TITLE = "Demo Presentation";

    @Override
    public void save(final Parameters parameters, final SlideShow slideShow) {
        throw new NotImplementedExcpeption("Saving a Demo slideshow is not implemented");
    }

    @Override
    public SlideShow load(final Parameters parameters) {

        // SlideShow is a singleton, so get the instance to work with
        SlideShow slideShow = SlideShow.createInstance();
        slideShow.setName(parameters.getString(Parameters.Parameter.SLIDESHOW_NAME));
        slideShow.setTitle(DEMO_TITLE);
        Slide slide = new Slide("JabberPoint");
        slide.addSlideItem(new TextItem(1, "Het Java Presentatie Tool"));
        slide.addSlideItem(new TextItem(2, "Copyright (c) 1996-2000: Ian Darwin"));
        slide.addSlideItem(new TextItem(2, "Copyright (c) 2000-now:"));
        slide.addSlideItem(new TextItem(2, "Gert Florijn en Sylvia Stuurman"));
        slide.addSlideItem(new TextItem(4, "JabberPoint aanroepen zonder bestandsnaam"));
        slide.addSlideItem(new TextItem(4, "laat deze presentatie zien"));
        slide.addSlideItem(new TextItem(1, "Navigeren:"));
        slide.addSlideItem(new TextItem(3, "Volgende slide: PgDn of Enter"));
        slide.addSlideItem(new TextItem(3, "Vorige slide: PgUp of up-arrow"));
        slide.addSlideItem(new TextItem(3, "Stoppen: q or Q"));
        slideShow.addSlide(slide);

        slide = new Slide("Demonstratie van levels en stijlen");
        slide.addSlideItem(new TextItem(1, "Level 1"));
        slide.addSlideItem(new TextItem(2, "Level 2"));
        slide.addSlideItem(new TextItem(1, "Nogmaals level 1"));
        slide.addSlideItem(new TextItem(1, "Level 1 heeft stijl nummer 1"));
        slide.addSlideItem(new TextItem(2, "Level 2 heeft stijl nummer 2"));
        slide.addSlideItem(new TextItem(3, "Zo ziet level 3 er uit"));
        slide.addSlideItem(new TextItem(4, "En dit is level 4"));
        slideShow.addSlide(slide);

        slide = new Slide("De derde slide");
        slide.addSlideItem(new TextItem(1, "Om een nieuwe presentatie te openen,"));
        slide.addSlideItem(new TextItem(2, "gebruik File->Open uit het menu."));
        slide.addSlideItem(new TextItem(1, " "));
        slide.addSlideItem(new TextItem(1, "Dit is het einde van de presentatie."));
        slide.addSlideItem(new BitmapItem(1, "JabberPoint.jpg"));
        slideShow.addSlide(slide);

        slide = new Slide("De vierde slide");
        slide.addSlideItem(new TextItem(1, "Deze slide bevat een Action"));
        slide.addSlideItem(
                new NoActionIndicator(1,
                    new AbsoluteNavigationAction(AbsoluteNavigationAction.NavigationPosition.FIRST),
                        new BorderActionIndicator(2,
                                new AuxilaryAction(AuxilaryAction.Action.BEEP),
                                    new TextItem(3, "Go to first slide"))));

        slideShow.addSlide(slide);

        slideShow.setCurrentSlideNumber(1);

        return slideShow;

    }

    /**
     * Testing
     * @param args
     */
    public static void main(String[] args) {

        Parameters parameters = new Parameters();
        parameters.setValue(Parameters.Parameter.SLIDESHOW_NAME, "Test");
        SlideShow slideShow = new DemoAccessor().load(parameters);

    }

}
