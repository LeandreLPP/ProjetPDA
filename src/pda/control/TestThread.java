package pda.control;

import pda.view.AppView;

public class TestThread extends Thread {
	AppView mere;
	Thread t;

	public TestThread(AppView mere){
		super("Diapo");
		this.mere = mere;
		this.start();
	}

	public void run(){
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mere.nextPhoto();
	}
}