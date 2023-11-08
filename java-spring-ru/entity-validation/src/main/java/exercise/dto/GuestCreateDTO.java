package exercise.dto;

// BEGIN

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class GuestCreateDTO {

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

}
// END
