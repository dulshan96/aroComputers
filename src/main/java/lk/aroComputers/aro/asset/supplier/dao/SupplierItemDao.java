package lk.aroComputers.aro.asset.supplier.dao;


import lk.aroComputers.aro.asset.item.entity.Item;
import lk.aroComputers.aro.asset.supplier.entity.Enum.ItemSupplierStatus;
import lk.aroComputers.aro.asset.supplier.entity.Supplier;
import lk.aroComputers.aro.asset.supplier.entity.SupplierItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SupplierItemDao extends JpaRepository<SupplierItem, Integer> {
    SupplierItem findBySupplierAndItem(Supplier supplier, Item item);

    List<SupplierItem> findBySupplier(Supplier supplier);

    List<SupplierItem> findBySupplierAndItemSupplierStatus(Supplier supplier, ItemSupplierStatus itemSupplierStatus);
}
