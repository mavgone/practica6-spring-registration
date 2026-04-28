package com.example.demo.DTO;
import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
  
public class UserDTO {
    
    @NotBlank(message = "Имя пользователя содержит недопустимые символы")
    @Size(min=6, max=20, message = "Имя пользователя должно содержать не менее 6 символов")
    private String username;
    
    @NotBlank(message="Неверный email")
    @Email    
    private String mail;
    
    private LocalDate date; 
    
    @NotBlank(message="Пароль содержит недопустимые символы")
    @Size(min=8, max=15, message="Пароль должен содержать не менее 8 символов")
    private String password;
    private String confirmPassword;

    public UserDTO() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public LocalDate getDate() {
      return date;
    }

    public void setDate(LocalDate date) {
      this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
