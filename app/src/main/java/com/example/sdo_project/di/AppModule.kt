package com.example.sdo_project.di

import com.example.sdo_project.BuildConfig
import com.example.sdo_project.data.repository.GradesRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseAuthRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseDisciplineRepositoryImpl
import com.example.sdo_project.data.repository.SupabaseGroupRepositoryImpl
import com.example.sdo_project.data.repository.UserRepositoryImpl
import com.example.sdo_project.domain.repository.AuthRepository
import com.example.sdo_project.domain.repository.DisciplineRepository
import com.example.sdo_project.domain.repository.GradesRepository
import com.example.sdo_project.domain.repository.GroupRepository
import com.example.sdo_project.domain.repository.UserRepository
import com.example.sdo_project.domain.usecase.auth.AuthUseCases
import com.example.sdo_project.domain.usecase.GetDisciplinesUseCase
import com.example.sdo_project.domain.usecase.user.GetTokenUseCase
import com.example.sdo_project.domain.usecase.auth.LoginUseCase
import com.example.sdo_project.domain.usecase.auth.LogoutUseCase
import com.example.sdo_project.domain.usecase.auth.ResetUseCase
import com.example.sdo_project.domain.usecase.auth.SignupUseCase
import com.example.sdo_project.domain.usecase.grade.EditGradeByStudentPointId
import com.example.sdo_project.domain.usecase.grade.GetGroupGradesByPointIdUseCase
import com.example.sdo_project.domain.usecase.grade.GetSectionGradesByStudentId
import com.example.sdo_project.domain.usecase.grade.GetSectionsGradesByStudentId
import com.example.sdo_project.domain.usecase.grade.GradeUseCase
import com.example.sdo_project.domain.usecase.group.GetGroupByIdUseCase
import com.example.sdo_project.domain.usecase.group.GetGroupsOfTeacherUseCase
import com.example.sdo_project.domain.usecase.group.GetStudentsByGroupIdUseCase
import com.example.sdo_project.domain.usecase.user.EditUserUseCase
import com.example.sdo_project.domain.usecase.user.GetUserUseCase
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
    fun provideUserRespository(
        client: SupabaseClient
    ): UserRepository = UserRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideGradeRespository(
        client: SupabaseClient
    ): GradesRepository = GradesRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideGroupeRespository(
        client: SupabaseClient
    ): GroupRepository = SupabaseGroupRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideAuthUseCases(
        repo: AuthRepository
    ): AuthUseCases = AuthUseCases(
        login = LoginUseCase( repo ),
        register = SignupUseCase( repo ),
        reset = ResetUseCase( repo ),
        logout = LogoutUseCase( repo ),
        getToken = GetTokenUseCase( repo )
    )

    @Provides
    @Singleton
    fun provideUserUseCase(
        repo: UserRepository
    ):GetUserUseCase = GetUserUseCase(repo)

    @Provides
    @Singleton
    fun provideEditUserUseCase(
        repo: UserRepository
    ):EditUserUseCase = EditUserUseCase(repo)

    @Provides
    @Singleton
    fun provideDisciplineUseCase(
        repo: DisciplineRepository
    ):GetDisciplinesUseCase = GetDisciplinesUseCase(repo)

    @Provides
    @Singleton
    fun provideStudentsByGroupIdUseCase(
        repo: GroupRepository
    ):GetStudentsByGroupIdUseCase = GetStudentsByGroupIdUseCase(repo)

    @Provides
    @Singleton
    fun provideGroupsByTeacherId(
        repo: GroupRepository
    ):GetGroupsOfTeacherUseCase = GetGroupsOfTeacherUseCase(repo)

    @Provides
    @Singleton
    fun provideGroupById(
        repo: GroupRepository
    ):GetGroupByIdUseCase = GetGroupByIdUseCase(repo)

    @Provides
    @Singleton
    fun provideGradeUseCase(
        repo: GradesRepository
    ):GradeUseCase = GradeUseCase(
        editGradeByStudentPointId = EditGradeByStudentPointId(repo),
        getGroupGradesByPointIdUseCase = GetGroupGradesByPointIdUseCase(repo),
        getSectionGradesByStudentId = GetSectionGradesByStudentId(repo),
        getSectionsGradesByStudentId = GetSectionsGradesByStudentId(repo)

    )


}