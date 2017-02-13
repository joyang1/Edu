/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.slv.util;


import java.text.DateFormat;
import java.util.Date;

/**
 *
 * @author 挺
 */
public class GetTime {
    public static String getTime(Date date){
        String time = "";
        time = DateFormat.getDateInstance(DateFormat.FULL).format(date);
        return time;
    }
}
