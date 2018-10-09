package com.flexionmobile.codingchallenge.integration;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.fluent.Request;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class IntegrationImpl implements Integration {
	private final String DEVELOPER_ID = "merliandras";
	private final String BASE_URL = "http://sandbox.flexionmobile.com/javachallenge/rest/";
	private final String BUY_URL = BASE_URL + "developer/%s/buy/%s";
	private final String GET_PURCHASES_URL = BASE_URL + "developer/%s/all";
	private final String CONSUME_URL = BASE_URL + "developer/%s/consume/%s";

	public Purchase buy(String itemId) {
		String url = String.format(BUY_URL, DEVELOPER_ID, itemId);
		Purchase purchase = null;
		try {
			String content = Request.Post(url)
				.execute().returnContent().asString();
			Gson gson = new Gson();
			purchase = gson.fromJson(content, PurchaseImpl.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return purchase;
	}

	public List<Purchase> getPurchases() {
		String url = String.format(GET_PURCHASES_URL, DEVELOPER_ID);
		List<Purchase> purchases = null;
		try {
			String content = Request.Get(url)
				.execute().returnContent().asString();
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(content, JsonObject.class);
			JsonElement jsonElement = jsonObject.get("purchases");
			purchases = gson.fromJson(jsonElement, new TypeToken<List<PurchaseImpl>>(){}.getType());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return purchases;
	}

	public void consume(Purchase purchase) {
		String url = String.format(CONSUME_URL, DEVELOPER_ID, purchase.getId());
		try {
			String content = Request.Post(url)
				.execute().returnContent().asString();
			System.out.println(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
