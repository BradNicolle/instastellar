package com.spaceappschallenge.adelaide.server;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spaceappschallenge.adelaide.client.CardService;
import com.spaceappschallenge.adelaide.shared.Card;

public class CardServiceImpl extends RemoteServiceServlet implements CardService {
	private static final long serialVersionUID = 3368976007898841707L;

	@Override
	public Card[] generateCards(String message, Date date) {
		Card[] cards = new Card[1];
		cards[0] = new Card("LOLCAT", "TROLLCAT", "DOLLCAT");
		return cards;
	}

}
