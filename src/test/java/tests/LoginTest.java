package tests;

import org.testng.annotations.Test;

import engine.KeywordEngine;

public class LoginTest
{
	@Test
	public void loginTest()
	{
		KeywordEngine keyEngine = new KeywordEngine();
		keyEngine.startExecution("login");
	}
}