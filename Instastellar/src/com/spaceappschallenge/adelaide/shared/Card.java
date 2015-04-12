package com.spaceappschallenge.adelaide.shared;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Card implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private static final long serialVersionUID = -1841062682596475325L;
	public static enum Source {NASA}
	private String url;
	private Source source;
	private String description;
	private String message;
	
	public Card() {}
	
	public Card(String url, Source source, String description, String message) {
		this.url = url;
		this.source = source;
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
