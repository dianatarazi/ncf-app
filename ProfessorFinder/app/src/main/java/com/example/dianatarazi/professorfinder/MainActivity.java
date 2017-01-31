package com.example.dianatarazi.professorfinder;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Document webPage;
    private String htmlURL = "https://www.ncf.edu/directory/listing/";
    private TextView parsedHtmlNode;
    private String htmlContentInStringFormat;
    private String htmlTitle;
    private String htmlLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parsedHtmlNode = (TextView)findViewById(R.id.html_content);

        jsoupAsyncTask jsoupRef = new jsoupAsyncTask();
        jsoupRef.execute();


    }


    private class jsoupAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }




        @Override
        protected Void doInBackground(Void... params) {
            try {
                webPage = Jsoup.connect(htmlURL).get();
                htmlTitle = webPage.title();
                //htmlContentInStringFormat = webPage.text();


            Elements links = webPage.select("a[href]");
            for (Element link : links) {
                // get the value from the href attribute
                System.out.println(link.attr("href"));




            }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            parsedHtmlNode.setText(htmlContentInStringFormat);
        }


    }
}
