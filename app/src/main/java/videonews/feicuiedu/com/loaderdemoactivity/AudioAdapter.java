package videonews.feicuiedu.com.loaderdemoactivity;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by zhangyu on 2016-08-14.
 */
//游标适配器
public class AudioAdapter extends CursorAdapter{
    public AudioAdapter(Context context) {
        super(context, null,true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
         View view= LayoutInflater.from(context).inflate(R.layout.item_local_video,null);
        return view;
    }
        //绑定布局
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv= (TextView) view.findViewById(R.id.tvVideoName);
       String videoName= cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
        tv.setText(videoName);
    }
}
