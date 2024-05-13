package com.misw.vinilos_g24;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;

import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;

import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class TestArtista {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void ArtistaEncontrado() throws InterruptedException {
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withText("Artistas")).perform(click());
        Thread.sleep(3000);
        onView(withText("carlos")).check(matches(isDisplayed()));
    }
    @Test
    public void ArtistaNoExiste() throws InterruptedException {
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withText("Artistas")).perform(click());
        Thread.sleep(3000);
        onView(withText("carlos quinto")).check(doesNotExist());
    }

}
