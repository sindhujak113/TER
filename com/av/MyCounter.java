package com.av;

import java.util.concurrent.atomic.AtomicInteger;

class MyCounter extends Thread { 
	  
	// Atomic counter Variable 
    AtomicInteger count; 
  
    // Constructor of class 
    MyCounter() 
    { 
        count = new AtomicInteger(); 
    } 
    // Overridden
    public synchronized void run() 
    { 
        int max = 1_000_00_000; 
  
        // incrementing counter 
        // total of max times 
        for (int i = 0; i < max; i++) { 
        	count.addAndGet(1); 
        } 
    } 
} 