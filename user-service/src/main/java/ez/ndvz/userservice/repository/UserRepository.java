package ez.ndvz.userservice.repository;

import ez.ndvz.userservice.domain.models.User;
import ez.ndvz.userservice.service.userDetailsServiceImpl.UserDetailsImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findApplicationUserByName(String username);

    Optional<User> findApplicationUserByEmail(String email);

}
