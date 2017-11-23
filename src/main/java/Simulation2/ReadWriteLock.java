package Simulation2;

public class ReadWriteLock {

    private int readers;
    private int writers;
    private int waitingReaders;
    private int waitingWriters;
    final Object lock = new Object();

    public void readLock() throws InterruptedException {
        synchronized(lock) {
            while(writers > 0 || waitingWriters > 0) {
                ++waitingReaders;
                lock.wait();
                --waitingReaders;
            }
            readers++;
        }
    }

    public void writeLock() throws InterruptedException {
        synchronized(lock) {
            while (writers > 0 || readers > 0) {
                ++waitingWriters;
                lock.wait();
                --waitingWriters;
            }
            writers++;
        }
    }

    public void readUnlock() {
        synchronized(lock) {
            readers--;
            if (readers == 0) {
                lock.notifyAll();
            }
        }
    }

    public void writeUnlock() {
        synchronized(lock) {
            writers--;
            lock.notifyAll();
        }
    }
}
