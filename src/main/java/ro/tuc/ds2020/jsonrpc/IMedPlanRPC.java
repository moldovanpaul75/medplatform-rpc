package ro.tuc.ds2020.jsonrpc;

import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;

import java.util.List;
import java.util.UUID;

@JsonRpcService("/medplanrpc")
public interface IMedPlanRPC {

    String sayHelloRmi(@JsonRpcParam(value = "msg") String msg);

    String findMedicationPlanById(@JsonRpcParam(value = "patientID") UUID patientID);

    String receiveMessage(@JsonRpcParam(value = "msg") String msg);

}
