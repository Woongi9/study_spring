package hello.itemservice.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jdbctemplate.JdbcTemplateItemRepositoryV1;
import hello.itemservice.repository.memory.MemoryItemRepository;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;

/**
 * packageName :  hello.itemservice.config
 * fileName : JdbcTemplateV1Config
 * author :  JinWoong
 * date : 2023/06/17 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/06/17           JinWoong            init
 */

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateV1Config {

	private final DataSource dataSource;

	@Bean
	public ItemService itemService() {
		return new ItemServiceV1(itemRepository());
	}

	@Bean
	public ItemRepository itemRepository() {
		return new JdbcTemplateItemRepositoryV1(dataSource);
	}

}
