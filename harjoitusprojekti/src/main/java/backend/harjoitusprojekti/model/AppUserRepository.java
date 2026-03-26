package backend.harjoitusprojekti.model;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
	AppUser findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
    
}