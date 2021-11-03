package com.koreait.board;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BoardListActivity extends AppCompatActivity {
    private RecyclerView rvList;
    private BoardListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_list);
        adapter = new BoardListAdapter();

        rvList = findViewById(R.id.rvList);
        rvList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getBoardList();
    }

    //글쓰기 Activity로 이동
    public void clkWrite(View v) {
        Intent intent = new Intent(this, BoardWriteActivity.class);
        startActivity(intent);
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
                    adapter.setList(result);
                    adapter.notifyDataSetChanged();
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

class BoardListAdapter extends RecyclerView.Adapter<BoardListAdapter.MyViewHolder> {
    private List<BoardVO> list;

    public void setList(List<BoardVO> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View v = li.inflate(R.layout.item_board, parent,false);
        MyViewHolder viewHolder = new MyViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BoardVO vo = list.get(position);
        holder.setItem(vo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("myLog", "iboard : " + vo.getIboard());

                Intent intent = new Intent(view.getContext(), BoardDetailActivity.class);
                intent.putExtra("iboard", vo.getIboard());
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIboard;
        private TextView tvTitle;
        private TextView tvWriter;
        private TextView tvRdt;

        public MyViewHolder(View v) {
            super(v);
            tvIboard = v.findViewById(R.id.tvIboard);
            tvTitle = v.findViewById(R.id.tvTitle);
            tvWriter = v.findViewById(R.id.tvWriter);
            tvRdt = v.findViewById(R.id.tvRdt);
        }

        public void setItem(BoardVO param) {
            tvIboard.setText(String.valueOf(param.getIboard()));
            tvTitle.setText(param.getTitle());
            tvWriter.setText(param.getWriter());
            tvRdt.setText(param.getRdt());
        }
    }
}

