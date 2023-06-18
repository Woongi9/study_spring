package hello.itemservice.repository.mybatis;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;

/**
 * packageName :  hello.itemservice.repository.mybatis
 * fileName : ItemMapper
 * author :  JinWoong
 * date : 2023/06/18 
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/06/18           JinWoong            init
 */

@Mapper
public interface ItemMapper {
	void save(Item item);

	void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateDto);

	List<Item> findAll(ItemSearchCond itemSearchCond);

	Optional<Item> findById(Long id);
}
