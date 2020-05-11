package com.example.coronafeed;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.lifecycle.ViewModelProviders;

public class ReadMoreDialog extends AppCompatDialogFragment {
    private String mDescription;
    private String mLink;
    private Article curArticle;
    private ArticleViewModel articleViewModel;
    private Boolean isHome;

    public ReadMoreDialog(String description, String link, Article article, ArticleViewModel viewModel, Boolean home) {
        mDescription = description;
        mLink = link;
        curArticle = article;
        articleViewModel = viewModel;
        isHome = home;
    }

    public ReadMoreDialog(String description, String link, Article article, ArticleViewModel viewModel, Boolean home, int position) {
        mDescription = description;
        mLink = link;
        curArticle = article;
        articleViewModel = viewModel;
        isHome = home;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(isHome) {
            builder.setTitle("Decription")
                    .setMessage(mDescription)
                    .setNegativeButton("Read More", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(mLink));
                            startActivity(intent);
                        }
                    })
                    .setNeutralButton("Read Later", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ReadLaterArticle rl_art = new ReadLaterArticle(curArticle.getTitle(),
                                    curArticle.getUrl(),
                                    curArticle.getSource(),
                                    curArticle.getDescription(),
                                    curArticle.getDate());

                            articleViewModel.insert(rl_art);


                            Toast toast = Toast.makeText(getContext(),
                                    "Adding to read later list...",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    })
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
        } else {
            builder.setTitle("Decription")
                    .setMessage(mDescription)
                    .setNegativeButton("Read More", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(mLink));
                            startActivity(intent);
                        }
                    })
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
        }

        return builder.create();
    }
}
