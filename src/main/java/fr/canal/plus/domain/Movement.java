package fr.canal.plus.domain;

import lombok.Data;

@Data
public
class Movement {


    private final String canal;
    private final String operation;
    private final long id;

    public Movement(String canal, String operation, long id) {

        this.canal = canal;
        this.operation = operation;
        this.id = id;
    }

    public boolean isOperation(String operation) {
        return operation.equals(this.operation);
    }
}
