package com.foodniche.rest.security;

import com.foodniche.db.entities.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author Alexey Dubrov
 *
 * Stores all authenticated tokens.
 */

@Component
public class TokenRepository {

    private static final Logger LOG = LoggerFactory.getLogger(TokenRepository.class);
    private static final Long CLEAN_DELAY_HRS = 8l;

    /**
     * Token class.
     */
    protected class Token {
        private String tokenStr;
        private Users user;
        private Date createdAt;
        private Date lastUsedAt;
        private Integer liveHours;

        public Token(Users user, Date createdAt, Date lastUsedAt, Integer liveHours) {
            tokenStr = UUID.randomUUID().toString().replace("-", "").toUpperCase();

            this.user = user;
            this.createdAt = createdAt;
            this.lastUsedAt = lastUsedAt;
            this.liveHours = liveHours;
        }

        public Users getUser() {
            return user;
        }

        public String getTokenStr() {
            return tokenStr;
        }

        public void setTokenStr(String tokenStr) {
            this.tokenStr = tokenStr;
        }

        public boolean isExpired() {
            Date now = new Date();
            Calendar cl = Calendar.getInstance();
            cl.setTime(lastUsedAt);

            cl.add(Calendar.HOUR, liveHours);

            return now.before(cl.getTime());
        }

        public void setUser(Users user) {
            this.user = user;
        }

        public Date getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(Date createdAt) {
            this.createdAt = createdAt;
        }

        public Date getLastUsedAt() {
            return lastUsedAt;
        }

        public void setLastUsedAt(Date lastUsedAt) {
            this.lastUsedAt = lastUsedAt;
        }

        public Integer getLiveHours() {
            return liveHours;
        }

        public void setLiveHours(Integer liveHours) {
            this.liveHours = liveHours;
        }
    }

    private ConcurrentMap<String, Token> tokenMap = new ConcurrentHashMap<>();
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    public void init() {
        // clean expired tokens
        executorService.scheduleWithFixedDelay(() -> {
            Iterator<Map.Entry<String, Token>> entryIterator = tokenMap.entrySet().iterator();
            while (entryIterator.hasNext()) {
                Map.Entry<String, Token> entry = entryIterator.next();
                if (entry.getValue().isExpired()) {
                    entryIterator.remove();
                }
            }
        }, CLEAN_DELAY_HRS, CLEAN_DELAY_HRS, TimeUnit.HOURS);
    }

    @PreDestroy
    public void onClose() {
        executorService.shutdown();
    }

    /**
     * Returns user byt not expired user.
     * @param token token string
     * @return user entity
     */
    public Users getUserByToken(String token) {
        Users user = null;

        Token tokenBean = tokenMap.get(token);
        if (tokenBean != null) {
            Date dt = new Date();

            if (!tokenBean.isExpired()) {
                user = tokenBean.getUser();
                tokenBean.setLastUsedAt(new Date());
            } else {
                tokenMap.remove(token);
            }
        }

        return user;
    }

    /**
     * Creates token for user.
     * @param user user entity
     * @param liveHours hours for expire
     * @return
     */
    public String createToken(Users user, Integer liveHours) {
        Token token = new Token(user, new Date(), new Date(), liveHours);

        tokenMap.put(token.getTokenStr(), token);

        return token.getTokenStr();
    }
}
