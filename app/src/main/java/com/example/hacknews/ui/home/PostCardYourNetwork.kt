/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.hacknews.ui.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.hacknews.R
import com.example.hacknews.data.posts.impl.item1
import com.example.hacknews.data.posts.impl.item2
import com.example.hacknews.data.posts.impl.item3
import com.example.hacknews.data.posts.impl.item4
import com.example.hacknews.data.posts.impl.item5
import com.example.hacknews.model.Item
import com.example.hacknews.model.PostAuthor
import com.example.hacknews.ui.theme.HacknewsTheme

@Composable
fun PostCardPopular(
    item: Item,
    navigateToArticle: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.size(280.dp, 240.dp)
    ) {
        Column(modifier = Modifier.clickable(onClick = { navigateToArticle(item.id) })) {

            Image(
                painter = painterResource(item.imageId),
                contentDescription = null, // decorative
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = item.metadata.author.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2
                )

                Text(
                    text = stringResource(
                        id = R.string.home_post_min_read,
                        formatArgs = arrayOf(
                            item.metadata.date,
                            item.metadata.readTimeMinutes
                        )
                    ),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Preview("Regular colors")
@Preview("Dark colors", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewPostCardPopular(
    @PreviewParameter(PostPreviewParameterProvider::class, limit = 1) item: Item
) {
    HacknewsTheme {
        Surface {
            PostCardPopular(item, {})
        }
    }
}

@Preview("Regular colors, long text")
@Composable
fun PreviewPostCardPopularLongText(
    @PreviewParameter(PostPreviewParameterProvider::class, limit = 1) item: Item
) {
    val loremIpsum =
        """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras ullamcorper pharetra massa,
        sed suscipit nunc mollis in. Sed tincidunt orci lacus, vel ullamcorper nibh congue quis.
        Etiam imperdiet facilisis ligula id facilisis. Suspendisse potenti. Cras vehicula neque sed
        nulla auctor scelerisque. Vestibulum at congue risus, vel aliquet eros. In arcu mauris,
        facilisis eget magna quis, rhoncus volutpat mi. Phasellus vel sollicitudin quam, eu
        consectetur dolor. Proin lobortis venenatis sem, in vestibulum est. Duis ac nibh interdum,
        """.trimIndent()
    HacknewsTheme {
        Surface {
            PostCardPopular(
                item.copy(
                    title = "Title$loremIpsum",
                    metadata = item.metadata.copy(
                        author = PostAuthor("Author: $loremIpsum"),
                        readTimeMinutes = Int.MAX_VALUE
                    )
                ),
                {}
            )
        }
    }
}

/**
 * Provides sample [Item] instances for Composable Previews.
 *
 * When creating a Composable Preview using @Preview, you can pass sample data
 * by annotating a parameter with @PreviewParameter:
 *
 * ```
 * @Preview
 * @Composable
 * fun MyPreview(@PreviewParameter(PostPreviewParameterProvider::class, limit = 2) post: Post) {
 *   MyComposable(post)
 * }
 * ```
 *
 * In this simple app we just return the hard-coded posts. When the app
 * would be more complex - e.g. retrieving the posts from a server - this would
 * be the right place to instantiate dummy instances.
 */
class PostPreviewParameterProvider : PreviewParameterProvider<Item> {
    override val values = sequenceOf(
        item1, item2, item3, item4, item5
    )
}
