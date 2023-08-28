package rocks;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String type;

    public Rock() {
    }

    public Rock(String type) {
        this.type = type;
    }
}
