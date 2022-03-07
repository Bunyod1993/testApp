package com.example.patient.repositories.auth

import android.content.SharedPreferences
import com.example.patient.database.ProfileDao
import com.example.patient.repositories.Resource
import com.example.patient.utils.Constants
import com.example.patient.utils.base.BaseRemoteRepository
import com.example.patient.utils.base.RemoteErrorEmitter
import com.example.patient.utils.enums.InputErrorType
import com.example.patient.utils.enums.InputType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val prefs: SharedPreferences,
    private val profileDao: ProfileDao
) : AuthRepository,
    BaseRemoteRepository() {
    override suspend fun login(
        emitter: RemoteErrorEmitter,
        login: String,password:String
    ): Flow<Resource<String>> {
        return flow {
            safeApiCallNoContext(emitter) {
                val resp = api.login(login,password)
                if (resp.code == 200) {
                    emit(Resource.Success(resp.message))
                    prefs.edit().putString(Constants.AUTH_TOKEN, resp.token).apply()
                    resp.user?.let { profileDao.insertPromoter(it) }
                } else {
                    emit(Resource.Error(resp.message))
                }
            }
        }
    }

    override fun getFields(): MutableList<Pair<InputType, InputErrorType>> =
        mutableListOf(
            Pair(InputType.EMAIL, InputErrorType.EMPTY),
            Pair(InputType.PASSWORD, InputErrorType.EMPTY)
        )
}