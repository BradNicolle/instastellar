package com.spaceappschallenge.adelaide.server;

import java.util.Date;

import com.spaceappschallenge.adelaide.shared.Card;
import com.spaceappschallenge.adelaide.shared.Card.Source;

public interface ImageFetcher {
	public Card[] fetchImages(String date);
	public Source getSource();
}
