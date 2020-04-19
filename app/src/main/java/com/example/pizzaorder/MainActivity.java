package com.example.pizzaorder;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 1000;
    Spinner extraCheeseSpinner;
    Spinner extraBulgarianCheeseSpinner;
    Spinner extraGreenOlivesSpinner;
    Spinner extraBlackOlivesSpinner;
    Spinner extraMushroomsSpinner;
    Spinner extraOnionsSpinner;
    Spinner extraTomatosSpinner;
    Spinner extraCornSpinner;
    SeekBar sauseSeekBar;
    CheckBox extraCheeseCheckBox;
    CheckBox extraBulgarianCheeseCheckBox;
    CheckBox extraGreenOlivesCheckBox;
    CheckBox extraBlackOlivesCheckBox;
    CheckBox extraMushroomsCheckBox;
    CheckBox extraOnionsCheckBox;
    CheckBox extraTomatosCheckBox;
    CheckBox extraCornCheckBox;
    Button selectDate;
    TextView dateText;
    Button selectTime;
    TextView timeText;
    TextView sauseCounterText;
    LinearLayout sauseLayout;
    RadioGroup sizeGroup;
    TextView totalPriceText;
    EditText nameText;
    EditText phoneText;
    EditText addressText;
    int sizeCost = 0;
    int extrasCost = 0;
    int sauseCost = 0;
    Boolean chooseSize = false;
    Boolean filledName = false;
    Boolean filledPhone = false;
    Boolean filledAddress = false;
    Boolean chooseDate = false;
    Boolean chooseTime = false;
    Button finishButton;
    String SizeText;
    int totalPrice;
    TextView blinkLine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectDate = findViewById(R.id.date_btn);
        dateText = findViewById(R.id.date_text);
        selectTime = findViewById(R.id.time_btn);
        timeText = findViewById(R.id.time_text);
        sauseLayout = findViewById(R.id.sause_layout);
        sauseSeekBar = findViewById(R.id.sause_sb);
        sauseCounterText = findViewById(R.id.sause_counter_Text);
        sizeGroup = findViewById(R.id.size_rb_group);
        totalPriceText = findViewById(R.id.total_cost_text);
        nameText = findViewById(R.id.name_et);
        phoneText = findViewById(R.id.phone_et);
        blinkLine = findViewById(R.id.blink_line);
        addressText = findViewById(R.id.address_et);
        finishButton = findViewById(R.id.finish_btn);
        nameText.setFilters(new InputFilter[] { filter });
        addressText.setFilters(new InputFilter[] { filter });
        blink();
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0)
                    filledName = true;
                else
                    filledName = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        phoneText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0)
                    filledPhone = true;
                else
                    filledPhone = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        addressText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() != 0)
                    filledAddress = true;
                else
                    filledAddress = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        updateTotalPrice();
        sizeListener sizeGroupListener = new sizeListener();
        sizeGroup.setOnCheckedChangeListener(sizeGroupListener);
        sauseCounterText.setText(getResources().getString(R.string.sause_counter) + " 0");
        selectTime.setEnabled(false);
        selectTime.setAlpha(.5f);
        extraCheeseCheckBox = findViewById(R.id.extra_cheese_cb);
        extraBulgarianCheeseCheckBox = findViewById(R.id.extra_bulgarian_cheese_cb);
        extraGreenOlivesCheckBox = findViewById(R.id.extra_green_olives_cb);
        extraBlackOlivesCheckBox = findViewById(R.id.extra_black_olives_cb);
        extraMushroomsCheckBox = findViewById(R.id.extra_mushrooms_cb);
        extraOnionsCheckBox = findViewById(R.id.extra_onions_cb);
        extraTomatosCheckBox = findViewById(R.id.extra_tomatos_cb);
        extraCornCheckBox = findViewById(R.id.extra_corn_cb);
        extraCheeseSpinner = findViewById(R.id.extra_cheese_spinner);
        extraBulgarianCheeseSpinner = findViewById(R.id.extra_bulgarian_cheese_spinner);
        extraGreenOlivesSpinner = findViewById(R.id.extra_green_olives_spinner);
        extraBlackOlivesSpinner = findViewById(R.id.extra_black_olives_spinner);
        extraMushroomsSpinner = findViewById(R.id.extra_mushrooms_spinner);
        extraOnionsSpinner = findViewById(R.id.extra_onions_spinner);
        extraTomatosSpinner = findViewById(R.id.extra_tomatos_spinner);
        extraCornSpinner = findViewById(R.id.extra_corn_spinner);
        extraCheeseSpinner.setEnabled(false);
        extraBulgarianCheeseSpinner.setEnabled(false);
        extraGreenOlivesSpinner.setEnabled(false);
        extraBlackOlivesSpinner.setEnabled(false);
        extraMushroomsSpinner.setEnabled(false);
        extraOnionsSpinner.setEnabled(false);
        extraTomatosSpinner.setEnabled(false);
        extraCornSpinner.setEnabled(false);
        extraCheeseSpinner.setVisibility(View.INVISIBLE);
        extraBulgarianCheeseSpinner.setVisibility(View.INVISIBLE);
        extraGreenOlivesSpinner.setVisibility(View.INVISIBLE);
        extraBlackOlivesSpinner.setVisibility(View.INVISIBLE);
        extraMushroomsSpinner.setVisibility(View.INVISIBLE);
        extraOnionsSpinner.setVisibility(View.INVISIBLE);
        extraTomatosSpinner.setVisibility(View.INVISIBLE);
        extraCornSpinner.setVisibility(View.INVISIBLE);
        extrasCheckboxListner extrasCheckboxListner = new extrasCheckboxListner();
        extraCheeseCheckBox.setOnCheckedChangeListener(extrasCheckboxListner);
        extraBulgarianCheeseCheckBox.setOnCheckedChangeListener(extrasCheckboxListner);
        extraGreenOlivesCheckBox.setOnCheckedChangeListener(extrasCheckboxListner);
        extraBlackOlivesCheckBox.setOnCheckedChangeListener(extrasCheckboxListner);
        extraMushroomsCheckBox.setOnCheckedChangeListener(extrasCheckboxListner);
        extraOnionsCheckBox.setOnCheckedChangeListener(extrasCheckboxListner);
        extraTomatosCheckBox.setOnCheckedChangeListener(extrasCheckboxListner);
        extraCornCheckBox.setOnCheckedChangeListener(extrasCheckboxListner);
        sauseSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                sauseCounterText.setText(getResources().getString(R.string.sause_counter) + " " + i);
                int counter;
                sauseLayout.removeAllViews();
                for(counter = 0 ; counter < i ; counter++)
                {
                    ImageView sauseImage = new ImageView(MainActivity.this);
                    sauseImage.setImageResource(R.drawable.sause);
                    LinearLayout.LayoutParams sauseLayoutParams= new LinearLayout.LayoutParams(120,120);
                    sauseImage.setLayoutParams(sauseLayoutParams);
                    sauseImage.setId(counter);
                    sauseLayout.addView(sauseImage);
                }
                sauseCost = i;
                updateTotalPrice();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,
                R.array.arrange_extras, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        extraCheeseSpinner.setAdapter(adapter);
        extraBulgarianCheeseSpinner.setAdapter(adapter);
        extraGreenOlivesSpinner.setAdapter(adapter);
        extraBlackOlivesSpinner.setAdapter(adapter);
        extraMushroomsSpinner.setAdapter(adapter);
        extraOnionsSpinner.setAdapter(adapter);
        extraTomatosSpinner.setAdapter(adapter);
        extraCornSpinner.setAdapter(adapter);
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        final int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        final int currentMinute = calendar.get(Calendar.MINUTE);
        final int[] pickedDate = new int[3];
        final DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateText.setText(day + "/" + (month + 1) + "/" + year);
                        pickedDate[0] = year;
                        pickedDate[1] = month + 1;
                        pickedDate[2] = day;
                        selectTime.setEnabled(true);
                        selectTime.setAlpha(1f);
                        chooseDate = true;
                        timeText.setText("");
                        chooseTime = false;
                    }
                }, year, month, dayOfMonth);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        final com.wdullaer.materialdatetimepicker.time.TimePickerDialog timePickerDialog = com.wdullaer.materialdatetimepicker.time.TimePickerDialog.newInstance(
                new com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                        timeText.setText(String.format("%02d:%02d", hourOfDay, minute));
                        chooseTime = true;
                    }
                },
                currentHour,
                currentMinute,
                true
        );
        selectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pickedDate[0] == year && pickedDate[1] == month + 1 && pickedDate[2] == dayOfMonth)
                    timePickerDialog.setMinTime(currentHour, currentMinute, 0);
                else
                    timePickerDialog.setMinTime(0,0,0);
                timePickerDialog.show(getSupportFragmentManager(), "Timepickerdialog");
            }
        });
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chooseSize != true)
                    Toasty.error(MainActivity.this, getResources().getString(R.string.choose_size_toast),
                            Toast.LENGTH_SHORT, true).show();
                else if(filledName != true)
                    Toasty.error(MainActivity.this, getResources().getString(R.string.filled_name_toast),
                            Toast.LENGTH_SHORT, true).show();
                else if(filledPhone != true)
                    Toasty.error(MainActivity.this, getResources().getString(R.string.filled_phone_toast),
                            Toast.LENGTH_SHORT, true).show();
                else if(filledAddress != true)
                    Toasty.error(MainActivity.this, getResources().getString(R.string.filled_address_toast),
                            Toast.LENGTH_SHORT, true).show();
                else if(chooseDate != true)
                    Toasty.error(MainActivity.this, getResources().getString(R.string.choose_date_toast),
                            Toast.LENGTH_SHORT, true).show();
                else if(chooseTime != true)
                    Toasty.error(MainActivity.this, getResources().getString(R.string.choose_time_toast),
                            Toast.LENGTH_SHORT, true).show();
                else {
                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText(getResources().getString(R.string.total_message))
                            .setContentText(SizeText + " - "
                                    + extrasCost + " " + getResources().getString(R.string.extras_message) + " - "
                                    + sauseCost + " " + getResources().getString(R.string.sause_message) + " - "
                                    + getResources().getString(R.string.total_cost_message) + " " + totalPrice + getResources().getString(R.string.currency))
                            .setConfirmText(getResources().getString(R.string.confirm_btn))
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                    new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                            .setConfirmText(getResources().getString(R.string.finish_btn))
                                            .setTitleText(getResources().getString(R.string.success_message))
                                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                @Override
                                                public void onClick(SweetAlertDialog sDialog) {
                                                    sDialog.dismissWithAnimation();
                                                    Intent mainIntent = new Intent(MainActivity.this,MainActivity.class);
                                                    MainActivity.this.startActivity(mainIntent);
                                                    MainActivity.this.finish();
                                                }
                                            })
                                            .show();
                                }
                            })
                            //.setCancelText("Don't delete")
                            .setCancelButton(getResources().getString(R.string.cancel_btn), new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
            }
        });
    }
    private void blink() {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 500;    //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(blinkLine.getVisibility() == View.VISIBLE){
                            blinkLine.setVisibility(View.INVISIBLE);
                        }else{
                            blinkLine.setVisibility(View.VISIBLE);
                        }
                        blink();
                    }
                });
            }
        }).start();
    }

    private class extrasCheckboxListner implements CompoundButton.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch(compoundButton.getId()) {
                case R.id.extra_cheese_cb:
                    extraCheeseSpinner.setEnabled(b);

                    extraCheeseSpinner.setVisibility(b?View.VISIBLE : View.INVISIBLE);
                    break;
                case R.id.extra_bulgarian_cheese_cb:
                    extraBulgarianCheeseSpinner.setEnabled(b);
                    extraBulgarianCheeseSpinner.setVisibility(b?View.VISIBLE : View.INVISIBLE);
                    break;
                case R.id.extra_green_olives_cb:
                    extraGreenOlivesSpinner.setEnabled(b);
                    extraGreenOlivesSpinner.setVisibility(b?View.VISIBLE : View.INVISIBLE);
                    break;
                case R.id.extra_black_olives_cb:
                    extraBlackOlivesSpinner.setEnabled(b);
                    extraBlackOlivesSpinner.setVisibility(b?View.VISIBLE : View.INVISIBLE);
                    break;
                case R.id.extra_mushrooms_cb:
                    extraMushroomsSpinner.setEnabled(b);
                    extraMushroomsSpinner.setVisibility(b?View.VISIBLE : View.INVISIBLE);
                    break;
                case R.id.extra_onions_cb:
                    extraOnionsSpinner.setEnabled(b);
                    extraOnionsSpinner.setVisibility(b?View.VISIBLE : View.INVISIBLE);
                    break;
                case R.id.extra_tomatos_cb:
                    extraTomatosSpinner.setEnabled(b);
                    extraTomatosSpinner.setVisibility(b?View.VISIBLE : View.INVISIBLE);
                    break;
                case R.id.extra_corn_cb:
                    extraCornSpinner.setEnabled(b);
                    extraCornSpinner.setVisibility(b?View.VISIBLE : View.INVISIBLE);
                    break;
            }
            extrasCost = b? extrasCost+1:extrasCost-1;
            updateTotalPrice();
        }
    }
    private class sizeListener implements RadioGroup.OnCheckedChangeListener
    {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int checkedRadioButton = radioGroup.getCheckedRadioButtonId();
            switch(checkedRadioButton) {
                case R.id.small_rb:
                    sizeCost = 10;
                    SizeText = getResources().getString(R.string.small_size_message);
                    break;
                case R.id.medium_rb:
                    sizeCost = 20;
                    SizeText = getResources().getString(R.string.medium_size_message);
                    break;
                case R.id.large_rb:
                    sizeCost = 30;
                    SizeText = getResources().getString(R.string.large_size_message);
                    break;
            }
            chooseSize = true;
            updateTotalPrice();
        }
    }

    private InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String blockCharacterSet = "~#^|$%*!@/()-'\":;,?{}=!$^';,?×÷<>{}€£¥₪₩%~`¤♡♥_|《》¡¿°•○●□■◇◆♧♣▲▼▶◀↑↓←→☆★▪:-);-):-D:-(:'(:O 1234567890&+.";
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };

    private void updateTotalPrice()
    {
        totalPrice = sizeCost + sauseCost + extrasCost;
        totalPriceText.setText(getResources().getString(R.string.cost_sum) + " " + totalPrice + getResources().getString(R.string.currency));
    }
}
