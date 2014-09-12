package com.ace.androidclassroomextension.utilities;

import java.util.Random;

/**
 * Id Generator implementation for unique IDs within the system
 *
 * Created by Dave on 12/09/2014.
 */
public class IDGeneratorImpl implements IDGenerator {
    private static int dateId;

    @Override
    public int getNewId() {
        dateId++;
        Random r = new Random();
        return dateId + Math.abs(r.nextInt());
    }
}
