package com.flexionmobile.codingchallenge.integration;

import java.util.List;

import com.flexionmobile.codingchallenge.integration.Purchase;

public interface Integration {
    public Purchase buy(String itemId);

    public List<Purchase> getPurchases();

    public void consume(Purchase purchase);
}