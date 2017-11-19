package jabberpoint.model.old;

import jabberpoint.model.old.AccessorOld;
import jabberpoint.model.old.BitmapItemOld;
import jabberpoint.model.old.PresentationOld;
import jabberpoint.model.old.SlideOld;

/**
 * Een ingebouwde demo-presentatie
 * 
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

class DemoPresentation extends AccessorOld {

    public void loadFile(PresentationOld presentation, String unusedFilename) {
        presentation.setTitle("Demo Presentation");
        SlideOld slide;
        slide = new SlideOld();
        slide.setTitle("JabberPoint");
        slide.append(1, "Het Java Presentatie Tool");
        slide.append(2, "Copyright (c) 1996-2000: Ian Darwin");
        slide.append(2, "Copyright (c) 2000-now:");
        slide.append(2, "Gert Florijn en Sylvia Stuurman");
        slide.append(4, "JabberPoint aanroepen zonder bestandsnaam");
        slide.append(4, "laat deze presentatie zien");
        slide.append(1, "Navigeren:");
        slide.append(4, "Volgende slide: PgDn of Enter");
        slide.append(4, "Vorige slide: PgUp of up-arrow");
        slide.append(4, "Stoppen: q or Q");
        presentation.append(slide);

        slide = new SlideOld();
        slide.setTitle("Demonstratie van levels en stijlen");
        slide.append(1, "Level 1");
        slide.append(2, "Level 2");
        slide.append(1, "Nogmaals level 1");
        slide.append(1, "Level 1 heeft stijl nummer 1");
        slide.append(2, "Level 2 heeft stijl nummer 2");
        slide.append(3, "Zo ziet level 3 er uit");
        slide.append(4, "En dit is level 4");
        presentation.append(slide);

        slide = new SlideOld();
        slide.setTitle("De derde slide");
        slide.append(1, "Om een nieuwe presentatie te openen,");
        slide.append(2, "gebruik File->Open uit het menu.");
        slide.append(1, " ");
        slide.append(1, "Dit is het einde van de presentatie.");
        slide.append(new BitmapItemOld(1, "JabberPoint.jpg"));
        presentation.append(slide);
    }

    public void saveFile(PresentationOld presentation, String unusedFilename) {
        throw new IllegalStateException("Save As->Demo! aangeroepen");
    }
}
