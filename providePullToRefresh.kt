package com.sarang.torang.di.pulltorefresh

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.PullToRefreshLayoutState
import com.sryang.library.pullrefresh.RefreshIndicatorState


data class PullToRefreshData(
    val modifier : Modifier = Modifier,
    val state : RefreshIndicatorState = RefreshIndicatorState.Default,
    val onRefresh: (() -> Unit) = {},
    val contents: @Composable () -> Unit = {}
)

fun providePullToRefresh(state: PullToRefreshLayoutState): @Composable (PullToRefreshData) -> Unit =
    { data ->
        state.updateState(
            when(data.state){
                RefreshIndicatorState.Default -> RefreshIndicatorState.Default
                RefreshIndicatorState.PullingDown -> RefreshIndicatorState.PullingDown
                RefreshIndicatorState.ReachedThreshold -> RefreshIndicatorState.ReachedThreshold
                RefreshIndicatorState.Refreshing -> RefreshIndicatorState.Refreshing
            })

        PullToRefreshLayout(modifier                = data.modifier,
                            pullRefreshLayoutState  = state,
                            refreshThreshold        = 80,
                            onRefresh               = data.onRefresh) {
            data.contents.invoke()
        }
    }