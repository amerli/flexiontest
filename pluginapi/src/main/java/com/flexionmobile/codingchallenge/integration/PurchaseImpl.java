package com.flexionmobile.codingchallenge.integration;

public class PurchaseImpl implements Purchase {
	private String id;
	private boolean consumed;
	private String itemId;
	
	public PurchaseImpl(String id, boolean consumed, String itemId) {
		this.id = id;
		this.consumed = consumed;
		this.itemId = itemId;
	}
	
	public String getId() {
		return id;
	}
	public boolean getConsumed() {
		return consumed;
	}
	public String getItemId() {
		return itemId;
	}
	
}
