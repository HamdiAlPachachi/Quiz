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


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentQ3 extends Fragment
{

    private ImageSwitcher is;
    Button nextButton;
    Button previousButton;

    private int[] imageIds = {R.drawable.colourful_logo, R.drawable.ucl_currentlogo, R.drawable.ucl_logo, R.drawable.ucl_logo1};
    private int count = imageIds.length;
    private int currentIndex = 0;

    public FragmentQ3()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_1, container, false);
        nextButton = (Button) view.findViewById(R.id.Next);
        previousButton = (Button) view.findViewById(R.id.Previous);
        is = (ImageSwitcher) view.findViewById(R.id.ImageSwitcher);

        is.setFactory(new ViewSwitcher.ViewFactory()
        {

            public View makeView()
            {
                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        });

        is.setImageResource((imageIds[0])); //to begin with first picture.

        switcher();

        return view;
    }

    public void switcher()
    {
        nextButton.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View view)
            {
                currentIndex++;                //currentIndex is the counter used to switch between pictures in the fragment.
                if (currentIndex == count)     //When "Next Picture" is clicked, currentIndex rises by 1, thereby raising the
                {                              //index in the imageIds array, which changes the image displayed by is.
                    currentIndex = 0;
                }
                is.setImageResource(imageIds[currentIndex]);
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
                is.setImageResource(imageIds[currentIndex]);
            }
        });


    }

}