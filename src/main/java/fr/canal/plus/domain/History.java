package fr.canal.plus.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class History {

    private List<Movement> movements;

    public History() {
        movements = new ArrayList<>();
    }

    public void add(Movement movement) {
        movements.add(movement);
    }

    public Movement lastMovement() {
        return movements.size() != 0 ?  movements.get(movements.size() - 1) : null;
    }
}
