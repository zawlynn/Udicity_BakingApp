/*
 * Copyright 2018 Dionysios Karatzas
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zawlynn.udacity.bakingapp;


import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class BakingAppTestcase extends BaseTest {

    @Test
    public void checkRecipeRecyclerView() {
        onView(withId(R.id.recRecipe))
                .check(matches(isDisplayed()));
        Navigation.getMeToRecipeInfo(0);
    }
    @Test
    public void checkStepClick(){
        onView(withId(R.id.recRecipe))
                .check(matches(isDisplayed()));
        Navigation.getMeToRecipeInfo(0);
        Navigation.selectRecipeStep(0);
    }

    @Test
    public void testAdd() {
        onView(withId(R.id.recRecipe))
                .check(matches(isDisplayed()));
        Navigation.getMeToRecipeInfo(0);
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText(R.string.add_widget)).perform(click());
    }
}
