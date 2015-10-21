package com.esprit.tdd;

import java.util.Arrays;
import java.util.List;
/***
 * 
 * @author malik 23
 *
 */
public class Car {
	ISMSModule smsModule;
	private boolean started;

	public void turnKey(String direction) {
		// TODO Auto-generated method stub
		List<String>directionValues=Arrays.asList("right","left");
		
		if(!directionValues.contains(direction))
		{
			if(smsModule.isReady()){
				try {
					smsModule.send("123456", "alarme");
					
				} catch (Exception e) {
					System.out.println(("num tel obligatoir"));
				}
			}
			
			throw new CloudNotStartException();
		}
		else if("left".equals(direction)){
			started=false;
		}else if("right".equals(direction)){
			started=true;
		}
		
	}

	public boolean isStarted() {
		
		return started;
	}



}
