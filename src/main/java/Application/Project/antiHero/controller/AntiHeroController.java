package Application.Project.antiHero.controller;


import Application.Project.antiHero.dto.AntiHeroDTO;
import Application.Project.antiHero.entity.AntiHeroEntity;
import Application.Project.antiHero.service.AntiHeroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@RequestMapping("/antiHero")
public class AntiHeroController {

    private final AntiHeroService antiHeroService;
    private final ModelMapper modelMapper;


    private AntiHeroEntity convertToEntity(AntiHeroDTO dto) {
        return modelMapper.map(dto, AntiHeroEntity.class);
    }

    private AntiHeroDTO convertToDTO(AntiHeroEntity entity) {
        return modelMapper.map(entity, AntiHeroDTO.class);
    }

    @GetMapping("/{id}")
    public AntiHeroDTO getAntiHeroById(@PathVariable("id") UUID id) {
        return convertToDTO(antiHeroService.findAntiHeroById(id));
    }

    @PostMapping
    public AntiHeroDTO postAntiHero(@Valid @RequestBody AntiHeroDTO dto) {
        var entity = convertToEntity(dto);
        var savedEntity = antiHeroService.addAntiHero(entity);

        return convertToDTO(savedEntity);
    }

    @PutMapping("/{id}")
    public void putAntiHero(@PathVariable("id") UUID id, @Valid @RequestBody AntiHeroDTO dto) {
        if(!id.equals(dto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
        }

        var entity = convertToEntity(dto);
        antiHeroService.updateAntiHero(id, entity);
    }

    @DeleteMapping("/{id}")
    public void deleteAntiHero(@PathVariable("id") UUID id) {
        antiHeroService.removeAntiHeroById(id);
    }

    @GetMapping
    public List<AntiHeroDTO> getAntiHeroes() {
        var antiHeroesList = StreamSupport.stream(
                antiHeroService.
                        findAll().
                        spliterator(), false
        ).collect(Collectors.toList());

        return antiHeroesList.stream().
                map(this::convertToDTO).
                collect(Collectors.toList());
    }
}

