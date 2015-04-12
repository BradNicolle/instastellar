package com.spaceappschallenge.adelaide.client;

import java.util.LinkedList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.spaceappschallenge.adelaide.shared.Card;

public class ImageSwitcher extends Composite implements ClickHandler {
	private ListBox listBox = new ListBox();
	private LinkedList<Card> cards = new LinkedList<>();
	private FlexTable thumbs = new FlexTable();
	private LinkedList<String> sources = new LinkedList<>();
	private LinkedList<String> currentSource = new LinkedList<>();
	private Image imageView = new Image();
	HorizontalPanel mainPanel = new HorizontalPanel();
	ScrollPanel imagePanel = new ScrollPanel();


	public ImageSwitcher() {

		HorizontalPanel mainPanel = new HorizontalPanel();
		mainPanel.setWidth("800px");
		VerticalPanel sourceList = new VerticalPanel();
		ScrollPanel sourcePanel = new ScrollPanel();
		sourcePanel.add(sourceList);
		imagePanel.setSize("250px", "400px");
		sourcePanel.setHeight("400px");
		imageView.setVisible(false);
		imageView.getElement().setId("imageView");

		// TESTING
		for (int i = 0; i < 5; i++) {
			Card c = new Card();
			c.source = "Google";
			c.url = "http://www.google.com/images/logo.gif";
			cards.add(c);
		}
		for (int i = 0; i < 5; i++) {
			Card c = new Card();
			c.source = "Atlassian";
			c.url = "https://design.atlassian.com/2.1/images/brand/logo-02.png";
			cards.add(c);
		}
		for (int i = 0; i < 5; i++) {
			Card c = new Card();
			c.source = "Microsoft";
			c.url = "http://logok.org/wp-content/uploads/2014/06/Microsoft-logo-m-box-880x660.png";
			cards.add(c);
		}

		for (Card c : cards) {
			if (!sources.contains(c.source)) {
				sources.add(c.source);
				CheckBox checkBox = new CheckBox(c.source);
				checkBox.setName(c.source);
				sourceList.add(checkBox);
				
				checkBox.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						CheckBox c = ((CheckBox) event.getSource());
						boolean checked = c.getValue();
						String name = c.getName();
						
						if(checked && !currentSource.contains(name)){
							currentSource.add(name);
							fill();
						}else if(currentSource.contains(name)){
							currentSource.remove(name);
							fill();
						}
						
					}
				});
			}
		}
		
		fill();
		
		imagePanel.add(thumbs);
		mainPanel.add(sourcePanel);
		mainPanel.add(imagePanel);
		mainPanel.add(imageView);
		// Add full View
		initWidget(mainPanel);
		setStyleName("imageViewer");
	}

	private void fill() {
		thumbs.removeAllRows();
		thumbs.clear();
		imageView.setVisible(false);
		imageView.setUrl("");
		int i=0;
		for (Card c : cards) {
			if (currentSource.contains(c.source)) {
				Image image = new Image();
				image.getElement().setId("imageThumb");
				image.setUrl(c.url);
				image.addClickHandler(this);
				thumbs.setWidget(i / 2, i % 2, image);
				i++;
			}
		}
	}

	@Override
	public void onClick(ClickEvent event) {
		Image image = (Image) event.getSource();
		if(!imageView.isVisible()){
			imageView.setVisible(true);
		}
		imageView.setUrl(image.getUrl());
	}

}
