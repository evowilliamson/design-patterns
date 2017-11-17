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
import jabberpoint.model.SlideFactory;
import jabberpoint.model.Slideshow;
import jabberpoint.model.Theme;
import jabberpoint.model.slideitems.BitmapItem;
import jabberpoint.model.slideitems.SlideItem;
import jabberpoint.model.slideitems.SlideItemFactory;
import jabberpoint.model.slideitems.TextItem;
import jabberpoint.model.util.Parameters;

public class XMLAccessor implements Accessor {
    
	/** Default API to use. */
    protected static final String DEFAULT_API_TO_USE = "dom";
    
    /** namen van xml tags of attributen */
    
    protected static final String SLIDESHOW = "slideshow";
    protected static final String SHOWTITLE = "title";
    protected static final String SLIDETITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    
    //kinds
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    protected static final String ACTIONITEM = "actionItem";
    
    //actions
    protected static final String NAVIGATIONACTION = "navigationAction";
    
    protected static final String ABSOLUTENAVIGATIONACTION = "absoluteNavigationAction";
    protected static final String FIRST = "first";
    protected static final String LAST = "last";
    protected static final String INDEX = "index";
    
    protected static final String RELATIVENAVIGATIONACTION = "relativeNavigationAction";
    protected static final String NEXT = "next";
    protected static final String PREVIOUS = "previous";
    
    protected static final String AUXILARYACTION = "auxilaryAction";
    protected static final String BEEP = "beep";
    protected static final String FLASH = "flash";
    
    protected static final String SLIDESHOWACTION = "slideShowAction";
    protected static final String OPENDEMOSLIDESHOWACTION = "openDemoSlideshowAction";
    
    
    
    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
    
    protected XMLAccessor(){}
    
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
			slide.addComponent(SlideItemFactory.createTextItem(level, item.getTextContent()));
		}
		else {
			if (IMAGE.equals(type)) {
				slide.addComponent(SlideItemFactory.createBitmapItem(level, item.getTextContent()));
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
    		out.println("<!DOCTYPE slideshow SYSTEM \"jabberpointNew.dtd\">");
    		out.println("<"+SLIDESHOW+">");
    		out.print("<"+SHOWTITLE+">");
    		out.print(slideShow.getTitle());
    		out.println("</"+SHOWTITLE+">");
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
		Slideshow slideShow = Slideshow.createInstance(Theme.MODERN);
        
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
				Slide slide = SlideFactory.createSlide(getTitle(xmlSlide, SLIDETITLE));
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
		
        slideShow.setCurrentSlideNumber(1);

        return slideShow;
    }
}
