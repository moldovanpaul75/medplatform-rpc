package ro.tuc.ds2020.dtos;

import java.util.List;
import java.util.UUID;

public class MedicationDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private String name;
    private String type;
    private List<SideEffectDTO> sideEffectList;

    public MedicationDTO() {
        super();
    }

    public MedicationDTO(UUID id, String name, String type, List<SideEffectDTO> sideEffectList) {
        super(id);
        this.name = name;
        this.type = type;
        this.sideEffectList = sideEffectList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SideEffectDTO> getSideEffectList() {
        return sideEffectList;
    }

    public void setSideEffectList(List<SideEffectDTO> sideEffectList) {
        this.sideEffectList = sideEffectList;
    }

    @Override
    public String toString() {
        return "MedicationDTO{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sideEffectList=" + sideEffectList +
                '}';
    }
}
