package com.suhakarakaya.searchmovie.Model


import java.io.Serializable

data class Movie(
    var Title: String? = null,
    var Year: String? = null,
    var Rated: String? = null,
    var Released: String? = null,
    var Runtime: String? = null,
    var Genre: String? = null,
    var Director: String? = null,
    var Writer: String? = null,
    var Actors: String? = null,
    var Plot: String? = null,
    var Language: String? = null,
    var Country: String? = null,
    var Awards: String? = null,
    var Poster: String? = null,
    var Ratings: List<Ratings>? = null,
    var Metascore: String? = null,
    var imdbRating: String? = null,
    var imdbVotes: String? = null,
    var imdbID: String? = null,
    var Type: String? = null,
    var DVD: String? = null,
    var BoxOffice: String? = null,
    var Production: String? = null,
    var Website: String? = null,
    var Response: String? = null,
    var Error: String? = null
) : Serializable
