package ro.tuc.ds2020.presentation;

import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.rmi.IMedPlanRMI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MedicationDispenserWindow extends JFrame {

    private JPanel contentPanel;
    private JTable jTable;
    private static IMedPlanRMI medPlanRMI;
    private List<MedicationPlanDTO> medPlans;
    private UUID patientID = UUID.fromString("608ad41c-a9d3-4744-8753-77e37f81bb56");

    public static void newMedDispenserWindow(final IMedPlanRMI medPlanRMI) {
        EventQueue.invokeLater(() -> {
            MedicationDispenserWindow.medPlanRMI =medPlanRMI;
            MedicationDispenserWindow frame = new MedicationDispenserWindow();
            frame.setVisible(true);
        });
    }

    public MedicationDispenserWindow() {
        setBounds(100, 100, 405, 294);
        setTitle("Medication Dispenser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setResizable(false);
        setLocation(780, 220);


        medPlans = medPlanRMI.findMedicationPlanById(patientID);
        jTable = new JTable();
        createTable();

        JScrollPane jSTabel = new JScrollPane(jTable);
        SimpleDigitalClock clock1 = new SimpleDigitalClock(this);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.add(clock1);
        contentPanel.add(jSTabel);
        setContentPane(contentPanel);
    }

    private void getTableRow(MouseEvent e) {
        int row = jTable.getSelectedRow();
        MedicationWindow.newMedicationWindow(medPlans.get(row),this);
    }

    public void sendMessageForTaken(String medName){
        System.out.println(medName + " taken at +" + new Date());
        medPlanRMI.receiveMessage(medName + " taken at +" + new Date() + " by patient with id: " + patientID);
    }

    public void sendMessageForNotTakenMorning(){
        medPlans.forEach(medPlan -> {
            if(medPlan.isMorning() && !medPlan.isEveningTaken()){
                medPlan.setMorningTaken(true);
                System.out.println("Patient with id: " + patientID + " did not take medication " + medPlan.getMedication().getName() + " in the morning");
                medPlanRMI.receiveMessage("Patient with id: " + patientID + " did not take medication " + medPlan.getMedication().getName() + " in the morning");
            }
        });
    }

    public void sendMessageForNotTakenAfternoon(){
        medPlans.forEach(medPlan -> {
            if(medPlan.isAfternoon() && !medPlan.isAfternoonTaken()){
                medPlan.setAfternoonTaken(true);
                System.out.println("Patient with id: " + patientID + " did not take medication " + medPlan.getMedication().getName() + " in the afternoon");
                medPlanRMI.receiveMessage("Patient with id: " + patientID + " did not take medication " + medPlan.getMedication().getName() + " in the afternoon");
            }
        });
    }

    public void sendMessageForNotTakenEvening(){
        medPlans.forEach(medPlan -> {
            if(medPlan.isEvening() && !medPlan.isEveningTaken()){
                medPlan.setEveningTaken(true);
                System.out.println("Patient with id: " + patientID + " did not take medication " + medPlan.getMedication().getName() + " in the evening");
                medPlanRMI.receiveMessage("Patient with id: " + patientID + " did not take medication " + medPlan.getMedication().getName() + " in the evening");
            }
        });
    }

    public void downloadMedicationPlans(){
        System.out.println("Downloading medication plans!");
        medPlans = medPlanRMI.findMedicationPlanById(patientID);
        createTable();
        System.out.println(medPlans);
    }

    private void createTable(){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] columnNames = {
                "MedPlanId",
                "Medication",
                "Side Effects",
                "Dosage",
                "Start",
                "End",
                "Morning",
                "Afternoon",
                "Evening",
        };
        model.setColumnIdentifiers(columnNames);
        medPlans.forEach(medPlan -> {
            model.addRow(new Object[]{
                    medPlan.getId(),
                    medPlan.getMedication().getName(),
                    medPlan.getMedication().getSideEffectList().toString(),
                    medPlan.getDosage(),
                    medPlan.getStart().toString(),
                    medPlan.getEnd().toString(),
                    medPlan.isMorning(),
                    medPlan.isAfternoon(),
                    medPlan.isEvening(),
            });
        });
        jTable.setModel(model);
        jTable.removeColumn(jTable.getColumn("MedPlanId"));
    }
}
