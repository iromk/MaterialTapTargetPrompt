/*
 * Copyright (C) 2016 Samuel Wall
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.co.samuelwall.materialtaptargetprompt.sample;

import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.ActionBarContextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class EmptyActivity extends AppCompatActivity
{

    MaterialTapTargetPrompt mFabPrompt;

    private ActionMode mActionMode;
    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback()
    {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu)
        {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.main, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu)
        {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item)
        {
            mActionMode.finish();
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode)
        {
            mActionMode = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        findViewById(R.id.other_examples_activity).setVisibility(View.GONE);
        findViewById(R.id.other_examples_dialog).setVisibility(View.GONE);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void showFabPrompt(View view)
    {
        if (mFabPrompt != null)
        {
            return;
        }
        mFabPrompt = new MaterialTapTargetPrompt.Builder(EmptyActivity.this)
                .setTarget(findViewById(R.id.fab))
                .setPrimaryText("Send your first email")
                .setSecondaryText("Tap the envelop to start composing your first email")
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setOnHidePromptListener(new MaterialTapTargetPrompt.OnHidePromptListener()
                {
                    @Override
                    public void onHidePrompt(MotionEvent event, boolean tappedTarget)
                    {
                        mFabPrompt = null;
                        //Do something such as storing a value so that this prompt is never shown again
                    }

                    @Override
                    public void onHidePromptComplete()
                    {

                    }
                })
                .create();
        mFabPrompt.show();
    }

    public void showSideNavigationPrompt(View view)
    {
        final MaterialTapTargetPrompt.Builder tapTargetPromptBuilder = new MaterialTapTargetPrompt.Builder(this)
                .setPrimaryText(R.string.menu_prompt_title)
                .setSecondaryText(R.string.menu_prompt_description)
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setMaxTextWidth(R.dimen.tap_target_menu_max_width)
                .setIcon(R.drawable.ic_back);
        final Toolbar tb = (Toolbar) this.findViewById(R.id.toolbar);
        tapTargetPromptBuilder.setTarget(tb.getChildAt(1));

        tapTargetPromptBuilder.setOnHidePromptListener(new MaterialTapTargetPrompt.OnHidePromptListener()
        {
            @Override
            public void onHidePrompt(MotionEvent event, boolean tappedTarget)
            {
                //Do something such as storing a value so that this prompt is never shown again
            }

            @Override
            public void onHidePromptComplete()
            {

            }
        });
        tapTargetPromptBuilder.show();
    }

    public void showOverflowPrompt(View view)
    {
        final MaterialTapTargetPrompt.Builder tapTargetPromptBuilder = new MaterialTapTargetPrompt.Builder(this)
                .setPrimaryText(R.string.overflow_prompt_title)
                .setSecondaryText(R.string.overflow_prompt_description)
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setMaxTextWidth(R.dimen.tap_target_menu_max_width)
                .setIcon(R.drawable.ic_more_vert);
        final Toolbar tb = (Toolbar) this.findViewById(R.id.toolbar);
        tapTargetPromptBuilder.setTarget(tb.getChildAt(2));

        tapTargetPromptBuilder.setOnHidePromptListener(new MaterialTapTargetPrompt.OnHidePromptListener()
        {
            @Override
            public void onHidePrompt(MotionEvent event, boolean tappedTarget)
            {
                //Do something such as storing a value so that this prompt is never shown again
            }

            @Override
            public void onHidePromptComplete()
            {

            }
        });
        tapTargetPromptBuilder.show();
    }

    public void showStylePrompt(View view)
    {
        final MaterialTapTargetPrompt.Builder builder = new MaterialTapTargetPrompt.Builder(this, R.style.MaterialTapTargetPromptTheme_FabTarget);
        final Toolbar tb = (Toolbar) this.findViewById(R.id.toolbar);
        builder.setIcon(R.drawable.ic_more_vert)
                .setTarget(tb.getChildAt(2))
                .show();
    }

    public void showNoAutoDismiss(View view)
    {
        if (mFabPrompt != null)
        {
            return;
        }
        mFabPrompt = new MaterialTapTargetPrompt.Builder(EmptyActivity.this)
                .setTarget(findViewById(R.id.fab))
                .setPrimaryText("No Auto Dismiss")
                .setSecondaryText("This prompt will only be removed after tapping the envelop")
                .setAnimationInterpolator(new FastOutSlowInInterpolator())
                .setAutoDismiss(false)
                .setAutoFinish(false)
                .setCaptureTouchEventOutsidePrompt(true)
                .setOnHidePromptListener(new MaterialTapTargetPrompt.OnHidePromptListener()
                {
                    @Override
                    public void onHidePrompt(MotionEvent event, boolean tappedTarget)
                    {
                        if (tappedTarget)
                        {
                            mFabPrompt.finish();
                            mFabPrompt = null;
                        }
                    }

                    @Override
                    public void onHidePromptComplete()
                    {

                    }
                })
                .show();
    }

    public void showActionModePrompt(View view)
    {
        mActionMode =  this.startSupportActionMode(mActionModeCallback);
        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                final ActionBarContextView actionBarContextView =
                        findActionBarContextView(getWindow().getDecorView().getRootView());
                if (actionBarContextView != null)
                {
                    new MaterialTapTargetPrompt.Builder(EmptyActivity.this)
                            .setPrimaryText(R.string.action_mode_prompt_title)
                            .setSecondaryText(R.string.action_mode_prompt_description)
                            .setAnimationInterpolator(new FastOutSlowInInterpolator())
                            .setMaxTextWidth(R.dimen.tap_target_menu_max_width)
                            .setTarget(actionBarContextView.getChildAt(0))
                            .setIcon(R.drawable.ic_back).show();
                }
            }
        });
    }

    /**
     * Finds the action bar context view by searching backwards through the views.
     * TODO: There must be a better way to do this.
     *
     * @param inView First view to search from
     * @return The found view or null
     */
    private ActionBarContextView findActionBarContextView(final View inView)
    {
        if (inView instanceof ViewGroup)
        {
            final ViewGroup viewGroup = (ViewGroup) inView;
            for (int i = viewGroup.getChildCount() - 1; i > -1; i--)
            {
                final View view = viewGroup.getChildAt(i);
                if (view instanceof ActionBarContextView)
                {
                    return (ActionBarContextView) view;
                }
                final ActionBarContextView contextView = this.findActionBarContextView(view);
                if (contextView != null)
                {
                    return contextView;
                }
            }
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        else if (id == android.R.id.home)
        {
            this.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
