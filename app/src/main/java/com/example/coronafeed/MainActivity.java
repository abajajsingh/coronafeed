package com.example.coronafeed;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

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
    private ViewAdapter mViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mArticleLab = new ArrayList<>();
        new ProcessInBackground().execute();
        buildRecyclerView();


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
                URL url = new URL("https://rss.app/feeds/VbzlGVUV0rEGlznl.xml");
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
                        mArticleLab.add(new Article(name, link, src, description, date));
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
            Log.d("list-size2", "" + mArticleLab.size());
            for(Article a : mArticleLab) {
                Log.d("titles-2", "" + a.getTitle());
            }
            mViewAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
        }

    }

    public void buildRecyclerView() {
        mArticleRecyclerView = findViewById(R.id.article_view);
        mViewAdapter = new ViewAdapter(this, mArticleLab);
        mArticleRecyclerView.setAdapter(mViewAdapter);
        mArticleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mViewAdapter.setOnItemClickListener(new ViewAdapter.onItemClickListener() {
            
        });
    }
}