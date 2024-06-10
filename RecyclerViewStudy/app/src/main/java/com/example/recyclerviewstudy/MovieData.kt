package com.example.recyclerviewstudy


val movies : MutableList<MovieItems> = mutableListOf<MovieItems>().apply {
    add(MovieItems.TextMovie(1, "이미지 없는 영화1"))
    add(MovieItems.ImageMovie(2, R.drawable.post1, "스마일"))
    add(MovieItems.TextMovie(3, "이미지 없는 영화2"))
    add(MovieItems.ImageMovie(4, R.drawable.post2, "노량"))
    add(MovieItems.TextMovie(5, "이미지 없는 영화3"))
    add(MovieItems.ImageMovie(6, R.drawable.post3, "범죄도시3"))
    add(MovieItems.TextMovie(7, "이미지 없는 영화4"))
    add(MovieItems.ImageMovie(8, R.drawable.post4, "서울의 봄"))
    add(MovieItems.TextMovie(9, "이미지 없는 영화5"))
    add(MovieItems.ImageMovie(10, R.drawable.post5, "실미도"))
    add(MovieItems.TextMovie(11, "이미지 없는 영화6"))
    add(MovieItems.ImageMovie(12, R.drawable.post6, "파묘"))
    add(MovieItems.TextMovie(13, "이미지 없는 영화7"))
}
