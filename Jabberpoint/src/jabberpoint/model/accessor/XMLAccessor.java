package jabberpoint.model.accessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jabberpoint.model.slideitems.DisplayableItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
    
    protected static final String HEAD = "head"; 
    protected static final String SLIDESHOW = "slideshow";
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String TITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String PRESENTATION = "presentation";
    
    //kinds
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    protected static final String ACTIONITEM = "actionItem";
    protected static final String IGNORING = "ignoring";
    
    //actions
    protected static final String NAVIGATIONACTION = "navigationAction";
    
    protected static final String ABSOLUTENAVIGATIONACTION = "absoluteNavigationAction";
    protected static final String FIRST = "first";
    protected static final String LAST = "last";
    protected static final String INDEX = "index";
    
    protected static final String RELATIVENAVIGATIONACTION = "relativeNavigationAction";
    protected static final String NEXT = "next";
    protected static final String PREVIOUS = "previous";
    
    protected static final String AUXILIARYACTION = "auxiliaryAction";
    protected static final String BEEP = "beep";
    protected static final String FLASH = "flash";
    
    protected static final String SLIDESHOWACTION = "slideShowAction";
    protected static final String OPENDEMOSLIDESHOWACTION = "openDemoSlideshowAction";

    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
    
    protected XMLAccessor() {
    }


    @Override
    public void save(final Parameters parameters, final Slideshow slideShow) {
    	try {
    		String filename = parameters.getString(Parameters.Parameter.FILE_NAME);
    		PrintWriter out = new PrintWriter(new FileWriter(filename));
    		int amountOfSlides = slideShow.getComponentCount();
    		out.println("<?xml version=\"1.0\"?>");
    		out.println("<!DOCTYPE slideshow SYSTEM \"jabberpoint.dtd\">");
    		out.println("<" + PRESENTATION + ">");
    		out.print("<" + SHOWTITLE +">");
    		out.print(slideShow.getTitle());
    		out.println("</"+SHOWTITLE+">");
    		for (int slideNumber=0; slideNumber < amountOfSlides; slideNumber++) {
    			Slide slide = (Slide) slideShow.getComponent(slideNumber);
    			out.println("<" + SLIDE + ">");
    			out.println("<" + SLIDETITLE + ">" + slide.getTitle() + "</" + SLIDETITLE +">");
    			int amountOfItems = slide.getComponentCount();
    			for (int itemNumber = 0; itemNumber < amountOfItems; itemNumber++) {
    				SlideItem slideItem = (SlideItem) slide.getComponent(itemNumber);
    				out.print("<" + ITEM + " " + KIND + "="); 
    				if (slideItem instanceof TextItem) {
    					out.print("\""+TEXT+"\" "+LEVEL+"=\"" + ((DisplayableItem) slideItem).getLevel() + "\">");
    					out.print( ( (TextItem) slideItem).getText());
    				}
    				else {
    					if (slideItem instanceof BitmapItem) {
    						out.print("\""+IMAGE+"\" "+LEVEL+"=\"" + ((DisplayableItem) slideItem).getLevel() + "\">");
    						out.print( ( (BitmapItem) slideItem).getFileName());
    					}
    					else {
    						System.out.println(IGNORING+ " " + slideItem);
    					}
    				}
    				out.println("</"+ITEM+">");
    			}
    			out.println("</"+SLIDE+">");
    		}
    		out.println("</"+PRESENTATION+">");
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
			doc.normalize();
			NodeList headers = doc.getElementsByTagName(HEAD);
			Element header = (Element)headers.item(0);
			String showtitle = header.getElementsByTagName(TITLE).item(0).getTextContent();
			
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
		
        slideShow.setCurrentSlideNumber(0);

        return slideShow;
    }
}