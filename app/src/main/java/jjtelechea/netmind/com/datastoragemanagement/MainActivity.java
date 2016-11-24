package jjtelechea.netmind.com.datastoragemanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        Button buttonOk = (Button) findViewById(R.id.btnOk);
        Button internalStorageButton = (Button) findViewById(R.id.btnInternalStorage);
        Button buttonGetFiles = (Button) findViewById(R.id.btnGetFiles);
        buttonOk.setOnClickListener(this);
        internalStorageButton.setOnClickListener(this);
        buttonGetFiles.setOnClickListener(this);


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
                    almacenarInternamente();
                break;
            case R.id.btnGetFiles:
                break;
        }
    }

    public void almacenarInternamente(){

        try {
            FileOutputStream mFileOutputStream = openFileOutput("internalStorageFile.txt",Context.MODE_PRIVATE);
            String outPutString = editText.getText().toString() + "\n";
            mFileOutputStream.write(outPutString.getBytes());
            mFileOutputStream.close();
        } catch (java.io.IOException e) {e.printStackTrace();}
    }
}
