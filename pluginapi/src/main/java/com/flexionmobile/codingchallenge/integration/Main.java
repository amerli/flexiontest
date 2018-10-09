package com.flexionmobile.codingchallenge.integration;

public class Main {
	public static void main(String[] args) {
		Integration integration = new IntegrationImpl();
		IntegrationTestRunner integrationTestRunner = new IntegrationTestRunner();
		integrationTestRunner.runTests(integration);
	}
}
