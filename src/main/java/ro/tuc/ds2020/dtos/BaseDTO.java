package ro.tuc.ds2020.dtos;

import java.io.Serializable;
import java.util.UUID;

public abstract class BaseDTO  implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID id;

    public BaseDTO() {
    }

    public BaseDTO(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}