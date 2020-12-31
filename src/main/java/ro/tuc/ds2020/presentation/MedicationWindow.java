package ro.tuc.ds2020.presentation;

import ro.tuc.ds2020.dtos.MedicationPlanDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MedicationWindow extends JFrame {

    private static MedicationPlanDTO medPlan;
    private static MedicationDispenserWindow parentWindow;

    public static void newMedicationWindow(final MedicationPlanDTO medPlan, final MedicationDispenserWindow parentWindow){
        EventQueue.invokeLater(() -> {
            MedicationWindow.medPlan = medPlan;
            MedicationWindow.parentWindow = parentWindow;
            MedicationWindow frame = null;
            try {
                frame = new MedicationWindow();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            frame.pack();
            frame.setVisible(true);
        });
    }


    public MedicationWindow() throws ParseException {
        setBounds(100, 100, 405, 294);
        setTitle("Medication");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(320, 520);
        setResizable(false);
        setLocation(780, 220);

        JPanel jPanel = new JPanel(new SpringLayout());
        int numPairs = 5;


        JLabel mediationLabel = new JLabel("Medication:", JLabel.TRAILING);
        JTextField medicationText = new JTextField(medPlan.getMedication().getName());
        medicationText.setEditable(false);
        jPanel.add(mediationLabel);
        mediationLabel.setLabelFor(medicationText);
        jPanel.add(medicationText);

        JLabel sideEffectsLabel = new JLabel("Side Effects:", JLabel.TRAILING);
        JTextField sideEffectsText = new JTextField(medPlan.getMedication().getSideEffectList().toString());
        sideEffectsText.setEditable(false);
        jPanel.add(sideEffectsLabel);
        mediationLabel.setLabelFor(sideEffectsText);
        jPanel.add(sideEffectsText);

        JLabel dosageLabel = new JLabel("Dosage:", JLabel.TRAILING);
        JTextField dosageText = new JTextField(medPlan.getDosage());
        dosageText.setEditable(false);
        jPanel.add(dosageLabel);
        mediationLabel.setLabelFor(dosageText);
        jPanel.add(dosageText);

        JLabel startLabel = new JLabel("Start:", JLabel.TRAILING);
        JTextField startText = new JTextField(medPlan.getStart().toString());
        startText.setEditable(false);
        jPanel.add(startLabel);
        mediationLabel.setLabelFor(startText);
        jPanel.add(startText);

        JLabel endLabel = new JLabel("Start:", JLabel.TRAILING);
        JTextField endText = new JTextField(medPlan.getEnd().toString());
        endText.setEditable(false);
        jPanel.add(endLabel);
        mediationLabel.setLabelFor(endText);
        jPanel.add(endText);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime currentTime = LocalTime.parse(LocalTime.now().format(formatter));

        if(currentTime.isAfter( LocalTime.parse( "08:00:00" ) )
                &&
                currentTime.isBefore( LocalTime.parse( "11:30:00" ))) {
            if (medPlan.isMorning() && !medPlan.isMorningTaken()) {
                JLabel morningLabel = new JLabel("Morning:", JLabel.TRAILING);
                JButton takeButton1 = new JButton("Taken");

                takeButton1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        medPlan.setMorningTaken(true);
                        parentWindow.sendMessageForTaken(medPlan.getMedication().getName());
                        MedicationWindow.this.dispose();
                    }
                });

                jPanel.add(morningLabel);
                mediationLabel.setLabelFor(takeButton1);
                jPanel.add(takeButton1);
                numPairs++;
            }
        }

        if(currentTime.isAfter( LocalTime.parse( "13:00:00" ) )
                &&
                currentTime.isBefore( LocalTime.parse( "16:30:00" ))) {
            if (medPlan.isAfternoon() && !medPlan.isAfternoonTaken()) {
                JLabel afternoonLabel = new JLabel("Afternoon:", JLabel.TRAILING);
                JButton takeButton2 = new JButton("Taken");

                takeButton2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        medPlan.setAfternoonTaken(true);
                        parentWindow.sendMessageForTaken(medPlan.getMedication().getName());
                        MedicationWindow.this.dispose();
                    }
                });

                jPanel.add(afternoonLabel);
                mediationLabel.setLabelFor(takeButton2);
                jPanel.add(takeButton2);
                numPairs++;
            }
        }

        if(currentTime.isAfter( LocalTime.parse( "18:00:00" ) )
                &&
                currentTime.isBefore( LocalTime.parse( "21:29:59" ))){
            if (medPlan.isEvening() && !medPlan.isEveningTaken()) {
                JLabel eveningLabel = new JLabel("Evening:", JLabel.TRAILING);
                JButton takeButton3 = new JButton("Taken");

                takeButton3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        medPlan.setMorningTaken(true);
                        parentWindow.sendMessageForTaken(medPlan.getMedication().getName());
                        MedicationWindow.this.dispose();
                    }
                });

                jPanel.add(eveningLabel);
                mediationLabel.setLabelFor(takeButton3);
                jPanel.add(takeButton3);
                numPairs++;
            }
        }


        SpringUtilities.makeCompactGrid(jPanel,
                numPairs, 2,           //rows, cols
                6, 6,        //initX, initY
                6, 6);          //xPad, yPad
        setContentPane(jPanel);
    }
}
