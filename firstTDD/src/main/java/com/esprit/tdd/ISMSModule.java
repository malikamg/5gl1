package com.esprit.tdd;

public interface ISMSModule {

	public void send(String number,String message);
	boolean isReady();
}
