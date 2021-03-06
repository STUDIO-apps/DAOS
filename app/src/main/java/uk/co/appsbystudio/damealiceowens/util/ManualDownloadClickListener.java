package uk.co.appsbystudio.damealiceowens.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import uk.co.appsbystudio.damealiceowens.Pages.newsContentViews.NewsItem;
import uk.co.appsbystudio.damealiceowens.R;

public class ManualDownloadClickListener implements View.OnClickListener {

    private final NewsItem activity;
    private final ImageView view;
    private final String URL;

    public ManualDownloadClickListener(NewsItem activity, ImageView view, String URL) {
        this.activity = activity;
        this.view = view;
        this.URL = URL;
    }

    @Override
    public void onClick(View v) {
        view.setClickable(false);
        view.setImageDrawable(activity.getResources().getDrawable(R.drawable.ic_icon_loading_image));
        Toast.makeText(activity, "Downloading...", Toast.LENGTH_SHORT).show();
        new ImageDownloader(activity).execute(URL);
    }
}
