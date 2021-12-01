package com.PayMyBuddy.PayMyBuddy.Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static String convertDate(LocalDateTime datetime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return datetime.format(formatter);
    }

    public static String convertAmount(Boolean positive, Integer amount){
        if(positive){
            return "+"+ amount+"€";
        }
        else {
            return "-"+amount+"€";
        }
    }
}
