package com.example.vaxtrax;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TravelVaccinationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_vaccinations);
        Bundle b = getIntent().getExtras();
        int i = b.getInt(TravelguideActivity.EXTRA, 0);
//      Set list items into textviews
        ((TextView)findViewById(R.id.tv3))
                .setText(Maa.getInstance().getRokote().get(i).getMaa());
        ((TextView)findViewById(R.id.v1))
                .setText(Maa.getInstance().getRokote().get(i).getV1());
        ((TextView)findViewById(R.id.v2))
                .setText(Maa.getInstance().getRokote().get(i).getV2());
        ((TextView)findViewById(R.id.v3))
                .setText(Maa.getInstance().getRokote().get(i).getV3());
        ((TextView)findViewById(R.id.v4))
                .setText(Maa.getInstance().getRokote().get(i).getV4());
        ((TextView)findViewById(R.id.v5))
                .setText(Maa.getInstance().getRokote().get(i).getV5());
        ((TextView)findViewById(R.id.v6))
                .setText(Maa.getInstance().getRokote().get(i).getV6());
        ((TextView)findViewById(R.id.v7))
                .setText(Maa.getInstance().getRokote().get(i).getV7());

    }
}
