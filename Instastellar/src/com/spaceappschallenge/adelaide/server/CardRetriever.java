package com.spaceappschallenge.adelaide.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spaceappschallenge.adelaide.shared.Card;

public class CardRetriever extends HttpServlet {
	private static final long serialVersionUID = -6853645714158089868L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		try {
			Long cardId = Long.parseLong(req.getPathInfo().substring(1));
			out.println("YOUR CARD NAME: " + cardId + "<br />");
			
			EntityManager em = EMFService.get().createEntityManager();
			Card c = em.find(Card.class, cardId);
			if (c == null) {
				out.println("No card found for name: " + cardId);
			}
			else {
				out.println(c.getId() + " " + c.getUrl() + " " + c.getMessage());
			}
			
		} catch(NumberFormatException nfe) {
			out.println("Invalid card id!");
		}
		
		out.close();
	}
}
