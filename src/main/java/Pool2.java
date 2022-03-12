
public class Pool2 extends Pool1{ //max kids/instructor
    protected int ki;

    public void init(int ki, int cap) {
        this.ki = ki;
    }

    public synchronized void kidSwims() throws InterruptedException {
        while(instructorsSwimming <= 0 || kidsSwimming*instructorsSwimming >= ki) {
            log.waitingToSwim();
            wait();
        }

        kidsSwimming++;

        notifyAll();
        log.swimming();
    }
}
