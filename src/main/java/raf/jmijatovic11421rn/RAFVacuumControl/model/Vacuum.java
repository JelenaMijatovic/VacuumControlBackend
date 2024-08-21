package raf.jmijatovic11421rn.RAFVacuumControl.model;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Vacuum {

    @Id
    @Generated
    private Long vacuum_id;

    @Column
    private Status status;

    @Column
    private String addedBy;

    @Column
    private Boolean active;
}
