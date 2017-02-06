package com.example.administrator.takeaway;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SurveyActivity extends AppCompatActivity {
    ArrayList<SurveyItem> survey = new ArrayList<SurveyItem>();
    TextView nameText;
    RatingBar aRatingBar;
    Button btnSub;
    ListView lstView;
    SurveyAdapter adapter;

    View.OnClickListener submitButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View arg0) {
            Toast.makeText(getApplicationContext(), "Review Button clicked", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(this, ....);
//            startActivity(intent);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        nameText =(TextView) findViewById(R.id.name);
        aRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        btnSub = (Button) findViewById(R.id.button);
        lstView = (ListView) findViewById(R.id.listView);

        survey.add(new SurveyItem("KFC", 1, false));
        survey.add(new SurveyItem("McDonalds", 4, false));
        survey.add(new SurveyItem("Chip Shop",3, false));
        Log.d("MK","Got here in Suvery Activity");


        adapter = new SurveyAdapter(this, R.layout.list_view_item, survey);

        lstView.setAdapter(adapter);



        btnSub.setOnClickListener(submitButtonListener);
    }

    class SurveyItem {
        private String name;
        private float itemRatingBar;
        private boolean regCust;

        public SurveyItem(String name, float f, boolean regCust) {
            this.name = name;
            this.itemRatingBar = f;
            this.regCust = regCust;
        }

        public String getName() {
            return name;
        }

        public float getItemRatingBar() {
            return itemRatingBar;
        }
    }

    class SurveyAdapter extends ArrayAdapter<SurveyItem> {
        private ArrayList<SurveyItem> objects;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        public SurveyAdapter(Context context, int resource, List objects) {
            super(context, resource, objects);
            this.objects = (ArrayList<SurveyItem>) objects;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Log.d("MK", "get View");
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_view_item, null);
            }

            SurveyItem surveyItem = objects.get(position);

            ((TextView) convertView.findViewById(R.id.name)).setText(surveyItem.getName());

            ((RatingBar) convertView.findViewById(R.id.ratingBar)).setRating(3f);

            ImageView iv = (ImageView)convertView.findViewById(R.id.imageView);

            if(surveyItem.getItemRatingBar()>3)
            iv.setImageResource(R.mipmap.a);
            else
            iv.setImageResource(R.mipmap.b);

            return convertView;

        }
    }
}
