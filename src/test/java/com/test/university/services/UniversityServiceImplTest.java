package com.test.university.services;

import static com.test.university.data.Lector.Degree.ASSISTANT;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import com.test.university.data.Department;
import com.test.university.data.Lector;
import com.test.university.data.repository.DepartmentRepository;
import com.test.university.data.repository.LectorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class UniversityServiceImplTest {

    @Mock
    private static DepartmentRepository departmentRepository;

    @Mock
    private static LectorRepository lectorRepository;

    @InjectMocks
    private UniversityServiceImpl universityService;

    private final PrintStream standardOut = System.out;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void showDepartmentHeadIfExists() {
        String name = "Marketing";
        Department department = new Department();
        Lector lector = new Lector();
        lector.setId(1000L);
        lector.setFirstName("Rick");
        lector.setLastName("Brown");
        lector.setSalary(1200);
        lector.setDegree(ASSISTANT);
        List<Lector> lectors = new ArrayList<>();
        lectors.add(lector);
        department.setId(1000L);
        department.setName("Marketing");
        department.setHeadLector(lector);
        department.setLectors(lectors);

        when(departmentRepository.existsByName(name))
            .thenReturn(true);
        when(departmentRepository.findByName(name))
            .thenReturn(Optional.of(department));

        setUp();
        universityService.showDepartmentHeadIfExists(name);

        assertEquals("Head of Marketing department is Rick Brown", outputStreamCaptor.toString()
            .trim());
    }

    @Test
    void showDepartmentStatisticIfExists() {
        String name = "Marketing";
        Department department = new Department();
        Lector lector = new Lector();
        lector.setId(1000L);
        lector.setFirstName("Rick");
        lector.setLastName("Brown");
        lector.setSalary(1200);
        lector.setDegree(ASSISTANT);
        List<Lector> lectors = new ArrayList<>();
        lectors.add(lector);
        department.setId(1000L);
        department.setName("Marketing");
        department.setHeadLector(lector);
        department.setLectors(lectors);

        when(departmentRepository.existsByName(name))
            .thenReturn(true);
        when(departmentRepository.findByName(name))
            .thenReturn(Optional.of(department));

        setUp();
        universityService.showDepartmentStatisticIfExists(name);

        assertEquals("assistans - 1\nassociate professors - 0\nprofessors - 0", outputStreamCaptor.toString()
            .trim());
    }

    @Test
    void showCountOfEmployeeIfExists() {
        String name = "Marketing";
        Department department = new Department();
        Lector lector = new Lector();
        lector.setId(1000L);
        lector.setFirstName("Rick");
        lector.setLastName("Brown");
        lector.setSalary(1200);
        lector.setDegree(ASSISTANT);
        List<Lector> lectors = new ArrayList<>();
        lectors.add(lector);
        department.setId(1000L);
        department.setName("Marketing");
        department.setHeadLector(lector);
        department.setLectors(lectors);

        when(departmentRepository.existsByName(name))
            .thenReturn(true);
        when(departmentRepository.findByName(name))
            .thenReturn(Optional.of(department));

        setUp();
        universityService.showCountOfEmployeeIfExists(name);

        assertEquals("1", outputStreamCaptor.toString()
            .trim());
    }

    @Test
    void globalSearch() {
        Lector lector1 = new Lector();
        lector1.setId(1000L);
        lector1.setFirstName("Rick");
        lector1.setLastName("Brown");
        lector1.setSalary(1200);
        lector1.setDegree(ASSISTANT);
        Lector lector2 = new Lector();
        lector2.setId(1000L);
        lector2.setFirstName("Robert");
        lector2.setLastName("Wilson");
        lector2.setSalary(1200);
        lector2.setDegree(ASSISTANT);
        List<Lector> lectors = new ArrayList<>();
        Collections.addAll(lectors, lector1, lector2);

        when(lectorRepository.findByTemplate("%template%"))
            .thenReturn(lectors);

        setUp();
        universityService.globalSearch("template");

        assertEquals("Rick Brown, Robert Wilson", outputStreamCaptor.toString()
            .trim());
    }

    private void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
}
