package com.example.internapp.DataClass

data class topHeadLines(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)