/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.fenix.compose.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mozilla.fenix.R
import org.mozilla.fenix.compose.PrimaryText
import org.mozilla.fenix.theme.FirefoxTheme

/**
 * Expandable header for sections of lists
 *
 * @param headerText The title of the header.
 * @param expanded Indicates whether the section of content is expanded. If null, the Icon will be hidden.
 * @param expandActionContentDescription The content description for expanding the section.
 * @param collapseActionContentDescription The content description for collapsing the section.
 * @param onClick Optional lambda for handling header clicks.
 */
@Composable
fun ExpandableListHeader(
    headerText: String,
    expanded: Boolean? = null,
    expandActionContentDescription: String? = null,
    collapseActionContentDescription: String? = null,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PrimaryText(
            text = headerText,
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.metropolis_semibold)),
            maxLines = 1,
        )

        expanded?.let {
            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                painter = painterResource(
                    if (expanded) R.drawable.ic_chevron_down else R.drawable.ic_chevron_up
                ),
                contentDescription = if (expanded) {
                    collapseActionContentDescription
                } else {
                    expandActionContentDescription
                },
                modifier = Modifier.size(20.dp),
                tint = FirefoxTheme.colors.iconPrimary,
            )
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun TextOnlyHeaderPreview() {
    FirefoxTheme {
        Box(Modifier.background(FirefoxTheme.colors.layer1)) {
            ExpandableListHeader(headerText = "Section title")
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun CollapsibleHeaderPreview() {
    FirefoxTheme {
        Box(Modifier.background(FirefoxTheme.colors.layer1)) {
            ExpandableListHeader(
                headerText = "Collapsible section title",
                expanded = true,
                expandActionContentDescription = "",
                collapseActionContentDescription = "",
                onClick = { println("Clicked section header") },
            )
        }
    }
}
