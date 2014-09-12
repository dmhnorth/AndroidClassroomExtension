package com.ace.androidclassroomextension.models;

import junit.framework.TestCase;

public class UserImplTest extends TestCase {
    private User user;

    public void setUp() throws Exception {
        user = new UserImpl("John");
        super.setUp();

    }

    public void testSetCurrentQuestion() throws Exception {
        String question = "question";
        user.setCurrentQuestion(question);
        assertTrue(user.isHandUp());
    }
}