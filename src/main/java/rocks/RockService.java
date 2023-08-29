package rocks;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RockService {
    private RockRepository repository;

    public RockService(RockRepository repository) {
        this.repository = repository;
//        List<Rock> defaultRocks = defaultRocks();
        repository.saveAll(defaultRocks());
    }

    public static List<Rock> defaultRocks() {
        return List.of(
                new Rock("Obsidian"),
                new Rock("Basalt"),
                new Rock("Quartz")
        );
    }

    public Rock saveRock(Rock rock) {
        this.repository.save(rock);
        return rock;
    }

    public List<Rock> getAllRocks() {
        List<Rock> list = new ArrayList<>();
        Iterable<Rock> rocks = repository.findAll();
        rocks.forEach(list::add);
        return list;
    }
}
