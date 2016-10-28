package com.example.codetribe.bmi2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    SeekBar height, weight,BMI;
    TextView tvHeight,tvWeight,tvBMI,tvInterprateBMI,tvClassify;
    double NumHeight, NumWeight;
    ImageView imgWeight;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        imgWeight =(ImageView)findViewById(R.id.imgWeight);
        height = (SeekBar)findViewById(R.id.seekHeight);
        weight = (SeekBar)findViewById(R.id.seekWeight);
        BMI = (SeekBar)findViewById(R.id.seekBMI);
        imageButton = (ImageButton)findViewById(R.id.imgButtom);
        tvInterprateBMI = (TextView)findViewById(R.id.tvInterprateBMI);
        tvInterprateBMI.setText(R.string.your_bmi_unknown);

        tvHeight =(TextView)findViewById(R.id.tvheight);
        tvHeight.setText("Height : "+height.getProgress()+" cm");

        tvWeight =(TextView)findViewById(R.id.tvwieght) ;
        tvWeight.setText("Weight : " + weight.getProgress()+" kg");

        tvClassify = (TextView)findViewById(R.id.tvClassify);

        tvBMI = (TextView)findViewById(R.id.tvBMI);
        tvBMI.setText("BMI : "+ BMI.getProgress()+" kg/m\u00B2");
        BMI.setEnabled(false);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),MainActivity.class);
                startActivity(intent);
            }
        });
        height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                double seekFormat = round((seekBar.getProgress()*0.1f),2);
                NumHeight = Double.valueOf(seekFormat);
                tvBMI.setText("BMI : "+ CalcBMI(NumWeight,NumHeight)+" kg/m\u00B2");
                BMI.setProgress((int) CalcBMI(NumWeight,NumHeight));
                tvHeight.setText("Height : "+ NumHeight+" cm" );
                BMI.setClickable(false);
                tvInterprateBMI.setText(interpretBMI(CalcBMI(NumWeight,NumHeight)));
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
               tvBMI.setText("BMI : "+ CalcBMI(NumWeight,NumHeight)+" kg/m\u00B2");
                BMI.setProgress((int) CalcBMI(NumWeight,NumHeight));
                BMI.setClickable(false);
                tvInterprateBMI.setText(interpretBMI(CalcBMI(NumWeight,NumHeight)));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvBMI.setText("BMI : "+ CalcBMI(NumWeight,NumHeight)+" kg/m\u00B2");
                BMI.setProgress((int) CalcBMI(NumWeight,NumHeight));
                BMI.setClickable(false);
                tvInterprateBMI.setText(interpretBMI(CalcBMI(NumWeight,NumHeight)));
            }
        });
        weight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                double seekFormat = round((seekBar.getProgress()*0.1f),2);
                NumWeight = Double.valueOf(seekFormat);;

                tvWeight.setText("Weight : " + NumWeight+" kg");
                tvBMI.setText("BMI : "+ CalcBMI(NumWeight,NumHeight)+" kg/m\u00B2");
                BMI.setProgress((int) CalcBMI(NumWeight,NumHeight));
                BMI.setEnabled(false);
                tvInterprateBMI.setText(interpretBMI(CalcBMI(NumWeight,NumHeight)));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
              tvBMI.setText("BMI : "+ CalcBMI(NumWeight,NumHeight)+" kg/m\u00B2");
                BMI.setProgress((int) CalcBMI(NumWeight,NumHeight));
                BMI.setClickable(false);
                tvInterprateBMI.setText(interpretBMI(CalcBMI(NumWeight,NumHeight)));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
               tvBMI.setText("BMI : "+ CalcBMI(NumWeight,NumHeight)+" kg/m\u00B2");
                BMI.setProgress((int) CalcBMI(NumWeight,NumHeight));
                BMI.setClickable(false);
                tvInterprateBMI.setText(interpretBMI(CalcBMI(NumWeight,NumHeight)));
            }
        });
    }
    public float CalcBMI(double w,double h){

        double BMI =  w/(h * h)*10000;
        double BMIFormat = round((BMI),2);
       return Float.parseFloat(String.valueOf(BMIFormat));

    }
    public String interpretBMI(float BMI){
        String interpret ="";
        if(BMI < 16)
        {
            imgWeight.setImageResource(R.drawable.severely_underweight);
            interpret = "Severely underweight";
            tvClassify.setBackgroundResource(R.color.LightYellow);
            imgWeight.setBackgroundResource(R.color.Yellow);
            tvClassify.setText(R.string.UnderWieghtClassify);
        }
        else if(BMI < 18.5 )
        {
            imgWeight.setImageResource(R.drawable.severely_underweight);
            interpret = "Under Weight";
            tvClassify.setBackgroundResource(R.color.LightAmber);
            imgWeight.setBackgroundResource(R.color.Amber);
            tvClassify.setText(R.string.UnderWieghtClassify);
        }
        else if(BMI < 25)
        {
            imgWeight.setImageResource(R.drawable.normalwieght);
            interpret = "Normal";
            tvClassify.setBackgroundResource(R.color.LightGreen);
            imgWeight.setBackgroundResource(R.color.Green);
            tvClassify.setText(R.string.NormalClassify);
        }
        else if(BMI < 30)
        {
            imgWeight.setImageResource(R.drawable.overweightwhite);
            interpret = "Over Weight";
            tvClassify.setBackgroundResource(R.color.LightDeepOrange);
            imgWeight.setBackgroundResource(R.color.DeepOrange);
            tvClassify.setText(R.string.overWieghtClassify);
        }
        else if(BMI == 0.0 )
        {
            interpret = getResources().getResourceName(R.string.your_bmi_unknown);
        }
        else
        {
            imgWeight.setImageResource(R.drawable.man);
            interpret = "Obese";
            tvClassify.setBackgroundResource(R.color.LightRed);
            imgWeight.setBackgroundResource(R.color.Red);
            tvClassify.setText(R.string.ObeseClassify);
        }
        return interpret;
    }
    public static double round(double value,int places )
    {
       if(places < 0)throw new IllegalArgumentException();
        long factor = (long)Math.pow(10,places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double)tmp / factor;
    }
}
