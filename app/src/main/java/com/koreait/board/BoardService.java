package com.koreait.board;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BoardService {

    @GET("selList")
    Call<List<BoardVO>> selBoardList();
}
