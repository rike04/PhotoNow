package hugo_silva.photonow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class FullImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image);

        AdapterImagens adapterImagens = new AdapterImagens(this);
        List<ImageView> images = new ArrayList<ImageView>();

        for (int i = 0; i < adapterImagens.getCount(); i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(adapterImagens.imagens[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            images.add(imageView);
        }

        // Finally create the adapter
        AdapterSwipe imagePagerAdapter = new AdapterSwipe(images);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(imagePagerAdapter);

        // Set the ViewPager to point to the selected image from the previous activity
        // Selected image id
        int position = getIntent().getExtras().getInt("id");
        viewPager.setCurrentItem(position);

    }
}
