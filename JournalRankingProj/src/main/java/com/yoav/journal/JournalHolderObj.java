package com.yoav.journal;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

public interface JournalHolderObj {
	
	/**
	 * inserts a created journal into the specific year collection of journals
	 * @param journal the journal to add
	 * @param year year to add to
	 * @return boolean value indicating the success/failure
	 */
	public void insertJournal(Journal journal , int year);
	
	/**
	 * 
	 * @param year the year from which we want to fetch all the journals
	 * @return list of collected journals
	 */
	public List<Journal>getAllJournalsForYear(int year);	
	
	/**
	 * pull the ranked journals from a given year
	 * @param year 
	 * @param filterPredicate predicate to filter the list upon
	 * @return
	 */
	public List<Journal> rankJournalsOfYear(int year , Predicate<Journal> filterPredicate);
	

}
