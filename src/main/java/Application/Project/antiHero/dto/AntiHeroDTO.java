package Application.Project.antiHero.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

import static io.lettuce.core.pubsub.PubSubOutput.Type.message;

@Getter
@Setter
public class AntiHeroDTO {
    private UUID id;

    @NotNull(message="First name is required")
    private String firstName;
    private String lastName;
    private String house;
    private String knownAs;

}
