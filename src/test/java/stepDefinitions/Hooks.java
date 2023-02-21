package stepDefinitions;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class Hooks {


	@Before 
	public void setup(Scenario scenario) {
	
		System.out.println(" Before Method " );
	}

	@After
	public void tearDown(Scenario scenario){
		System.out.println(" After Method " );
	}

}
