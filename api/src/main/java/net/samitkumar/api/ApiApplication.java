package net.samitkumar.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

	@Bean
	DbClient dbClient(WebClient.Builder builder, @Value("${spring.application.db.uri}") String dbURI) {
		WebClientAdapter adapter = WebClientAdapter.create(builder.baseUrl(dbURI).build());
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

		return factory.createClient(DbClient.class);
	}

	@Bean
	RouterFunction<ServerResponse> routerFunction(RouterHandler routerHandler) {
		return RouterFunctions
				.route()
				.path("/category", categoryPath -> categoryPath
						.GET("", routerHandler::getAllCategory))
				.build();

	}
}

@Component
@RequiredArgsConstructor
class RouterHandler {
	final DbClient dbClient;

	public Mono<ServerResponse> getAllCategory(ServerRequest request) {
		return dbClient.allCategory()
				.map(root -> root._embedded().categories())
				.flatMap(ServerResponse.ok()::bodyValue);
	}
}
record Root(Embedded _embedded) {
	record Embedded(List<Category> categories) {
		record Category(Long id, String name, String description, LocalDateTime createdAt){}
	}
}



@HttpExchange("/db")
interface DbClient {

	@GetExchange("/categories")
	Mono<Root> allCategory();

	@GetExchange("/categories/{id}")
	Root.Embedded.Category categoryById(@PathVariable Long id);

	@PostExchange("/categories")
	Root.Embedded.Category saveCategory(@RequestBody Root.Embedded.Category category);

	@PutExchange("/categories/{id}")
	Root.Embedded.Category updateCategory(@RequestBody Root.Embedded.Category category, @PathVariable("id") Long id);

}
