/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundedbuffer;

import boundedbuffer.TimeOutException;

/**
 *
 * @author Fein
 */
public class TimeOutSingleElemBuffer {

    private Integer elem = null;

    public synchronized void put(Integer newElem, long timeOut) throws InterruptedException, TimeOutException {
        long waitTime = timeOut;

        while (elem != null && waitTime > 0) {
            long t0 = System.currentTimeMillis();
            this.wait(waitTime);
            long t1 = System.currentTimeMillis();
            long elapsedTime = t1 - t0;
            waitTime -= elapsedTime;
        }

        if (elem != null) {
            throw new TimeOutException();
        }

        this.elem = newElem;
        this.notifyAll();
    }

    public synchronized Integer get(long timeOut) throws InterruptedException, TimeOutException {
        long waitTime = timeOut;
        Integer result;

        while (elem == null && waitTime > 0) {
            long t0 = System.currentTimeMillis();
            this.wait(waitTime);
            long t1 = System.currentTimeMillis();
            long elapsedTime = t1 - t0;
            waitTime -= elapsedTime;
        }

        if (elem == null) {
            throw new TimeOutException();
        }

        result = elem;
        elem = null;

        return result;
    }
}
