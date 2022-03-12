
public class Pool3 extends Pool2{ //max capacity
    protected int cap; // pool max capacity

    public void init(int ki, int cap) {
        super.init(ki, cap);
        this.cap = cap;
    }

    public synchronized void kidSwims() throws InterruptedException {
        while(instructorsSwimming <= 0 || kidsSwimming*instructorsSwimming >= ki
                || kidsSwimming + instructorsSwimming >= cap) {
            log.waitingToSwim();
            wait();
        }

        kidsSwimming++;

        notifyAll();
        log.swimming();
    }

    public synchronized void instructorSwims() throws InterruptedException {
        while(kidsSwimming + instructorsSwimming >= cap){
            log.waitingToSwim();
            wait();
        }
        instructorsSwimming++;

        log.swimming();
        notifyAll();
    }
}
