package com.yoav.journal;

import java.util.UUID;

public class Journal {
	
	private String name;
	private float score;
	private int publicationYear;
	private String text;
	private String abstractText;
	private boolean reviewJournal;
	private UUID journalUUID;
	
	
	//getters setters
	
	public UUID getUUID() {
		return journalUUID;
	}
	public boolean isReviewJournal() {
		return reviewJournal;
	}
	public int getPublicationYear() {
		return publicationYear;
	}
	public String getName() {
		return name;
	}
	public float getScore() {
		return score;
	}
	
	public void setRank(float rank){
		this.score = rank;
	}
	
	
	public String getText() {
		return text;
	}
	public String getAbstractText() {
		return abstractText;
	}
	
	/**
	 * 
	 * @param _publicationYear year of publication
	 * @param _name journal name
	 * @param _rank journal rank
	 * @param _text journal text
	 * @param _abstractText abstract for text
	 * @param _reviewJournalindicate if this is an review journal
	 */
	public Journal(int _publicationYear, String _name,float _rank , String _text , String _abstractText , boolean _reviewJournal){
		this.publicationYear = _publicationYear;
		this.name = _name;
		this.score = _rank;
		this.text = _text;
		this.abstractText = _abstractText;
		this.reviewJournal = _reviewJournal;
		
		journalUUID = UUID.randomUUID();
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder str = new StringBuilder();
		str.append("Publication Year : " +publicationYear);
		str.append("\r\n");
		str.append("name : " +name);
		str.append("\r\n");
		str.append("score : " +score);
		str.append("\r\n");
		str.append("text : " +text);
		return str.toString();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Journal){
			if(this.publicationYear == ((Journal)obj).getPublicationYear()
				&& this.name == ((Journal)obj).getName()
				&& this.text == ((Journal)obj).getText())
				return true;			
		}
		return false;	
	}
	
	@Override
	public int hashCode() {
		return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 31). // two randomly chosen prime numbers
	            // if deriving: appendSuper(super.hashCode()).
	            append(name).
	            append(text).
	            append(journalUUID).
	            toHashCode();
		
	}
}
