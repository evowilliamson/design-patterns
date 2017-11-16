package jabberpoint.controller;

import java.awt.Point;
import java.awt.Rectangle;

import jabberpoint.model.slideitems.ActionItemDecorator;
import jabberpoint.model.slideitems.SlideItem;

public class BoundingBox {

	Rectangle r;
	ActionItemDecorator a;
	
	// Blocking construction with no arguments.
	private BoundingBox() {
	}
	
	public BoundingBox(Rectangle r, ActionItemDecorator a){
		this.r = r;
		this.a = a;
		
	}
	
	protected boolean contains(Point p){
		return r.contains(p);
	}
	
	protected ActionItemDecorator getItem(){
		return a;
	}

}
