package com.spaceappschallenge.adelaide.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.spaceappschallenge.adelaide.shared.Card;
import com.spaceappschallenge.adelaide.shared.Card.Source;

public class NASAImageFetcher implements ImageFetcher {
	private static final String URL_PREFIX = "http://apod.nasa.gov/apod/";
	private Pattern imgUrl = Pattern.compile("IMG SRC=\"(.*)\".?alt");

	@Override
	public Card[] fetchImages(String date) {

		final String url = URL_PREFIX + "ap" + date + ".html";

		System.out.println(url);

		StringBuffer buffer = new StringBuffer();
		try {
			URL u = new URL(url);
			URLConnection conn = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				buffer.append(inputLine);
			}
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String data = buffer.toString();
		Matcher m = imgUrl.matcher(data);
		
		Card[] card = new Card[1];
		if(m.find()){
			card[0] = new Card();
			card[0].url = "http://apod.nasa.gov/apod/"+m.group(1);
			card[0].source = "NASA";
		}
		System.out.println(card[0].url);
		return card;
	}

	@Override
	public Source getSource() {
		return Source.NASA;
	}

}
