package ro.tuc.ds2020.dtos;

import java.util.List;

public class MedicationDTO extends BaseDTO {

    private String name;
    private String type;
    private List<SideEffectDTO> sideEffectList;

    public MedicationDTO() {
        super();
    }

}
