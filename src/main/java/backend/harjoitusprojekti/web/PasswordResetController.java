package backend.harjoitusprojekti.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

import backend.harjoitusprojekti.model.AppUser;
import backend.harjoitusprojekti.model.AppUserRepository;
import backend.harjoitusprojekti.model.ResetPasswordForm;

import backend.harjoitusprojekti.model.UserNotFoundException;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;

@Controller
public class PasswordResetController {

    private AppUserRepository urepository;

    @Autowired
    private JavaMailSender mailSender;

    public PasswordResetController(AppUserRepository urepository) {
        this.urepository = urepository;
    }

    // Direct user to the forgot password page
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String forgotPassword(Model model) {
        return "forgotpassword";
    }

    // Send a reset email to the user
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public String processForgotPassword(HttpServletRequest request, Model model){
        try {
            String email = request.getParameter("email");
            UUID uuid = UUID.randomUUID();
            String token = uuid.toString().replaceAll("-", "");

            Optional<AppUser> appUserOptional = urepository.findByEmail(email);

            if (appUserOptional.isEmpty()) {
                throw new UserNotFoundException("Could not find the user with this email.");
            }

            AppUser appUser = appUserOptional.get();
            appUser.setResetToken(token);
            urepository.save(appUser);

            String url = request.getRequestURL().toString();
            String passwordResetLink = url.replace(request.getServletPath(), "") + "/resetpassword?token=" + token;

            sendResetEmail(email, passwordResetLink);

            model.addAttribute("message", "We have sent you a reset link. Please check your email.");

        } catch (UserNotFoundException exception) {
            model.addAttribute("error", exception.getMessage());
        } catch (MessagingException exception) {
            model.addAttribute("error", "Error while sending email.");
        }

        return "forgotpassword";
    }

    // Direct user to the reset password form if the token is valid
    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    public String showResetPasswordForm(@RequestParam("token") String token, Model model) {
        AppUser user = urepository.findByResetToken(token);

        if (user == null) {
            return "tokenerror";
        }

        model.addAttribute("token", token);
        model.addAttribute("resetform", new ResetPasswordForm());
        return "resetpassword";
    }

    // Reset user password
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public String processResetPassword(@RequestParam("token") String token,
            @Valid @ModelAttribute("resetform") ResetPasswordForm resetForm,
            BindingResult bindingResult,
            Model model) {

        AppUser appUser = urepository.findByResetToken(token);

        // tarkistus ettei token ole virheellinen tai vanhentunut
        if (appUser == null) {
            return "tokenerror";
        }

        // jos validoinnissa virheitä, palataan lomakkeelle
        if (bindingResult.hasErrors()) {
            model.addAttribute("token", token);
            return "resetpassword";
        }

        // salasanatarkistus
        if (!resetForm.getPassword().equals(resetForm.getPasswordCheck())) {
            bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
            model.addAttribute("token", token);
            return "resetpassword";
        }

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String hashPwd = bc.encode(resetForm.getPassword());

        appUser.setPasswordHash(hashPwd);
        appUser.setResetToken(null);
        urepository.save(appUser);

        return "redirect:/login";
    }

    // Send password reset email 
    //sähköposti tulee omasta osoitteestani tässä harjoituksessa, sillä käytän sitä esimerkki sähköpostina 
    @Value("${spring.mail.username}")
    private String fromEmail;

    private void sendResetEmail(String email, String passwordResetLink) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromEmail);
        helper.setTo(email);

        String content = "<p>Hello,</p>" + "<p>You have requested to reset your password</p>"
                + "<p>Click the link below to reset your password</p>" + "<p><a href=\"" + passwordResetLink
                + "\">Change my password</a></p>";

        helper.setSubject("Password reset link");
        helper.setText(content, true);

        mailSender.send(message);
    }
}
