package com.epam.brest;

import java.util.concurrent.TimeUnit;

public class RunnerPort implements Runnable{

    private Port port;

    public RunnerPort() {
        this.port = new Port();
    }

    public RunnerPort(Port port) {
        this.port = port;
    }

    @Override
    public void run() {
        while(!port.getShips().isEmpty()) {
            System.out.print(Thread.currentThread().getName()+ ": ");
            port.deleteShip();
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public static void main(String[] args) {

        RunnerPort runnerPort = new RunnerPort();

        for(int i = 1; i <= runnerPort.getPort().getNumberOfBerths(); i++) {
            Thread runner = new Thread(runnerPort, "Причал" + i);
            runner.start();
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
