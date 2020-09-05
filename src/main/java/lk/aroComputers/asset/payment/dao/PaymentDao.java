package lk.aroComputers.asset.payment.dao;

import lk.aroComputers.asset.PurchaseOrder.entity.PurchaseOrder;
import lk.aroComputers.asset.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository< Payment,Integer> {
    List< Payment> findByPurchaseOrder(PurchaseOrder purchaseOrder);

    Payment findFirstByOrderByIdDesc();
}
