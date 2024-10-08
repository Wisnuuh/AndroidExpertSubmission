package com.dicoding.androidexpertsubmission.core.data.source.remote.reponse

import com.google.gson.annotations.SerializedName

data class GamesResponse(

	@field:SerializedName("count")
	val count: Int,

	@field:SerializedName("results")
	val results: List<ResultsItem>
)

data class ResultsItem(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("released")
	val released: String,

	@field:SerializedName("background_image")
	val backgroundImage: String,

	@field:SerializedName("rating")
	val rating: Double,
)
