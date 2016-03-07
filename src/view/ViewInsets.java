/**
 * this class is an enum of the insets for each area of the border pane
 * @author calinelson
 */
package view;

import javafx.geometry.Insets;

public enum ViewInsets {
	RIGHT(new Insets(0, 10,5,5)), 
	LEFT(new Insets(0,5,5,10)), 
	TOP(new Insets(10,0,10,10)), 
	TURTLE(new Insets(0,5,5, 5)), 
	BOTTOM(new Insets(5,10,10,10));
	
	private Insets inset;
	ViewInsets(Insets i){
		this.inset = i;
		
	}
	
	/**
	 * returns insets for border pane area based on area name
	 * @return insets object 
	 */
	public Insets getInset(){
		return this.inset;
	}
	
}
