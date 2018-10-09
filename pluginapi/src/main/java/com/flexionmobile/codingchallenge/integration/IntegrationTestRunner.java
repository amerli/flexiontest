package com.flexionmobile.codingchallenge.integration;

import java.util.List;

import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;

public class IntegrationTestRunner {
    public void runTests(Integration integration) {
        if (integration == null) {
            throw new IllegalStateException("test failed: you must pass an instance of Integration");
        }
        Purchase purchase = integration.buy("item1");
        if (purchase == null) {
            throw new IllegalStateException("test failed: buy did not result in a purchase");
        }
        if (purchase.getConsumed()) {
            throw new IllegalStateException("test failed: purchase should not be marked as consumed before consumption");
        }
        try {
            integration.consume(purchase);
        }
        catch (Exception e) {
            throw new IllegalStateException("test failed: failed to consume purchase", e);
        }
        String alreadyConsumedPurchaseId = purchase.getId();
        if (alreadyConsumedPurchaseId == null || alreadyConsumedPurchaseId.length() == 0) {
            throw new IllegalStateException("test failed: the returned purchase id is blank");
        }
        List<Purchase> purchases = integration.getPurchases();
        for (Purchase p : purchases) {
            String purchaseId = p.getId();
            if (purchaseId == null || purchaseId.length() == 0) {
                throw new IllegalStateException("test failed: the returned purchase id is blank");
            }
            if (p.getConsumed()) continue;
            if (alreadyConsumedPurchaseId.equals(purchaseId)) {
                throw new IllegalStateException("test failed: purchase with id '" + purchaseId + "' should already be consumed");
            }
            integration.consume(p);
        }
    }
}
