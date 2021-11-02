package com.koreait.board;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BoardService {

    @GET("selList")
    Call<List<BoardVO>> selBoardList();

    @POST("ins")
    Call<Void> insBoard(@Body BoardVO param);
}
