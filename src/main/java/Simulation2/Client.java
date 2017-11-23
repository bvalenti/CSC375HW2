package Simulation2;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable {

    int clientID;
    AuctionSite instance;
    ArrayList<Item> savedItems = new ArrayList<>();
    Main main;

    public Client(int id, AuctionSite inst, Main mn) {
        clientID = id;
        instance = inst;
        main = mn;
    }

    /*
    Client thread simulation performs reads and possibly a write action given some probability.
     */
    public void run() {
        int tlr;
        ArrayList<Item> readItems;

        try {
            for (int i = 0; i < 10000; i++) {
                tlr = ThreadLocalRandom.current().nextInt(1, 100);
                if (tlr > 95) { //Try write, but first read data.
                    if (savedItems.size() != 0) {
                        tlr = ThreadLocalRandom.current().nextInt(0, savedItems.size());
                        doWrite(savedItems.get(tlr));
                    } else {
                        readItems = doRead();
                        tlr = ThreadLocalRandom.current().nextInt(0, readItems.size());
                        doWrite(readItems.get(tlr));
                    }
                } else { //Read stuff and store as saved favorites.
                    readItems = doRead();
                    if (tlr > 80) {
                        tlr = ThreadLocalRandom.current().nextInt(0, readItems.size());
                        if (!savedItems.contains(readItems.get(tlr))) {
                            savedItems.add(readItems.get(tlr));
                        }
                    }
                }
            }
            main.latch.countDown();
        } catch (InterruptedException e) {}
    }

    /*
    Reads in a random number of items for client to view.
     */
    private ArrayList<Item> doRead() throws InterruptedException {
        int tlr = ThreadLocalRandom.current().nextInt(1,20);
        ArrayList<Item> randItems = instance.getRandomItems(tlr);
        return randItems;
    }

    /*
    Performed after reading in items. Places a bid on a randomly selected item.
     */
    private void doWrite(Item toWriteTo) throws InterruptedException {
        instance.placeBid(toWriteTo.itemName,toWriteTo.highestBid + 1,clientID);
    }
}
