package com.PayMyBuddy.PayMyBuddy.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static String convertDate(LocalDateTime datetime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy  HH:mm");
        return datetime.format(formatter);
    }

    public static String convertAmount(Boolean positive, double amount){
        if(positive){
            return "+"+ amount+"€";
        }
        else {
            return "-"+amount+"€";
        }
    }
}
