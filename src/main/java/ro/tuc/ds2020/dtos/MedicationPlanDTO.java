package ro.tuc.ds2020.dtos;

import java.util.Date;
import java.util.UUID;

public class MedicationPlanDTO extends BaseDTO {

    private String dosage;
    private Date start;
    private Date end;
    private boolean morning;
    private boolean afternoon;
    private boolean evening;

    private String patient;
    private String doctor;
    private MedicationDTO medication;

    public MedicationPlanDTO() {
        super();
    }
}
