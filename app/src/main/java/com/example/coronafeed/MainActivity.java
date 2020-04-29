package com.example.coronafeed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView mArticleRecyclerView;
    private ArrayList<Article> mArticleLab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mArticleLab = new ArrayList<>();
        new ProcessInBackground().execute();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArticleRecyclerView = findViewById(R.id.article_view);
        ViewAdapter viewAdapter = new ViewAdapter(this, mArticleLab);
        mArticleRecyclerView.setAdapter(viewAdapter);
        mArticleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Log.i(this.getLocalClassName(),"Activity created.");
        Log.d("list-size2", "" + mArticleLab.size());
    }


    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection()
                    .getInputStream();
        } catch(IOException e) {
            return null;
        }
    }

    public class ProcessInBackground extends AsyncTask<Integer, Void, Exception> {
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        Exception exception = null;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Loading RSS Feed... ");
            progressDialog.show();
        }

        @Override
        protected Exception doInBackground(Integer... integers) {
            try {
                URL url = new URL("https://rss.app/feeds/6LBg9dueEjaUYCE1");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(url), "UTF_8");

                boolean insideItem = false;
                int eventType = xpp.getEventType();

                String name = "";
                String link = "";
                String src = "";
                String description = "";
                String date = "";

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                        } else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                name = xpp.nextText();
                            }
                        } else if (xpp.getName().equalsIgnoreCase("description")) {
                            if (insideItem) {
                                description = xpp.nextText();
                            }
                        } else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                link = xpp.nextText();
                            }
                        } else if (xpp.getName().equalsIgnoreCase("dc:creator")) {
                            if (insideItem) {
                                src = xpp.nextText();
                            }
                        } else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                            if (insideItem) {
                                date = xpp.nextText();
                            }
                        }
                    } else if (eventType == XmlPullParser.END_TAG && xpp.getName().equalsIgnoreCase("item")) {
                        Log.d("thing", name + "   " + link + "   " + src + "  ");
                        Article art = new Article(name, link, src, description, date);
                        mArticleLab.add(art);
                        Log.d("list-size", "" + mArticleLab.size());
                        insideItem = false;
                    }


                    eventType = xpp.next();
                }
            } catch (MalformedURLException e) {
                exception = e;
            } catch (XmlPullParserException e) {
                exception = e;
            } catch (IOException e) {
                exception = e;
            }

            return exception;
        }

        protected void onPostExecute(Exception s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }


    }
}