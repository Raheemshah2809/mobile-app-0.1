package com.example.MobileApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
                Toast.makeText(MainActivity.this, "Second Page", Toast.LENGTH_LONG).show();
            }
        });

        ImageView simpleImageView= findViewById(R.id.simpleImageView);
        simpleImageView.setImageResource(R.drawable.lion);
    }

    public void openActivity2() {
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }
    public class Food implements Serializable {

        private  String mName;
        private int mAmount;
        private int mQuantity;

        public void setmQuantity(int mQuantity) {
            this.mQuantity = mQuantity;
        }

        public Food(){}

        public Food(String mName, int mAmount) {
            this.mName = mName;
            this.mAmount = mAmount;
            this.mQuantity = 1;
        }

        public String getmName() {
            return mName;
        }

        public int getmAmount() {
            return mAmount;
        }

        public int getmQuantity(){
            return mQuantity;
        }

        public void addToQuantity(){
            this.mQuantity += 1;
        }

        public void removeFromQuantity(){
            if(this.mQuantity > 1){
                this.mQuantity -= 1;
            }
        }

    }
    public class OrderAdapter extends ArrayAdapter<Food>{
        private final List<Food> list;
        private final Context context;

        TextView currentFoodName,
                currentCost,
                quantityText,
                addMeal,
                subtractMeal,
                removeMeal;

        public OrderAdapter(Context context, List<Food> myOrders) {
            super(context, 0, myOrders);
            this.list = myOrders;
            this.context = context;
        }


        public View getView(final int position, View convertView, ViewGroup parent){
            View listItemView = convertView;
            if(listItemView == null){
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.item_my_meal,parent,false
                );
            }

            final Food currentFood = getItem(position);

            currentFoodName = listItemView.findViewById(R.id.selected_food_name);
            currentCost = listItemView.findViewById(R.id.selected_food_amount);
            subtractMeal = listItemView.findViewById(R.id.minus_meal);
            quantityText = listItemView.findViewById(R.id.quantity);
            addMeal = listItemView.findViewById(R.id.plus_meal);
            removeMeal = listItemView.findViewById(R.id.delete_item);

            //Set the text of the meal, amount and quantity
            currentFoodName.setText(currentFood.getmName());
            currentCost.setText("GH"+"\u20B5"+" "+ (currentFood.getmAmount() * currentFood.getmQuantity()));
            quantityText.setText("x "+ currentFood.getmQuantity());

            //OnClick listeners for all the buttons on the ListView Item
            addMeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentFood.addToQuantity();
                    quantityText.setText("x "+ currentFood.getmQuantity());
                    currentCost.setText("GH"+"\u20B5"+" "+ (currentFood.getmAmount() * currentFood.getmQuantity()));
                    notifyDataSetChanged();
                }

                private void notifyDataSetChanged() {
                }
            });

            subtractMeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentFood.removeFromQuantity();
                    quantityText.setText("x "+currentFood.getmQuantity());
                    currentCost.setText("GH"+"\u20B5"+" "+ (currentFood.getmAmount() * currentFood.getmQuantity()));
                    notifyDataSetChanged();
                }
            });

            removeMeal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(position);
                    notifyDataSetChanged();
                }
            });

            return listItemView;
        }

    }
}
