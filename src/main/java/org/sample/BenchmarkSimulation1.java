package org.sample;

import Simulation1.AuctionSite;
import Simulation1.Client;
import Simulation1.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

public class BenchmarkSimulation1 {

    @Benchmark @BenchmarkMode(Mode.AverageTime)
    public void benchmark1() throws InterruptedException {
        Main myMain = new Main();
        myMain.runSimulation();
    }
}
