package view.tbar.popupdisplays;

import javafx.scene.image.ImageView;

/**
 * 
 * Interface for all game elements that can be edited in Game Authoring
 * Environment (actors, levels, and game itself)
 * 
 * @author Stephen
 *
 */

public interface IEditableElement {

	/**
	 * sets the IEditableGameElement's name to the given name
	 * 
	 * @param name
	 *            to be set as IEditableGameElement's name
	 */
	public void setName(String name);

	/**
	 * 
	 * @return the IEditableGameElement's name
	 */
	public String getName();

	/**
	 * 
	 * @return the IEditableGameElement's ImageView
	 */
	public ImageView getImageView();

	/**
	 * sets the IEditableGameElement's ImageView to the given ImageView
	 * 
	 * @param imageView
	 *            to be set as IEditableGameElement's ImageView
	 */
	public void setImage(ImageView imageView);

}
