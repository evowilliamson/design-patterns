package jabberpoint.controller;

import java.awt.Point;
import java.awt.Rectangle;

import jabberpoint.model.slideitems.SlideItem;

public class BoundingBox {

	Rectangle r;
	SlideItem s;
	
	// Blocking construction with no arguments.
	private BoundingBox() {
	}
	
	public BoundingBox(Rectangle r, SlideItem s){
		this.r = r;
		this.s = s;
		
	}
	
	protected boolean contains(Point p){
		return r.contains(p);
	}
	
	protected SlideItem getItem(){
		return s;
	}

}
