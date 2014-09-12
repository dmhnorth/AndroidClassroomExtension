package com.ace.androidclassroomextension.models;

import java.util.Random;

/**
 * Id Generator for unique IDs within the system
 *
 * Created by Dave on 12/09/2014.
 */
public class IDGenerator {


    private static int dateId;

    public static int getNewId() {
        dateId++;
        Random r = new Random();
        return dateId + Math.abs(r.nextInt());
    }
}
