package com.jasonette.seed.View;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.mobeedev.library.SlidingMenuBuilder;
import com.mobeedev.library.SlidingNavigation;
import com.mobeedev.library.gravity.SlideGravity;

public class LeftSideMenu {
    public LeftSideMenu(Activity activity, Toolbar toolbar, Bundle savedInstanceState) {
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        RelativeLayout menuView = new RelativeLayout(activity);
        menuView.setLayoutParams(rlp);

        TextView textView = new TextView(activity);
        textView.setText("TESt MENU");
        menuView.addView(textView);

        SlidingNavigation menu = new SlidingMenuBuilder(activity)
                .withMenuOpened(false)
                .withMenuLocked(false)
                .withDragDistance(250)
                .withRootViewElevation(0)
                .withRootViewYTranslation(0)
                .withRootViewScale(1f)
                .withGravity(SlideGravity.LEFT)
                .withSavedState(savedInstanceState)
                .withContentClickableWhenMenuOpened(true)
                .withMenuView(menuView)
                .withToolbarMenuToggle(toolbar)
                .inject();
    }
}
