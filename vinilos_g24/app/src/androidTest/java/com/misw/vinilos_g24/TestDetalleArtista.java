package com.misw.vinilos_g24;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;


public class TestDetalleArtista {
    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void ArtistaDetalleExiste() throws InterruptedException {
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withText("Artistas")).perform(click());
        Thread.sleep(3000);
        onView(withText("carlos")).perform(click());
        Thread.sleep(3000);
        onView(withText("carlos melito")).check(matches(isDisplayed()));
        Thread.sleep(3000);
    }
    @Test
    public void ArtistaDetalleNoExiste() throws InterruptedException {
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withText("Artistas")).perform(click());
        Thread.sleep(3000);
        onView(withText("carlos")).perform(click());
        Thread.sleep(3000);
        onView(withText("carlos quinto")).check(doesNotExist());
        Thread.sleep(3000);
    }
}
