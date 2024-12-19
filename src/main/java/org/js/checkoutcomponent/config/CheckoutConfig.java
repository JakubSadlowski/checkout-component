package org.js.checkoutcomponent.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.js.checkoutcomponent.errors.GlobalExceptionHandler;
import org.js.checkoutcomponent.service.CheckoutService;
import org.js.checkoutcomponent.service.item.ItemsDAO;
import org.js.checkoutcomponent.service.item.ItemsDAOImpl;
import org.js.checkoutcomponent.service.item.mapper.ItemsMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.js.checkoutcomponent.service.item.mapper")
public class CheckoutConfig {
    @Bean
    public CheckoutService getCheckoutService(ItemsDAO itemsDAO) {
        return new CheckoutService(itemsDAO);
    }

    @Bean
    public ItemsDAO getItemsDAO(ItemsMapper itemsMapper) {
        return new ItemsDAOImpl(itemsMapper);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
            .setName("testdb") // Specify the database name
            .addScript("classpath:/database/schema.sql")
            .addScript("classpath:/database/data.sql")
            .build();
    }
}
