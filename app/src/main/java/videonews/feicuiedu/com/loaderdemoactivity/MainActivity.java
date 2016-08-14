package videonews.feicuiedu.com.loaderdemoactivity;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

//使用Liader获取手机中所有音频文件


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private GridView gridView;

    private AudioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView= (GridView) findViewById(R.id.gridView);
       adapter=new AudioAdapter(this);
        gridView.setAdapter(adapter);
        //初始化Loader（如果已存在，会自动重用的）
        //这里的LoaderManager是扩展包的LoaderManger但是getLoaderanager是系统包
        // 的应该和上面实现的是扩展包的所以这里也应该是扩展包的LoderaManager
        getSupportLoaderManager().initLoader(0,null,this);

    }
    //创建一个Loader给它一个Loader
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String [] xinxi = {
                MediaStore.Audio.Media._ID,//音乐的ID
                 MediaStore.Audio.Media.DISPLAY_NAME,//名字
                MediaStore.Audio.Media.DATA,//数据
                MediaStore.Audio.Media.ALBUM,//专辑
                MediaStore.Audio.Media.ARTIST,//艺术家
                MediaStore.Audio.Media.DURATION,//时长
                MediaStore.Audio.Media.SIZE//大小
        };
        //传入参数1上下文2uri（到底读什么数据呢）这里读的是音频
        // 3在手机音乐数据库中查询的信息比如ID，时间但是都要放到一个数组里面才能做查询操作。
        //4排序功能按时间大小什么的来排序
        return new CursorLoader(
               this
                , MediaStore.Audio.Media.EXTERNAL_CONTENT_URI //uri（到底读什么数据呢）这里读的是音频
                ,xinxi// 3在手机音乐数据库中查询的信息比如ID，时间但是都要放到一个数组里面才能做查询操作。
                ,null,null,null);
    }
    //加载结束后来触发
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        //首先移到第一列
//        if(cursor.moveToFirst()) {
//            //这一步是通过游标来获取到数据
//            //通过名字取拿到数据库中的列
//            do {
//                String artist =cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
//                String name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
//            Log.d("TAG",artist+""+name);
//            }while (cursor.moveToNext());
//            int desplayName = data.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME);
//            String name = data.getString(desplayName);
//            //通过艺术家来获取到数据库中的列
//            int artist = data.getColumnIndex(MediaStore.Audio.Media.ARTIST);
//            String whoName = data.getString(artist);
//            Log.d("TAG", artist + "" + desplayName);
//        }
        //将Loader异步加载完成后的游标Cursor，交给适配器Adapter
        adapter.swapCursor(cursor);
    }
    //重置数据后触发
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
