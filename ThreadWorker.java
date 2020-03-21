package com.byui;

import java.util.Random;

public class ThreadWorker extends Thread {

	int waitTime = 0;
	
	static void threadMessage(String message) {
        String threadName = 
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                          threadName,
                          message);
    }
	
	public ThreadWorker(int waitTime) {
		this.waitTime = waitTime;
	}
	
	public static void main(String[] args) {
		ThreadWorker worker = new ThreadWorker(1000);
		ThreadWorker worker1 = new ThreadWorker(2000);
		ThreadWorker worker2 = new ThreadWorker(5000);
		worker.start();
		worker1.start();
		worker2.start();
	}
	
	@Override
	public void run()
	{
		threadMessage("Execution started");
		
		threadMessage("Sleeping for ["+waitTime/1000+"] sec..");
		
		try {
			Thread.sleep(waitTime);
		} catch (InterruptedException e) {
			threadMessage("Interruption occurred");
		}
		Random rand = new Random();
		
		threadMessage("Awake now and prints a random number ["+rand.nextInt(100)+"]");
		threadMessage("Exiting now");
	}
}
