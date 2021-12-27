package com.example.ludoed.model;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.example.ludoed.R;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Test;

import org.junit.Before;


public class AnimationProcessingTest extends TestCase {
    AnimationProcessing ap ;

    @Before
    public void setUp() throws Exception {
        ap = new AnimationProcessing();
        Context context = InstrumentationRegistry.getInstrumentation().getContext();
        ap.playSound(context, R.raw.ring, true);
    }
    @Test
    public void testPlaySound() {
        assertTrue(ap.getMedia().isPlaying());
    }

    @Test
    public void testStopSound() {
        ap.stopSound();
        assertFalse(ap.getMedia().isPlaying());
    }

    @After
    public void tearDown()throws Exception{
        ap= null;
    }



}