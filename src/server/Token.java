/**
 * @author Alexis Chappron - Julian Didier
 */

package server;

/**
 * Token class
 */
public class Token {

    private int tokens;
    private int max;

    /**
     * Constructor of Token class
     *
     * @param tokens represents the maximum number of threads executed at the same time.
     */
    public Token(int tokens) {
        this.tokens = tokens;
        max = tokens;
    }

    /**
     * Get tokens
     * @return tokens
     */
    public int getTokens() {
        return tokens;
    }

    /**
     * Take one token. If tokens is empty, the thread must wait.
     */
    public synchronized void take() {
        while (isEmpty()) {
            try {
                System.out.println("Thread is waitting : tokens empty.");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tokens--;
        System.out.println("Token taken - Number =  " + tokens );
    }

    /**
     * Release one token. If tokens is full, the thread must wait.
     * Normally we wouldn't enter in "isFull()" condition. Just a security measure.
     */
    public synchronized void release() {
        while (isFull()) {
            System.out.println("Thread is waitting : tokens full.");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tokens++;
        notifyAll(); // the thread notify that a token is free
        System.out.println("Token released - Number =  " + tokens );
    }

    /**
     * Check if tokens is full
     * @return true or false
     */
    public boolean isFull() {
        return tokens == max;
    }

    /**
     * Check if tokens is empty
     * @return true or false
     */
    public boolean isEmpty() {
        return tokens <= 0;
    }

}
