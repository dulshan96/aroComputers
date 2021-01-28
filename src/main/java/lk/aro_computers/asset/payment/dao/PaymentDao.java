package lk.aro_computers.asset.payment.dao;


import lk.aro_computers.asset.payment.entity.Payment;
import lk.aro_computers.asset.purchase_order.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentDao extends JpaRepository< Payment,Integer> {
    List< Payment> findByPurchaseOrder(PurchaseOrder purchaseOrder);

    Payment findFirstByOrderByIdDesc();

  List< Payment> findByCreatedAtIsBetween(LocalDateTime from, LocalDateTime to);
  List< Payment> findByCreatedAtIsBetweenAndCreatedBy(LocalDateTime from, LocalDateTime to, String userName);
}
