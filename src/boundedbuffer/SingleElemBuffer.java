/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package boundedbuffer;

/**
 *
 * @author Fein
 */
public class SingleElemBuffer {

    private Integer element = null;

    public synchronized void put(Integer newElem) throws InterruptedException {
        while (element != null) {
            this.wait();
        }
        this.element = newElem;
        this.notifyAll();
    }

    public synchronized Integer get() throws InterruptedException {
        while (element == null) {
            this.wait();
        }
        int result = this.element;
        this.element = null;
        this.notifyAll();
        return result;
    }
}
