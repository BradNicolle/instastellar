package com.spaceappschallenge.adelaide.server;

import java.util.Date;

import com.spaceappschallenge.adelaide.shared.Card.Source;

public interface ImageFetcher {
	public String[] fetchImages(Date date);
	public Source getSource();
}
