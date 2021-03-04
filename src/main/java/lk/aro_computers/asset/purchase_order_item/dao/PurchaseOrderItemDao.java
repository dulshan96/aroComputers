package lk.aro_computers.asset.purchase_order_item.dao;


import lk.aro_computers.asset.item.entity.Item;
import lk.aro_computers.asset.purchase_order.entity.PurchaseOrder;
import lk.aro_computers.asset.purchase_order_item.entity.PurchaseOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseOrderItemDao extends JpaRepository< PurchaseOrderItem, Integer> {
    PurchaseOrderItem findByPurchaseOrderAndItem(PurchaseOrder purchaseOrder, Item item);
    List<PurchaseOrderItem> findByPurchaseOrder(PurchaseOrder purchaseOrder);
}
