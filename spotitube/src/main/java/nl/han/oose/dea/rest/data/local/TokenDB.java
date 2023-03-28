package nl.han.oose.dea.rest.data.local;

import jakarta.ejb.Singleton;
import jakarta.inject.Inject;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


@Singleton
public class TokenDB {
    private static TokenDB instance;
    private Map<String, TokenData> tokenMap;

    private TokenDB() {
        this.tokenMap = new HashMap<>();
        startTokenCleanerThread();
    }


    public static synchronized TokenDB getInstance() {
        if (instance == null) {
            instance = new TokenDB();
        }
        return instance;
    }

    public void addToken(String token, TokenData tokenData) {
        tokenMap.put(token, tokenData);
    }

    public boolean isTokenValid(String token) {
        if (!tokenMap.containsKey(token)) {
            return false;
        }

        LocalDateTime tokenCreationDate = tokenMap.get(token).getCreationDate();
        LocalDateTime now = LocalDateTime.now();

        //check if the token has passed two weeks
        if (tokenCreationDate.plusWeeks(2).isBefore(now)) {
            tokenMap.remove(token);
            return false;
        }

        return true;
    }

    private void startTokenCleanerThread() {
        Thread cleanerThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(3600000); //om de uur
                    synchronized (this) {
                        Iterator<Map.Entry<String, TokenData>> iterator = tokenMap.entrySet().iterator();
                        while (iterator.hasNext()) {
                            LocalDateTime tokenCreationDate = iterator.next().getValue().getCreationDate();
                            LocalDateTime now = LocalDateTime.now();
                            if (tokenCreationDate.plusWeeks(2).isBefore(now)) {
                                iterator.remove();
                            }
                        }
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cleanerThread.setDaemon(true);
        cleanerThread.start();
    }

    public TokenData getTokenData(String token) {
        return tokenMap.get(token);
    }

    public void removeToken(String token) {
        tokenMap.remove(token);
    }

    public boolean containsToken(String token) {
        return tokenMap.containsKey(token);
    }

    public String getUsername(String token) {
        return tokenMap.get(token).getUsername();
    }

    public Map<String, TokenData> getTokens() {
        return tokenMap;
    }



}

