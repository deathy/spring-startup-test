package ro.deathy.spring.springstartuptest;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
        basePackages = {"ro.deathy"},
        entityManagerFactoryRef = "myappEntityManagerFactory")
@EnableTransactionManagement
public class PersistenceConfiguration {

    @Primary
    @Bean(name = "myappEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, DataSource db1DataSource) {
        return builder
                .dataSource(db1DataSource)
                .packages("ro.deathy")
                .persistenceUnit("default")
//                .properties(Map.of("hibernate.archive.scanner","org.hibernate.boot.archive.scan.internal.DisabledScanner"))
                .build();
    }

}
