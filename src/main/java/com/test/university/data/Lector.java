package com.test.university.data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
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
@Table(name = "lector")
public class Lector extends BaseEntity {

    private String firstName;

    private String lastName;

    private int salary;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    public enum Degree {
        ASSISTANT, ASSOCIATE_PROFESSOR, PROFESSOR;
    }

    @ManyToMany(mappedBy = "lectors", fetch = FetchType.EAGER)
    private List<Department> departments = new ArrayList<>();

}
