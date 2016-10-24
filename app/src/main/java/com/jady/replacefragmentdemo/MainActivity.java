package com.jady.replacefragmentdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String param;

    private ViewPager mViewPager;
    private FragmentAdapter adapter;
    private IconTabPageIndicator mIndicator;

    private boolean toSelf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initFragments();
        initViews();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mIndicator = (IconTabPageIndicator) findViewById(R.id.indicator);
        List<BaseFragment> fragments = initFragments();
        adapter = new FragmentAdapter(fragments, getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mIndicator.setVisibility(View.VISIBLE);
        mIndicator.setViewPager(mViewPager);

    }

    private List<BaseFragment> initFragments() {
        List<BaseFragment> fragments = new ArrayList<>();

        Fragment0 fragment0 = new Fragment0();
        fragment0.setTitle("首页");
        fragment0.setIconId(R.drawable.tab_myinfo_selector);
        fragments.add(fragment0);

        Fragment1 fragment1 = new Fragment1();
        fragment1.setTitle("分类");
        fragment1.setIconId(R.drawable.tab_myinfo_selector);
        fragments.add(fragment1);

        Fragment2 fragment2= new Fragment2();
        fragment2.setTitle("我");
        fragment2.setIconId(R.drawable.tab_myinfo_selector);
        fragments.add(fragment2);

        return fragments;
    }

    class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
        private List<BaseFragment> mFragments;
        private final FragmentManager fm;

        public FragmentAdapter(List<BaseFragment> fragments, FragmentManager fm) {
            super(fm);
            this.fm = fm;
            mFragments = fragments;
        }

        public void setFragments(List<BaseFragment> fragments) {
            if (this.mFragments != null) {
                FragmentTransaction ft = fm.beginTransaction();
                for (Fragment f : this.mFragments) {
                    ft.remove(f);
                }
                ft.commit();
                fm.executePendingTransactions();
            }
            this.mFragments = fragments;
            notifyDataSetChanged();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getItemPosition(Object object) {
            if (object instanceof Fragment0) {
                Fragment1 s= new Fragment1();
                s.updateFragment1(param);
                return POSITION_NONE;
            }else if (object instanceof Fragment1) {
                ((Fragment1) object).updateFragment1(param);
                return POSITION_NONE;
            }
            return super.getItemPosition(object);
        }

        @Override
        public int getIconResId(int index) {
            return mFragments.get(index).getIconId();
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragments.get(position).getTitle();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            param = "第一种更新";
            mIndicator.setCurrentItem(1);
            adapter.notifyDataSetChanged();
        } else if (id == R.id.nav_gallery) {
            param = "第一种更新";
            mIndicator.setCurrentItem(1);
            adapter.notifyDataSetChanged();
        } else if (id == R.id.nav_slideshow) {
            param = "Waiting";
            mIndicator.setCurrentItem(1);
            adapter.notifyDataSetChanged();
        } else if (id == R.id.nav_manage) {
            param = "You have no message";
            mIndicator.setCurrentItem(1);
            adapter.notifyDataSetChanged();
        } else if (id == R.id.nav_share) {
            param = "No.1 曾智师 外号大哥 团队领袖人物 后台服务器搭建\n" +
                    "No.2 董谦 外号董大神 无人机的运行 无人机关键代码\n" +
                    "No.3 郭红英 外号红哥 网页领袖人物 网页端实现\n" +
                    "No.4 杨艺文 外号强者 团队文档中坚力量\n" +
                    "No.5 罗九恒 加油鼓劲领袖人物\n";
            mIndicator.setCurrentItem(1);
            adapter.notifyDataSetChanged();

        } else if (id == R.id.nav_send) {
            param = "Please send e-mail to 476586927@qq.com";
            mIndicator.setCurrentItem(1);
            adapter.notifyDataSetChanged();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
