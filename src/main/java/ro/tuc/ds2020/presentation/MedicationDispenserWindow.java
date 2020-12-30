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
import java.util.List;
import java.util.UUID;

public class MedicationDispenserWindow extends JFrame {

    private JPanel contentPanel;
    private JTable jTable;
    private static IMedPlanRMI medPlanRMI;
    private List<MedicationPlanDTO> medPlans;

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

        medPlans = medPlanRMI.findMedicationPlanById(UUID.fromString("608ad41c-a9d3-4744-8753-77e37f81bb56"));

        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        jTable = new JTable(model);
        jTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                getTableRow(e);
            }
        });

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
        jTable.removeColumn(jTable.getColumn("MedPlanId"));
        JScrollPane jSTabel = new JScrollPane(jTable);

        SimpleDigitalClock clock1 = new SimpleDigitalClock();

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
}
