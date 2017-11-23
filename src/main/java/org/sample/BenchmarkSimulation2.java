package org.sample;

import Simulation2.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

public class BenchmarkSimulation2 {

    @Benchmark @BenchmarkMode(Mode.AverageTime)
    public void benchmark2() throws InterruptedException {
        Main myMain = new Main();
        myMain.runSimulation();
    }
}
