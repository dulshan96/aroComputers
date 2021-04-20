package lk.aro_computers.asset.user_management.role.entity;

import lk.aro_computers.asset.common_asset.model.enums.LiveDead;
import lk.aro_computers.asset.user_management.user.entity.User;
import lk.aro_computers.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role extends AuditEntity {

    @NotNull
    @Size( min = 4, message = "Your Role name cannot be accepted.Enter more than 4 characters!" )
    @Column( unique = true )
    private String roleName;

    @Enumerated(EnumType.STRING)
    private LiveDead liveDead;

    @ManyToMany(mappedBy = "roles")
    private List< User > users;
}
