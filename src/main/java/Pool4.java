
public class Pool4 extends Pool3 { //kids cannot enter if there are instructors waiting to exit
    private int instructorsReadyToRest = 0;
    public synchronized void kidSwims() throws InterruptedException {
        while(instructorsSwimming <= 0 || kidsSwimming*instructorsSwimming >= ki
                || kidsSwimming + instructorsSwimming >= cap
                || instructorsReadyToRest >= 1) {
            log.waitingToSwim();
            wait();
        }

        kidsSwimming++;

        notifyAll();
        log.swimming();
    }

    public synchronized void instructorRests() throws InterruptedException {
        boolean counterIncremented = false;

        while(kidsSwimming >= 1) {
            instructorsReadyToRest++;
            counterIncremented = true;
            log.waitingToRest();
            wait();
        }

        if (counterIncremented)
            instructorsReadyToRest--;

        instructorsSwimming--;

        notifyAll();
        log.resting();
    }
}
