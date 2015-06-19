package pda.control;

import pda.view.AppView;

public class TestThread extends Thread {
	AppView mere;
	Thread t;

	public TestThread(AppView mere, Thread main){
		super("Diapo");
		this.mere = mere;
		this.t = main;
		this.start();
	}

	public void run(){
		try {
			System.out.println("Debut du sleep");
			if(Thread.currentThread() == this){
				this.wait(2000);
			}
			System.out.println("Fin du sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mere.nextPhoto();
	}
}