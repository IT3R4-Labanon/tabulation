package com.example.myapplication.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.myapplication.Models.Contestants;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ContestantAdapter extends ArrayAdapter<Contestants> implements ListAdapter {

    private final int mResource;
    private int position;
    private Context mContext;
    private List<Contestants> readingList = new ArrayList<>();

    public ContestantAdapter(@NonNull Context context, ArrayList<Contestants> contestantArrayList) {
        super(context, 0 , contestantArrayList);
        mContext = context;
        mResource = 0;
        readingList = contestantArrayList;
    }

    @Override
    public int getPosition(@Nullable Contestants item)
    {
        int x=0;
        return super.getPosition(item);
    }
    public void setPosition(int _position)
    {
        position = _position;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createViewFromResource(LayoutInflater.from(getContext()), position, convertView, parent, mResource);
    }

    private @NonNull
    View createViewFromResource(@NonNull LayoutInflater inflater, int position,
                                @Nullable View convertView, @NonNull ViewGroup parent, int resource)
    {
        final TextView contestantName;
        final TextView contestantDescription;
        Contestants contestants = readingList.get(position);

        if(convertView == null) {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.listrow_contestants, parent, false);
        }

        contestantName =  convertView.findViewById(R.id.tv_conName);
        contestantDescription = convertView.findViewById(R.id.tv_conAddress);

        contestantName.setText(contestants.getName());
        contestantDescription.setText(contestants.getDescription());
//        if (member.getMemberPhoto() != null){
//            try {
//                File file = new File(member.getMemberPhoto());
//                Bitmap bitmapImage = BitmapFactory.decodeStream(new FileInputStream(file));
//                if (bitmapImage != null){
//                    imgMemberImage.setImageBitmap(getCroppedBitmap(bitmapImage));
//                }
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
        return convertView;
    }


    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }


}
