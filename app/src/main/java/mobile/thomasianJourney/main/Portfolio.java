package mobile.thomasianJourney.main;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import mobile.thomasianJourney.main.vieweventsfragments.R;

public class Portfolio extends AppCompatActivity {

    //list view per tab

    ListView list;
    String dates [] = {"Jan 01", "Jan 02", "Jan 14", "Feb 03", "Feb 14", "Feb 30", "Mar 03", "Mar 05"};
    String titles[] = {"Title One", "Title Two", "Title Three", "Title Four","Title Five","Title Six","Title Seven","Title Eight"};
    String descriptions[] = {"Description One...", "Description Two...", "Description Three...", "Description Four...","Description Five...","Description Six...","Description Seven...","Description Eight..."};


    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
//    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ViewPagerAdapter adapter;
    private TabLayout mTablayout;

    Dialog dialog_help;
    ImageView closeDialogHelp;

    TextView txtContent1, txtContent2, txtContent3, txtContent4, txtContent5, txtContent6;
    Animation animationUp, animationUp1, animationUp2, animationUp3, animationUp4,animationUp5, animationUp6;
    Animation animationDown, animationDown1, animationDown2, animationDown3, animationDown4, animationDown5, animationDown6 ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        dialog_help = new Dialog(this);

        // Create list view
        mTablayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.container);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        list = findViewById(R.id.list2);

        //create instance of class MyAdapter
        adapter.AddFragment(new Year1(), "MUST-DO");
        adapter.AddFragment(new Year2(), "GOOD TO DO");
        adapter.AddFragment(new Year3(), "DON'T MISS");
        adapter.AddFragment(new Year4(), "GOOD TO BECOME");
//        MyAdapter adapter = new MyAdapter(this, dates, titles, descriptions);
        mViewPager.setAdapter(adapter);
        mTablayout.setupWithViewPager(mViewPager);

        mTablayout.getTabAt(0);
        mTablayout.getTabAt(1);
        mTablayout.getTabAt(2);
        mTablayout.getTabAt(3);
        mViewPager.setOffscreenPageLimit(3);

// to set icon for tabs
//        tablayout.getTabAt(0).setIcon(R.drawable.ic_call);
//        tablayout.getTabAt(1).setIcon(R.drawable.ic_group);
//        tablayout.getTabAt(2).setIcon(R.drawable.ic_star);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);



//        list.setAdapter(adapter);


        //handle item clicks
