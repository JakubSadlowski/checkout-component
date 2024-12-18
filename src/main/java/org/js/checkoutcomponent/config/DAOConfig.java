package org.js.checkoutcomponent.config;

import org.js.checkoutcomponent.service.item.ItemsDAO;
import org.js.checkoutcomponent.service.item.ItemsDAOImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DAOConfig {
    @Bean
    public ItemsDAO getItemsDAO() {
        return new ItemsDAOImpl();
    }
}
