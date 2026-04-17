package dev.nova.graphqloverhal.model

open class HalModel(
    val links: List<HalLink> = emptyList(),
)

data class HalLink(
    val rel: String,
    val href: String
)