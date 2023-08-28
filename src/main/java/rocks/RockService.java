package rocks;

import org.springframework.stereotype.Service;

@Service
public class RockService {
    private RockRepository repository;

    public RockService(RockRepository repository) {
        this.repository = repository;
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
