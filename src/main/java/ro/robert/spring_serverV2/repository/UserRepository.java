package ro.robert.spring_serverV2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.robert.spring_serverV2.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query(value = "select * from users where email=(?1)",nativeQuery = true)
    Optional<User> findByEmail(@Param("?") String email);
}
