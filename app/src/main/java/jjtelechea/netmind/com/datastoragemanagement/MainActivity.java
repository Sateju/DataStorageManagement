package jjtelechea.netmind.com.datastoragemanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private EditText editText;
    private TextView textView;

    private static final String MY_SHARED_PREFERENCES = "My Shared preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.edtTextUpdate);
        textView = (TextView) findViewById(R.id.textViewHello);
        Button btnOk = (Button) findViewById(R.id.btnOk);
        Button btnInternalStorage = (Button) findViewById(R.id.btnInternalStorage);
        Button btnGetFiles = (Button) findViewById(R.id.btnGetFiles);
        Button btnExternalStorage = (Button) findViewById(R.id.btnExternalStorage);
        Button btnDataBase = (Button) findViewById(R.id.btnDataBase);
        btnOk.setOnClickListener(this);
        btnInternalStorage.setOnClickListener(this);
        btnGetFiles.setOnClickListener(this);
        btnExternalStorage.setOnClickListener(this);
        btnDataBase.setOnClickListener(this);


        //Leemos las preferences y lo añadimos a la vista
        SharedPreferences mSettings = getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        String mString = mSettings.getString("String del TextView",textView.getText().toString());
        textView.setText(mString);
    }

    @Override
    protected void onStop() {
        SharedPreferences mSettings = getSharedPreferences(MY_SHARED_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSettings.edit();
        mEditor.putString("String del TextView",textView.getText().toString());
        mEditor.apply();
        super.onStop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnOk:
                    textView.setText(editText.getText().toString());
                break;
            case R.id.btnInternalStorage:
                    doInternalStorage();
                break;
            case R.id.btnGetFiles:
                for (String fileList : this.fileList() )
                {
                    Toast.makeText(this, fileList, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnExternalStorage:
                doExternalStorage(); //TODO esto no tira :(
                break;
            case R.id.btnDataBase:
                MyDatabase myDatabase = new MyDatabase(getApplicationContext());
                SQLiteDatabase s = myDatabase.getReadableDatabase(); //Creación de la base de datos
                break;
        }
    }

    private void doInternalStorage(){

        try {
            FileOutputStream mFileOutputStream = openFileOutput("internalStorageFile.txt",Context.MODE_PRIVATE);
            String outPutString = editText.getText().toString() + "\n";
            mFileOutputStream.write(outPutString.getBytes());
            mFileOutputStream.close();
        } catch (java.io.IOException e) {e.printStackTrace();}
    }

    private void doExternalStorage(){
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            Toast.makeText(this,"Media Mounted true",Toast.LENGTH_SHORT).show();
        }
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            Toast.makeText(this,"Media Mounted Read Only true",Toast.LENGTH_SHORT).show();
        }

        File downloadFolder =  new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS),"newDirectory/newSubDirectory");
        if(!downloadFolder.mkdirs()){
            Toast.makeText(this,"Directory not created", Toast.LENGTH_SHORT).show();
        }
    }

}
