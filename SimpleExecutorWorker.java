package com.byui;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SimpleExecutorWorker
{
    public static void main(String[] args) 
    {
    	
    	/*********************Standard ExecutorService******************************************/
        
        //Executes only one thread
        ExecutorService executor= Executors.newSingleThreadExecutor(); 
        Runnable worker = new ExecutorThread("Single Thread");
        executor.execute(worker);
        executor.shutdown();
        
        //Executes 5 threads
    	ExecutorService executor1 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            Runnable worker1 = new ExecutorThread("" + i);
            executor1.execute(worker1);
        }
        executor1.shutdown();
       
        /***************************************************************************************/
        
        
        /*********************Runnable tasks with submit*****************************************
         * **************************************************************************************/
        
        //Executes 3 threads
        ExecutorService executor3 = Executors.newFixedThreadPool(3);
        
        Runnable myDateTask = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("Current time printed is :: " + LocalDateTime.now());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        
        // execute task using submit() method
        Future<String> result = executor3.submit(myDateTask, "DONE");
        
        while(result.isDone() == false) {
            try {
                System.out.println("This method returns value as : " + result.get());
                break;
            } 
            catch (Exception e){
                e.printStackTrace();
            }
            //Sleep for 1 second
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Be sure to shut down the executor service
        executor3.shutdownNow();
        
        /***************************************************************************************
         * *************************************************************************************/
        
    	/*********************ScheduledExecutorService*****************************************/
        ScheduledExecutorService executor2 = Executors.newScheduledThreadPool(1);
       
        Runnable taskWorker = new ExecutorThread("Scheduled Thread");
       
        //schedule thread with fixed delay
        executor2.scheduleWithFixedDelay(taskWorker, 0, 1, TimeUnit.SECONDS);
        executor2.execute(taskWorker);
        executor2.shutdown();

        /**************************************************************************************/
    }
}

class ExecutorThread implements Runnable
{
	private String command;
	
	static void printMessage(String message) {
        String threadName = 
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",
                          threadName,
                          message);
    }
	
	public ExecutorThread(String s) {
		this.command=s;
	}

    @Override
    public void run() {
    	printMessage(" Start. Command = "+command);
        processCommand();
        printMessage(" End.");
    }

    private void processCommand() {
        try {
        	// thread sleeps for 5 secs
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        	printMessage("Throws interrupted exception : "+e.getMessage());
        }
    }

    @Override
    public String toString(){
        return this.command;
    }
}
