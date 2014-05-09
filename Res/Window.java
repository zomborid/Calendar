package Res;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Res.Edit.DeleteEditWindow;
import Res.Edit.NewEditWindow;
import Res.Edit.NoSelect;
import Res.Edit.RewriteEditWindow;
import Res.GUI.WeekViewSampleGUI;
import Res.GUI.Views.WeekView;

/**
 * Window class, which extends JFrame to whole GUI.
 * 
 * @author ZODVAAT.SZE / FENVABT.SZE
 */
public class Window extends JFrame {
	/** File's path. */
	private String path;
	/** JMenuBar to menu items. */
	private JMenuBar menu;
	/** JMenu into the menu bar. */
	private JMenu fileMenu;
	private JMenu editMenu;
	private JMenu viewMenu;
	/** JMenuItem into the menus. */
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem exitItem;
	private JMenuItem reWriteItem;
	private JMenuItem deleteItem;
	private JMenuItem newItem;
	private JMenuItem weekItem;
	private JMenuItem monthItem;
	/** JFileChooser to select files to save and open. */
	private JFileChooser fileChooser;

	/**
	 * Default constructor, which make window.
	 */
	public Window() {
		super("Calendar");

		gui = null;
		GUIPanel = getContentPane();

		menu = new JMenuBar();
		fileMenu = new JMenu("File");
		openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener() { // Action listener to openItem
			@Override
			public void actionPerformed(ActionEvent e) { // If select open item show chooser window and store file path
				fileChooser = new JFileChooser();
				fileChooser.showOpenDialog(null);
				path = fileChooser.getSelectedFile().getPath();
			}
		});
		saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new ActionListener() { // Action listener to saveItem
			@Override
			public void actionPerformed(ActionEvent e) { // If select save item show chooser window and store file path
				fileChooser = new JFileChooser();
				fileChooser.showSaveDialog(null);
				path = fileChooser.getSelectedFile().getPath();
			}
		});
		exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener() { // Action listener to exitItem
			@Override
			public void actionPerformed(ActionEvent e) { // If select exit item close the program
				System.exit(0);
			}
		});
		editMenu = new JMenu("Edit");
		newItem = new JMenuItem("New");
		newItem.addActionListener(new ActionListener() { // Action listener to newItem
			@Override
			public void actionPerformed(ActionEvent e) { // If select new item show new edit window
				NewEditWindow n = new NewEditWindow();
				n.setVisible(true);
			}
		});
		reWriteItem = new JMenuItem("Rewrite");
		reWriteItem.addActionListener(new ActionListener() { // Action listener to reWriteItem
					@Override
					public void actionPerformed(ActionEvent e) { // If select rewrite item show rewrite edit window
						if (Res.GUI.WeekViewSampleGUI.selectedEntry == null) {
							NoSelect no = new NoSelect();
							no.setVisible(true);
						} else {
							RewriteEditWindow r = new RewriteEditWindow();
							r.setVisible(true);
						}
					}
				});
		deleteItem = new JMenuItem("Delete");
		deleteItem.addActionListener(new ActionListener() { // Action listener to deleteItem
					@Override
					public void actionPerformed(ActionEvent e) { // If select delete item show delete edit window
						DeleteEditWindow d = new DeleteEditWindow();
						d.setVisible(true);
					}
				});
		viewMenu = new JMenu("View");
		weekItem = new JMenuItem("Week");
		weekItem.addActionListener(new ActionListener() { // Action listener to weekItem
			@Override
			public void actionPerformed(ActionEvent e) { // If select week item refresh gui to week view
				refreshGUI(new WeekViewSampleGUI());
			}
		});
		monthItem = new JMenuItem("Month");
		// monthItem.addActionListener(new ActionListener() { // Action listener to monthItem
		// @Override
		// public void actionPerformed(ActionEvent e) { // If select month item refresh gui to month view
		// refreshGUI(new WeekViewSampleGUI());
		// }
		// });

		setJMenuBar(menu); // Add menu bar to window
		menu.add(fileMenu); // Add menus to menu bar
		menu.add(editMenu);
		menu.add(viewMenu);
		fileMenu.add(openItem); // Add menu items to file menu
		fileMenu.addSeparator();
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		editMenu.add(newItem); // Add menu items to edit menu
		editMenu.addSeparator();
		editMenu.add(reWriteItem);
		editMenu.addSeparator();
		editMenu.add(deleteItem);
		viewMenu.add(weekItem); // Add menu items to view menu
		viewMenu.addSeparator();
		viewMenu.add(monthItem);

		setLayout(new FlowLayout());
		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Point location = new Point(400, 300);
		setLocation(location);

		setResizable(false);

		// setting Window size according to WeekView's size
		int reqWidth = WeekView.getViewWidth();
		int reqHeight = WeekView.getViewHeight();
		setSize(reqWidth, reqHeight);
		Dimension actualSize = getContentPane().getSize();
		int extraW = 50;// reqWidth - actualSize.width;
		int extraH = 100;// reqHeight - actualSize.height;
		setSize(reqWidth + extraW, reqHeight + extraH);

		refreshGUI(new WeekViewSampleGUI()); // loads the first GUI
		setVisible(true);
	}

	private GUIGenerator gui;
	private Container GUIPanel; // Container for changable GUIs

	/**
	 * Sets contents of window.
	 * 
	 * @author ZODVAAT.SZE
	 * @param gui
	 *            Component holder class to show
	 */
	public void refreshGUI(GUIGenerator gui) {
		if (this.gui != null) {
			this.gui.destroy();
		}
		GUIPanel.removeAll();

		this.gui = gui;
		gui.show(this, GUIPanel);

		validate();
		GUIPanel.repaint();
	}
}
