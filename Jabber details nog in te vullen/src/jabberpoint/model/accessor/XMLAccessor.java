package jabberpoint.model.accessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jabberpoint.model.CompositeSlideShowComponent;
import jabberpoint.model.Slide;
import jabberpoint.model.Slideshow;
import jabberpoint.model.action.AbsoluteNavigationAction;
import jabberpoint.model.action.AuxilaryAction;
import jabberpoint.model.old.BitmapItemOld;
import jabberpoint.model.old.SlideOld;
import jabberpoint.model.old.TextItemOld;

/*
import jabberpoint.model.old.BitmapItemOld;
import jabberpoint.model.old.SlideItemOld;
import jabberpoint.model.old.SlideOld;
import jabberpoint.model.old.TextItemOld;
*/

import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.SlideItem;
import jabberpoint.model.slideitems.TextItem;
import jabberpoint.model.util.Parameters;

public class XMLAccessor implements Accessor {
    
	/** Default API to use. */
    protected static final String DEFAULT_API_TO_USE = "dom";
    
    /** namen van xml tags of attributen */
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    
    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
    
    
    private String getTitle(Element element, String tagName) {
    	NodeList titles = element.getElementsByTagName(tagName);
    	return titles.item(0).getTextContent();
    	
    }

    protected void loadSlideItem(Slide slide, Element item) {
		int level = 1; // default
		NamedNodeMap attributes = item.getAttributes();
		String leveltext = attributes.getNamedItem(LEVEL).getTextContent();
		if (leveltext != null) {
			try {
				level = Integer.parseInt(leveltext);
			}
			catch(NumberFormatException x) {
				System.err.println(NFE);
			}
		}
		String type = attributes.getNamedItem(KIND).getTextContent();
		if (TEXT.equals(type)) {
			slide.addComponent(new TextItem(level, item.getTextContent()));
		}
		else {
			if (IMAGE.equals(type)) {
				slide.addComponent(new BitmapItem(level, item.getTextContent()));
			}
			else {
				System.err.println(UNKNOWNTYPE);
			}
		}
	}

	@Override
    public void save(final Parameters parameters, final Slideshow slideShow) {
    	try {
    		String filename = parameters.getString(Parameters.Parameter.FILE_NAME);
    		PrintWriter out = new PrintWriter(new FileWriter(filename));
    		int amountOfSlides = slideShow.getComponentCount();
    		out.println("<?xml version=\"1.0\"?>");
    		out.println("<!DOCTYPE presentation SYSTEM \"jabberpoint.dtd\">");
    		out.println("<presentation>");
    		out.print("<showtitle>");
    		out.print(slideShow.getTitle());
    		out.println("</showtitle>");
    		for (int slideNumber=0; slideNumber < amountOfSlides; slideNumber++) {
    			Slide slide = (Slide) slideShow.getComponent(slideNumber);
    			out.println("<slide>");
    			out.println("<title>" + slide.getTitle() + "</title>");
    			int amountOfItems = slide.getComponentCount();
    			for (int itemNumber = 0; itemNumber < amountOfItems; itemNumber++) {
    				SlideItem slideItem = (SlideItem) slide.getComponent(itemNumber);
    				out.print("<item kind="); 
    				if (slideItem instanceof TextItem) {
    					out.print("\"text\" level=\"" + slideItem.getLevel() + "\">");
    					out.print( ( (TextItem) slideItem).getText());
    				}
    				else {
    					if (slideItem instanceof BitmapItem) {
    						out.print("\"image\" level=\"" + slideItem.getLevel() + "\">");
    						out.print( ( (BitmapItem) slideItem).getFileName());
    					}
    					else {
    						System.out.println("Ignoring " + slideItem);
    					}
    				}
    				out.println("</item>");
    			}
    			out.println("</slide>");
    		}
    		out.println("</presentation>");
    		out.close();
		}
		catch (Exception iox) {
			System.err.println(iox.toString());
		}
    }

	@Override
    public Slideshow load(final Parameters parameters) {
		String filename = parameters.getString(Parameters.Parameter.FILE_NAME);
		Slideshow slideShow = Slideshow.createInstance();
        
		int slideNumber, itemNumber, max = 0, maxItems = 0;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();    
			Document document = builder.parse(new File(filename)); // maak een JDOM document
			Element doc = document.getDocumentElement();
			slideShow.setTitle(getTitle(doc, SHOWTITLE));

			NodeList slides = doc.getElementsByTagName(SLIDE);
			max = slides.getLength();
			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);
				Slide slide = new Slide(getTitle(xmlSlide, SLIDETITLE));
				//slide.setTitle(getTitle(xmlSlide, SLIDETITLE));
				slideShow.addComponent(slide);
				
				NodeList slideItems = xmlSlide.getElementsByTagName(ITEM);
				maxItems = slideItems.getLength();
				for (itemNumber = 0; itemNumber < maxItems; itemNumber++) {
					Element item = (Element) slideItems.item(itemNumber);
					loadSlideItem(slide, item);
				}
			}
		} 
		catch (IOException iox) {
			System.err.println(iox.toString());
		}
		catch (SAXException sax) {
			System.err.println(sax.getMessage());
		}
		catch (ParserConfigurationException pcx) {
			System.err.println(PCE);
		}
		/*
        // Create a new instance and store it in the Singleton Slideshow
        Slideshow slideShow = Slideshow.createInstance();
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

        slideShow.addSlide(slide);*/

        slideShow.setCurrentSlideNumber(1);

        return slideShow;
    }
}
