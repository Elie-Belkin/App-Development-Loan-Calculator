package com.example.loancalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText amount, rate, term, fees, min_pay;
    private float amount_f, rate_f, term_f, fees_f, min_pay_f;
    private Button calculateLoan;
    private TextView payment, interest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.loan_amount);
        rate = findViewById(R.id.interest_rate);
        term = findViewById(R.id.loan_term);
        fees = findViewById(R.id.loan_fees);
        min_pay = findViewById(R.id.min_payment);

        calculateLoan = findViewById(R.id.calculate_loan);

        payment = findViewById(R.id.monthly_payment);
        interest = findViewById(R.id.total_interest);

        calculateLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount_f = Float.parseFloat(amount.getText().toString());
                rate_f = Float.parseFloat(rate.getText().toString());
                term_f = Float.parseFloat(term.getText().toString());
                fees_f = Float.parseFloat(fees.getText().toString());
                min_pay_f = Float.parseFloat(min_pay.getText().toString());

                double monthlyPayment = getMonthlyPayment();
                payment.setText("Your monthly payment is " + monthlyPayment);
                double totalInterest = getTotalInterest();
                interest.setText("Your total interest is " + totalInterest);
            }
        });
    }

    public double getMonthlyPayment() {
        double n = (amount_f + fees_f) * (rate_f * 0.01 / 12) / (1 - (1/Math.pow(1 + rate_f * 0.01 / 12, term_f * 12)));
        return Math.max(n, min_pay_f);
    }

    public double getTotalInterest() {
        return amount_f * (Math.pow(1 + rate_f * 0.01, term_f * 12) - 1);
    }


}