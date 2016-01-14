package server.strategy;

import java.util.Properties;

public class HighLevelStrategy implements ServerStrategy {

    public void getStrategy(Properties config) {
        if (Integer.parseInt(config.getProperty("POOL_SIZE")) > 0) {

        }
    }

    public void getFixedStrategy() {

    }

    public void getCachedStrategy() {

    }
}
