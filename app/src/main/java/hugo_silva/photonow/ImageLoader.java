package hugo_silva.photonow;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by rike4 on 05/06/2017.
 */

public class ImageLoader extends AsyncTask <Void, Void, Bitmap> {

    private String path;
    private View v;
    private Bitmap bmap;
    private ProgressDialog mProgressDialog;
    private ImageView imgV;

    ImageLoader(String Url, View v, ImageView imgV)
    {
        path = Url;
        this.v = v;
        bmap = null;
        this.imgV = imgV;
    }

    @Override
    protected void onPreExecute() {
        mProgressDialog = ProgressDialog.show(v.getContext(),
                "A carregar", "A carregar...", true);
    }

    @Override
    protected Bitmap doInBackground(Void... arg0) {

        Bitmap bitmap;
        bitmap = BitmapFactory.decodeFile(path);
        bmap = Util.bitmapResizer(bitmap,320, 240);

        return bitmap;
    }

    @Override
    protected void onPostExecute( Bitmap result )  {
        mProgressDialog.dismiss();

        imgV.setImageBitmap(bmap);
    }
}
