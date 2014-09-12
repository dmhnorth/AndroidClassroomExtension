package com.ace.androidclassroomextension.utilities;

import java.util.Random;

/**
 * Id Generator implementation for unique IDs within the system
 * Using a Singleton Design Pattern
 *
 * Created by Dave on 12/09/2014.
 */
public class IDGeneratorImpl implements IDGenerator {
    private int uniqueId;
    private static IDGenerator instance;

    private IDGeneratorImpl() {
        //
    }

    public static IDGenerator getInstance() {
        if (!(instance == null)){
            return instance;
        } else {
            instance = new IDGeneratorImpl();
            return instance;
        }
    }

    @Override
    public int getNewId() {
        uniqueId++;
        Random r = new Random();
        return uniqueId + Math.abs(r.nextInt());
    }
}
