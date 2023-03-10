package Application.Project.antiHero.service;

import Application.Project.antiHero.Repository.AntiHeroRepository;
import Application.Project.antiHero.entity.AntiHeroEntity;
import Application.Project.antiHero.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AntiHeroService {

    private final AntiHeroRepository antiHeroRepository;

    public Iterable<AntiHeroEntity> findAll() {
        return antiHeroRepository.findAll();
    }

    public AntiHeroEntity findAntiHeroById(UUID id) {
        return findOrThrow(id);
    }

    public void removeAntiHeroById(UUID id) {
        antiHeroRepository.deleteById(id);
    }

    public AntiHeroEntity addAntiHero(AntiHeroEntity antiHeroEntity) {
        return antiHeroRepository.save(antiHeroEntity);
    }

    public void updateAntiHero(UUID id,AntiHeroEntity antiHeroEntity) {
        findOrThrow(id);
        antiHeroRepository.save(antiHeroEntity);
    }

    private AntiHeroEntity findOrThrow(final UUID id) {
        return antiHeroRepository
                .findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Anti hero not found with id: " + id)
                );
    }



}
