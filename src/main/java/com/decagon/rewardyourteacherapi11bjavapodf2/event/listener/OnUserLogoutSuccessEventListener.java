package com.decagon.rewardyourteacherapi11bjavapodf2.event.listener;

import com.decagon.rewardyourteacherapi11bjavapodf2.cache.LoggedOutJwtTokenCache;
import com.decagon.rewardyourteacherapi11bjavapodf2.event.OnUserLogoutSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OnUserLogoutSuccessEventListener implements ApplicationListener<OnUserLogoutSuccessEvent> {

    private final LoggedOutJwtTokenCache tokenCache;
    private static final Logger logger = LoggerFactory.getLogger(OnUserLogoutSuccessEventListener.class);

    @Autowired
    public OnUserLogoutSuccessEventListener(LoggedOutJwtTokenCache tokenCache) {
        this.tokenCache = tokenCache;
    }

    public void onApplicationEvent(OnUserLogoutSuccessEvent event) {
        if (null != event) {

            logger.info(String.format("Log out success event received for user [%s] for device", event.getUserEmail()));
            tokenCache.markLogoutEventForToken(event);
        }
    }
}