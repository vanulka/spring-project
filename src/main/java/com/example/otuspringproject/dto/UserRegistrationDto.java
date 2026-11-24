package com.example.otuspringproject.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationDto {

    @NotBlank(message = "Имя пользователя не может быть пустым")
    @Size(min = 3, max = 20, message = "Имя пользователя должно быть от 3 до 20 символов")
    private String username;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Введите корректный email (например, user@mail.com)")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 4, message = "Пароль должен содержать минимум 4 символа")
    private String password;

    @NotBlank(message = "Пожалуйста, повторите пароль")
    private String confirmPassword;

    @AssertTrue(message = "Пароли не совпадают")
    public boolean isPasswordMatching() {
        return password != null && password.equals(confirmPassword);
    }
}