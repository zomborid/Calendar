package Res.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;

import Res.GUIGenerator;
import Res.Window;
import Res.Bin.CalendarEntry;
import Res.Bin.CalendarSelectionChangedListener;
import Res.Bin.EventedList;
import Res.Bin.Interval;
import Res.Data.DataModel;
import Res.GUI.Views.CalendarView;
import Res.GUI.Views.WeekView;

public class ViewGUI implements GUIGenerator {

	private Window parent;
	public static WeekView w;
	private JLabel label;
	public static CalendarEntry selectedEntry;

	@Override
	public CalendarView show(final Window parent, Container container) {
		this.parent = parent;

		final EventedList<CalendarEntry> elist = DataModel.getEntryList();
		Date now = new Date(System.currentTimeMillis() - WeekView.HOUR_MILLIS * 48 * 0);

		elist.add(new CalendarEntry(new Interval(now.getTime() - WeekView.HOUR_MILLIS * 5, now.getTime()
				- WeekView.HOUR_MILLIS * 2), "TestType0", "Long Title0", Color.BLACK, new Color(255, 200, 80),
				"Test Description0"));

		elist.add(new CalendarEntry(new Interval(now.getTime() - WeekView.HOUR_MILLIS * 2, now.getTime()
				+ WeekView.HOUR_MILLIS * 36), "TestType1", "Long Title1", Color.BLACK, new Color(255, 200, 80),
				"Test Description1"));
		elist.add(new CalendarEntry(new Interval(now.getTime() - WeekView.HOUR_MILLIS * 2, now.getTime()
				- WeekView.HOUR_MILLIS * 1), "TestType2", "Long Title2", Color.BLACK, new Color(200, 200, 80),
				"Test Description2"));
		elist.add(new CalendarEntry(new Interval(now.getTime() - WeekView.HOUR_MILLIS * 0, now.getTime()
				+ WeekView.HOUR_MILLIS * 2), "TestType3", "Long Title3", CalendarEntry.DEFAULT_FOREGROUND_COLOR,
				CalendarEntry.DEFAULT_BACKGROUND_COLOR, "Test Description3"));
		elist.add(new CalendarEntry(new Interval(now.getTime() - WeekView.HOUR_MILLIS * 2, now.getTime()
				+ WeekView.HOUR_MILLIS * 0), "TestType4", "Long Title4", Color.BLACK, new Color(255, 200, 80),
				"Test Description4"));
		elist.add(new CalendarEntry(new Interval(now.getTime() - WeekView.HOUR_MILLIS * 1, now.getTime()
				+ WeekView.HOUR_MILLIS * 2), "TestType5", "Long Title5", Color.BLACK, new Color(255, 200, 80),
				"Test Description5"));

		w = new WeekView(elist);

		w.toInterval(now);
		w.addSelectionChangedListener(new CalendarSelectionChangedListener() {
			@Override
			public void selectionChanged(CalendarEntry e) {
				if (e == null) {
					selectedEntry = null;
					System.out.println("Nothing selected");
					return;
				}
				selectedEntry = e;
				System.out.println(e.getType());
			}
		});

		label = new JLabel(w.getDisplayedIntervalStart().toString());

		// JButton btnBack = new JButton("Back");
		// btnBack.addActionListener(new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent event) {
		// parent.refreshGUI(new SampleGUI());
		// }
		// });
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				w.nextInterval();
				label.setText(w.getDisplayedIntervalStart().toString());
			}
		});
		JButton btnPrev = new JButton("Prev");
		btnPrev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				w.prevInterval();
				label.setText(w.getDisplayedIntervalStart().toString());
			}
		});
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (elist.size() > 0) {
					elist.remove(0);
					w.repaint();
				}
			}
		});

		// container.add(btnBack);
		container.add(btnPrev);
		container.add(label);
		container.add(btnNext);
		container.add(btnTest);
		container.add(w);

		return w;
	}

	@Override
	public void destroy() {
	}
}
