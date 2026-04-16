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
    public String processRegistration(@ModelAttribute("user") UserDTO userDTO, 
        RedirectAttributes redirectAttributes) {
        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("error", "пароли разные емае");
            return "redirect:/register";
        }
        
        User user = new User();
        user.setUsername(userDTO.getName());
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
