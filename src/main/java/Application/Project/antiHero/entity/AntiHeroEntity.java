package Application.Project.antiHero.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "anti_hero_entity")
@AllArgsConstructor
@NoArgsConstructor
public class AntiHeroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    @NotNull(message = "Name is required")
    private String firstName;
    private String lastName;
    private String house;
    private String knownAs;
    private String createdAt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());



}
