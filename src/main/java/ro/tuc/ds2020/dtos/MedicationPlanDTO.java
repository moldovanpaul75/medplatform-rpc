package ro.tuc.ds2020.dtos;

import java.util.Date;
import java.util.UUID;


public class MedicationPlanDTO extends BaseDTO {

    private static final long serialVersionUID = 1L;

    private String dosage;
    private String start;
    private String end;
    private boolean morning;
    private boolean afternoon;
    private boolean evening;

    private boolean morningTaken = false;
    private boolean afternoonTaken = false;
    private boolean eveningTaken = false;


    private MedicationDTO medication;

    public MedicationPlanDTO() {
        super();
    }

    public MedicationPlanDTO(UUID id, String dosage, String start, String end, boolean morning, boolean afternoon, boolean evening, MedicationDTO medication) {
        super(id);
        this.dosage = dosage;
        this.start = start;
        this.end = end;
        this.morning = morning;
        this.afternoon = afternoon;
        this.evening = evening;
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public boolean isMorning() {
        return morning;
    }

    public void setMorning(boolean morning) {
        this.morning = morning;
    }

    public boolean isAfternoon() {
        return afternoon;
    }

    public void setAfternoon(boolean afternoon) {
        this.afternoon = afternoon;
    }

    public boolean isEvening() {
        return evening;
    }

    public void setEvening(boolean evening) {
        this.evening = evening;
    }

    public MedicationDTO getMedication() {
        return medication;
    }

    public void setMedication(MedicationDTO medication) {
        this.medication = medication;
    }

    public boolean isMorningTaken() {
        return morningTaken;
    }

    public void setMorningTaken(boolean morningTaken) {
        this.morningTaken = morningTaken;
    }

    public boolean isAfternoonTaken() {
        return afternoonTaken;
    }

    public void setAfternoonTaken(boolean afternoonTaken) {
        this.afternoonTaken = afternoonTaken;
    }

    public boolean isEveningTaken() {
        return eveningTaken;
    }

    public void setEveningTaken(boolean eveningTaken) {
        this.eveningTaken = eveningTaken;
    }

    @Override
    public String toString() {
        return "MedicationPlanDTO{" +
                "dosage='" + dosage + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", morning=" + morning +
                ", afternoon=" + afternoon +
                ", evening=" + evening +
                ", medication=" + medication +
                '}';
    }
}
