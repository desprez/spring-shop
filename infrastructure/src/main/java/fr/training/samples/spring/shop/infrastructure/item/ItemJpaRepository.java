package fr.training.samples.spring.shop.infrastructure.item;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.training.samples.spring.shop.domain.item.Item;

public interface ItemJpaRepository extends JpaRepository<Item, String> {

}
