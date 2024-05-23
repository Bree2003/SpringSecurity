package springbootsecurity.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import springbootsecurity.persistence.entity.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findUserEntityByUsername(String username);
    @Query("SELECT u FROM UserEntity u WHERE u.username = ?1")
    public Optional<UserEntity> findUser(String username);
}
