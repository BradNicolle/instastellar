package com.spaceappschallenge.adelaide.shared;

import java.io.Serializable;

public class Card implements Serializable {
	private static final long serialVersionUID = -1841062682596475325L;
	public String url;
	public String source;
	public String description;
	
	public Card() {}
	
	public Card(String url, String source, String description) {
		this.url = url;
		this.source = source;
		this.description = description;
	}
}
