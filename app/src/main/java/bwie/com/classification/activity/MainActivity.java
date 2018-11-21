package bwie.com.classification.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bwie.com.classification.R;
import bwie.com.classification.cart.CheFragment;
import bwie.com.classification.fenlei.FenFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FenFragment homeFragment;
    private CheFragment myFragment;
    private List<Fragment> list;
    private ViewPager vp;
    private TextView rb1;
    private TextView rb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.vp);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        homeFragment = new FenFragment();
        myFragment = new CheFragment();
        list = new ArrayList<>();
        list.add(homeFragment);
        list.add(myFragment);
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb1:
                vp.setCurrentItem(0);
                break;
            case R.id.rb2:
                vp.setCurrentItem(1);
                break;
        }
    }
}
