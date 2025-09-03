package com.sarang.torang.di.pulltorefresh

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sryang.library.pullrefresh.PullToRefreshLayout
import com.sryang.library.pullrefresh.PullToRefreshLayoutState
import com.sryang.library.pullrefresh.RefreshIndicatorState

fun providePullToRefresh(state: PullToRefreshLayoutState): @Composable ((modifier : Modifier, isRefreshing: Boolean, onRefresh: (() -> Unit), contents: @Composable (() -> Unit)) -> Unit) =
    { modifier, isRefreshing, onRefresh, contents ->

        if (isRefreshing) {
            state.updateState(RefreshIndicatorState.Refreshing)
        } else {
            state.updateState(RefreshIndicatorState.Default)
        }

        PullToRefreshLayout(
            modifier = modifier, pullRefreshLayoutState = state, refreshThreshold = 80, onRefresh = onRefresh
        ) {
            contents.invoke()
        }
    }