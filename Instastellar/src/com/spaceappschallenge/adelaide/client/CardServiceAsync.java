package com.spaceappschallenge.adelaide.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spaceappschallenge.adelaide.shared.Card;

public interface CardServiceAsync {

	void submitCard(Card card, AsyncCallback<String> callback);
	void submitDate(String date, AsyncCallback<Card[]> callback);
}
