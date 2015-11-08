package com.yoav.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.yoav.journal.Journal;
import com.yoav.journal.JournalHolderObj;
import com.yoav.journal.SingletonJournalHolderImpl;

public class TestSingletonJournalHolder {
	private transient static final Log LOG = LogFactory.getLog(TestSingletonJournalHolder.class);
	
	private static JournalHolderObj holderImpl;
	
	@BeforeClass
	public static void Setup() {
		holderImpl = SingletonJournalHolderImpl.INSTANCE;
	}
	
	
	@Test
	public final void testJournalHolderObjImpl() {
		assertNotNull(holderImpl);		
	}

	/**
	 * testing an insert into the journal repo object
	 */
	@Test
	public final void testInsertJournal() {
		LOG.info("testing the insert method");
		//create a dummy journal and insert it
		Journal myJournal = new Journal(2015, "The Most Weirdly Specific, Lazy Spam Email I Ever Received"
				, 3.5f
				, "That's only the subject heading and already we're off to a poor start."
						+ " This person is, presumably, trying to sell their services as someone who can"
						+ " get my nonexistent website better placement in Google search results."
						+ " If I want a service of any kind, I at least want it from someone who has "
						+ "the wherewithal to capitalize their words in email subject headings."
				, "short abstract", false);
		holderImpl.insertJournal(myJournal, myJournal.getPublicationYear());		
		
		List<Journal> JournalSet = holderImpl.getAllJournalsForYear(2015);
		assertNotNull(JournalSet);
		Journal retJournal = (Journal) JournalSet.iterator().next();
		
		assertEquals(myJournal, retJournal);
		
		
	}
	
	/**
	 * test the ranking  - so that output is sorted according to logic
	 */
	@Test
	public final void testRanking(){
		
		
		Journal journalA = new Journal(2015, "Journal A"
				, 3.1f
				, "their words in email subject headings."
				, "short abstract2", false);
		
		Journal journalB = new Journal(2015, "Journal B"
				, 4.9f
				, "the fox jumped over yada yada yada ."
				, "short abstract3", false);
		
		Journal journalC = new Journal(2015, "Journal C"
				, 4.9f
				, "biggest journal in test."
				, "short abstract4", false);
		
		Journal journalD = new Journal(2015, "Journal D"
				, 7.9f
				, "don't panic "
				, "short abstract3", true);
		
		Journal journalE = new Journal(2015, "Journal E"
				, 4.0f
				, "don't panic2 ,some text"
				, "short abstract5", true);
		
		//List<Journal> journalsPerYear = holderImpl.getAllJournalsForYear(2015);
		
		holderImpl.insertJournal(journalB, 2015);
		holderImpl.insertJournal(journalA, 2015);
		holderImpl.insertJournal(journalC, 2015);
		holderImpl.insertJournal(journalD, 2015);
		holderImpl.insertJournal(journalE, 2015);
		
		
		List<Journal> rankList = holderImpl.rankJournalsOfYear(2015, p -> !p.isReviewJournal());		
		//assert rankList.get(0) == biggestJournal;
		rankList.forEach(System.out::println);
				
		assert rankList.get(0).equals(journalB);
		assert rankList.get(1).equals(journalC);
	}

	
	@Test	
	public final void testGetJournalsForYear() {
		testRanking();
		List<Journal> rankedJournalsList = holderImpl.getAllJournalsForYear(2015); 
		assertNotNull(rankedJournalsList);
	}

	

}
