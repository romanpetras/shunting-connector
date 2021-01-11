package eu.circletouch.shuntingconn.repositories;

import eu.circletouch.shuntingconn.entities.LocomotorEntity;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
class MissionRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LocomotorRepository locomotorRepository;

    @Test
    public void whenFindByName_thenReturnLocomotorEntity() {
        LocomotorEntity alex = new LocomotorEntity();
        entityManager.persistAndFlush(alex);

        LocomotorEntity found = locomotorRepository.findById(alex.getId()).get();
//        assertThat(found.getName()).isEqualTo(alex.getName());
    }

    @Test
    public void whenInvalidName_thenReturnNull() {
//        LocomotorEntity fromDb = locomotorRepository.findByName("doesNotExist");
//        assertThat(fromDb).isNull();
    }

    @Test
    public void whenFindById_thenReturnLocomotorEntity() {
        LocomotorEntity emp = new LocomotorEntity();
        entityManager.persistAndFlush(emp);

        LocomotorEntity fromDb = locomotorRepository.findById(emp.getId()).orElse(null);
//        assertThat(fromDb.getName()).isEqualTo(emp.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
//        LocomotorEntity fromDb = locomotorRepository.findById(-11l).orElse(null);
//        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfLocomotorEntitys_whenFindAll_thenReturnAllLocomotorEntitys() {
        LocomotorEntity alex = new LocomotorEntity();
        LocomotorEntity ron = new LocomotorEntity();
        LocomotorEntity bob = new LocomotorEntity();

        entityManager.persist(alex);
        entityManager.persist(bob);
        entityManager.persist(ron);
        entityManager.flush();

        List<LocomotorEntity> allLocomotorEntitys = locomotorRepository.findAll();

//        assertThat(allLocomotorEntitys).hasSize(3).extracting(LocomotorEntity::getName).containsOnly(alex.getName(), ron.getName(), bob.getName());
    }
}