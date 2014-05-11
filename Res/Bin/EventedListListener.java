package Res.Bin;

import java.util.List;

/**
 * Listener for EventedList events.
 * 
 * @author ZODVAAT.SZE
 */
public interface EventedListListener<ListItem>{
	/**
	 * Called when single list item modified.
	 * 
	 * @author ZODVAAT.SZE
	 * @param item Modified item.
	 */
	public void itemModified(ListItem item);
	/**
	 * Called when full list modified.
	 * 
	 * @author ZODVAAT.SZE
	 * @param oldVersion Old entries of list.
	 * @param newVersion New entries of list.
	 */
	public void listModified(List<ListItem> oldVersion, List<ListItem> newVersion);
}
