package service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.item.QItem;
import repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void test() {
        QItem item = QItem.item;

        itemRepository.findAll(
                item.name.contains("장난감").and(item.price.between(10000, 20000))
        );//QueryDSL 페이징과 정렬 기능도 함께 사용할 수 있음.
        //QueryDslPredicateExecutor의 한계 join, fetch를 사용할 수 없음. 묵시적 조인은 가능함.
        //JPAQuery를 직접 사용하거나 스프링 데이터JPA가 제공하는 QueryDslRepositorySupport를 사용해야 함.
    }
}
