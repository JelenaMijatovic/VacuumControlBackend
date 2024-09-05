package raf.jmijatovic11421rn.RAFVacuumControl.model;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
public class ErrorMessage {

    @Id
    @Generated
    private Long id;

    @Column
    private Date date;

    @Column
    private Long vacuumId;

    @Column
    private String action;

    @Column
    private String errorText;
}
