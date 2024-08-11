package com.feature_repos.userrepos.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.core_ui.theme.GitHubReposTheme
import com.model.OwnerModel
import com.model.UserRepoDetailsModel

@Composable
fun RepoListItem(
    repoDetailsModel: UserRepoDetailsModel,
    onItemClicked: (UserRepoDetailsModel) -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClicked(repoDetailsModel) }
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = repoDetailsModel.repoName,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier.align(CenterVertically),
                text = repoDetailsModel.openedIssuesCount.toString(),
                color = Color.Blue,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.End
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RepoListItemPreview() {
    GitHubReposTheme {
        RepoListItem(repoDetailsModel = UserRepoDetailsModel(
            "Test", 12, 2, 4, OwnerModel(""))
        ) {}
    }
}