package raf.jmijatovic11421rn.RAFVacuumControl.model;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Vacuum {

    @Id
    @Generated
    private Long vacuum_id;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column
    private String addedBy;

    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;
}
