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
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tokens--;
    }

    /**
     * normalement pas de dépassement de borne
     * mesure de sécurité
     */
    public synchronized void release() {
        while (isFull()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        tokens++;
    }

    public boolean isFull() {
        return tokens == max;
    }

    public boolean isEmpty() {
        return tokens == 0;
    }

}
