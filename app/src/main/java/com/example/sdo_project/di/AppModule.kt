package com.example.sdo_project.di

import com.example.sdo_project.BuildConfig
import com.example.sdo_project.data.repository.SupabaseAuthRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseDisciplineRepositoryImpl
import com.example.sdo_project.domain.repository.AuthRepository
import com.example.sdo_project.domain.repository.DisciplineRepository
import com.example.sdo_project.domain.usecase.AuthHomeUseCases
import com.example.sdo_project.domain.usecase.AuthUseCases
import com.example.sdo_project.domain.usecase.GetTokenUseCase
import com.example.sdo_project.domain.usecase.LoginUseCase
import com.example.sdo_project.domain.usecase.LogoutUseCase
import com.example.sdo_project.domain.usecase.ResetUseCase
import com.example.sdo_project.domain.usecase.SignupUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient = createSupabaseClient(
        supabaseKey = BuildConfig.supabaseApi,
        supabaseUrl = BuildConfig.supabaseUrl,
    ) {
        install(Auth)
        install(Postgrest)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        client: SupabaseClient
    ): AuthRepository = SupabaseAuthRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideDisciplineRepository(
        client: SupabaseClient
    ): DisciplineRepository = SupabaseDisciplineRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideAuthUseCases(
        repo: AuthRepository
    ): AuthUseCases = AuthUseCases(
        login = LoginUseCase( repo ),
        register = SignupUseCase( repo ),
        reset = ResetUseCase( repo ),
        logout = ResetUseCase( repo ),
        getToken = GetTokenUseCase( repo )
    )


}