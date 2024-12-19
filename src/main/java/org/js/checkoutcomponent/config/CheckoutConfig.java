package org.js.checkoutcomponent.config;

import org.js.checkoutcomponent.service.CheckoutService;
import org.js.checkoutcomponent.service.item.ItemsDAO;
import org.js.checkoutcomponent.service.item.ItemsDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckoutConfig {
    @Bean
    public CheckoutService getCheckoutService() {
        return new CheckoutService(getItemsDAO());
    }

    @Bean
    public ItemsDAO getItemsDAO() {
        return new ItemsDAOImpl();
    }
}
