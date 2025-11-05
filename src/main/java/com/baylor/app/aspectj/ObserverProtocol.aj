package com.baylor.app.aspectj;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.WeakHashMap;

public abstract aspect ObserverProtocol {

	protected interface Subject {}
	protected interface Observer {}
	
	private WeakHashMap perSubjectObservers;
	
	protected List getObservers(Subject s) {
		if (perSubjectObservers == null) {
			perSubjectObservers = new WeakHashMap();
		}
		List observers = (List) perSubjectObservers.get(s);
		
		if (observers == null) {
			observers = new LinkedList();
			perSubjectObservers.put(s,  observers);
		}
		
		return observers;
	}
	
	public void addObserver(Subject s, Observer o) {
		getObservers(s).add(o);
	}
	
	public void removeObserver(Subject s, Observer o) {
		getObservers(s).remove(o);
	}
	
	abstract protected pointcut subjectChange(Subject s);
	
	abstract protected void updateObserver(Subject s, Observer o);
	
	after(Subject s): subjectChange(s) {
		Iterator iter = getObservers(s).iterator();
		while (iter.hasNext()) {
			updateObserver(s, ((Observer)iter.next()));
		}
	}
}
