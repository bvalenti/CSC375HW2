package Simulation1;

import org.openjdk.jmh.runner.RunnerException;

import java.io.IOException;

public class Main {
    int numThreads = 8;

    CountDownLatch latch = new CountDownLatch(numThreads);

    public static void main(String args[]) throws RunnerException, IOException {
        AuctionSite myAuction = AuctionSite.getInstance();
        Client clients[] = new Client[32];
        Main main = new Main();

        for (int i = 0; i < 8; i++) {
            clients[i] = new Client(i+1, myAuction, main);
            new Thread(clients[i]).start();
        }
    }

    public void runSimulation() throws InterruptedException {
        AuctionSite myAuction = AuctionSite.getInstance();
        Client clients[] = new Client[numThreads];

        for (int i = 0; i < numThreads; i++) {
            clients[i] = new Client(i+1, myAuction, this);
            new Thread(clients[i]).start();
        }

        latch.await();
    }
}
