
public class Pool1 extends Pool {   //no kids alone
    protected int kidsSwimming = 0, instructorsSwimming = 0;

    public void init(int ki, int cap) {}

    public synchronized void kidSwims() throws InterruptedException {
        while(instructorsSwimming <= 0) {
            log.waitingToSwim();
            wait();
        }

        kidsSwimming++;

        notifyAll();
        log.swimming();
    }

    public synchronized void kidRests() {
        kidsSwimming--;

        log.resting();
        notifyAll();
    }

    public synchronized void instructorSwims() throws InterruptedException {
        instructorsSwimming++;

        log.swimming();
        notifyAll();
    }

    public synchronized void instructorRests() throws InterruptedException {
        while(kidsSwimming >= 1 && instructorsSwimming == 1) {
            log.waitingToRest();
            wait();
        }

        instructorsSwimming--;

        notifyAll();
        log.resting();
    }
}
