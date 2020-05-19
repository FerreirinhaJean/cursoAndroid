package com.example.organizzeclone.helper;

import java.text.SimpleDateFormat;

public class DateUtil {

    public static String dataAtual() {
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return new String(sdf.format(date));
    }


    public static String mesAnoDataEscolhida(String data) {

        String retornoData[] = data.split("/");
        String mesAno = retornoData[1] + retornoData[2];
        return mesAno;
    }
}
