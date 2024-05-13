package com.misw.vinilos_g24;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestDetalleAlbum {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void AlbumDetalleExiste() throws InterruptedException{
        Thread.sleep(3000);
        onView(withContentDescription("Open navigation drawer")).perform(click());
        onView(withText("Álbumes")).perform(click());
        Thread.sleep(3000);
        onView(withText("pruebita")).perform(click());
        Thread.sleep(5000);
        onView(withText("Rock")).check(matches(isDisplayed()));
        Thread.sleep(5000);
    }
    @Test
    public void AlbumDetalleNoExiste() throws InterruptedException{

        onView(withContentDescription("Open navigation drawer")).perform(click());
        Thread.sleep(3000);
        onView(withText("Álbumes")).perform(click());
        Thread.sleep(5000);
        onView(withText("S&M")).perform(click());
        Thread.sleep(3000);
        onView(withText("Rock")).check(matches(isDisplayed()));
        Thread.sleep(3000);
    }
}
