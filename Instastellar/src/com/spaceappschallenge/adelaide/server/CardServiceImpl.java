package com.spaceappschallenge.adelaide.server;

import java.util.Date;

import javax.persistence.EntityManager;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.spaceappschallenge.adelaide.client.CardService;
import com.spaceappschallenge.adelaide.shared.Card;

public class CardServiceImpl extends RemoteServiceServlet implements CardService {
	private static final long serialVersionUID = 3368976007898841707L;

	@Override
	public String submitCard(Card card) {
		if (card != null) {
			EntityManager em = EMFService.get().createEntityManager();
			em.persist(card);
			em.close();
		}

		return Long.toString(card.getId());
	}

	@Override
	public Card[] submitDate(String date) {
		// TODO Auto-generated method stub
		return new NASAImageFetcher().fetchImages(date);
	}

}
