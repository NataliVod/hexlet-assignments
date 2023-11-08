package exercise.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
public class GuestDTO {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @NotEmpty
    private String name;
    @Email
    private String email;

    @Pattern(regexp = "\\+\\d{11,13}")
    private String phoneNumber;

    @Size(min = 4, max = 4)
    private String clubCard;
    @Future
    private LocalDate cardValidUntil;

    private LocalDate createdAt;
}
