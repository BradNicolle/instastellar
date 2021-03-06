package com.spaceappschallenge.adelaide.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.spaceappschallenge.adelaide.shared.Card;

@RemoteServiceRelativePath("card")
public interface CardService extends RemoteService {
	String submitCard(Card card);
	Card[] submitDate(String date);
}
