package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView origintTv = (TextView)findViewById(R.id.origin_tv);
        origintTv.setText(sandwich.getPlaceOfOrigin());

        TextView alsoKnownTv = (TextView)findViewById(R.id.also_known_tv);
        alsoKnownTv.setText(sandwich.getAlsoKnownAs().size() > 0 ? sandwich.getAlsoKnownAs().toString() : "");

        TextView detailPlaceOfOriginTv = (TextView)findViewById(R.id.detail_place_of_origin_tv);
        detailPlaceOfOriginTv.setText(sandwich.getPlaceOfOrigin());

        TextView descriptionTv = (TextView)findViewById(R.id.description_tv);
        descriptionTv.setText(sandwich.getDescription());

        TextView ingredientsTv = (TextView)findViewById(R.id.ingredients_tv);
        ingredientsTv.setText(sandwich.getIngredients().size() > 0 ? sandwich.getIngredients().toString() : "");
    }
}
