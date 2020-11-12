package lk.aro_computers.asset.payment.dao;

import lk.aro_computers.asset.purchase_order.entity.PurchaseOrder;
import lk.aro_computers.asset.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository< Payment,Integer> {
    List< Payment> findByPurchaseOrder(PurchaseOrder purchaseOrder);

    Payment findFirstByOrderByIdDesc();
}
