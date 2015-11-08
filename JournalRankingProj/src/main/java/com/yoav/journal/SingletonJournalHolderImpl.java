package com.yoav.journal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * the enun singleton pattern combined with the JournalHolderObj interface
 * @author Yoav
 *
 */
public enum SingletonJournalHolderImpl implements JournalHolderObj {
	INSTANCE;
	
		
	private final Map<Integer, Set<Journal>> journalMap =new ConcurrentHashMap<Integer, Set<Journal>>(new HashMap<Integer, Set<Journal>>());
	private Set<Journal> collectionPerYear ;	//temp usage set
	private transient static final Log LOG = LogFactory.getLog(SingletonJournalHolderImpl.class);
	  
	
	@Override
	public void insertJournal(Journal journal, int year) {
		
		try {
			synchronized (this) {
				collectionPerYear = journalMap.get(year);
				if (collectionPerYear == null) { //first time for this year
					collectionPerYear = new TreeSet<Journal>((o1, o2) -> {
						return (int) ((o2.getScore() - o1.getScore() == 0) ? o1
								.getName().compareTo(o2.getName()) : Math
								.abs(o2.getScore() - o1.getScore())
								/ (o2.getScore() - o1.getScore()));
					});
					journalMap.put(journal.getPublicationYear(), collectionPerYear);
				}
				//insert only if 
			
					collectionPerYear.add(journal);			
				
			}
		} catch (Exception e) {
			LOG.fatal(e.getMessage());			
			
		}
	}
	
	

	@Override
	public List<Journal> getAllJournalsForYear(int year) {
		// TODO Auto-generated method stub
		return journalMap.get(year).stream().collect(Collectors.toList()) ;
	}

	@Override
	public List<Journal> rankJournalsOfYear(int year, Predicate<Journal> reviewJournalsPredicate) {
	
		try {
			collectionPerYear = journalMap.get(year);
			List<Journal> rankingList = collectionPerYear
					.parallelStream()
					.filter(reviewJournalsPredicate)
					.collect(Collectors.toList());
			return rankingList;
		} catch (Exception e) {
			LOG.fatal(e.getMessage());
			return new ArrayList<Journal>(1);
		}
	}



//	.sorted((o1, o2) -> {
//		return (int) ((o2.getScore() - o1.getScore() == 0) ? o1
//				.getName().compareTo(o2.getName()) : Math
//				.abs(o2.getScore() - o1.getScore())
//				/ (o2.getScore() - o1.getScore()));
//	})

}
