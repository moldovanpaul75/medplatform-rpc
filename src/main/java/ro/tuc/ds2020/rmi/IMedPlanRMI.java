package ro.tuc.ds2020.rmi;

import ro.tuc.ds2020.dtos.MedicationPlanDTO;

import java.util.List;
import java.util.UUID;


public interface IMedPlanRMI {

    String sayHelloRmi(String msg);
    String receiveMessage(String msg);
    List<MedicationPlanDTO> findMedicationPlanById(UUID patientID);
}
