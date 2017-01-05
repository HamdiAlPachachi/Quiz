package com.example.aryanheydari.quiz;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import android.util.Log;

public class FragmentQ1 extends Fragment
{

    private ImageSwitcher ImageSwitcher;
    Button nextButton;
    Button previousButton;

    int[] imageIds = {R.drawable.cars_in_quad, R.drawable.ucl, R.drawable.unversity_college_london};
    int count = imageIds.length;
    int currentIndex = 0;

    public FragmentQ1()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        nextButton = (Button) view.findViewById(R.id.Next);
        previousButton = (Button) view.findViewById(R.id.Previous);
        ImageSwitcher = (ImageSwitcher) view.findViewById(R.id.ImageSwitcher);

        //return view;

        ImageSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {

            public View makeView()
            {
                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });

        ImageSwitcher.setImageResource((imageIds[0])); //to begin with first picture.

        nextButton.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View view)
            {
                currentIndex++;                //currentIndex is the counter used to switch between pictures in the fragment.
                if (currentIndex == count)     //When "Next Picture" is clicked, currentIndex rises by 1, thereby raising the
                {                              //index in the imageIds array, which changes the image displayed by ImageSwitcher.
                    currentIndex = 0;
                }
                ImageSwitcher.setImageResource(imageIds[currentIndex]);
            }

        });

        previousButton.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View view)
            {
                currentIndex--;
                if (currentIndex == -1)
                {
                    currentIndex = count - 1;
                }
                ImageSwitcher.setImageResource(imageIds[currentIndex]);
            }
        });

        return view;

    }
}