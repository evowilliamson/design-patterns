package jabberpoint.model.accessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jabberpoint.model.slideitems.DisplayableItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import jabberpoint.model.Slide;
import jabberpoint.model.SlideFactory;
import jabberpoint.model.Slideshow;
import jabberpoint.model.Theme;
import jabberpoint.model.action.AbsoluteNavigationAction.NavigationPosition;
import jabberpoint.model.action.Action;
import jabberpoint.model.action.ActionFactory;
import jabberpoint.model.slideitems.ActionItemDecorator;
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
    protected static final String THEME = "theme";
    protected static final String SLIDENUMBER = "slidenumber";
    protected static final String SLIDESHOW = "slideshow";
    protected static final String SHOWTITLE = "showtitle";
    protected static final String SLIDETITLE = "title";
    protected static final String TITLE = "title";
    protected static final String SLIDE = "slide";
    protected static final String ITEM = "item";
    protected static final String ITEMS = "items";
    protected static final String LEVEL = "level";
    protected static final String KIND = "kind";
    protected static final String PRESENTATION = "presentation";
    
    //kinds
    protected static final String TEXT = "text";
    protected static final String IMAGE = "image";
    protected static final String ACTION = "action";
    protected static final String ACTIONITEM = "actionItem";
    protected static final String IGNORING = "ignoring";
    
    //actions
    protected static final String NAME = "name";
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
    protected static final String EXIT = "exit";
    
    protected static final String SLIDESHOWACTION = "slideShowAction";
    protected static final String OPENDEMOSLIDESHOWACTION = "openDemoSlideshowAction";

    /** tekst van messages */
    protected static final String PCE = "Parser Configuration Exception";
    protected static final String UNKNOWNTYPE = "Unknown Element type";
    protected static final String NFE = "Number Format Exception";
    
    protected XMLAccessor() {
    }

    ArrayList<Action> actionList = null;
    
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

    private void getDecoratedActions(Element element){
    	String name = element.getAttribute(NAME);
    	if (name.equals(ABSOLUTENAVIGATIONACTION)){
    		
    	} else if (name.equals(FIRST)){
    		actionList.add(ActionFactory.createAbsoluteNavigationAction(NavigationPosition.FIRST));
    	} else if (name.equals(LAST)){
    		actionList.add(ActionFactory.createAbsoluteNavigationAction(NavigationPosition.LAST));
    	} else if (name.equals(INDEX)){
    		actionList.add(ActionFactory.createAbsoluteNavigationAction(1))
    	} else if (name.equals(BEEP)){
    		
    	} else if (name.equals(FLASH)){
    		
    	} else if (name.equals(EXIT)){
    		
    	}
    }
    private SlideItem getItem(Element element){
    	if (element.getTagName().equals(ACTION)){
    		if (actionList == null)
    		{
    			actionList = new ArrayList<Action>();
    		}
    		getDecoratedActions(element);
    		
    	}
    }
    
    @Override
    public Slideshow load(final Parameters parameters) {
		String filename = parameters.getString(Parameters.Parameter.FILE_NAME);
		Slideshow slideshow = Slideshow.createInstance(Theme.NORMAL);
		Theme theme = Theme.NORMAL;
        
		int slideNumber = 0, setSlideNumber = 1, itemNumber, max = 0, maxItems = 0;
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();    
			Document document = builder.parse(new File(filename)); // maak een JDOM document
			Element doc = document.getDocumentElement();
			doc.normalize();
			NodeList headers = doc.getElementsByTagName(HEAD);
			Element header = (Element)headers.item(0);
			String showtitle = header.getElementsByTagName(TITLE).item(0).getTextContent();
			NodeList themes = header.getElementsByTagName(THEME);
			if (themes.getLength() > 0) // theme is present
			{
				String configTheme = themes.item(0).getTextContent();
				for (Theme t : Theme.values()) {
			        if (t.name().equals(configTheme)) {
			            theme = t;
			        }
			    }
			}
			NodeList slidenumbers = header.getElementsByTagName(SLIDENUMBER);
			if (slidenumbers.getLength() > 0) // slideNumber  is present
			{
				String numberText = slidenumbers.item(0).getTextContent();
				if (numberText != null) {
					try {
						setSlideNumber = Integer.parseInt(numberText);
					}
					catch(NumberFormatException x) {
						System.err.println(NFE);
					}
				}
			}
			slideshow = Slideshow.createInstance(theme);
			
			NodeList slides = doc.getElementsByTagName(SLIDE);
			max = slides.getLength();
			for (slideNumber = 0; slideNumber < max; slideNumber++) {
				Element xmlSlide = (Element) slides.item(slideNumber);
				NodeList titles = xmlSlide.getElementsByTagName(TITLE);
				Slide slide = SlideFactory.createSlide(titles.item(0).getTextContent());
				
				NodeList items = xmlSlide.getElementsByTagName(ITEMS);
				Element itemList = (Element) items.item(0);
				
				NodeList itemNodes = itemList.getChildNodes();
				int maxItemNodes = itemNodes.getLength();
				
				SlideItem  slideItem = null;
				for (itemNumber = 0; itemNumber < maxItemNodes; itemNumber++){
					Node node = itemNodes.item(itemNumber);
					if(node instanceof Element){
						slideItem = getItem((Element) node);
						slide.addComponent(slideItem);
						actionItemDecorator  = null;
					}
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

        return slideshow;
    }
}