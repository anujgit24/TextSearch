package com.counter.spring.rest.utility;

import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class SortByValueMap<K extends Comparable<K>, V extends Comparable<V>>
		extends TreeMap<K, V> {

	private static final long serialVersionUID = 1000023l;

	@Override
	public Set<Entry<K, V>> entrySet() {
		Set<Entry<K, V>> entriesUnsorted = super.entrySet();
		Set<Entry<K, V>> entrySorted = new TreeSet<Entry<K, V>>(
				new Comparator<Entry<K, V>>() {
					@Override
					public int compare(Entry<K, V> firstEntry,
							Entry<K, V> secondEntry) {
						int compare = secondEntry.getValue().compareTo(
								firstEntry.getValue());
						if (compare == 0) {
							compare = secondEntry.getKey().compareTo(
									firstEntry.getKey());
						}
						return compare;
					}
				});
		entrySorted.addAll(entriesUnsorted);
		return entrySorted;
	}

}