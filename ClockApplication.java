package com.example.clock;

public class ClockApplication {
    public static void main(String[] args) {
        // Create a clock instance
        Clock clock = new Clock();
        
        // Create and start the time update thread (lower priority)
        Thread updateThread = new Thread(clock::updateTime);
        updateThread.setName("TimeUpdater");
        updateThread.setPriority(Thread.MIN_PRIORITY); // Lower priority
        updateThread.setDaemon(true); // Run in background
        
        // Create and start the display thread (higher priority)
        Thread displayThread = new Thread(clock::displayTime);
        displayThread.setName("TimeDisplay");
        displayThread.setPriority(Thread.MAX_PRIORITY); // Higher priority
        
        // Start both threads
        updateThread.start();
        displayThread.start();
        
        // Let the clock run for 10 seconds for demonstration
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Interrupt the display thread to stop the clock
        displayThread.interrupt();
    }
}
