package lk.aro_computers.asset.employee.dao;


import lk.aro_computers.asset.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeDao extends JpaRepository< Employee, Integer> {
    Employee findFirstByOrderByIdDesc();

    Employee findByNic(String nic);
    Employee findByOfficeEmail(String officeEmail);
    Employee  findByMobileOne(String mobileOne);


}
