package com.boazlev.fnf.indexer;

class IdCount {
	private String id;
	private int count;

	IdCount(String id, int count) {
		this.id = id;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public String getId() {
		return id;
	}

}