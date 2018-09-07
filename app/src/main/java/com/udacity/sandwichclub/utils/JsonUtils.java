package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject object = new JSONObject(json);
            JSONObject nameObject = object.getJSONObject("name");
            String mainName = nameObject.getString("mainName");
            List<String> alsoKnow = new ArrayList<>();
            JSONArray alsoKnowJsonArray = nameObject.getJSONArray("alsoKnownAs");
            for (int i = 0; i < alsoKnowJsonArray.length(); i++){
                alsoKnow.add(alsoKnowJsonArray.getString(i));
            }
            String placeOfOrigin = object.getString("placeOfOrigin");
            String description = object.getString("description");
            String image = object.getString("image");
            List<String> ingredients = new ArrayList<>();
            JSONArray jsonArrayIngredients = object.getJSONArray("ingredients");
            for (int i = 0; i < jsonArrayIngredients.length(); i++){
                ingredients.add(jsonArrayIngredients.getString(i));
            }
            return new Sandwich(mainName, alsoKnow, placeOfOrigin, description, image, ingredients);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
