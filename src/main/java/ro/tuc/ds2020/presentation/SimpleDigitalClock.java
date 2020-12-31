package ro.tuc.ds2020.presentation;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;


public class SimpleDigitalClock extends JPanel {

    private int hour, minute, second;
    private String aHour = "";
    private String bMinute = "";
    private String cSecond = "";
    private static MedicationDispenserWindow parentWindow;

    SimpleDigitalClock(MedicationDispenserWindow medicationDispenserWindow) {
        parentWindow = medicationDispenserWindow;
        Timer t = new Timer(1000, e -> repaint());
        t.start();
    }

    @Override
    public void paintComponent(Graphics v) {
        super.paintComponent(v);
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        second = calendar.get(Calendar.SECOND);

        if(hour == 11 && minute == 30 && second == 1){
            parentWindow.sendMessageForNotTakenMorning();
        }

        if(hour == 16 && minute == 30 && second == 1){
            parentWindow.sendMessageForNotTakenAfternoon();
        }

        if(hour == 21 && minute == 30 && second == 0){
            parentWindow.sendMessageForNotTakenEvening();
        }

        if (hour < 10) this.aHour = "0";
        if (hour >= 10) this.aHour = "";
        if (minute < 10) this.bMinute = "0";
        if (minute >= 10) this.bMinute = "";
        if (second < 10) this.cSecond = "0";
        if (second >= 10) this.cSecond = "";

        String time = aHour + hour + ":" + bMinute + minute + ":" + cSecond + second;
        int length = this.getWidth() <= this.getHeight() ? this.getWidth() : this.getHeight();

        v.setColor(Color.BLACK);
        v.setFont( new Font("SansSerif", Font.PLAIN, length / 5));
        v.drawString(time,  length / 6, length / 2);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 35);
    }
}