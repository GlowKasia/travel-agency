package pl.sdajava25.travelagency.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotEmpty
    @Size(min = 4, max = 50)
    private String name;

    @NotEmpty
    @Size(min = 4, max = 50)
    private String surname;

    @NotEmpty
    @Size(min = 4, max = 50)
    private String password;

    @NotEmpty
    @Pattern(regexp = "^[a-z0-9A-Z0._%+-]+@[a-z0-9A-Z0.-]+\\.[a-zA-Z]{2,6}$")
    private String email;



}
