package com.koreait.board;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BoardWriteActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etCtnt;
    private EditText etWriter;
    private BoardService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        etTitle = findViewById(R.id.etTitle);
        etCtnt = findViewById(R.id.etCtnt);
        etWriter = findViewById(R.id.etWriter);

        Retrofit retrofit = RetroFitObj.getInstance();
        service = retrofit.create(BoardService.class);
    }

    //글쓰기 버튼 클릭
    public void clkReg(View v) {
        String title = etTitle.getText().toString();
        String ctnt = etCtnt.getText().toString();
        String writer = etWriter.getText().toString();

        BoardVO data = new BoardVO();
        data.setTitle(title);
        data.setCtnt(ctnt);
        data.setWriter(writer);

        Call<Void> call = service.insBoard(data);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> res) {
                if(res.isSuccessful()) {
                    Log.i("myLog", "통신 성공");
                    finish();
                } else {
                    Log.i("myLog", "통신 오류");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("myLog", "통신 자체 실패");
            }
        });
    }

}