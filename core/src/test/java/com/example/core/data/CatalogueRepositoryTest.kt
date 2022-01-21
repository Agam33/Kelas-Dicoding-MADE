package com.example.core.data

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.LocalDataSourceImpl
import com.example.core.data.source.local.entity.PopularMovieEntities
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.RemoteDataSourceImpl
import com.example.core.utils.DataDummy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CatalogueRepositoryTest {

    @Mock private lateinit var remoteDataSource: RemoteDataSource

    @Mock private lateinit var localDataSource: LocalDataSource

    private lateinit var catalogueRepository: FakeCatalogueRepository

    private val expectedSize = 4

    @Before
    fun setup() {
        catalogueRepository = FakeCatalogueRepository(remoteDataSource, localDataSource)
    }

    @Test
    fun `Get Popular Movies`() {
        val movies = DataDummy.popularMovies()
        `when`(localDataSource.getPopularMovies()).thenReturn(movies)

        val movieEntities = catalogueRepository.getPopularMovie()

        verify(localDataSource).getPopularMovies()
        assertNotNull(movieEntities)

    }

    @Test
    fun `Get Top Rated Movie`() {

    }

    @Test
    fun `Get Now Playing Movies`() {

    }

    @Test
    fun `Get Up Coming Movies`() {

    }

    @Test
    fun `Get Popular Tv`() {

    }

    @Test
    fun `Get Top Rated Tv`() {

    }

    @Test
    fun `Get On Air Tv`() {

    }

    @Test
    fun `Get Airing Today`() {

    }
}