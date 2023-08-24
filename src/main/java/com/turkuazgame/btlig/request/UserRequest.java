package com.turkuazgame.btlig.request;

import com.turkuazgame.btlig.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Data
public class UserRequest {

    private final String MESSAGE_USERNAME_UNIQUE = "Kullanıcı Adı Mevcut !";
    private final String MESSAGE_USERNAME_NOTNULL = "Kullanıcı Adı Boş Olamaz !";
    private final String MESSAGE_FIRSTNAME_NOTNULL = "Ad Boş Olamaz !";
    private final String MESSAGE_LASTNAME_NOTNULL = "Soyad Boş Olamaz !";
    private final String MESSAGE_EMAIL_NOTNULL = "E-posta Boş Olamaz !";
    private final String MESSAGE_EMAIL_NOTSUITABLE = "Email Uygun Değil !";
    private final String MESSAGE_PASSWORD_SIZE = "Şifre en az 4, en çok 255 karakter olmalıdır !";
    private final String MESSAGE_PASSWORD_PATTERN = "Şifre en az bir büyük harf, bir küçük harf ve bir rakam içermelidir !";

    private long userId;

    @NotNull(message=MESSAGE_USERNAME_NOTNULL)
    @Size(min = 4, max=255)
    @UniqueUsername(message = "{btlig.constraint.username.UniqueUsername.message}")
    private String username;

    @NotNull(message=MESSAGE_FIRSTNAME_NOTNULL)
    private String firstName;

    @NotNull(message=MESSAGE_LASTNAME_NOTNULL)
    private String lastName;

    @NotNull(message=MESSAGE_EMAIL_NOTNULL)
    @Email(message=MESSAGE_EMAIL_NOTSUITABLE)
    private String email;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message=MESSAGE_PASSWORD_PATTERN)
    @Size(min = 8, max=255, message=MESSAGE_PASSWORD_SIZE)
    private String password;

    private String userImage;

}
