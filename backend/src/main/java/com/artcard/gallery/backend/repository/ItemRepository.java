package com.artcard.gallery.backend.repository;

import com.artcard.gallery.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {
}
