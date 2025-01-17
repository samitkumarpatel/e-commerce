package net.samitkumar.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class DbApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void entityTest(@Autowired UserRepository userRepository,
					@Autowired CategoryRepository categoryRepository,
					@Autowired ProductRepository productRepository,
					@Autowired InventoryRepository inventoryRepository,
					@Autowired CartRepository cartRepository,
					@Autowired OrderRepository orderRepository) {
		assertAll(
				() -> userRepository
						.saveAll(
								List.of(
										User.builder().name("uone").email("uone@noreply.net").passwordHash("1234one")
												.addresses(
														Set.of(
																UserAddress.builder().address("Abc Street, NZ").build(),
																UserAddress.builder().address("LLM Street, NZ").build()
														)
												)
												.build(),
										User.builder().name("utwo").email("utwo@noreply.net").passwordHash("1234two")
												.addresses(
														Set.of(
																UserAddress.builder().address("KKR plaza, IN").build()
														)
												)
												.build()
								)
						),
				() -> userRepository.findAll().forEach(System.out::println),
				() -> categoryRepository
					.saveAll(
							List.of(
									Category.builder().name("food").description("Grocery Item").build(),
									Category.builder().name("drinks").description("Energy drinks").build()
							)
					),
				() -> categoryRepository.findAll().forEach(System.out::println),
				() -> productRepository
					.saveAll(
							List.of(
									Product.builder()
											.category(1L)
											.name("Milk")
											.price(200.40)
											.description("Fat milk")
											.build(),
									Product.builder()
											.category(1L)
											.name("Fruits Juice")
											.price(100.00)
											.description("Watermelon Juice")
											.build()
							)

					),
				() -> productRepository.findAll().forEach(System.out::println),
				() -> inventoryRepository
						.saveAll(
								List.of(
										Inventory.builder().product(1L).quantity(30).build(),
										Inventory.builder().product(2L).quantity(10).build()
								)
						),
				() -> inventoryRepository.findAll().forEach(System.out::println),
				() -> cartRepository.save(
						Cart.builder()
							.users(1L)
							.items(
									Set.of(
											CartItem.builder().product(1L).quantity(2).build(),
											CartItem.builder().product(2L).quantity(1).build()
									)
							)
							.build()
						),
				() -> cartRepository.findAll().forEach(System.out::println),
				() -> orderRepository.save(
						Order.builder()
								.users(1L)
								.deliveryAddress(1L)
								.items(
										Set.of(
												OrderItem.builder().product(1L).quantity(2).price(300.00).build(),
												OrderItem.builder().product(2L).quantity(1).price(200.10).build()
										)
								)
								//.status(OrderStatus.PENDING)
								.paymentReference(UUID.randomUUID().toString())
								.totalPrice(600.22)
								.build()
				),
				() -> orderRepository.findAll().forEach(System.out::println)
		);
	}

}
