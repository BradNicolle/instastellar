package com.spaceappschallenge.adelaide.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.spaceappschallenge.adelaide.shared.Card;

public interface CardServiceAsync {

	void submitCard(Card card, AsyncCallback<String> callback);

}
