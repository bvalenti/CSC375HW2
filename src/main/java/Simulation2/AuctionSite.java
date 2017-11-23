package Simulation2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class AuctionSite {
    volatile HashMap<String, Item> items = new HashMap<>();
    private final ReadWriteLock rwl = new ReadWriteLock();
//    private final Lock w = rwl.writeLock();
//    private final Lock r = rwl.readLock();

    private static AuctionSite instance;

    static {
        try {
            instance = new AuctionSite();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private AuctionSite() throws IOException {
        readItemData();
    }

    public static AuctionSite getInstance() { return instance; }

    public Item getItem(String itemName) throws InterruptedException {
        rwl.readLock();
        try { return items.get(itemName); }
        finally { rwl.readLock(); }
    }

    public void placeBid(String itemName, double amnt, int id) throws InterruptedException {
        rwl.writeLock();
        Item item = items.get(itemName);
        if (amnt > item.highestBid) {
            item.highestBid = amnt;
            item.highestBidderID = id;
        }
        rwl.writeUnlock();
    }

    public ArrayList<Item> getRandomItems(int tlr) throws InterruptedException {
        ArrayList<Item> out = new ArrayList<>();
        int grabbedItems[] = new int[tlr];
        int a;

        rwl.readLock();
        try {
//            Item itemsArray[] = items.values().toArray();
            for (int i = 0; i < tlr; i++) {
                a = ThreadLocalRandom.current().nextInt(0,items.values().size());
                if (!checkForDuplicate(grabbedItems,a)) {
                    grabbedItems[i] = a;
                    out.add((Item) items.values().toArray()[a]);
                } else {
                    i--;
                }
            }
        } finally {
            rwl.readUnlock();
        }
        return out;
    }

    private void readItemData() throws IOException {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("Items2.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = "";
        while ((line = br.readLine()) != null) {
            String[] wordLine = line.split(",");
            Item item = new Item(wordLine[0]);
            items.put(wordLine[0],item);
        }
    }

    public Boolean checkForDuplicate(int ints[], int c) {
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == c) {
                return true;
            }
        }
        return false;
    }
}
