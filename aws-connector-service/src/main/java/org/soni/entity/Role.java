package org.soni.entity;



import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.envers.Audited;
import org.soni.config.AuditTable;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@Audited
@EqualsAndHashCode(callSuper = false)
@DynamicUpdate
@DynamicInsert
public class Role extends AuditTable<String> implements Serializable {
    private static final long serialVersionUID = -3128980483134253500L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String name;
    private String role;
    private String description;
    private boolean enabled = true;
}