package com.spaceappschallenge.adelaide.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DatePicker;
import com.spaceappschallenge.adelaide.shared.Card;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Instastellar implements EntryPoint {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting
	 * service.
	 */
	private final CardServiceAsync cardService = GWT.create(CardService.class);

	private HorizontalPanel mainPanel = new HorizontalPanel();
	private VerticalPanel stepOne = new VerticalPanel();
	private VerticalPanel imagePanel = new VerticalPanel();
	private Card[] receivedImage;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button selectButton = new Button("Select");
		final Label stepOneLabel = new Label("Step 1");
		final Label errorLabel = new Label();
		final DatePicker datePicker = new DatePicker();
		final Label info = new Label("Enter Message");
		final TextBox inputMessage = new TextBox();
		final Button done = new Button("Done");

		// We can add style names to widgets
		selectButton.getElement().setId("selectButton");
		stepOneLabel.getElement().setId("stepLabel");
		datePicker.setYearAndMonthDropdownVisible(true);

		// Add to stepone
		stepOne.getElement().setAttribute("align", "center");
		stepOne.setStyleName("mainPanel");
		stepOne.add(stepOneLabel);
		stepOne.add(datePicker);
		stepOne.add(selectButton);

		// Add to mainPanel
		mainPanel.add(stepOne);

		//imagePanel.add(new ImageSwitcher());
		imagePanel.add(info);
		imagePanel.add(inputMessage);
		imagePanel.add(done);
		

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element

		RootPanel.get("mainPanel").add(mainPanel);
		RootPanel.get("imagePanel").add(imagePanel);

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Your Instastellar Card!");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				dialogBox.hide();
				selectButton.setEnabled(true);
				selectButton.setFocus(true);
			}
		});

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendDateToServer();
				//sendNameToServer();
			}

			private void sendDateToServer() {
				// First, we validate the input.
				errorLabel.setText("");

				// Then, we send the input to the server.
				selectButton.setEnabled(false);
				serverResponseLabel.setText("");
				DateTimeFormat format = DateTimeFormat.getFormat("yyMMdd");
				String date = format.format(datePicker.getValue());
				cardService.submitDate(date,
						new AsyncCallback<Card[]>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox.setText("Couldn't retrieve Images");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
								selectButton.setEnabled(true);
							}

							public void onSuccess(Card[] result) {
								// TODO: Implement stuff
								selectButton.setEnabled(true);
								closeButton.setFocus(true);
								receivedImage = result;
							}
						});
			}

			/**
			 * Send the name from the nameField to the server and wait for a
			 * response.
			 */
			private void sendNameToServer() {
				// First, we validate the input.
				errorLabel.setText("");

				// Then, we send the input to the server.
				selectButton.setEnabled(false);
				serverResponseLabel.setText("");

				cardService.submitCard(receivedImage[0], new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
						dialogBox.setText("Couldn't retrieve Images");
						serverResponseLabel
								.addStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML(SERVER_ERROR);
						dialogBox.center();
						closeButton.setFocus(true);
					}

					public void onSuccess(String result) {
						dialogBox.setText("Your Instastellar Card!");
						serverResponseLabel
								.removeStyleName("serverResponseLabelError");
						serverResponseLabel.setHTML("<a href='/cards/" + result + "'>Image link</a>");
						dialogBox.center();
						closeButton.setFocus(true);
					}
				});
			}
		}

		// Add a handler to send the name to the server
		final MyHandler handler = new MyHandler();
		done.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				if(receivedImage!=null){
					receivedImage[0].message = inputMessage.getText();
					handler.sendNameToServer();
				}
			}
		});
		selectButton.addClickHandler(handler);
	}
}
