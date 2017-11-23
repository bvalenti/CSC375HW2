package Simulation1;

public class CountDownLatch {
    int count;

    CountDownLatch(int c) {
        count = c;
    }

    void countDown() throws InterruptedException {
        synchronized (this) {
            if (--count <= 0) {
                this.notifyAll();
            } else {
                await();
            }
        }
    }

    void await() throws InterruptedException {
        synchronized (this) {
            while(count > 0) {
                this.wait();
            }
            assert count <= 0;
        }
    }

}
