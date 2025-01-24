package net.samitkumar.db;

import net.samitkumar.db.entity.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class DbApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbApplication.class, args);
	}

	@Bean
	RepositoryRestConfigurer repositoryRestConfigurer() {
		return RepositoryRestConfigurer.withConfig(config -> {
			config.exposeIdsFor(
					User.class,
					UserAddress.class,
					Category.class,
					Cart.class,
					CartItem.class,
					Product.class,
					Inventory.class,
					Order.class,
					OrderItem.class,
					OrderStatus.class);
		});
	}

}

