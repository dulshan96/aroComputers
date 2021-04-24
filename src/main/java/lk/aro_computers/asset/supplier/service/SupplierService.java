package lk.aro_computers.asset.supplier.service;

import lk.aro_computers.asset.common_asset.model.enums.LiveDead;
import lk.aro_computers.asset.employee.entity.Employee;
import lk.aro_computers.asset.supplier.dao.SupplierDao;
import lk.aro_computers.asset.supplier.entity.Supplier;
import lk.aro_computers.asset.supplier_item.entity.enums.ItemSupplierStatus;
import lk.aro_computers.util.interfaces.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@CacheConfig(cacheNames = "supplier")
public class SupplierService implements AbstractService<Supplier, Integer> {
    private final SupplierDao supplierDao;

    @Autowired
    public SupplierService(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    public List<Supplier> findAll() {
        return supplierDao.findAll().stream()
                .filter(x -> LiveDead.ACTIVE.equals(x.getLiveDead()))
                .collect(Collectors.toList());
    }

    public Supplier findById(Integer id) {
        return supplierDao.getOne(id);
    }

    public Supplier persist(Supplier supplier) {
        if (supplier.getId() == null) {
            supplier.setItemSupplierStatus(ItemSupplierStatus.CURRENTLY_BUYING);
            supplier.setLiveDead(LiveDead.ACTIVE);
        }
        return supplierDao.save(supplier);
    }

    public boolean delete(Integer id) {
        Supplier supplier =  supplierDao.getOne(id);
        supplier.setLiveDead(LiveDead.STOP);
        supplierDao.save(supplier);
        return false;
    }

    public List<Supplier> search(Supplier supplier) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Supplier> supplierExample = Example.of(supplier, matcher);
        return supplierDao.findAll(supplierExample);
    }

    public Supplier lastSupplier() {
        return supplierDao.findFirstByOrderByIdDesc();
    }

    public Supplier findByIdAndItemSupplierStatus(Integer supplierId, ItemSupplierStatus itemSupplierStatus) {
        return supplierDao.findByIdAndItemSupplierStatus(supplierId,itemSupplierStatus);
    }

    @Cacheable
    public Supplier findByName(String name) {
        return supplierDao.findByName(name);
    }

    @Cacheable
    public Supplier findByEmail(String email) {
        return supplierDao.findByEmail(email);
    }
}
