package server;

public class Token {

    private int tokens;
    private int max;

    public Token(int tokens) {
        this.tokens = tokens;
        max = tokens;
    }

    public int getTokens() {
        return tokens;
    }

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
     * normalement pas de dépassement de borne
     * mesure de sécurité
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
        notifyAll();
        System.out.println("Token released - Number =  " + tokens );
    }

    public boolean isFull() {
        return tokens == max;
    }

    public boolean isEmpty() {
        return tokens <= 0;
    }

}
