package com.example.core.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class CatalogueRepositoryTest {

    @Rule
    @JvmField
    val rule: TestRule = InstantTaskExecutorRule()

    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val localDataSource = mock(LocalDataSource::class.java)

    private val catalogueRepository = FakeCatalogueRepository(remoteDataSource, localDataSource)

    private val expectedSize = 4

    @Test
    fun `Get Popular Movies, should be success`() = runBlocking {
        val movies = DataDummy.popularMovies()
        `when`(localDataSource.getPopularMovies()).thenReturn(movies)
        catalogueRepository.getPopularMovie()

        val movieEntities = DataDummy.popularMovies()
        verify(localDataSource).getPopularMovies()
        assertNotNull(movieEntities)
        assertEquals(expectedSize, movieEntities.first().size)
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