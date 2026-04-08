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
@Controller
public class PasswordResetController {

    private AppUserRepository urepository;

    public PasswordResetController(AppUserRepository urepository) {
        this.urepository = urepository;
    }

	@Autowired
	private JavaMailSender mailSender;


    // Verify user email
    @RequestMapping(value = "/verifyemail", method = RequestMethod.GET)
    public String verifyEmail(@RequestParam(value = "token") String token, Model model) {
        AppUser user = urepository.findByVerificationToken(token);

        if (user != null) {
            user.setEnabled(true);
            user.setVerificationToken(null);
            urepository.save(user);

            return "verify_email";
        } else {
            return "token_error";
        }

    }

    // Direct user to the forgot password page
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
    public String forgotPassword(Model model) {
        return "forgotpassword";
    }

    // Send a reset email to the user
    @RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
    public String processForgotPasword(HttpServletRequest request, Model model) throws MessagingException {
        try {
            String email = request.getParameter("email");
            UUID uuid = UUID.randomUUID();
            String token = uuid.toString().replaceAll("-", "");

  
            Optional<AppUser> appUserOptional = urepository.findByEmail(email);

                if (appUserOptional.isEmpty()) {
                    throw new UserNotFoundException("Could not find the user with this email.");
                }

                AppUser appUserReal = appUserOptional.get();


            if (appUserReal == null) {
                throw new UserNotFoundException("Could not find the user with this email.");
            } else if (!appUserReal.isEnabled()) {
                throw new UserNotFoundException(
                        "The user is not verified. Please check your email for verification link");
            } else {
                appUserReal.setResetToken(token);
                urepository.save(appUserReal);
            }

            String url = request.getRequestURL().toString();

            // gets rid of /forgot_password
            String passwordResetLink = url.replace(request.getServletPath(), "") + "/resetpassword?token=" + token;
            System.out.println(passwordResetLink);

            sendResetEmail(email, passwordResetLink);

            model.addAttribute("message", "We have sent you a reset link. Please check your email.");

        } catch (UserNotFoundException exeption) {
            model.addAttribute("error", exeption.getMessage());
        } catch (MessagingException exception) {
            model.addAttribute("error", "Error while sending email");
        }
        return "forgotpassword";
    }

    // Direct user to the reset password form if the token is valid
    @RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
    public String showResetPasswordForm(@RequestParam(value = "token") String token, Model model) {
        AppUser user = urepository.findByResetToken(token);
        model.addAttribute("token", token);
        model.addAttribute("resetform", new ResetPasswordForm());

        if (user == null) {
            return "tokenerror";
        }

        return "resetpassword";
    }

    // Reset user password
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST)
    public String showResetPasswordForm(@RequestParam(value = "token") String token,
            @Valid @ModelAttribute("resetform") ResetPasswordForm resetForm, BindingResult bindingResult) {
        AppUser appUser = urepository.findByResetToken(token);

        if (!bindingResult.hasErrors()) {
            if (resetForm.getPassword().equals(resetForm.getPasswordCheck())) {
                String pwd = resetForm.getPassword();
                BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
                String hashPwd = bc.encode(pwd);

                appUser.setPasswordHash(hashPwd);
                appUser.setResetToken(null);

                urepository.save(appUser);
            }
        } else {
            bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
            return "resetpassword";
        }

        return "redirect:/login";
    }

    // Sending verification email
    private void sendVerificationEmail(String email, String verificationLink) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("languageapp4@gmail.com");
        helper.setTo(email);

        String content = "<p>Hello,</p>"
                + "<p>Thank you for registering. Please verify your email by clicking the link below:</p>"
                + "<p><a href=\"" + verificationLink + "\">Verify my email</a></p>";

        helper.setSubject("Email Verification");
        helper.setText(content, true);

        mailSender.send(message);

    }

    // Send password reset email
    private void sendResetEmail(String email, String passwordResetLink) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("languageapp4@gmail.com");
        helper.setTo(email);

        String content = "<p>Hello,</p>" + "<p>You have requested to reset your password</p>"
                + "<p>Click the link below to reset your password</p>" + "<p><a href=\"" + passwordResetLink
                + "\">Change my password</a></p>";

        helper.setSubject("Password reset link");
        helper.setText(content, true);

        mailSender.send(message);
    }
}
