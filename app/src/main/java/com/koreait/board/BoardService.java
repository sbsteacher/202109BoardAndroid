package com.koreait.board;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BoardService {

    @POST("ins")
    Call<Void> insBoard(@Body BoardVO param);

    @GET("selList")
    Call<List<BoardVO>> selBoardList();

    @GET("sel")
    Call<BoardVO> selBoardDetail(@Query("iboard") int iboard);

    @GET("del")
    Call<Void> delBoard(@Query("iboard") int iboard);
}
