package com.example.demo.Controller;
import com.example.demo.DTO.UserDTO;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult; 
import jakarta.validation.Valid;


@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid @ModelAttribute("user") UserDTO userDTO,
        BindingResult bindingResult,
        Model model,
        RedirectAttributes redirectAttributes) {        

        if (bindingResult.hasErrors()) {
          model.addAttribute("user", userDTO);
          return "register";
        }
        
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            model.addAttribute("notMatchPasswordError", "пароли разные емае");
            model.addAttribute("user", userDTO);
            return "register";
        }

        if (userRepository.existsByMail(userDTO.getMail())) {
            model.addAttribute("existsByMailError", "юзер алреди екзистс");
            model.addAttribute("user", userDTO);
            return "register";
        }   

        if (userRepository.existsByUsername(userDTO.getUsername())) {
          model.addAttribute("existsByUsernameError", "имя юзера занято сори");
          model.addAttribute("user", userDTO);
          return "register";
        }
    
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setMail(userDTO.getMail());
        user.setDate(userDTO.getDate());
        user.setPassword(userDTO.getPassword());
        
        userRepository.save(user);
        

        redirectAttributes.addFlashAttribute("name", user.getUsername());
        redirectAttributes.addFlashAttribute("mail", user.getMail());
        
        return "redirect:/result";
    }
    
    @GetMapping("/result")
    public String showResult() {
        return "result";
    }
}
