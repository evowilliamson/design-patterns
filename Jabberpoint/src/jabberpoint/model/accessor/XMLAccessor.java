package jabberpoint.model.accessor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
import jabberpoint.model.action.AbsoluteNavigationAction;
import jabberpoint.model.action.AbsoluteNavigationAction.NavigationPosition;
import jabberpoint.model.action.Action;
import jabberpoint.model.action.ActionFactory;
import jabberpoint.model.action.AuxiliaryAction;
import jabberpoint.model.action.AuxiliaryAction.AuxAction;
import jabberpoint.model.action.OpenDemoSlideshowAction;
import jabberpoint.model.action.RelativeNavigationAction;
import jabberpoint.model.action.RelativeNavigationAction.NavigationDirection;
import jabberpoint.model.exception.NotImplementedException;
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
    protected static final String ARGUMENT = "argument";
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

    private ArrayList<Action> actionList = null;
    private SlideItem lastSlideItem = null;
    
    private void writeAction(Action action, PrintWriter out){
    	if (action instanceof AbsoluteNavigationAction){
    		AbsoluteNavigationAction navAction = (AbsoluteNavigationAction) action;
    		NavigationPosition pos = navAction.getPosition();
    		if (pos == NavigationPosition.INDEX){
    			out.print(INDEX);
    			out.print("\" "+ARGUMENT+"=\"");
    			out.print(navAction.getSlideNumber());
    		} else if (pos == NavigationPosition.FIRST){
    			out.print(FIRST);
    		} else if (pos == NavigationPosition.LAST){
    			out.print(LAST);
    		} else {
    			throw new NotImplementedException("It is not allowed to write actions wich are not implemented.");
    		}
    	} else if (action instanceof RelativeNavigationAction){
    		RelativeNavigationAction navAction = (RelativeNavigationAction) action;
    		NavigationDirection dir = navAction.getNavigationDirection();
    		if (dir == NavigationDirection.NEXT){
    			out.print(NEXT);
    		} else if (dir == NavigationDirection.PREVIOUS){
    			out.print(PREVIOUS);
    		} else {
    			throw new NotImplementedException("It is not allowed to write actions wich are not implemented.");
    		}
    	} else if (action instanceof AuxiliaryAction){
    		AuxiliaryAction auxAction = (AuxiliaryAction) action;
    		AuxAction aux = auxAction.getAction();
    		if (aux == AuxAction.BEEP){
    			out.print(BEEP);
    		} else if (aux == AuxAction.FLASH){
    			out.print(FLASH);
    		} else if (aux == AuxAction.EXIT){
    			out.print(EXIT);
    		} else {
    			throw new NotImplementedException("It is not allowed to write actions wich are not implemented.");
    		}
    	} else if (action instanceof OpenDemoSlideshowAction){
    		out.print(OPENDEMOSLIDESHOWACTION);
    	}	
    }
    
    private void writeActionItem(ActionItemDecorator item, PrintWriter out, int actionNumber){
    	List<Action> actions = item.getActions();
    	if (actionNumber < actions.size()){
    		Action action = actions.get(actionNumber);
    		out.print("<"+ACTION+" name=\"");
    		writeAction(action, out);
    		out.println("\">");
    		writeActionItem(item, out, actionNumber + 1);
    		out.println("</"+ACTION+">");
    	} else{
    		writeItem(item.getSlideItem(), out);
    	}
    }
    
    private void writeItem(SlideItem item, PrintWriter out){
    	if (item instanceof BitmapItem){
    		BitmapItem bitmapItem = (BitmapItem) item;
    		out.print("<"+IMAGE+" "+LEVEL+"=\""+bitmapItem.getLevel()+"\">");
    		out.print(bitmapItem.getFileName());
    		out.println("</"+IMAGE+">");
    	} else if (item instanceof TextItem){
    		TextItem textItem = (TextItem) item;
    		out.print("<"+TEXT+" "+LEVEL+"=\""+textItem.getLevel()+"\">");
    		out.print(textItem.getText());
    		out.println("</"+TEXT+">");
    	} else if (item instanceof ActionItemDecorator){
    		ActionItemDecorator actionItem = (ActionItemDecorator) item;
    		writeActionItem(actionItem, out, 0);
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
    		out.println("<" + SLIDESHOW + ">");
    		out.println("<" + HEAD + ">");
    		out.println("<" + TITLE +">"+slideShow.getTitle()+"</"+TITLE+">");
    		out.println("<"+THEME+">"+slideShow.getTheme().name()+"</"+THEME+">");
    		out.println("<"+SLIDENUMBER+">"+slideShow.getCurrentSlideNumber()+1+"</"+SLIDENUMBER+">");
    		out.println("</"+HEAD+">");
    		for (int slideNumber=0; slideNumber < amountOfSlides; slideNumber++) {
    			Slide slide = (Slide) slideShow.getComponent(slideNumber);
    			out.println("<"+SLIDE+">");
    			out.println("<"+TITLE+">" +slide.getTitle()+"</"+TITLE+">");
    			out.println("<"+ITEMS+">");
    			int amountOfItems = slide.getComponentCount();
    			for (int itemNumber = 0; itemNumber < amountOfItems; itemNumber++) {
    				SlideItem slideItem = (SlideItem) slide.getComponent(itemNumber);
    				writeItem(slideItem, out);
    			}
    			out.println("</"+ITEMS+">");
    			out.println("</"+SLIDE+">");
    		}
    		out.println("</"+SLIDESHOW+">");
    		out.close();
		}
		catch (Exception iox) {
			System.err.println(iox.toString());
		}
    }

    private void getDecoratedActions(Element element){
    	try{
    		String name = element.getAttribute(NAME);
    		if  (name.equals(FIRST)){
    			actionList.add(ActionFactory.createAbsoluteNavigationAction(NavigationPosition.FIRST));
    		} else if (name.equals(LAST)){
    			actionList.add(ActionFactory.createAbsoluteNavigationAction(NavigationPosition.LAST));
    		} else if (name.equals(INDEX)){
    			String argument = element.getAttribute(ARGUMENT);
    			Action action = ActionFactory.createAbsoluteNavigationAction(Integer.valueOf(argument));
    			actionList.add(action);
    		} else if (name.equals(NEXT)){
    			actionList.add(ActionFactory.createRelativeNavigationAction(NavigationDirection.NEXT));
    		} else if (name.equals(PREVIOUS)){
    			actionList.add(ActionFactory.createRelativeNavigationAction(NavigationDirection.PREVIOUS));
    		} else if (name.equals(BEEP)){
    			actionList.add(ActionFactory.createAuxiliaryAction(AuxAction.BEEP));
    		} else if (name.equals(FLASH)){
    			actionList.add(ActionFactory.createAuxiliaryAction(AuxAction.FLASH));
    		} else if (name.equals(EXIT)){
    			actionList.add(ActionFactory.createAuxiliaryAction(AuxAction.EXIT));
    		} else if (name.equals(OPENDEMOSLIDESHOWACTION)){
    			actionList.add(ActionFactory.createOpenDemoSlideshowAction());
    		} else {
    			System.out.println("Parsing of unknown action.");
    		}
    		NodeList nodes = element.getChildNodes();
			int maxNodes = nodes.getLength();
			
			SlideItem  slideItem = null;
			for (int i = 0; i < maxNodes; i++){
				Node node = nodes.item(i);
				if(node instanceof Element){
					getItem((Element) node);
				}
			}
    	}
    	catch (NumberFormatException e){
    		System.err.println(NFE);
    	}
    }
    private SlideItem getVisibleItem(Element element){
    	try{
    		if (element.getTagName().equals(TEXT)){
    			String level = element.getAttribute(LEVEL);
    			String content = element.getTextContent();
    			int i = Integer.parseInt(level);
    			return SlideItemFactory.createTextItem(i, content);
    		} else if (element.getTagName().equals(IMAGE)){
    			String level = element.getAttribute(LEVEL);
    			String content = element.getTextContent();
    			int i = Integer.parseInt(level);
    			return SlideItemFactory.createBitmapItem(i, content);
    		} else {
    			return lastSlideItem;// is not a visible Item
    		}
    	}
    	catch (NumberFormatException e){
    		System.err.println(NFE);
    		return lastSlideItem;
    	}
    }
    private SlideItem getItem(Element element){
    	if (element.getTagName().equals(ACTION)){
    		if (actionList == null) // first begin
    		{
    			actionList = new ArrayList<Action>();
    			getDecoratedActions(element);
    			SlideItem actionItem = SlideItemFactory.createActionItemDecorator(lastSlideItem, actionList);
    			return actionItem;
    		} else{
    			getDecoratedActions(element);
    			return lastSlideItem;
    		}
    		
    	} else { // is visible item
    		lastSlideItem =  getVisibleItem(element);
    		return lastSlideItem;
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
			if (slidenumbers.getLength() > 0) // slideNumber is present
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
			slideshow.setTitle(showtitle);
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
						// resetting own vars
						actionList = null;
						lastSlideItem = null;
					}
				}
				slideshow.addComponent(slide);
				
			}
			slideshow.setCurrentSlideNumber(setSlideNumber-1);
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