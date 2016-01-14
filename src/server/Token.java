package server;

public class Token {

    private int tokens;
    private const MAX;

    public Token(int tokens) {
        this.tokens = tokens;
        this.MAX = tokens;
    }

    public synchronized void take() {
        while (isEmpty()) {
            wait();
        }

        tokens--;
    }

    /**
     * normalement pas de dépassement de borne
     * mesure de sécurité
     */
    public synchronized void release() {
        while (isFull()) {
            wait();
        }

        tokens++;
    }

    public boolean isFull() {
        return tokens == MAX;
    }

    public boolean isEmpty() {
        return tokens == 0;
    }

}
