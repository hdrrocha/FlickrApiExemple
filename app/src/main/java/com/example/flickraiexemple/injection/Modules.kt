package com.example.flickraiexemple.injection

import androidx.navigation.NavController
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.example.flickraiexemple.BuildConfig
import com.example.flickraiexemple.data.api.FlickerApi
import com.example.flickraiexemple.data.api.SizesApi
import com.example.flickraiexemple.data.model.Photo
import com.example.flickraiexemple.data.source.FlickerHomePagingSource
import com.example.flickraiexemple.data.repository.FlickerHomeRepositoryImp
import com.example.flickraiexemple.data.repository.SizesPhotosRepositoryImp
import com.example.flickraiexemple.domain.mapper.abs.FlickerHomeMapper
import com.example.flickraiexemple.domain.mapper.FlickerHomeMapperImp
import com.example.flickraiexemple.domain.mapper.abs.SizePhotosMapper
import com.example.flickraiexemple.domain.mapper.SizePhotosMapperImp
import com.example.flickraiexemple.domain.repository.FlickerHomeRepository
import com.example.flickraiexemple.domain.repository.SizesPhotosRepository
import com.example.flickraiexemple.domain.usercase.abs.FlickerHomeUseCase
import com.example.flickraiexemple.domain.usercase.FlickerHomeUseCaseImp
import com.example.flickraiexemple.domain.usercase.abs.SizesPhotosUseCase
import com.example.flickraiexemple.domain.usercase.SizesPhotosUseCaseImp
import com.example.flickraiexemple.domain.viewmodel.FlickerHomeViewModel
import com.example.flickraiexemple.domain.viewmodel.PhotoDetailsViewModel
import com.example.flickraiexemple.router.HomeRouter
import com.example.flickraiexemple.router.abs.HomeRouterAbs
import com.example.flickraiexemple.router.PhotoDetailsRouter
import com.example.flickraiexemple.router.abs.PhotoDetailsRouterAbs

import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


object Modules {
    private val network = module {

        single {
            Moshi.Builder()
                .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                .build()
        }

        single {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(OkHttpProfilerInterceptor())
                        .build()
                )
                .build()
        }
    }

    private val api = module {
        single {
            val retrofit: Retrofit = get()
            retrofit.create(FlickerApi::class.java)
        }
        single {
            val retrofit: Retrofit = get()
            retrofit.create(SizesApi::class.java)
        }
    }
    private val paging = module {
        single<PagingSource<Int, Photo>> { FlickerHomePagingSource(api = get()) }
        single { PagingConfig(FlickerHomeRepositoryImp.DEFAULT_LIST_SIZE) }
        single { Pager(get()) { get<PagingSource<Int, Photo>>() } }
    }

    private val repository = module {
        single<FlickerHomeRepository> {
            FlickerHomeRepositoryImp(
                pager = get()
            )
        }
        single<SizesPhotosRepository> {
            SizesPhotosRepositoryImp(
                api = get()
            )
        }
    }

    private val mapper = module {
        single<FlickerHomeMapper> {
            FlickerHomeMapperImp()
        }
        single<SizePhotosMapper> {
            SizePhotosMapperImp()
        }
    }

    private val useCase = module {
        single<FlickerHomeUseCase> {
            FlickerHomeUseCaseImp(
                mapper = get(),
                flickerHomeRepository = get()
            )
        }
        single<SizesPhotosUseCase> {
            SizesPhotosUseCaseImp(
                mapper = get(),
                repository = get()
            )
        }
    }

    private val viewModel = module {
        viewModel {
            FlickerHomeViewModel(
                useCase = get()
            )
        }
        viewModel {
            PhotoDetailsViewModel(
                useCase = get()
            )
        }
    }

    private val database = module {
        //TODO
    }

    private val dao = module {
        //TODO
    }


    private val router = module {
        factory<HomeRouterAbs> { (navController: NavController) ->
            HomeRouter(navController = navController)
        }
        factory<PhotoDetailsRouterAbs> { (navController: NavController) ->
            PhotoDetailsRouter(navController = navController)
        }
    }

    var all = listOf(
        network,
        api,
        paging,
        repository,
        mapper,
        useCase,
        viewModel,
        router


    )
}