//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                if (position==0){
//                    Toast.makeText(MainActivity.this, "Item One Clicked...", Toast.LENGTH_SHORT).show();
//                }
//                if (position==1){
//                    Toast.makeText(MainActivity.this, "Item Two Clicked...", Toast.LENGTH_SHORT).show();
//                }
//                if (position==2){
//                    Toast.makeText(MainActivity.this, "Item Three Clicked...", Toast.LENGTH_SHORT).show();
//                }
//                if (position==3){
//                    Toast.makeText(MainActivity.this, "Item Four Clicked...", Toast.LENGTH_SHORT).show();
//                }
//                if (position==4){
//                    Toast.makeText(MainActivity.this, "Item Five Clicked...", Toast.LENGTH_SHORT).show();
//                }
//                if (position==5){
//                    Toast.makeText(MainActivity.this, "Item Six Clicked...", Toast.LENGTH_SHORT).show();
//                }
//                if (position==6){
//                    Toast.makeText(MainActivity.this, "Item Seven Clicked...", Toast.LENGTH_SHORT).show();
//                }
//                if (position==7){
//                    Toast.makeText(MainActivity.this, "Item Eight Clicked...", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });




        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    // list view adapter

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String myDates[];
        String myTitles[];
        String myDescriptions[];

        MyAdapter(Context c, String[] dates, String[] titles, String[] descriptions){
            super(c,R.layout.row, titles);
            this.context=c;
            this.myDates=dates;
            this.myTitles=titles;
            this.myDescriptions=descriptions;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            Button myDate = row.findViewById(R.id.button1);
//            TextView myTitle = row.findViewById(R.id.text1);
            TextView myDescription = row.findViewById(R.id.text2);
            myDate.setText(dates[position]);
//            myTitle.setText(titles[position]);
            myDescription.setText(descriptions[position]);
            return row;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            ShowDialogHelp();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //deleted PlaceholderFragment class from here

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Returning the current years
            switch (position) {
                case 0:
                    Year1 activity_year1 = new Year1();
                    return activity_year1;
                case 1:
                    Year2 activity_year2 = new Year2();
                    return activity_year2;
                case 2:
                    Year3 activity_year3 = new Year3();
                    return activity_year3;
                case 3:
                    Year4 activity_year4 = new Year4();
                    return activity_year4;
                default:
                    return null;
            }
        }
        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "1ST YEAR";
                case 1:
                    return "2ND YEAR";
                case 2:
                    return "3RD YEAR";
                case 3:
                    return "4TH YEAR";
            }
            return null;
        }
    }

    public void ShowDialogHelp() {
        dialog_help.setContentView(R.layout.dialog_help);
        closeDialogHelp = (ImageView) dialog_help.findViewById(R.id.closeDialogHelp);
//        title_text1 = (TextView) dialog_help.findViewById(R.id.title_text1);
//        title_text2 = (TextView) dialog_help.findViewById(R.id.title_text2);
//        title_text3 = (TextView) dialog_help.findViewById(R.id.title_text3);
//        title_text4 = (TextView) dialog_help.findViewById(R.id.title_text4);
//        title_text5 = (TextView) dialog_help.findViewById(R.id.title_text5);
//        title_text6 = (TextView) dialog_help.findViewById(R.id.title_text6);
//        content_text1 = (TextView) dialog_help.findViewById(R.id.content_text1);
//        content_text2 = (TextView) dialog_help.findViewById(R.id.content_text2);
//        content_text3 = (TextView) dialog_help.findViewById(R.id.content_text3);
//        content_text4 = (TextView) dialog_help.findViewById(R.id.content_text4);
//        content_text5 = (TextView) dialog_help.findViewById(R.id.content_text5);
//        content_text6 = (TextView) dialog_help.findViewById(R.id.content_text6);
//        txthelp = (TextView) dialog_help.findViewById(R.id.txthelp);
//        scrollhelp = (NestedScrollView) dialog_help.findViewById(R.id.scrollhelp);
//        layouthelp = (LinearLayout) dialog_help.findViewById(R.id.layouthelp);
        txtContent1 = (TextView) dialog_help.findViewById(R.id.title_text1);
        TextView txtTitle1 = (TextView) dialog_help.findViewById(R.id.content_text1);
        txtContent1.setVisibility(View.GONE);

        animationUp1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent1.isShown()){
                    txtContent1.setVisibility(View.GONE);
                    txtContent1.startAnimation(animationUp1);
                }
                else{
                    txtContent1.setVisibility(View.VISIBLE);
                    txtContent1.startAnimation(animationDown1);
                }
            }
        });

        // help 2
        txtContent2 = (TextView) dialog_help.findViewById(R.id.title_text2);
        TextView txtTitle2 = (TextView) dialog_help.findViewById(R.id.content_text2);
        txtContent2.setVisibility(View.GONE);

        animationUp2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent2.isShown()){
                    txtContent2.setVisibility(View.GONE);
                    txtContent2.startAnimation(animationUp2);
                }
                else{
                    txtContent2.setVisibility(View.VISIBLE);
                    txtContent2.startAnimation(animationDown2);
                }
            }
        });

        // help 3
        txtContent3 = (TextView) dialog_help.findViewById(R.id.title_text3);
        TextView txtTitle3 = (TextView) dialog_help.findViewById(R.id.content_text3);
        txtContent3.setVisibility(View.GONE);

        animationUp3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent3.isShown()){
                    txtContent3.setVisibility(View.GONE);
                    txtContent3.startAnimation(animationUp3);
                }
                else{
                    txtContent3.setVisibility(View.VISIBLE);
                    txtContent3.startAnimation(animationDown3);
                }
            }
        });

        // help 4
        txtContent4 = (TextView) dialog_help.findViewById(R.id.title_text4);
        TextView txtTitle4 = (TextView) dialog_help.findViewById(R.id.content_text4);
        txtContent4.setVisibility(View.GONE);

        animationUp4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown4 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent4.isShown()){
                    txtContent4.setVisibility(View.GONE);
                    txtContent4.startAnimation(animationUp4);
                }
                else{
                    txtContent4.setVisibility(View.VISIBLE);
                    txtContent4.startAnimation(animationDown4);
                }
            }
        });

        // help 5
        txtContent5 = (TextView) dialog_help.findViewById(R.id.title_text5);
        TextView txtTitle5 = (TextView) dialog_help.findViewById(R.id.content_text5);
        txtContent5.setVisibility(View.GONE);

        animationUp5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown5 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent5.isShown()){
                    txtContent5.setVisibility(View.GONE);
                    txtContent5.startAnimation(animationUp5);
                }
                else{
                    txtContent5.setVisibility(View.VISIBLE);
                    txtContent5.startAnimation(animationDown5);
                }
            }
        });

        // help 6
        txtContent6 = (TextView) dialog_help.findViewById(R.id.title_text6);
        TextView txtTitle6 = (TextView) dialog_help.findViewById(R.id.content_text6);
        txtContent6.setVisibility(View.GONE);

        animationUp6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        animationDown6 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);

        txtTitle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtContent6.isShown()){
                    txtContent6.setVisibility(View.GONE);
                    txtContent6.startAnimation(animationUp6);
                }
                else{
                    txtContent6.setVisibility(View.VISIBLE);
                    txtContent6.startAnimation(animationDown6);
                }
            }
        });




        closeDialogHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_help.dismiss();
            }


        });

        dialog_help.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_help.show();


    }



}
