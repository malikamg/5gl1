package com.esprit.tdd;
//import static importe toute les methode statique de cette classe
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class carTest {

	@Mock ISMSModule smsModule;
	@InjectMocks Car car;
	
	@Before
	public void setUp(){
		
		when(smsModule.isReady()).thenReturn(true);
		//a utilisé quand une methode return void
		doThrow(IllegalArgumentException.class).when(smsModule).send("123456", "alarme");
	}
	@Test
	public void itShouldStartWhenKeyTurnedRight() {
		String direction="right";
		car.turnKey(direction);
		assertTrue(car.isStarted());

	}
	
	@Test
	public void itShouldStopWhenKeyIsTurnedLeft(){
		String direction="left";
		car.turnKey(direction);
		assertFalse(car.isStarted());
	}
	
	@Test(expected=CloudNotStartException.class)
	public void itShouldThrowAnNonStartExceptionWhenWrongArgumentIsGiven(){
		String direction="x";
		car.turnKey(direction);
	//	assertFalse(car.isStarted());
	}
	@Test
	public void itShoulSendSmsNotificationWhenWrongArgumentIsGiven(){

		try {
			String direction="x";
			car.turnKey(direction);
			
		} catch (CloudNotStartException e) {
			// TODO: handle exception
		}
		finally{
			//fainally dans le cas ou il ya des miltiple exception dans le try ou meme dans l catch ,elle verifie tj a la fin
			verify( smsModule).send(Mockito.eq("123456"), anyString());
		
		}
	}

}
