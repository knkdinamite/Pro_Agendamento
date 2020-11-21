package com.projeto.activities;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.projeto.R;
import com.projeto.models.Aplicacao;
import com.projeto.models.Usuario;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<StartActivity> mActivityTestRule = new ActivityTestRule<>(StartActivity.class);

    @Test
    public void loginActivityTest() {

        Aplicacao.aguardar(2000);
        if(Usuario.verificaUsuarioLogado() == null){
            ViewInteraction appCompatButton = onView(
                    allOf(withId(R.id.login_button_login), withText("Login"),
                            childAtPosition(
                                    allOf(withId(R.id.cronstrain),
                                            childAtPosition(
                                                    withClassName(is("androidx.core.widget.NestedScrollView")),
                                                    0)),
                                    12),
                            isDisplayed()));
            appCompatButton.perform(click());

            ViewInteraction appCompatButton2 = onView(
                    allOf(withId(R.id.login_button_login), withText("Login"),
                            childAtPosition(
                                    allOf(withId(R.id.cronstrain),
                                            childAtPosition(
                                                    withClassName(is("androidx.core.widget.NestedScrollView")),
                                                    0)),
                                    12),
                            isDisplayed()));
            appCompatButton2.perform(click());

        }
        Aplicacao.aguardar(5000);
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
