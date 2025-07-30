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
    fun provideGroupRepository(
        client: SupabaseClient
    ): GroupRepository = SupabaseGroupRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideGradeRepository(
        client: SupabaseClient
    ): GradesRepository = GradesRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideUserRepository(
        client: SupabaseClient
    ): UserRepository = UserRepositoryImpl(client)

    @Provides
    @Singleton
    fun provideGetUser(
        repo: UserRepository
    ): GetUserUseCase = GetUserUseCase ( repo )

    @Provides
    @Singleton
    fun provideEditUser(
        repo: UserRepository
    ): EditUserUseCase = EditUserUseCase ( repo )

    @Provides
    @Singleton
    fun provideGetEmail(
        repo: UserRepository
    ): GetEmailUseCase = GetEmailUseCase ( repo )

    @Provides
    @Singleton
    fun provideLogout(
        repo: AuthRepository
    ): LogoutUseCase = LogoutUseCase( repo )

    @Provides
    @Singleton
    fun provideChangePass(
        repo: AuthRepository
    ): ChangePasswordUseCase = ChangePasswordUseCase( repo )

    @Provides
    @Singleton
    fun provideGetPointsByDisciplineId(
        repo: GradesRepository
    ): GetPointsByDisciplineIdUseCase = GetPointsByDisciplineIdUseCase( repo )

    @Provides
    @Singleton
    fun provideChangeGradeUseCase(
        repo: GradesRepository
    ): ChangeGradeUseCase = ChangeGradeUseCase( repo )

    @Provides
    @Singleton
    fun provideGetToken(
        repo: AuthRepository
    ): GetTokenUseCase = GetTokenUseCase( repo )

    @Provides
    @Singleton
    fun provideGetGroupsOfTeacher(
        repo: GroupRepository
    ): GetGroupsOfTeacherUseCase = GetGroupsOfTeacherUseCase( repo )



    @Provides
    @Singleton
    fun provideGetGradesWithStudentInfo(
        repo: GradesRepository
    ): GetGradesWithStudentInfoUseCase = GetGradesWithStudentInfoUseCase( repo )

    @Provides
    @Singleton
    fun provideAuthUseCases(
        repo: AuthRepository
    ): AuthUseCases = AuthUseCases(
        login = LoginUseCase( repo ),
        register = SignupUseCase( repo ),
        reset = ResetUseCase( repo )
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