package com.sarang.torang.di.pulltorefresh

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.PullToRefreshLayoutState
import com.sryang.library.pullrefresh.RefreshIndicatorState


data class PullToRefreshData(
    val modifier : Modifier = Modifier,
    val state : com.sarang.torang.compose.feed.state.RefreshIndicatorState = com.sarang.torang.compose.feed.state.RefreshIndicatorState.Default,
    val onRefresh: (() -> Unit) = {},
    val contents: @Composable () -> Unit = {}
)

fun providePullToRefresh(state: PullToRefreshLayoutState): @Composable (PullToRefreshData) -> Unit =
    { data ->
        state.updateState(
            when(data.state){
                com.sarang.torang.compose.feed.state.RefreshIndicatorState.Default -> RefreshIndicatorState.Default
                com.sarang.torang.compose.feed.state.RefreshIndicatorState.PullingDown -> RefreshIndicatorState.PullingDown
                com.sarang.torang.compose.feed.state.RefreshIndicatorState.ReachedThreshold -> RefreshIndicatorState.ReachedThreshold
                com.sarang.torang.compose.feed.state.RefreshIndicatorState.Refreshing -> RefreshIndicatorState.Refreshing
            })

        PullToRefreshLayout(
            modifier = data.modifier, pullRefreshLayoutState = state, refreshThreshold = 80, onRefresh = data.onRefresh
        ) {
            data.contents.invoke()
        }
    }