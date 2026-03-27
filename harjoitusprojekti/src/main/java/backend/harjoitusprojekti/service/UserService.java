package backend.harjoitusprojekti.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import backend.harjoitusprojekti.model.AppUser;
import backend.harjoitusprojekti.model.AppUserRepository;
import backend.harjoitusprojekti.web.RegisterForm;
//tämä hakee tiedot formista tarkistaa ettei ne ole käytössä
//lopuksi tallentaa uuden käyttäjän roolilla user tietokantaan
//huom VASTA TÄMÄ TALLENTAA
@Service
public class UserService {
    private final AppUserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(AppUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public void saveNewUser(RegisterForm registerForm) {
        if (userRepository.findByUsername(registerForm.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.findByEmail(registerForm.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        AppUser user = new AppUser();
        user.setUsername(registerForm.getUsername());
        user.setEmail(registerForm.getEmail());
        user.setPasswordHash(passwordEncoder.encode(registerForm.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
    }
}
