package hugo_silva.photonow;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentManager;
import android.view.ViewGroup;

import java.io.ByteArrayOutputStream;

/**
 * Created by rike4 on 28/05/2017.
 */

public class Util {

    private Util(){}

    public static byte[] bitmapToArray(Bitmap imagem) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagem.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public static Bitmap arrayToBitmap(byte[] array) {
        Bitmap bmp = BitmapFactory.decodeByteArray(array, 0, array.length);
        return bmp;

    }
}
