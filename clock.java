package com.example.clock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
    private String currentTime;
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
    private final Object lock = new Object();
    
    public void updateTime() {
        while (true) {
            synchronized (lock) {
                currentTime = timeFormat.format(new Date());
            }
            try {
                // Update time every 100ms for precision
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
    
    public void displayTime() {
        while (!Thread.currentThread().isInterrupted()) {
            String timeToDisplay;
            synchronized (lock) {
                timeToDisplay = currentTime;
            }
            
            // Clear the console for better visibility
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.println("Current Time: " + timeToDisplay);
            System.out.println("Display Thread Priority: " + 
                             Thread.currentThread().getPriority());
            
            try {
                // Refresh display every second
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
