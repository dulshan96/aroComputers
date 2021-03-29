package lk.aro_computers.asset.ledger.dao;


import lk.aro_computers.asset.item.entity.Item;
import lk.aro_computers.asset.ledger.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LedgerDao extends JpaRepository< Ledger, Integer > {
  List< Ledger > findByItem(Item item);

  List< Ledger > findByCreatedAtBetween(LocalDateTime form, LocalDateTime to);

  Ledger findByItemAndSellPrice(Item item, BigDecimal sellPrice);
}
