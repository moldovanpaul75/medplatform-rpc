package ro.tuc.ds2020.dtos;

import java.util.UUID;

public class SideEffectDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private String name;
    private String details;

    public SideEffectDTO(){
        super();
    }

    public SideEffectDTO(UUID id, String name, String details) {
        super(id);
        this.name = name;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
