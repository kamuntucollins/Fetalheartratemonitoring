package com.example.fetalheartratemonitoring;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {


    Context mcontext;
    List<screenitem> mListScreen;


    public IntroViewPagerAdapter(Context mcontext, List<screenitem> mListScreen) {
        this.mcontext = mcontext;
        this.mListScreen = mListScreen;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen = inflater.inflate(R.layout.layoutscreen, null);
        ImageView imgSlide = layoutScreen.findViewById(R.id.intro_img);
        TextView title = layoutScreen.findViewById(R.id.intro_title);
        TextView discription = layoutScreen.findViewById(R.id.into_discription);
        title.setText(mListScreen.get(position).getTitle());
        discription.setText(mListScreen.get(position).getDiscription());
        imgSlide.setImageResource(mListScreen.get(position).getScreenimg());
        container.addView(layoutScreen);

        return layoutScreen;
    }

    @Override
    public int getCount() {
        return mListScreen.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
