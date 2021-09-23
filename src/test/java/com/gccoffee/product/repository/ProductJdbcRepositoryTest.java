package com.gccoffee.product.repository;

import static com.wix.mysql.EmbeddedMysql.*;
import static com.wix.mysql.config.MysqldConfig.*;
import static com.wix.mysql.distribution.Version.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.gccoffee.model.Category;
import com.gccoffee.product.domain.Product;
import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductJdbcRepositoryTest {

	static EmbeddedMysql embeddedMysql;

	private final Product newProduct = new Product(UUID.randomUUID(), "new-product", Category.COFFEE_BEAN_PACKAGE,
		1000L);

	@Autowired
	ProductRepository repository;

	@BeforeAll
	static void setup() {
		MysqldConfig config = aMysqldConfig(v8_0_11)
			.withCharset(Charset.UTF8)
			.withPort(2215)
			.withUser("test", "test1234!")
			.withTimeZone("Asia/Seoul")
			.build();

		embeddedMysql = anEmbeddedMysql(config)
			.addSchema("test_management", ScriptResolver.classPathScript("schema.sql"))
			.start();
	}

	@AfterAll
	static void cleanup() {
		embeddedMysql.stop();
	}

	@Test
	@Order(1)
	@DisplayName("상품 추가")
	void testInsert() {
		repository.insert(newProduct);
		var all = repository.findAll();
		assertThat(all.isEmpty(), is(false));
	}

	@Test
	@Order(2)
	@DisplayName("상품 이름으로 조회")
	void testFindByName() {
		var product = repository.findByName(newProduct.getProductName());
		assertThat(product.isEmpty(), is(false));
	}

	@Test
	@Order(3)
	@DisplayName("상품 아이디로 조회")
	void testFindById() {
		Optional<Product> product = repository.findById(newProduct.getProductId());
		assertThat(product.isEmpty(), is(false));
	}

	@Test
	@Order(4)
	@DisplayName("카테고리로 상품들 조회")
	void testFindByCategory() {
		List<Product> products = repository.findByCategory(Category.COFFEE_BEAN_PACKAGE);
		assertThat(products.isEmpty(), is(false));
	}

	@Test
	@Order(5)
	@DisplayName("상품 수정")
	void testUpdate() {
		newProduct.changeProductName("updated-product");
		repository.update(newProduct);

		Optional<Product> product = repository.findById(newProduct.getProductId());
		assertThat(product.isEmpty(), is(false));
		assertThat(product.get(), samePropertyValuesAs(newProduct));
	}

	@Test
	@Order(6)
	@DisplayName("상품 전체 삭제")
	void testDeleteAll() {
		repository.deleteAll();
		List<Product> all = repository.findAll();
		assertThat(all.isEmpty(), is(true));
	}

}