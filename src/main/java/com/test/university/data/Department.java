package com.test.university.data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class Department extends BaseEntity{

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "department_lector",
        joinColumns = {@JoinColumn(name = "department_id")},
        inverseJoinColumns = {@JoinColumn(name = "lector_id")}
    )
    private List<Lector> lectors = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "lector_id")
    private Lector headLector;

}
