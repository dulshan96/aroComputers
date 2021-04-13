package lk.aro_computers.asset.employee.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.aro_computers.asset.common_asset.model.FileInfo;
import lk.aro_computers.asset.common_asset.model.enums.CivilStatus;
import lk.aro_computers.asset.common_asset.model.enums.Gender;
import lk.aro_computers.asset.common_asset.model.enums.LiveDead;
import lk.aro_computers.asset.common_asset.model.enums.Title;
import lk.aro_computers.asset.employee.entity.enums.Designation;
import lk.aro_computers.asset.employee.entity.enums.EmployeeStatus;
import lk.aro_computers.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Employee")
public class Employee extends AuditEntity {

    @Column(unique = true)
    private String code;

    @Size(min = 4, message = "Your name cannot be accepted.required least 4 characters ")
    private String name;

    @Size(min = 4, message = "At least 4 characters should be included calling name")
    private String callingName;

    @Size(max = 12, min = 10, message = "NIC number is contained numbers between 9 and X/V or 12 ")
    @Column(unique = true)
    private String nic;

    @Size(max = 10, message = "Mobile number length should be contained 10 or 9")
    private String mobileOne;

    @Size(max = 10, message = "Mobile number length should be contained 10 or 9")
    private String mobileTwo;

    @Size(max = 10, message = "Phone number length should be contained 10 or 9")
    private String land;

    @Column(unique = true)
    private String officeEmail;

    @Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NULL", length = 255)
    private String address;

    @Enumerated(EnumType.STRING)
    private Title title;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Designation designation;

    @Enumerated(EnumType.STRING)
    private CivilStatus civilStatus;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus employeeStatus;

    @Enumerated(EnumType.STRING)
    private LiveDead liveDead;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfAssignment;


    @Transient
    private MultipartFile file;

    @Transient
    private FileInfo fileInfo;

}
