package Simulation1;

import java.io.Serializable;

public class Item implements Serializable {
//    ArrayList<Bid> bids = new ArrayList<Bid>();
    String itemName;
    double highestBid;
    int highestBidderID;

    public Item(String name) {
        itemName = name;
        highestBidderID = 0;
        highestBid = 0;
    }
}
