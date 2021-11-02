package com.koreait.board;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BoardListActivity extends AppCompatActivity {
    private RecyclerView rvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);
        rvList = findViewById(R.id.rvList);
        getBoardList();
    }

    private void getBoardList() {
        Retrofit retrofit = RetroFitObj.getInstance();
        BoardService service = retrofit.create(BoardService.class);
        Call<List<BoardVO>> call = service.selBoardList();
        call.enqueue(new Callback<List<BoardVO>>() {
            @Override
            public void onResponse(Call<List<BoardVO>> call, Response<List<BoardVO>> res) {
                if(res.isSuccessful()) {
                    List<BoardVO> result = res.body();

                    for(BoardVO vo : result) {
                        Log.i("myLog", vo.getIboard() + ", " + vo.getTitle());
                    }
                } else {
                    Log.e("myLog", "통신 오류 : " + res.code());
                }
            }

            @Override
            public void onFailure(Call<List<BoardVO>> call, Throwable t) {
                Log.e("myLog", "통신 자체 실패");
            }
        });
    }
}