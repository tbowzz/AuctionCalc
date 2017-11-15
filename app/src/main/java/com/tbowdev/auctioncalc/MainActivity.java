package com.tbowdev.auctioncalc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.ShareActionProvider;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

import static com.tbowdev.auctioncalc.TaxCalculator.BOISE;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String TAG = "MainActivity";

    private EditText mTitleText;
    private EditText mMaxBidText;
    private Button mSubmit;

    private String mLocation;
    private boolean mIsCalculated;

    private CardView mResultsCard;
    private TextView mSalesPriceFee;
    private TextView mInternetFee;
    private TextView mLoadFee;
    private TextView mTaxes;
    private TextView mTotalCost;

    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleText = (EditText) findViewById(R.id.input_title);
        mTitleText.setVisibility(View.GONE);
        mMaxBidText = (EditText) findViewById(R.id.input_maxbid);
        AppCompatSpinner locationSpinner = (AppCompatSpinner) findViewById(R.id.location_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.spinner_item, Model.instance.getLocations());
        adapter.setDropDownViewResource(R.layout.spinner_item);
        locationSpinner.setAdapter(adapter);
        locationSpinner.setOnItemSelectedListener(this);
        mLocation = BOISE;

        mIsCalculated = false;

        mSubmit = (Button) findViewById(R.id.submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mMaxBidText.getText() == null || mMaxBidText.getText().length() == 0) {
//                    Snackbar.make(v, "You must enter a bid amount.", Snackbar.LENGTH_LONG).show();
                    Snackbar.make(v, Html.fromHtml("<font color=\"#ffffff\">You must enter a bid amount.</font>"), Snackbar.LENGTH_LONG).show();
                    return;
                }
                mIsCalculated = true;
                double bidPrice = Double.parseDouble(mMaxBidText.getText().toString());
                String title = mTitleText.getText().toString();
                VehicleCost vehicleCost = Model.instance.calculateCost(title, bidPrice, mLocation);
                updateResults(vehicleCost);
            }
        });

        mResultsCard = (CardView) findViewById(R.id.my_results_card);
        mResultsCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(!mIsCalculated) return false;

                PopupMenu popup = new PopupMenu(MainActivity.this, mResultsCard);
                popup.getMenuInflater().inflate(R.menu.share_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");

                        String shareBody = "Here is the price breakdown for a bid of $" + mMaxBidText.getText().toString()
                                + "\nTotal Cost: " + mTotalCost.getText().toString()
                                + "\nSales Price Fee: " + mSalesPriceFee.getText().toString()
                                + "\nInternet Fee: " + mInternetFee.getText().toString()
                                + "\nLoad Fee: " + mLoadFee.getText().toString()
                                + "\nTaxes: " + mTaxes.getText().toString()
                                + "";

                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Auction Calc Results");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        return true;
                    }
                });

                popup.show();
                return true;
            }
        });
        mSalesPriceFee = (TextView) findViewById(R.id.sales_fee_text);
        mInternetFee = (TextView) findViewById(R.id.internet_fee_text);
        mLoadFee = (TextView) findViewById(R.id.load_fee_text);
        mTaxes = (TextView) findViewById(R.id.tax_text);
        mTotalCost = (TextView) findViewById(R.id.total_cost_text);
    }

    protected void updateResults(VehicleCost vehicleCost) {
        Log.d(TAG, "updateResults");
        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        String salePriceFee = " " + formatter.format(vehicleCost.getSalePriceFee());
        String internetFee = " " + formatter.format(vehicleCost.getInternetFee());
        String loadFee = " " + formatter.format(vehicleCost.getLoadFee());
        String taxes = " " + formatter.format(vehicleCost.getTaxVal());
        String total = " " + formatter.format(vehicleCost.getTotalCost());

        mSalesPriceFee.setText(salePriceFee);
        mInternetFee.setText(internetFee);
        mLoadFee.setText(loadFee);
        mTaxes.setText(taxes);
        mTotalCost.setText(total);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.d(TAG, "onItemSelected: " + position);
        mLocation = Model.instance.getLocations().get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
