package com.av;

public class NotSecureCounter 
{ 
    public static void main(String[] args) throws InterruptedException 
    { 
        // Instance of MyCounter Class 
        MyCounter counter = new MyCounter(); 
  
        // Defining Two different threads 
        Thread pri = new Thread(counter, "Primary"); 
        Thread sec = new Thread(counter, "Secondary"); 
  
        // Threads will start executing 
        pri.start(); 
        sec.start(); 
  
        // main thread will wait for 
        // both threads to get completed 
        pri.join(); 
        sec.join(); 
  
        // display final value of count variable 
        System.out.println(counter.count); 
    } 
} 
