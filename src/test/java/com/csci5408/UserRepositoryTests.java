package com.csci5408;

import static org.assertj.core.api.Assertions.assertThat;

import com.csci5408.model.UserData;
import com.csci5408.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    // test methods go below
    @Test
    public void testCreateUser() {
        UserData user = new UserData();
        user.setEmail("test@gmail.com");
        user.setName("test");
        user.setPhone("1234567891");
        user.setAddress("Halifax,Canada");
        user.setUname("Test123");
        user.setPassword("password");

        UserData savedUser = repo.save(user);

        UserData existUser = entityManager.find(UserData.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

}
