package com.adrenastudies.myapplication.api

import android.util.Log
import com.adrenastudies.myapplication.api.services.Users
import com.adrenastudies.myapplication.interfaces.ApiCallback
import com.adrenastudies.myapplication.interfaces.HTTPResponse
import com.adrenastudies.myapplication.model.ListUsers
import com.adrenastudies.myapplication.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class ApiRequest {

    companion object {

        private val aResponsesSucces = arrayOf(200, 201, 204)
        private val aResponsesError  = arrayOf(404, 405)
        private val aResponsesForbidden = arrayOf(401, 403, 422)


        private fun httpManager(response: Int, listResult: List<*>, error: String, httpResponse: HTTPResponse) {

            var onError = true

            for (aResponsesSucces in aResponsesSucces) {
                if (aResponsesSucces == response) {
                    httpResponse.onSuccess(listResult)
                    onError = false
                }
            }

            for (anAResponsesForbidden in aResponsesForbidden) {
                if (anAResponsesForbidden == response) {
                    httpResponse.onForbidden(listResult)
                    onError = false
                }
            }

            for (anAResponsesError in aResponsesError) {
                if (anAResponsesError == response) {
                    httpResponse.onError(error)
                    onError = false
                }
            }

            if (onError) {
                httpResponse.onError(error)
            }

        }

        private fun httpManager(response: Int, objResult: Any, error: String, httpResponse: HTTPResponse) {

            var onError = true

            for (aResponsesSucces in aResponsesSucces) {
                if (aResponsesSucces == response) {
                    httpResponse.onSuccess(objResult)
                    onError = false
                }
            }

            for (anAResponsesForbidden in aResponsesForbidden) {
                if (anAResponsesForbidden == response) {
                    httpResponse.onForbidden(objResult)
                    onError = false
                }
            }

            for (anAResponsesError in aResponsesError) {
                if (anAResponsesError == response) {
                    httpResponse.onError(error)
                    onError = false
                }
            }

            if (onError) {
                httpResponse.onError(error)
            }
        }

        private fun httpManager(response: Int, result: String, error: String, httpResponse: HTTPResponse) {

            var onError = true

            for (aResponsesSucces in aResponsesSucces) {
                if (aResponsesSucces == response) {
                    httpResponse.onSuccess(result)
                    onError = false
                }
            }

            for (anAResponsesForbidden in aResponsesForbidden) {
                if (anAResponsesForbidden == response) {
                    httpResponse.onForbidden(result)
                    onError = false
                }
            }

            for (anAResponsesError in aResponsesError) {
                if (anAResponsesError == response) {
                    httpResponse.onError(error)
                    onError = false
                }
            }

            if (onError) {
                httpResponse.onError(error)
            }
        }

        //    <!-- ............... HTTP Manager ................. -->

        private fun getErrorBody(errorBody: ResponseBody?): String {
            try {
                return if (errorBody != null) {
                    errorBody.string()
                } else {
                    ""
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return ""
        }

        //    <!-- ............... Services ................. -->

        fun getUser(id:String, apiCallback:ApiCallback) {

            val apiUser = ApiConfig.getClient().create(Users::class.java)
            val users = apiUser.getUsers(id)

            users.enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    httpManager(response.code(), response.body() as User, getErrorBody(response.errorBody()), object : HTTPResponse() {

                        override fun onSuccess(result: Any) {
                            super.onSuccess(result)
                            apiCallback.onSuccess(result)
                        }

                        override fun onForbidden(result: Any) {
                            super.onForbidden(result)
                            apiCallback.onError(result.toString())
                        }
                    })
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    httpManager(500, "", t.toString(), object : HTTPResponse() {
                        override fun onError(error: String) {
                            super.onError(error)
                            apiCallback.onError()
                        }
                    })
                }

            })
        }

        fun getAllUsers(apiCallback:ApiCallback) {

            val apiUser = ApiConfig.getClient().create(Users::class.java)
            val users = apiUser.getAllUsers()

            users.enqueue(object : Callback<ListUsers> {
                override fun onResponse(call: Call<ListUsers>, response: Response<ListUsers>) {
                    httpManager(response.code(), response.body() as ListUsers, getErrorBody(response.errorBody()), object : HTTPResponse() {

                        override fun onSuccess(result: Any) {
                            super.onSuccess(result)
                            apiCallback.onSuccess(result)
                        }

                        override fun onForbidden(result: Any) {
                            super.onForbidden(result)
                            apiCallback.onError(result.toString())
                        }
                    })
                }

                override fun onFailure(call: Call<ListUsers>, t: Throwable) {
                    Log.e("fail", t.toString())
                }

            })
        }
    }

}