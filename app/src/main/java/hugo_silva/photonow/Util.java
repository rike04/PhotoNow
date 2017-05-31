package hugo_silva.photonow;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

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

    public static void changeFragments(Fragment current_fragment, int id, Fragment novo) {
        FragmentManager fm = current_fragment.getFragmentManager();
        fm.beginTransaction()
                .replace(id, novo)
                .addToBackStack(null)
                .commit();
    }

    public static Bitmap bitmapResizer(Bitmap bitmap,int newWidth,int newHeight) {
        Bitmap scaledBitmap = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);

        float ratioX = newWidth / (float) bitmap.getWidth();
        float ratioY = newHeight / (float) bitmap.getHeight();
        float middleX = newWidth / 2.0f;
        float middleY = newHeight / 2.0f;

        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);

        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bitmap, middleX - bitmap.getWidth() / 2,
                middleY - bitmap.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));

        return scaledBitmap;
    }

}
