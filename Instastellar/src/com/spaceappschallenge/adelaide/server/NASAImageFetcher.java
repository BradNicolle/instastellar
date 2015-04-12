package com.spaceappschallenge.adelaide.server;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.spaceappschallenge.adelaide.shared.Card.Source;

public class NASAImageFetcher implements ImageFetcher {
	private static final String URL_PREFIX = "http://apod.nasa.gov/apod/";

	@Override
	public String[] fetchImages(Date date) {
		String[] ret = new String[1];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yymmdd");
		ret[0] = URL_PREFIX + "ap" + dateFormat.format(date) + ".html";
		return ret;
	}
	
	@Override
	public Source getSource() {
		return Source.NASA;
	}

}
